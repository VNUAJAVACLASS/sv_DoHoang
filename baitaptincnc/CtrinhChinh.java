package QLLH;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class CtrinhChinh {
    private HashMap<Integer, Tuan> dsTuan;
    private LocalDate ngayBatDauHK;

    public CtrinhChinh() {
        this.dsTuan = new HashMap<>();
        this.ngayBatDauHK = LocalDate.of(2025, 1, 13); // Ngày bắt đầu HK2
    }



    public void docDuLieu(String filePath) throws IOException {
        File input = new File(filePath);
        Document doc = Jsoup.parse(input, "UTF-8");
        Element table = doc.select("table").first();
        if (table == null) throw new IOException("Không tìm thấy bảng thời khóa biểu");
        Elements rows = table.select("tbody tr");

        String maMH = "", tenMH = "", nhomTo = "", soTinChi = "", lop = "";

        for (Element row : rows) {
            Elements cols = row.select("td");
            if (cols.size() < 10) continue;

            if (!cols.get(0).text().trim().isEmpty()) {
                maMH = cols.get(0).text().trim();
                tenMH = cols.get(1).text().trim();
                nhomTo = cols.get(2).text().trim();
                soTinChi = cols.get(3).text().trim();
                lop = cols.get(6).text().trim();
            }

            int thu;
            try {
                thu = Integer.parseInt(cols.get(8).text().trim());
            } catch (NumberFormatException e) {
                continue;
            }

            int tietBatDau = Integer.parseInt(cols.get(9).text().trim());
            int soTiet = Integer.parseInt(cols.get(10).text().trim());
            String phong = cols.get(11).text().trim();
            String giangVien = cols.get(12).text().trim();
            String chuoiTuan = cols.get(13).text().trim();

            List<Integer> dsSoTuan = phanTichChuoiTuan(chuoiTuan);
            LichHoc lichHoc = new LichHoc(maMH, tenMH, nhomTo, soTinChi, lop, thu, tietBatDau, soTiet, phong, giangVien);

            for (Integer tuan : dsSoTuan) {
                dsTuan.computeIfAbsent(tuan, k -> new Tuan(k));
                dsTuan.get(tuan).themLichHoc(thu, lichHoc);
            }
        }
    }

    private List<Integer> phanTichChuoiTuan(String chuoiTuan) {
        List<Integer> dsTuan = new ArrayList<>();
        for (int i = 0; i < chuoiTuan.length(); i++) {
            char c = chuoiTuan.charAt(i);
            if (c != '-') {
                int tuan = Character.getNumericValue(c);
                if (tuan >= 0 && tuan <= 9) dsTuan.add(tuan);
            }
        }
        return dsTuan;
    }

    public void hienThiMenu() {
        Scanner scanner = new Scanner(System.in);
        int luaChon;

        do {
            System.out.println("\n===== CHƯƠNG TRÌNH QUẢN LÝ THỜI KHÓA BIỂU =====");
            System.out.println("1. Xem thời khóa biểu ngày hiện tại");
            System.out.println("2. Xem thời khóa biểu cả tuần (1-22)");
            System.out.println("3. Xem thời khóa biểu theo tuần, thứ");
            System.out.println("4. Xem thời khóa biểu theo ngày (dd/MM/yyyy)");
            System.out.println("0. Thoát");
            System.out.print("Nhập lựa chọn: ");

            try {
                luaChon = scanner.nextInt();
                scanner.nextLine();
                switch (luaChon) {
                    case 1: 
                    	xemTKBNgayHienTai();
                    	break;
                    case 2 : 
                    {
                        System.out.print("Nhập số tuần: ");
                        int tuan = scanner.nextInt(); scanner.nextLine();
                        xemTKBTuan(tuan);
                        break;
                    }
                    case 3 :
                    {
                        System.out.print("Nhập số tuần: ");
                        int tuanChon = scanner.nextInt();
                        System.out.print("Nhập thứ (2-8): ");
                        int thuChon = scanner.nextInt();
                        scanner.nextLine();
                        xemTKBThuTuan(tuanChon, thuChon);
                        break;
                    }
                    case 4 : {
                        System.out.print("Nhập ngày (dd/MM/yyyy): ");
                        String ngayNhap = scanner.nextLine();
                        xemTKBTheoNgay(ngayNhap);
                        break;
                    }
                    case 0: System.out.println("Cảm ơn bạn đã sử dụng chương trình!");
                    default :
                    	System.out.println("Lựa chọn không hợp lệ.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Vui lòng nhập số!");
                scanner.nextLine();
                luaChon = -1;
            }
        } while (luaChon != 0);

        scanner.close();
    }

    public void xemTKBNgayHienTai() {
        xemTKBTheoNgay(LocalDate.now());
    }

    public void xemTKBTuan(int soTuan) {
        if (soTuan < 1 || soTuan > 22) {
            System.out.println("Tuần không hợp lệ!");
            return;
        }

        Tuan tuan = dsTuan.get(soTuan);
        if (tuan == null) {
            System.out.println("Không có lịch học tuần " + soTuan);
            return;
        }

        LocalDate ngayDauTuan = ngayBatDauHK.plusWeeks(soTuan - 1);
        System.out.println("\n===== THỜI KHÓA BIỂU TUẦN " + soTuan + " =====");
        System.out.println("Từ ngày " + ngayDauTuan.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + " đến " + ngayDauTuan.plusDays(6).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        for (int thu = 2; thu <= 8; thu++) {
            List<LichHoc> ds = tuan.getDsLichHoc(thu);
            if (ds != null && !ds.isEmpty()) {
                LocalDate ngay = ngayDauTuan.with(TemporalAdjusters.previousOrSame(DayOfWeek.of(thu == 8 ? 7 : thu)));
                System.out.println("\nThứ " + (thu == 8 ? "Chủ nhật" : thu) + " (" + ngay.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "):");
                ds.forEach(System.out::println);
            }
        }
    }

    public void xemTKBThuTuan(int soTuan, int thu) {
        if (soTuan < 1 || soTuan > 22 || thu < 2 || thu > 8) {
            System.out.println("Dữ liệu tuần hoặc thứ không hợp lệ!");
            return;
        }

        Tuan tuan = dsTuan.get(soTuan);
        if (tuan == null) {
            System.out.println("Không có lịch học tuần " + soTuan);
            return;
        }

        LocalDate ngayDauTuan = ngayBatDauHK.plusWeeks(soTuan - 1);
        LocalDate ngay = ngayDauTuan.with(TemporalAdjusters.previousOrSame(DayOfWeek.of(thu == 8 ? 7 : thu)));
        List<LichHoc> ds = tuan.getDsLichHoc(thu);

        System.out.println("\n===== TKB TUẦN " + soTuan + ", THỨ " + (thu == 8 ? "Chủ nhật" : thu) + " =====");
        System.out.println("Ngày: " + ngay.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        if (ds != null && !ds.isEmpty()) ds.forEach(System.out::println);
        else System.out.println("Không có lịch học ngày này.");
    }

    public void xemTKBTheoNgay(String ngayStr) {
        try {
            LocalDate ngay = LocalDate.parse(ngayStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            xemTKBTheoNgay(ngay);
        } catch (Exception e) {
            System.out.println("Sai định dạng ngày (dd/MM/yyyy)!");
        }
    }

    public void xemTKBTheoNgay(LocalDate ngay) {
        long soNgay = ngay.toEpochDay() - ngayBatDauHK.toEpochDay();
        int soTuan = (int) (soNgay / 7) + 1;
        if (soTuan < 1 || soTuan > 22 || ngay.isBefore(ngayBatDauHK)) {
            System.out.println("Ngày không trong học kỳ!");
            return;
        }

        int thu = ngay.getDayOfWeek().getValue();
        if (thu == 7) thu = 8;

        System.out.println("\n===== TKB NGÀY " + ngay.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " =====");
        System.out.println("Tuần: " + soTuan + ", Thứ: " + (thu == 8 ? "Chủ nhật" : thu));

        Tuan tuan = dsTuan.get(soTuan);
        if (tuan == null) {
            System.out.println("Không có lịch học tuần " + soTuan);
            return;
        }

        List<LichHoc> ds = tuan.getDsLichHoc(thu);
        if (ds != null && !ds.isEmpty()) ds.forEach(System.out::println);
        else System.out.println("Không có lịch học ngày này.");
    }
}

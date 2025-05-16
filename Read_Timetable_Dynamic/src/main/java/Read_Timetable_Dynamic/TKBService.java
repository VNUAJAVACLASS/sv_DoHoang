package Read_Timetable_Dynamic;

import java.time.LocalDate;
import java.util.Map;

public class TKBService {

    private final KyHoc tkb;

    public TKBService(String url) {
        try {
            this.tkb = ReadHtmlWithJsoup.parseTKB(url);
        } catch (Exception e) {
            throw new RuntimeException("Không thể đọc thời khóa biểu từ URL: " + url, e);
        }
    }

    public void printTKBToday() {
        LocalDate today = LocalDate.now();
        int tuan = TKBDateHelper.getWeekFromDate(today);
        int id = TKBDateHelper.getThuFromDate(today);

        System.out.println("📅 Hôm nay là: " + today + " (Tuần " + tuan + ", " + getThuString(id) + ")");

        Tuan t = tkb.getDsTuan().get(tuan);
        if (t == null) {
            System.out.println("⛔ Không có dữ liệu thời khóa biểu cho tuần này.");
            return;
        }

        Thu thu = t.getThu(id);
        printNgay(id, today, thu);
    }

    public void inTKBTuanHienTai() {
        LocalDate today = LocalDate.now();
        int idTuan = TKBDateHelper.getWeekFromDate(today);

        System.out.println("📅 Hôm nay: " + today + " → Tuần " + idTuan);
        Tuan t = tkb.getDsTuan().get(idTuan);

        if (t == null) {
            System.out.println("⛔ Không có dữ liệu thời khóa biểu cho tuần này.");
            return;
        }

        for (int idthu = 2; idthu <= 7; idthu++) { // Lưu ý: Thứ 1 (CN) không in ra trong in theo tuần
            Thu thu = t.getThu(idthu);
            LocalDate date = TKBDateHelper.START_DATE.plusDays((idthu - 1) * 7 + (idthu - 2));
            printNgay(idthu, date, thu);
        }
    }

    public void inTKBTheoTuan(int tuan) {
        Tuan t = tkb.getDsTuan().get(tuan);
        if (t == null) {
            System.out.println("⛔ Không có thời khóa biểu cho tuần " + tuan);
            return;
        }
        System.out.println("📅 Thời khóa biểu tuần " + tuan + ":");
        printAll(tuan, t);
    }

    public void inTKBTheoNgay(LocalDate date) {
        int tuan = TKBDateHelper.getWeekFromDate(date);
        int idthu = TKBDateHelper.getThuFromDate(date);

        Tuan t = tkb.getDsTuan().get(tuan);
        if (t == null) {
            System.out.println("⛔ Không có thời khóa biểu cho tuần " + tuan);
            return;
        }

        Thu thu = t.getThu(idthu);
        System.out.println("📅 " + date + " (Tuần " + tuan + ", " + getThuString(idthu) + "):");
        printNgay(idthu, date, thu);
    }

    public void printAllTKB() {
        for (Map.Entry<Integer, Tuan> entryTuan : tkb.getDsTuan().entrySet()) {
            int soTuan = entryTuan.getKey();
            Tuan tuan = entryTuan.getValue();
            System.out.println("====== Tuần " + soTuan + " ======");
            printAll(soTuan, tuan);
        }
    }

    private void printAll(int soTuan, Tuan tuan) {
        for (Map.Entry<Integer, Thu> entryNgay : tuan.getDsThu().entrySet()) {
            int idthu = entryNgay.getKey();
            Thu thu = entryNgay.getValue();
            LocalDate date = TKBDateHelper.START_DATE.plusDays((soTuan - 1) * 7 + (idthu - 2));
            printNgay(idthu, date, thu);
        }
    }

    private void printNgay(int id, LocalDate date, Thu thu) {
        System.out.println(getThuString(id) + (date != null ? " (" + date + ")" : "") + ":");
        if (thu == null || thu.getDanhSachLichHoc().isEmpty()) {
            System.out.println("     🛌 Không có môn học.");
            return;
        }
        for (LichHoc lh : thu.getDanhSachLichHoc()) {
            printMonHoc(lh);
        }
    }

    private void printMonHoc(LichHoc mh) {
        int tietBD = mh.getTietBD();
        int tietKT = tietBD + mh.getSoTiet() - 1;

        // Tìm TietHoc tương ứng từ enum
        TietHoc tietHocBD = getTietHocByTiet(tietBD);
        TietHoc tietHocKT = getTietHocByTiet(tietKT);

        System.out.print("Mã môn học: " + mh.getMaMon() + " || Tên MH: " + mh.getTenMon());
        System.out.print(" || Thời gian: " + tietHocBD.getGioBatDau() + " - " + tietHocKT.getGioKetThuc());
        System.out.println(" || Phòng: " + mh.getPhong() + " || GV: " + mh.getGv());
    }

	private String getThuString(int thu) {
        switch (thu) {
            case 2: return "Thứ 2";
            case 3: return "Thứ 3";
            case 4: return "Thứ 4";
            case 5: return "Thứ 5";
            case 6: return "Thứ 6";
            case 7: return "Thứ 7";
            case 8: return "Chủ Nhật";
            default: return "Không xác định";
        }
    }
	
    public TietHoc getTietHocByTiet(int tiet) {
        for (TietHoc t : TietHoc.values()) {
            if (t.getSoTiet() == tiet) {
                return t;
            }
        }
        return null;
    }
}
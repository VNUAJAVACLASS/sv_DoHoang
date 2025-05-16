package QLLH;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TKBParser {
    public static void main(String[] args) {
        try {
            // Đọc file HTML
            String htmlContent = new String(Files.readAllBytes(Paths.get("dohoang.html")));
            Document doc = Jsoup.parse(htmlContent);
            
            // Lấy bảng thời khóa biểu
            Element table = doc.select("table").first();
            if (table == null) {
                System.out.println("Không tìm thấy bảng thời khóa biểu");
                return;
            }
            
            // Lấy tiêu đề các cột
            Elements headers = table.select("thead tr th");
            System.out.println("=== THÔNG TIN CÁC CỘT ===");
            for (int i = 0; i < headers.size(); i++) {
                System.out.println(i + ": " + headers.get(i).text());
            }
            
            // Lấy các dòng dữ liệu
            Elements rows = table.select("tbody tr");
            System.out.println("\n=== THÔNG TIN CÁC MÔN HỌC ===");
            
            for (Element row : rows) {
                Elements cols = row.select("td");
                if (cols.size() < 10) continue;
                
                String maMH = "";
                String tenMH = "";
                String nhomTo = "";
                String soTinChi = "";
                String lop = "";
                
                // Kiểm tra nếu có thông tin mã môn học
                Element colMaMH = cols.get(0);
                if (!colMaMH.text().trim().isEmpty()) {
                    maMH = colMaMH.text().trim();
                    tenMH = cols.get(1).text().trim();
                    nhomTo = cols.get(2).text().trim();
                    soTinChi = cols.get(3).text().trim();
                    lop = cols.get(6).text().trim();
                    
                    System.out.println("\nMã MH: " + maMH);
                    System.out.println("Tên môn học: " + tenMH);
                    System.out.println("Nhóm tổ: " + nhomTo);
                    System.out.println("Số tín chỉ: " + soTinChi);
                    System.out.println("Lớp: " + lop);
                }
                
                // Lấy thông tin lịch học
                String thu = cols.get(8).text().trim();
                String tietBD = cols.get(9).text().trim();
                String soTiet = cols.get(10).text().trim();
                String phong = cols.get(11).text().trim();
                String giangVien = cols.get(12).text().trim();
                
                // Lấy thông tin chuỗi tuần học
                Element colThoiGian = cols.get(13);
                String chuoiTuan = colThoiGian.text().trim();
                
                System.out.println("- Thứ: " + thu);
                System.out.println("- Tiết bắt đầu: " + tietBD);
                System.out.println("- Số tiết: " + soTiet);
                System.out.println("- Phòng: " + phong);
                System.out.println("- Giảng viên: " + giangVien);
                System.out.println("- Chuỗi tuần: " + chuoiTuan);
                
                // Phân tích chuỗi tuần học
                StringBuilder dsTuan = new StringBuilder("  * Các tuần học: ");
                for (int i = 0; i < chuoiTuan.length(); i++) {
                    char c = chuoiTuan.charAt(i);
                    if (c != '-') {
                        dsTuan.append(c).append(", ");
                    }
                }
                System.out.println(dsTuan.toString().replaceAll(", $", ""));
            }
            
        } catch (IOException e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
        }
    }
}
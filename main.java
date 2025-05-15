package QLLH;

import java.io.IOException;

public class main {
    public static void main(String[] args) {
        CtrinhChinh app = new CtrinhChinh();
        try {
            app.docDuLieu("C:\\Users\\vanho\\eclipse-workspace\\HelloMaven\\src\\main\\java\\QLLH\\dovanhoang.html");
            app.hienThiMenu();
        } catch (IOException e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
        }
    }
}

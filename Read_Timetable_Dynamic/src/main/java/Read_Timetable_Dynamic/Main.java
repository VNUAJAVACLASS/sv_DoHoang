package Read_Timetable_Dynamic;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Đổi "tkb.html" thành tên file HTML chứa thời khóa biểu trong thư mục resources
        String htmlFile = "C:\\Users\\vanho\\eclipse-workspace\\dohoang.html\\public";


        // Khởi tạo service đọc TKB từ file
        TKBService service = new TKBService(htmlFile);

        while (true) {
            System.out.println("\n====== MENU ======");
            System.out.println("1. In thời khóa biểu hôm nay");
            System.out.println("2. In thời khóa biểu tuần hiện tại");
            System.out.println("3. In thời khóa biểu theo tuần");
            System.out.println("4. In toàn bộ thời khóa biểu");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            int choice = scanner.nextInt(); scanner.nextLine();

            switch (choice) {
                case 1:
                    service.printTKBToday();
                    break;
                case 2:
                    service.inTKBTuanHienTai();
                    break;
                case 3:
                    System.out.print("Nhập số tuần: ");
                    int tuan = scanner.nextInt();
                    service.inTKBTheoTuan(tuan);
                    break;
                case 4:
                    service.printAllTKB();
                    break;

                case 0:
                    System.out.println("Tạm biệt!");
                    return;
                default:
                    System.out.println("❗ Lựa chọn không hợp lệ.");
            }
        }
    }
}

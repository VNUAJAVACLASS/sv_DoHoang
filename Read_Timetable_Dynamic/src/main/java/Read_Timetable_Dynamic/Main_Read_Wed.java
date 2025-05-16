package Read_Timetable_Dynamic;

import java.util.Scanner;

public class Main_Read_Wed {
	public static void main(String[] args) {
		TimetableFetcher fetcher = new TimetableFetcher();
		Scanner scanner = new Scanner(System.in);

		try {
			fetcher.fetchAndUpdateTimetable(scanner);
			System.out.println("Đã cập nhật và tải lại thời khóa biểu.");
			System.in.read();
		} catch (Exception e) {
			System.err.println("Lỗi khi cập nhật thời khóa biểu: " + e.getMessage());
			e.printStackTrace();
		}

		while (true) {
			System.out.println("\n====== MENU ======");
			System.out.println("1. Đăng nhập tài khoản để lấy tkb ");
			System.out.println("2. Cập nhâp lại thời khoá biểu của mình");
			System.out.println("0. Thoát");
			System.out.print("Chọn: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				try {
					fetcher.fetchAndUpdateTimetable(scanner);
					System.out.println("Đã cập nhật và tải lại thời khóa biểu.");
					System.in.read();
				} catch (Exception e) {
					System.err.println("Lỗi khi cập nhật thời khóa biểu: " + e.getMessage());
					e.printStackTrace();
				}
				break;
			case 2:
				try {
					fetcher.UpdateTimetable();
					System.out.println("Đã cập nhật và tải lại thời khóa biểu.");
					System.in.read();
				} catch (Exception e) {
					System.err.println("Lỗi khi cập nhật thời khóa biểu: " + e.getMessage());
					e.printStackTrace();
				}
				break;

			case 0:
				System.out.println("Tạm biệt!");
				return;
			}
		}

	}
}
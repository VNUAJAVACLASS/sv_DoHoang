package Read_Timetable_Dynamic;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.microsoft.playwright.*;

public class TimetableFetcher {
    private static final String URL = "https://daotao.vnua.edu.vn/";
    private static final String FILE_PATH = "C:\\Users\\vanho\\eclipse-workspace\\dohoang.html\\public";

    public void fetchAndUpdateTimetable(Scanner scanner) throws Exception {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            boolean isLoggedIn = false;
            while (!isLoggedIn) {
                System.out.print("Nhập tài khoản: ");
                String username = scanner.nextLine();
                System.out.print("Nhập mật khẩu: ");
                String password = scanner.nextLine();

                page.navigate(URL);
                page.waitForSelector("input[name='username']");
                page.fill("input[name='username']", username);
                page.fill("input[name='password']", password);
                page.click("button:has-text(\"Đăng nhập\")");

                try {
                    page.waitForSelector("#WEB_TKB_HK", new Page.WaitForSelectorOptions().setTimeout(5000));
                    isLoggedIn = true;
                } catch (Exception e) {
                    boolean loginError = page.locator("div.alert-danger, div.text-danger").isVisible();
                    if (loginError) {
                        System.out.println("Tài khoản hoặc mật khẩu không đúng. Vui lòng thử lại.");
                    } else {
                        System.out.println("Đăng nhập thất bại do lý do không xác định. Vui lòng thử lại.");
                    }
                }
            }

            page.click("#WEB_TKB_HK");

            page.waitForSelector("div[role='combobox']");
            page.click("div[role='combobox']");
            page.waitForSelector("div[role='option']:has-text('Học kỳ 2 - Năm học 2024 - 2025')");
            page.click("div[role='option']:has-text('Học kỳ 2 - Năm học 2024 - 2025')");

            page.waitForFunction("""
                () => {
                    const rows = document.querySelectorAll('table tbody tr');
                    return Array.from(rows).some(r => !r.innerText.includes('Không tìm thấy dữ liệu'));
                }
            """);

            String tkbHtml = (String) page.evaluate("document.querySelector('table').outerHTML");

            String originalHtml = new String(Files.readAllBytes(Paths.get(FILE_PATH)), StandardCharsets.UTF_8);

            Pattern pattern = Pattern.compile("(?s)(<body.*?>)(.*?)(</body>)");
            Matcher matcher = pattern.matcher(originalHtml);

            String newHtml;
            if (matcher.find()) {
                newHtml = matcher.replaceFirst(matcher.group(1) + "\n" + tkbHtml + "\n" + matcher.group(3));
            } else {
                newHtml = "<!DOCTYPE html>\n<html>\n<head><meta charset=\"UTF-8\"><title>TKB</title></head>\n<body>\n"
                        + tkbHtml + "\n</body>\n</html>";
            }

            Files.write(Paths.get(FILE_PATH), newHtml.getBytes(StandardCharsets.UTF_8));
            System.out.println("Đã cập nhật thời khóa biểu vào file HTML.");

            browser.close();
        }
    }
    
    public void UpdateTimetable() throws Exception {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            // Truy cập trang
            page.navigate(URL);
            page.waitForSelector("input[name='username']");
            page.fill("input[name='username']", "687629");
            page.fill("input[name='password']", "Hoan12345@1");
            page.click("button:has-text(\"Đăng nhập\")");

            
 /*           // Đọc tài khoản từ biến môi trường
            String username = System.getenv("TKB_USERNAME");
            String password = System.getenv("TKB_PASSWORD");
            if (username == null || password == null) {
                throw new IllegalStateException("Vui lòng set biến môi trường TKB_USERNAME và TKB_PASSWORD");
            }
*/

            // Đợi trang load bảng thời khóa biểu
            page.waitForSelector("#WEB_TKB_HK", new Page.WaitForSelectorOptions().setTimeout(5000));

            page.click("#WEB_TKB_HK");

            page.waitForSelector("div[role='combobox']");
            page.click("div[role='combobox']");
            page.waitForSelector("div[role='option']:has-text('Học kỳ 2 - Năm học 2024 - 2025')");
            page.click("div[role='option']:has-text('Học kỳ 2 - Năm học 2024 - 2025')");

            page.waitForFunction("""
                () => {
                    const rows = document.querySelectorAll('table tbody tr');
                    return Array.from(rows).some(r => !r.innerText.includes('Không tìm thấy dữ liệu'));
                }
            """);;

            // Trích xuất bảng HTML
            String tkbHtml = (String) page.evaluate("document.querySelector('table').outerHTML");

            // Đọc file HTML gốc
            String originalHtml = new String(Files.readAllBytes(Paths.get(FILE_PATH)), StandardCharsets.UTF_8);

            // Thay phần thân HTML bằng bảng thời khóa biểu
            Pattern pattern = Pattern.compile("(?s)(<body.*?>)(.*?)(</body>)");
            Matcher matcher = pattern.matcher(originalHtml);

            String newHtml;
            if (matcher.find()) {
                newHtml = matcher.replaceFirst(matcher.group(1) + "\n" + tkbHtml + "\n" + matcher.group(3));
            } else {
                newHtml = "<!DOCTYPE html>\n<html>\n<head><meta charset=\"UTF-8\"><title>TKB</title></head>\n<body>\n"
                        + tkbHtml + "\n</body>\n</html>";
            }

            // Ghi lại file HTML
            Files.write(Paths.get(FILE_PATH), newHtml.getBytes(StandardCharsets.UTF_8));
            System.out.println("Đã cập nhật thời khóa biểu vào file HTML.");

            browser.close();
        }
    }
}
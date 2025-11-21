package controller;

import dao.UserDAO;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final int MAX_ATTEMPTS = 3;   // 3 lần sai
    private static final int LOCK_TIME = 30;     // khóa 30s

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // Số lần nhập sai
        Integer attempts = (Integer) session.getAttribute("loginAttempts");
        Long lockTime = (Long) session.getAttribute("lockTime");

        if (attempts == null) attempts = 0;

        // Kiểm tra xem user có đang bị khóa không
        if (lockTime != null) {
            long elapsed = (System.currentTimeMillis() - lockTime) / 1000;

            if (elapsed < LOCK_TIME) {
                long wait = LOCK_TIME - elapsed;
                request.setAttribute("error", "Bạn đã nhập sai quá nhiều. Hãy thử lại sau " + wait + " giây.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            } else {
                // Hết thời gian khóa → reset
                session.removeAttribute("lockTime");
                session.setAttribute("loginAttempts", 0);
                attempts = 0;
            }
        }

        // Nhận thông tin từ form
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO dao = new UserDAO();
        User user = dao.loginByEmail(email, password);

        // Đăng nhập thành công
        if (user != null) {

            // Lưu thông tin user vào session
            session.setAttribute("user", user);
            session.setAttribute("username", user.getFullname()); // để hiển thị trong home.jsp

            // Reset số lần nhập sai
            session.removeAttribute("loginAttempts");
            session.removeAttribute("lockTime");

            // *** QUAN TRỌNG: Redirect đúng context path ***
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        // Nếu sai mật khẩu
        attempts++;
        session.setAttribute("loginAttempts", attempts);

        if (attempts >= MAX_ATTEMPTS) {
            session.setAttribute("lockTime", System.currentTimeMillis());
            request.setAttribute("error", "Bạn đã sai 3 lần! Tài khoản tạm khóa 30 giây.");
        } else {
            request.setAttribute("error", "Sai email hoặc mật khẩu! Lần " + attempts + " / 3");
        }

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

}

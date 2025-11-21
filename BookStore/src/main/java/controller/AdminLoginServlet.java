package controller;

import dao.UserDAO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin-login")
public class AdminLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("admin-login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserDAO dao = new UserDAO();
        User user = dao.loginByEmail(username, password);

        if (user == null) {
            req.setAttribute("error", "Tài khoản hoặc mật khẩu không đúng!");
            req.getRequestDispatcher("admin-login.jsp").forward(req, resp);
            return;
        }

        if (user.getRole() != 1) {
            req.setAttribute("error", "Bạn không có quyền truy cập Admin!");
            req.getRequestDispatcher("admin-login.jsp").forward(req, resp);
            return;
        }

        // Lưu session
        HttpSession session = req.getSession();
        session.setAttribute("admin", user);

        // Chuyển đến trang admin
        resp.sendRedirect("admin-dashboard.jsp");
    }
}

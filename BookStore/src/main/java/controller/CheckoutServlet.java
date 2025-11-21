package controller;

import dao.BookDAO;
import model.Book;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            request.setAttribute("message", "Giỏ hàng đang trống!");
            request.getRequestDispatcher("cart.jsp").forward(request, response);
            return;
        }

        BookDAO dao = new BookDAO();
        List<Book> items = new ArrayList<>();
        int total = 0;

        for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
            int id = entry.getKey();
            int qty = entry.getValue();

            Book b = dao.getBookById(id);
            b.setQuantity(qty);                 // gán số lượng vào book
            total += b.getPrice() * qty;

            items.add(b);
        }

        request.setAttribute("items", items);
        request.setAttribute("total", total);

        // gửi tổng tiền sang VNPAY
        session.setAttribute("total", total);

        request.getRequestDispatcher("checkout.jsp").forward(request, response);
    }
}

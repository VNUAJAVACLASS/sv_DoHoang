package controller;

import dao.BookDAO;
import model.Book;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // Lấy giỏ hàng
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
            session.setAttribute("cart", cart);
        }

        String action = request.getParameter("action");

        // === ADD ===
        if ("add".equals(action)) {

            int id = Integer.parseInt(request.getParameter("id"));
            cart.put(id, cart.getOrDefault(id, 0) + 1);

            session.setAttribute("cart", cart);
            session.setAttribute("message", "Đã thêm vào giỏ hàng!");

            response.sendRedirect("home");
            return;
        }

        // === REMOVE ===
        if ("remove".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            cart.remove(id);
            response.sendRedirect("cart");
            return;
        }

        // === UPDATE ===
        if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            int qty = Integer.parseInt(request.getParameter("qty"));

            if (qty <= 0) cart.remove(id);
            else cart.put(id, qty);

            response.sendRedirect("cart");
            return;
        }
        // LOAD CART ITEMS TO SHOW IN cart.jsp
        BookDAO dao = new BookDAO();
        List<Book> items = new ArrayList<>();
        int total = 0;

        for (Map.Entry<Integer, Integer> e : cart.entrySet()) {
            int bookId = e.getKey();
            int qty = e.getValue();

            Book b = dao.getBookById(bookId);
            if (b != null) {
                b.setQuantity(qty); // quantity dùng tạm để lưu số lượng cart
                items.add(b);
                total += b.getPrice() * qty;
            }
        }

        request.setAttribute("items", items);
        request.setAttribute("total", total);

        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }
}

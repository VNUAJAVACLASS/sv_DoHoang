package controller;

import dao.BookDAO;
import model.Book;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private static final int PRODUCT_PER_PAGE = 6;  // Sách nổi bật
    private static final int NEW_BOOK_LIMIT = 4;    // Sách mới

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BookDAO dao = new BookDAO();

        // ==== Danh mục (hiển thị bên trái) ====
        List<Book> bookList = dao.getAllBooks();

        // ==== Sách nổi bật (phân trang) ====
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int offset = (page - 1) * PRODUCT_PER_PAGE;
        List<Book> books = dao.getBooksByPage(offset, PRODUCT_PER_PAGE);

        int totalBooks = dao.countBooks();
        int totalPages = (int) Math.ceil((double) totalBooks / PRODUCT_PER_PAGE);

        // ==== Sách mới ====
        List<Book> newBooks = dao.getNewBooks(NEW_BOOK_LIMIT);

        // ==== Gửi sang JSP ====
        request.setAttribute("bookList", bookList);
        request.setAttribute("books", books);
        request.setAttribute("newBooks", newBooks);

        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}

package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.BookDAO;
import model.Book;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String keyword = req.getParameter("keyword");

        BookDAO dao = new BookDAO();
        List<Book> list = dao.searchByTitle(keyword);

        req.setAttribute("books", list);
        req.setAttribute("keyword", keyword);

        
        req.getRequestDispatcher("home.jsp").forward(req, resp);

    }
}

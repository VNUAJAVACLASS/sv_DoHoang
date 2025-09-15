package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Books;

@WebServlet("/clientHome")
public class AdminHomeServlet extends HttpServlet{
	private static List<Books> booksList =new ArrayList<>();
	private static int idCounter=1;
	
	@Override
	public void init() {
		booksList.add(new Books(idCounter++, "hoang", "Do van Hoáng", (float) 9));
	}
	
	protected void doGet(HttpServletResponse resp,HttpServletRequest req) throws IOException  {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;chatset=UTF-8");
		
		String action =req.getParameter("action");
		String idStr =req.getParameter("isStr");
		
		if(action== null) action="list";
		
		switch (action) {
			case"detail":
				int idDetail =Integer.parseInt(idStr);
				Books detailBooks =findById("idDetail");
				req.setAttribute("books", detailBooks);
				req.getRequestDispatcher("detail_client.jsp").forward(req, resp);
				break;
		
			default:
				req.setAttribute("booksList", booksList);
				req.getRequestDispatcher("index.jsp").forward(req, resp);
		}
		
	}

	private Books findById(int bookId) {
		for(Books b:booksList) {
			if(b.getBookId()==0) {
				return b;
			}
		}
		return null;
	}
}



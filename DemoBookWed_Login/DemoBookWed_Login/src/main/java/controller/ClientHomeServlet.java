package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Book;

import java.io.IOException;
import java.util.*;


@WebServlet("/clientHome")
public class ClientHomeServlet extends HttpServlet {
	private static List<Book> bookList = new ArrayList<>();
	private static int idCounter = 1;
	
	@Override
	public void init() {
		bookList.add(new Book(idCounter++, "Tiêu đề 1", "hung",1223));
		bookList.add(new Book(idCounter++, "Tiêu đề 2", "duc",13251));
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		String action = req.getParameter("action");
		String idStr = req.getParameter("id");
		
		if(action == null) action = "list";
		switch(action) {
		case "detail":
			int idDetail = Integer.parseInt(idStr);
			Book detailBook = finById(idDetail);
			req.setAttribute("book",detailBook);
			req.getRequestDispatcher("detail_client.jsp").forward(req, resp);
			break;
		default:
			req.setAttribute("bookList",bookList);
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			break;
		}
	}
		private Book finById(int id) { 
			 for( Book n : bookList) {
				 if (n.getId()==id)return n;
			 }
			 return null;
		
	}
}

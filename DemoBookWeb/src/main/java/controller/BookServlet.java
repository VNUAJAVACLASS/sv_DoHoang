package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

import model.Book;

public class BookServlet extends HttpServlet {
	private static List<Book> booksList = new ArrayList<>();
	private static int idCounter = 1;

	@Override
	public void init() {
		// Dữ liệu giả
		booksList.add(new Book(idCounter++, "Tiêu đề 1", "Author 1", 100000L, "abc"));
		booksList.add(new Book(idCounter++, "Tiêu đề 2", "Author 2", 100000L, "abc"));
		booksList.add(new Book(idCounter++, "Tiêu đề 3", "Author 3", 100000L, "abc"));
	}

	// Phương thức doGet được gọi khi method gửi từ phía client là "GET"
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Thiết lập UTF-8 cho request, response để hiển thị đúng tiếng Việt
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		// Lấy về giá trị các tham số được gửi qua request từ client
		String action = req.getParameter("action");
		String idStr = req.getParameter("id");
		// action == null ứng với trường hợp lần đầu vào ứng dụng
		// hoặc khi click link "Quay lại danh sách", ko cần tham số action
		if (action == null)
			action = "list";
		switch (action) {
		case "create": // bấm link tạo tin mới, trả về form tạo mới
			req.getRequestDispatcher("form.jsp").forward(req, resp);
			break;
		case "edit":
			// bấm link sửa
			// Lấy nội dung với id tin tức tương ứng cần sửa
			int idEdit = Integer.parseInt(idStr);
			Book editNews = findById(idEdit);
			// ghi vào request để trang form.jsp lấy ra điền vào các trường
			req.setAttribute("book", editNews);
			req.getRequestDispatcher("form.jsp").forward(req, resp);
			break;
		case "delete":
			int idDelete = Integer.parseInt(idStr);
			booksList.removeIf(n -> n.getBookId() == idDelete);
			resp.sendRedirect("books"); // quay lại trang chủ tin tức
			break;
		case "detail":
			int idDetail = Integer.parseInt(idStr);
			Book detailNews = findById(idDetail);
			req.setAttribute("book", detailNews);
			req.getRequestDispatcher("detail.jsp").forward(req, resp);
			break;
		default: // trường hợp lần đầu vào ứng dụng hoặc bấm link quay lại danh sách
			// ghi ds tin tức vào request để trang list.jsp lấy ra hiển thị
			req.setAttribute("booksList", booksList);
			req.getRequestDispatcher("list.jsp").forward(req, resp);
			break;
		}
	}

	// Phương thức doPost được gọi khi method gửi từ phía client là "POST"
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		// Lấy các trường từ form được submit tới ở trang form.jsp
		String idStr = req.getParameter("id");
		String title = req.getParameter("title");
		String author = req.getParameter("author");
		String priceStr = req.getParameter("price");
		long price = 0;
		if (priceStr != null && !priceStr.isEmpty()) {
		    try {
		        price = Long.parseLong(priceStr);
		    } catch (NumberFormatException e) {
		        price = 0;
		    }
		}		String imagePath = req.getParameter("imagePath");

		// Nếu ko có id, ứng với tình huống tạo mới tin tức
		if (idStr == null || idStr.isEmpty()) {
			// Tạo tin tức mới với id mới
			Book newBooks = new Book(idCounter++, title, author, price, imagePath);
			booksList.add(newBooks);
		} else {
			// Cập nhật tin tức với id tương ứng
			int id = Integer.parseInt(idStr);
			Book existing = findById(id);
	        if (existing != null) {
	            existing.setTitle(title);
	            existing.setAuthor(author);
	            existing.setPrice(price);
	            existing.setImagePath(imagePath);
	        }
		}
		// sẽ ghi newsList vào request và chuyển tiếp tới trang list.jsp
		resp.sendRedirect("books");
	}

	// Lấy ra đối tượng tin tức theo id từ danh sách tin tức
	private Book findById(int id) {
		for (Book b : booksList) {
			if (b.getBookId() == id)
				return b;
		}
		return null;
	}
}

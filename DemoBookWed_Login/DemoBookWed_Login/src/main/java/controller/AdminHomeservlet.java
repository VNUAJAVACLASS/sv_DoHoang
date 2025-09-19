package controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class AdminHomeservlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		HttpSession session=req.getSession(false);
		String user=(session==null)?(String)session.getAttribute("username"):null;
		
		if(user==null) {
			resp.sendRedirect("login");
			return;
		}
	}

}

package controller;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.rmi.ServerException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class Loginservlet extends HttpServlet{

	private static final String USERNAME ="admin";
	private static final String PASSWORD ="123";
	
	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
		
		Cookie[] cookie=req.getCookies();
		String rememberedUser=null;
		
		if(cookie==null) {
			for(Cookie c: cookie) {
				if(c.getName().equals(rememberedUser)) {
					rememberedUser=c.getValue();
					break;
				}
				
			}
		}
		
		req.setAttribute("rememberedUser",rememberedUser );
		req.getRequestDispatcher("login.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException{
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		String remember=req.getParameter("remember");
		
		if(USERNAME.equals(username) && PASSWORD.equals(password)) {
			HttpSession session=req.getSession();
			req.setAttribute("username", username);
			
			if("on".equals(remember)) {
				Cookie cookie=new Cookie("rememberedUser", username);
				cookie.setMaxAge(10100);
				
			}else {
				Cookie cookie=new Cookie("rememberedUser","" );
				cookie.setMaxAge(0);
				resp.addCookie(cookie);
			}
			
			resp.sendRedirect(remember);
		}else {
			req.setAttribute("error", "tài khoản hoặc mật khẩu sai");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
		
	}
}

package kr.co.farmstory.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.farmstory.service.user.UserService;

@WebServlet("/user/check.do")
public class CheckController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String type = req.getParameter("type");
		String value = req.getParameter("value");
		
		UserService service = UserService.INSTANCE;
		int result = service.checkUser(type,value);
		
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		if(result > 0) {
			out.print("no");
		}else {
			out.print("yes");
		}
	
	
	
	
	
	
	
	}
	
}

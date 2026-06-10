package kr.co.farmstory.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import org.mindrot.jbcrypt.BCrypt;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.farmstory.dto.user.UserDTO;
import kr.co.farmstory.service.user.UserService;

@WebServlet("/user/updatePass.do")
public class UpdatePassController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		// 1. 새 비밀번호 받기
		String pass = req.getParameter("pass");
		
		// 2. 세션에서 로그인 유저 가져오기
		HttpSession session = req.getSession();
		UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");
		
		// 3. 비밀번호 업데이트
		UserService service = UserService.INSTANCE;
		String hashedPass = BCrypt.hashpw(pass, BCrypt.gensalt());
		int result = service.updatePass(sessUser.getUserid(), hashedPass);
		
		// 4.결과 반환
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		if(result > 0) {
			out.print("success");
		}else {
			out.print("fail");
		}
	
	
	
	
	
	
	}
	
	
	
	
}

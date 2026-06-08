package kr.co.farmstory.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.farmstory.dto.user.UserDTO;
import kr.co.farmstory.service.user.UserService;


@WebServlet("/user/register.do")
public class RegisterController extends HttpServlet{

	
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/user/register.jsp").forward(req, resp);
	
	
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userid = req.getParameter("uid");
		String pass = req.getParameter("pass1");
		String name = req.getParameter("name");
		String nick = req.getParameter("nick");
		String email = req.getParameter("email");
		String hp = req.getParameter("hp");
		String zip = req.getParameter("zip");
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
		String regip = req.getRemoteAddr();
		
		
		UserDTO dto = new UserDTO();
		dto.setUserid(userid);
		dto.setPass(pass);
		dto.setName(name);
		dto.setNick(nick);
		dto.setEmail(email);
		dto.setHp(hp);
		dto.setZip(zip);
		dto.setAddr1(addr1);
		dto.setAddr2(addr2);
		dto.setRegip(regip);
		
		UserService service = UserService.INSTANCE;
		int result = service.insertUser(dto);
		
		if(result > 0) {
			// 가입 성공 -> 로그인 페이지
			resp.sendRedirect(req.getContextPath() + "/user/login.do");
		}else {
			// 가입 실패 -> 회원가입 페이지 이동
			req.getRequestDispatcher("/WEB-INF/views/user/register.jsp").forward(req, resp);
		}
	
	
	}
	
	
}

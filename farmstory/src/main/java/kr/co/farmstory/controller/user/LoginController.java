package kr.co.farmstory.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.farmstory.dto.user.UserDTO;
import kr.co.farmstory.service.user.UserService;


@WebServlet("/user/login.do")
public class LoginController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(req,resp);
	
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// 1. 데이터 받기
		String userid = req.getParameter("uid");
		String pass = req.getParameter("pass");
		
		// 2. DB에서 유저 조회
		UserService service = UserService.INSTANCE;
		UserDTO dto = service.selectUser(userid);
		
		// 3, 로그인 처리
		if(dto != null && dto.getPass().equals(pass)) {
			// 성공 -> 세션에 저장
			HttpSession session = req.getSession();
			session.setAttribute("loginUser", dto);
			resp.sendRedirect(req.getContextPath() + "/index.jsp");
		}else {
			// 실패 -> 로그인 페이지로
			req.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(req, resp);
		}
	}
		
}

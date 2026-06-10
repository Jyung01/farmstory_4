package kr.co.farmstory.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.farmstory.service.user.UserService;

@WebServlet("/user/userid.do")
public class UserIdController extends HttpServlet{

	private static final long serialVersionUID = 1L;
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/user/userid.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 데이터 받기
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		
		// 2, DB에서 아이디 찾기
		UserService service = UserService.INSTANCE;
		String userid = service.selectUserIdByNameEmail(name, email);
		
		// 3. 결과처리
		if(userid != null) {
			req.setAttribute("userid",userid);
			req.getRequestDispatcher("/WEB-INF/views/user/useridResult.jsp").forward(req, resp);
		}else {
			req.setAttribute("error", "일치하는 회원 정보가 없습니다.");
			req.getRequestDispatcher("/WEB-INF/views/user/userid.jsp").forward(req, resp);
		
		}
		
		
	
	}
	
}

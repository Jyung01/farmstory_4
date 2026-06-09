package kr.co.farmstory.controller.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.farmstory.dto.user.UserDTO;
import kr.co.farmstory.service.user.UserService;

@WebServlet("/admin/userList.do")
public class UserListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserService service = UserService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<UserDTO> dtoList = service.selectAll();
		req.setAttribute("dtoList", dtoList);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/userList.jsp");
		dispatcher.forward(req, resp);
	
	}
}

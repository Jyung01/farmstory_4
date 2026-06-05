package kr.co.farmstory.controller.test;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.farmstory.dto.user.UserDTO;

@WebServlet("/test/admin.do")
public class TestAdminController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDTO user = new UserDTO();

        user.setUserid("admin");
        user.setName("관리자");
        user.setNick("관리자닉네임");
        user.setRole("admin");

        HttpSession session = req.getSession();

        session.setAttribute("sessUser", user);

        resp.sendRedirect(req.getContextPath()+ "/index.jsp");
	}
}

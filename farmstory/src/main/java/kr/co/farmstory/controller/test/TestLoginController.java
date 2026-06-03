package kr.co.farmstory.controller.test;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.farmstory.dto.UserDTO;

@WebServlet("/test/login.do")
public class TestLoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 	UserDTO user = new UserDTO();

        user.setUserid("test1");
        user.setName("테스트");
        user.setNick("테스트닉네임");
        user.setRole("member");

        HttpSession session = req.getSession();

        session.setAttribute("sessUser", user);

        resp.sendRedirect(req.getContextPath()+ "/index.jsp");
	}
}

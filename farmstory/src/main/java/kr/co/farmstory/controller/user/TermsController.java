package kr.co.farmstory.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.farmstory.dto.terms.TermsDTO;
import kr.co.farmstory.service.terms.TermsService;

@WebServlet("/user/terms.do")
public class TermsController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		TermsService service = TermsService.INSTANCE;
		TermsDTO dto = service.selectTerms();
		
		
		
		
		req.setAttribute("terms", dto);
		req.getRequestDispatcher("/WEB-INF/views/user/terms.jsp").forward(req, resp);
	
	
	}
	
	
}

package kr.co.farmstory.controller.article;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.farmstory.dto.article.ArticleDTO;
import kr.co.farmstory.service.article.ArticleService;
import kr.co.farmstory.util.ArticleSwitch;

@WebServlet("/article/list.do")
public class ListController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	ArticleService service = ArticleService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 파라미터 값 저장
		String groupName = req.getParameter("groupName");
		String cate = req.getParameter("cate");
		
		req.setAttribute("groupName", groupName);
		req.setAttribute("cate", cate);
		req.setAttribute("groupTitle", ArticleSwitch.GROUP_TITLE.get(groupName));
		req.setAttribute("cateTitle", ArticleSwitch.CATE_TITLE.get(cate));
		req.setAttribute("navImage", ArticleSwitch.NAV_IMAGE.get(cate));
		
		List<ArticleDTO> dtoList =service.findAll(groupName, cate);
		
		req.setAttribute("dtoList", dtoList);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/article/list.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}

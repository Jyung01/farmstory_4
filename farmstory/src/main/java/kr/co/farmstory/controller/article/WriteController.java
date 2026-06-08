package kr.co.farmstory.controller.article;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.farmstory.dto.article.ArticleDTO;
import kr.co.farmstory.dto.user.UserDTO;
import kr.co.farmstory.service.article.ArticleService;
import kr.co.farmstory.util.ArticleSwitch;

@WebServlet("/article/write.do")
public class WriteController extends HttpServlet {
	
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
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/article/write.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(false);
	    UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");

		
		// 파라미터 값 저장
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String groupName = req.getParameter("groupName");
		String cate = req.getParameter("cate");
		String regip = req.getRemoteAddr();
		
		ArticleDTO dto = new ArticleDTO();
		dto.setGroupName(groupName);
	    dto.setCate(cate);
	    dto.setTitle(title);
	    dto.setContent(content);
	    dto.setWriter(sessUser.getUserid());
	    dto.setRegip(req.getRemoteAddr());

	    service.register(dto);
		
	    resp.sendRedirect(req.getContextPath()
	            + "/article/list.do?groupName="
	            + groupName
	            + "&cate="
	            + cate);
		
		
	}
}

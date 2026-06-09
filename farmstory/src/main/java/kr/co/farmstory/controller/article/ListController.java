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
import kr.co.farmstory.dto.page.PageGroupDTO;
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
		String page = req.getParameter("page");
		String searchType = req.getParameter("searchType");
		String keyword = req.getParameter("keyword");

		if(keyword == null) {
		    keyword = "";
		}
		
		
		int currentPage = service.getCurrentPage(page);
	    int total = service.selectCount(groupName, cate, searchType, keyword);
	    int start = service.getLimitStart(currentPage);
	    int lastPageNum =service.getLastPageNum(total);
	    int pageStartNum = service.getListNumStart(total, currentPage);
	    
	    PageGroupDTO pageGroup = service.getCurrentPageGroup(currentPage, lastPageNum);
	    List<ArticleDTO> dtoList =service.findAll(groupName, cate, start, searchType, keyword);

	    
	    req.setAttribute("searchType", searchType);
	    req.setAttribute("keyword", keyword);
		req.setAttribute("groupName", groupName);
		req.setAttribute("cate", cate);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("pageStartNum", pageStartNum);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageGroup", pageGroup);
		req.setAttribute("groupTitle", ArticleSwitch.GROUP_TITLE.get(groupName));
		req.setAttribute("cateTitle", ArticleSwitch.CATE_TITLE.get(cate));
		req.setAttribute("navImage", ArticleSwitch.NAV_IMAGE.get(cate));
		req.setAttribute("dtoList", dtoList);
		
		
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/article/list.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}

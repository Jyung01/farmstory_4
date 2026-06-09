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
import kr.co.farmstory.dto.article.FileDTO;
import kr.co.farmstory.dto.page.PageGroupDTO;
import kr.co.farmstory.service.article.ArticleService;
import kr.co.farmstory.service.article.FileService;
import kr.co.farmstory.util.ArticleSwitch;

@WebServlet("/article/view.do")
public class ViewController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	ArticleService service = ArticleService.INSTANCE;
	FileService fileService = FileService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 파라미터 값 저장
		String groupName = req.getParameter("groupName");
		String cate = req.getParameter("cate");
		String page = req.getParameter("page");
		int ano = Integer.parseInt(req.getParameter("ano"));
		
		// 조회수 증가 서비스 요청
		service.updateHit(ano);
		
		// 파일 조회 서비스 요청
		List<FileDTO> fileList = fileService.findFiles(ano);
		
		ArticleDTO dto = service.findById(ano);
		
		req.setAttribute("dto", dto);
		req.setAttribute("groupName", groupName);
        req.setAttribute("cate", cate);
        req.setAttribute("page", page);
        req.setAttribute("fileList", fileList);

        req.setAttribute("groupTitle", ArticleSwitch.GROUP_TITLE.get(groupName));
        req.setAttribute("cateTitle", ArticleSwitch.CATE_TITLE.get(cate));
        req.setAttribute("navImage", ArticleSwitch.NAV_IMAGE.get(cate));
		
		
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/article/view.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}

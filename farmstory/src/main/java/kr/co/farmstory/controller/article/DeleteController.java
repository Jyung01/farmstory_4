package kr.co.farmstory.controller.article;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.farmstory.dto.article.ArticleDTO;
import kr.co.farmstory.dto.article.FileDTO;
import kr.co.farmstory.dto.page.PageGroupDTO;
import kr.co.farmstory.dto.user.UserDTO;
import kr.co.farmstory.service.article.ArticleService;
import kr.co.farmstory.service.article.FileService;
import kr.co.farmstory.util.ArticleSwitch;

@WebServlet("/article/delete.do")
public class DeleteController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	ArticleService service = ArticleService.INSTANCE;
	FileService fileService = FileService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(false);
	    UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");

	    int ano = Integer.parseInt(req.getParameter("ano"));

	    String groupName = req.getParameter("groupName");
	    String cate = req.getParameter("cate");
	    String page = req.getParameter("page");
	    
	    if(!service.isOwnerOrAdmin(ano, sessUser)) {
	        resp.sendRedirect(req.getContextPath()
	                + "/article/view.do?ano=" + ano
	                + "&groupName=" + groupName
	                + "&cate=" + cate
	                + "&page=" + page);
	        return;
	    }
		
        // 글 파일 리스트 요청
        List<FileDTO> fileList = fileService.findFiles(ano);
        
        // 파일 삭제
        for(FileDTO file : fileList) {

            File savedFile =
                    new File(req.getServletContext().getRealPath("/upload/article"), file.getSfname());

            if(savedFile.exists()) {
                savedFile.delete();
            }
        }
        
        // db 삭제
        service.remove(ano);
		
		
		// 그룹으로 리다이렉트
        resp.sendRedirect(req.getContextPath()
                + "/article/list.do?groupName=" + groupName
                + "&cate=" + cate + "&delete=success");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
	    UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");

	    int ano = Integer.parseInt(req.getParameter("ano"));

	    String groupName = req.getParameter("groupName");
	    String cate = req.getParameter("cate");
	    String page = req.getParameter("page");
	    
	    if(!service.isOwnerOrAdmin(ano, sessUser)) {
	        resp.sendRedirect(req.getContextPath()
	                + "/article/view.do?ano=" + ano
	                + "&groupName=" + groupName
	                + "&cate=" + cate
	                + "&page=" + page);
	        return;
	    }
	}	
}

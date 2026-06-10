package kr.co.farmstory.controller.comment;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.farmstory.dto.article.CommentDTO;
import kr.co.farmstory.dto.user.UserDTO;
import kr.co.farmstory.service.article.CommentService;

@WebServlet("/comment/delete.do")
public class DeleteController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	CommentService service = CommentService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(false);
	    UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");
		
		// 전달 파라미터 저장
		int cno = Integer.parseInt(req.getParameter("cno"));
        int parent = Integer.parseInt(req.getParameter("parent"));

        String groupName = req.getParameter("groupName");
        String cate = req.getParameter("cate");
        String page = req.getParameter("page");
	    
        if(!service.isOwnerOrAdmin(cno, sessUser)) {
            resp.sendRedirect(req.getContextPath()
                    + "/article/view.do?ano=" + parent
                    + "&groupName=" + groupName
                    + "&cate=" + cate
                    + "&page=" + page);
            return;
        }
        
        // 댓글 삭제 서비스 요청
        service.remove(cno, parent);
	    
	    
	    // 리다이렉트
	    resp.sendRedirect(req.getContextPath()
	            + "/article/view.do?ano="+ parent + "&groupName=" + groupName + "&cate=" + cate
	            + "&page=" + page);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
	    UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");
		
		// 전달 파라미터 저장
		int cno = Integer.parseInt(req.getParameter("cno"));
        int parent = Integer.parseInt(req.getParameter("parent"));

        String groupName = req.getParameter("groupName");
        String cate = req.getParameter("cate");
        String page = req.getParameter("page");
	    
        if(!service.isOwnerOrAdmin(cno, sessUser)) {
            resp.sendRedirect(req.getContextPath()
                    + "/article/view.do?ano=" + parent
                    + "&groupName=" + groupName
                    + "&cate=" + cate
                    + "&page=" + page);
            return;
        }
	}
}

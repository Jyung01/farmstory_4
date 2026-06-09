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

@WebServlet("/comment/write.do")
public class WriteController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	CommentService service = CommentService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");
		
		// 전달 파라미터 저장
	    int parent = Integer.parseInt(req.getParameter("parent"));
	    String content = req.getParameter("content");
	    String groupName = req.getParameter("groupName");
	    String cate = req.getParameter("cate");
	    String page = req.getParameter("page");
	    String regip = req.getRemoteAddr();
	    
	    
	    // 댓글 객체 생성
	    CommentDTO dto = new CommentDTO();
	    dto.setParent(parent);
	    dto.setContent(content);
	    dto.setWriter(sessUser.getUserid());
	    dto.setRegip(regip);
	    
	    // 댓글 등록 서비스 호출
	    service.register(dto);
	    
	    
	    // 리다이렉트
	    resp.sendRedirect(req.getContextPath()
	            + "/article/view.do?ano="+ parent + "&groupName=" + groupName + "&cate=" + cate
	            + "&page=" + page);

	    
	    
	}
}

package kr.co.farmstory.controller.event;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.farmstory.dto.event.EventDTO;
import kr.co.farmstory.dto.user.UserDTO;
import kr.co.farmstory.service.event.EventService;

@WebServlet("/event/delete.do")
public class DeleteController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	EventService service = EventService.INSTANCE;

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(false);
        UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");
        
        // 권한 확인
        if(sessUser == null || !"admin".equals(sessUser.getRole())) {
            resp.sendRedirect(req.getContextPath() + "/event/view.do");
            return;
        }

        int eventNo = Integer.parseInt(req.getParameter("eventNo"));

        // 삭제 서비스 요청
        service.remove(eventNo);

        // 리다이렉트
        resp.sendRedirect(req.getContextPath() + "/event/view.do");
	}
	
}

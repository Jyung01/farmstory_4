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

@WebServlet("/event/modify.do")
public class ModifyController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	EventService service = EventService.INSTANCE;

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 불필요한 get 접근 막기
		
		HttpSession session = req.getSession(false);
        UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");
        
        // 권한 확인
        if(sessUser == null || !"admin".equals(sessUser.getRole())) {
            resp.sendRedirect(req.getContextPath() + "/event/view.do");
            return;
        }
        
        // 리다이렉트
        resp.sendRedirect(req.getContextPath() + "/event/view.do");

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(false);
        UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");
        
        // 권한 확인
        if(sessUser == null || !"admin".equals(sessUser.getRole())) {
            resp.sendRedirect(req.getContextPath() + "/event/view.do");
            return;
        }

        int eventNo = Integer.parseInt(req.getParameter("eventNo"));
        
        String title = req.getParameter("title");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");

        EventDTO dto = new EventDTO();
        dto.setEventNo(eventNo);
        dto.setTitle(title);
        dto.setStartDate(startDate);
        dto.setEndDate(endDate);

        // 수정 서비스 요청
        service.modify(dto);

        // 리다이렉트
        resp.sendRedirect(req.getContextPath() + "/event/view.do");
	}
	
}

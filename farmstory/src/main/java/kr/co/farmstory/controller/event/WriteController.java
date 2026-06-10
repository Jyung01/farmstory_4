package kr.co.farmstory.controller.event;

import java.io.IOException;

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

@WebServlet("/event/write.do")
public class WriteController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	EventService service = EventService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(false);
        UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");
        
        // 작성 권한 확인
        if(sessUser == null || !"admin".equals(sessUser.getRole())) {
            resp.sendRedirect(req.getContextPath() + "/event/view.do");
            return;
        }
        
        // 파라미터 저장
        String title = req.getParameter("title");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        
        // 이벤트 객체 생성 후 받아온 내용 입력
        EventDTO dto = new EventDTO();
        dto.setTitle(title);
        dto.setStartDate(startDate);
        dto.setEndDate(endDate);
        
        
        // 이벤트 등록 서비스 호출
        service.register(dto);
        
        // 리다이렉트
        resp.sendRedirect(req.getContextPath() + "/event/view.do");
        
        
	}
	
}

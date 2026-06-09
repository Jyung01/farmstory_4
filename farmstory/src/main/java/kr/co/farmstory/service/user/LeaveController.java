package kr.co.farmstory.service.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.farmstory.dto.user.UserDTO;

@WebServlet("/user/leave.do")
public class LeaveController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // =========================
        // 1. 세션에서 로그인 유저 가져오기
        // =========================
        HttpSession session = req.getSession();
        UserDTO loginUser = (UserDTO) session.getAttribute("sessUser");

        // =========================
        // 2. 로그인 체크
        // - 로그인 안 되어 있으면 로그인 페이지 이동
        // =========================
        if (loginUser == null) {
            resp.sendRedirect(req.getContextPath() + "/user/login.do");
            return;
        }

        // =========================
        // 3. 탈퇴 처리 (DB 업데이트)
        // - leaveDate = NOW() 업데이트
        // =========================
        UserService service = UserService.INSTANCE;
        int result = service.updateLeaveDate(loginUser.getUserid());

        // =========================
        // 4. 결과 처리
        // =========================
        if (result > 0) {

            // 탈퇴 성공
            // - 세션 삭제 (로그아웃 처리)
            session.invalidate();

            // - index.jsp 이동 + alert용 파라미터 전달
            resp.sendRedirect(req.getContextPath() + "/index.jsp?leave=success");
            return;

        } else {

            // 탈퇴 실패
            // - 마이페이지로 이동 + 실패 파라미터
            resp.sendRedirect(req.getContextPath() + "/user/myinfo.do?leave=fail");
            return;
        }
    }
}
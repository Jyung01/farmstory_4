package kr.co.farmstory.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.farmstory.dto.user.UserDTO;
import kr.co.farmstory.service.user.UserService;

@WebServlet("/user/modify.do")
public class ModifyController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // =========================
    // 1. 마이페이지에서 이동 (화면 유지용)
    // =========================
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/views/user/modify.jsp").forward(req, resp);
    }

    // =========================
    // 2. 회원정보 수정 처리
    // =========================
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 한글 처리
        req.setCharacterEncoding("UTF-8");

        // =========================
        // 1. 세션에서 로그인 유저 가져오기
        // =========================
        HttpSession session = req.getSession();
        UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");

        if (sessUser == null) {
            resp.sendRedirect(req.getContextPath() + "/user/login.do");
            return;
        }

        // =========================
        // 2. 폼 데이터 받기
        // =========================
        String name = req.getParameter("name");
        String nick = req.getParameter("nick");
        String email = req.getParameter("email");
        String hp = req.getParameter("hp");
        String zip = req.getParameter("zip");
        String addr1 = req.getParameter("addr1");
        String addr2 = req.getParameter("addr2");

        // =========================
        // 3. DTO 세팅
        // =========================
        UserDTO dto = new UserDTO();
        dto.setUserid(sessUser.getUserid()); // 아이디는 변경 X
        dto.setName(name);
        dto.setNick(nick);
        dto.setEmail(email);
        dto.setHp(hp);
        dto.setZip(zip);
        dto.setAddr1(addr1);
        dto.setAddr2(addr2);

        // =========================
        // 4. DB 업데이트
        // =========================
        UserService service = UserService.INSTANCE;
        int result = service.updateUser(dto);

        // =========================
        // 5. 결과 처리
        // =========================
        if (result > 0) {

            // 세션도 최신 정보로 갱신
            session.setAttribute("sessUser", dto);

            resp.sendRedirect(req.getContextPath() + "/user/myinfo.do?update=success");
        } else {
            resp.sendRedirect(req.getContextPath() + "/user/myinfo.do?update=fail");
        }
    }
}
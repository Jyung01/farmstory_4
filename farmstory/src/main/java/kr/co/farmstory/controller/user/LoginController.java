package kr.co.farmstory.controller.user;

import java.io.IOException;

import org.mindrot.jbcrypt.BCrypt;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.farmstory.dto.user.UserDTO;
import kr.co.farmstory.service.user.UserService;

@WebServlet("/user/login.do")
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userid = req.getParameter("uid");
        String pass = req.getParameter("pass");

        if(userid == null || userid.isEmpty() || pass == null || pass.isEmpty()) {
            req.setAttribute("error", "아이디 또는 비밀번호를 입력해주세요.");
            req.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(req, resp);
            return;
        }

        // 아이디로만 유저 조회
        UserService service = UserService.INSTANCE;
        UserDTO dto = service.selectUser(userid);

        // BCrypt로 비밀번호 비교
        if(dto != null && BCrypt.checkpw(pass, dto.getPass())) {
            HttpSession session = req.getSession();
            session.setAttribute("sessUser", dto);
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        } else {
            req.setAttribute("error", "아이디 또는 비밀번호가 틀렸습니다.");
            req.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(req, resp);
        }
    }
}
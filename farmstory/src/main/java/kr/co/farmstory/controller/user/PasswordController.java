package kr.co.farmstory.controller.user;

import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.farmstory.service.user.UserService;
import kr.co.farmstory.util.MailHelper;

@WebServlet("/user/password.do")
public class PasswordController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.getRequestDispatcher("/WEB-INF/views/user/password.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	    // 1. 데이터 받기
	    String name = req.getParameter("name");
	    String email = req.getParameter("email");

	    // 빈칸 체크
	    if(name == null || name.isEmpty() || email == null || email.isEmpty()) {
	        req.setAttribute("error", "이름과 이메일을 입력해주세요.");
	        req.getRequestDispatcher("/WEB-INF/views/user/password.jsp").forward(req, resp);
	        return;
	    }

	    // 2. 유저 확인
	    UserService service = UserService.INSTANCE;
	    String userid = service.selectUserByNameEmail(name, email);

	    if(userid != null) {
	        // 3. 임시 비밀번호 생성 (8자리)
	        String tempPass = UUID.randomUUID().toString().substring(0, 8);

	        // 4. DB에 임시 비밀번호 저장
	        service.updatePass(userid, tempPass);

	        // 5. 이메일 발송
	        MailHelper mailHelper = new MailHelper();
	        String subject = "[팜스토리] 임시 비밀번호입니다.";
	        String content = "임시 비밀번호: <b>" + tempPass + "</b><br>로그인 후 비밀번호를 변경해주세요.";
	        mailHelper.sendMail(email, subject, content);

	        req.setAttribute("message", "임시 비밀번호가 이메일로 발송되었습니다.");
	        req.getRequestDispatcher("/WEB-INF/views/user/password.jsp").forward(req, resp);
	    } else {
	        req.setAttribute("error", "일치하는 회원 정보가 없습니다.");
	        req.getRequestDispatcher("/WEB-INF/views/user/password.jsp").forward(req, resp);
	    }
	}
}
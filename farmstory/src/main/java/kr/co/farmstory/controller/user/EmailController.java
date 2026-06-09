package kr.co.farmstory.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.farmstory.util.MailHelper;

@WebServlet("/user/email.do")
public class EmailController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 
		// 1. 이메일 받기
		String email = req.getParameter("email");
		
		// 2. 랜덤 인증번호 생성 (6자리)
		int authNum = (int)(Math.random() * 900000) + 100000;
		
		//3. 세션에 저장
		HttpSession session = req.getSession();
		session.setAttribute("authNum", authNum);
		
		//4. 이메일 발송
		MailHelper mailHelper = new MailHelper();
		String subject = "[팜스토리] 이메일 인증번호 입니다.";
		String content = "인증번호: <b>" + authNum + "</b>";
		mailHelper.sendMail(email, subject, content);
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print("sent");
	
	
	
	
	
	}
	
}

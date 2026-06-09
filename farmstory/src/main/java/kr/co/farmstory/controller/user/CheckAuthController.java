package kr.co.farmstory.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/user/checkAuth.do")
public class CheckAuthController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // 1. 사용자가 입력한 인증번호 받기
        String auth = req.getParameter("auth");
        
        // 2. 세션에서 발송한 인증번호 꺼내기
        HttpSession session = req.getSession();
        int authNum = (int) session.getAttribute("authNum");
        
        // 3. 비교
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        
        if(auth.equals(String.valueOf(authNum))) {
            out.print("yes");
        } else {
            out.print("no");
        }
    }
}
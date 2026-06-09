package kr.co.farmstory.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.farmstory.service.user.UserService;

@WebServlet("/user/check.do")
public class CheckController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 1. 요청값 받기
        String type = req.getParameter("type");
        String value = req.getParameter("value");

        // 2. 서비스 호출
        UserService service = UserService.INSTANCE;
        int result = service.selectUserCheck(type, value); // ← 이름 통일

        // 3. 응답 설정 (JS에서 숫자로 받게)
        resp.setContentType("text/plain; charset=UTF-8");

        PrintWriter out = resp.getWriter();

        // 4. 결과 반환
        if (result > 0) {
            out.print("1"); // 중복
        } else {
            out.print("0"); // 사용 가능
        }
    }
}
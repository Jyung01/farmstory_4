package kr.co.farmstory.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.farmstory.dto.UserDTO;

import java.io.IOException;
/* 나중에 테스트 하실때 주석 풀어서 테스트 해보시면 될거 같습니다
@WebFilter({
        "/myinfo/*",
        "/cart/*",
        "/order/*"
})
*/
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        // false 뜻 : 세션 있으면 가져오고 없으면 만들지 않기
        HttpSession session = req.getSession(false);
        UserDTO sessUser = null;

        if (session != null) {
            sessUser = (UserDTO) session.getAttribute("sessUser");
        }

        // 로그인 안 했으면 로그인 페이지로 리다이렉트
        if (sessUser == null) {
            resp.sendRedirect(req.getContextPath() + "/user/login.do");
            return;
        }

        // 로그인 상태면 통과
        chain.doFilter(request, response);
    }
}
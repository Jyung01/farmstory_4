package kr.co.farmstory.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.farmstory.dto.user.UserDTO;

import java.io.IOException;

@WebFilter({
    "/article/write.do",
    "/article/modify.do",
    "/article/delete.do",
    "/comment/write.do",
    "/comment/modify.do",
    "/comment/delete.do",
    "/market/cart.do",
    "/order/*",
    "/user/logout.do",
    "/user/modify.do",
    "/user/myinfo.do",
    "/user/ordered.do",
    "/user/cart.do",
    "/user/delete.do"
})

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
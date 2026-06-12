package kr.co.farmstory.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.farmstory.dto.user.UserDTO;

import java.io.IOException;

@WebFilter({
    "/admin/*",
    "/event/write.do",
    "/event/modify.do",
    "/event/delete.do"
})
public class AdminCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        // 기존 세션 가져오기
        HttpSession session = req.getSession(false);
        UserDTO sessUser = null;

        // 로그인 정보 가져오기
        if (session != null) {
            sessUser = (UserDTO) session.getAttribute("sessUser");
        }

        // 로그인 안 했거나 관리자가 아니면 메인으로 리다이렉트
        if (sessUser == null || !"admin".equals(sessUser.getRole())) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        // 관리자 권한 확인 통과
        chain.doFilter(request, response);
    }
}
package kr.co.farmstory.controller.market;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.farmstory.dto.cart.CartDTO;
import kr.co.farmstory.dto.user.UserDTO;
import kr.co.farmstory.service.market.MarketService;

@WebServlet("/market/order.do")
public class OrdersController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/myinfo/ordered.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");

        if (sessUser == null) {
            resp.sendRedirect(req.getContextPath() + "/test/login.do"); 
            return;
        }
        
        String[] cartNoArr = req.getParameterValues("cartNo");
        
        UserDTO orderUser = MarketService.INSTANCE.selectOrderUser(sessUser.getUserid());
        req.setAttribute("orderUser", orderUser);

        List<CartDTO> orderList = MarketService.INSTANCE.selectCartListByCartNo(cartNoArr);
        req.setAttribute("orderList", orderList);

        req.getRequestDispatcher("/WEB-INF/views/Market/order.jsp").forward(req, resp);
    }
}
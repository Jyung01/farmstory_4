package kr.co.farmstory.controller.market;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.farmstory.dto.orders.OrdersDTO;
import kr.co.farmstory.dto.user.UserDTO;
import kr.co.farmstory.service.orders.OrdersService;

@WebServlet("/user/ordered.do")
public class OrderListController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	HttpSession session = req.getSession();
    	UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");

    	if (sessUser != null) {
    	    List<OrdersDTO> ordersList = OrdersService.INSTANCE.selectOrdersList(sessUser.getUserid());
    	    req.setAttribute("ordersList", ordersList);
    	    req.getRequestDispatcher("/WEB-INF/views/myinfo/ordered.jsp").forward(req, resp);
    	} else {
    	    resp.sendRedirect(req.getContextPath() + "/user/login.do");
    	}
    }

    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
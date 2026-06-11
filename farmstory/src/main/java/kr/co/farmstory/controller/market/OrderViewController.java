package kr.co.farmstory.controller.market;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.farmstory.dto.orderItem.OrderItemDTO;
import kr.co.farmstory.dto.orders.OrdersDTO;
import kr.co.farmstory.service.orders.OrdersService;

@WebServlet("/user/orderView.do")
public class OrderViewController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderNoStr = req.getParameter("orderNo");
        
        if (orderNoStr != null && !orderNoStr.isEmpty()) {
            int orderNo = Integer.parseInt(orderNoStr);

            OrdersDTO order = OrdersService.INSTANCE.selectOrder(orderNo);
            List<OrderItemDTO> orderItemList = OrdersService.INSTANCE.selectOrderItems(orderNo);
            
            req.setAttribute("order", order);
            req.setAttribute("orderItemList", orderItemList);
        }
        
        req.getRequestDispatcher("/WEB-INF/views/myinfo/orderView.jsp").forward(req, resp);
    }
}
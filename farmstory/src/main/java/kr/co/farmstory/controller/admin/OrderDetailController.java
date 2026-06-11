package kr.co.farmstory.controller.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.farmstory.dto.orderItem.OrderItemDTO;
import kr.co.farmstory.dto.orders.OrdersDTO;
import kr.co.farmstory.service.admin.OrderListService;

@WebServlet("/admin/orderDetail.do")
public class OrderDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	OrderListService service =  OrderListService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String orderNo = req.getParameter("orderNo");
		
		OrdersDTO dto = service.findByOrderNo(orderNo);
		List<OrderItemDTO> dtoList = service.findByProdNo(orderNo);
		
		
		
		req.setAttribute("order", dto);
		req.setAttribute("itemList", dtoList);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/orderDetail.jsp");
		dispatcher.forward(req, resp);
	}
}

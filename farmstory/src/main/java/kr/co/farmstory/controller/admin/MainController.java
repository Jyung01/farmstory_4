package kr.co.farmstory.controller.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.farmstory.dto.admin.ProductDTO;
import kr.co.farmstory.dto.orderItem.OrderItemDTO;
import kr.co.farmstory.dto.user.UserDTO;
import kr.co.farmstory.service.admin.OrderListService;
import kr.co.farmstory.service.admin.ProductService;
import kr.co.farmstory.service.user.UserService;

@WebServlet("/admin/main.do")
public class MainController extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	
	ProductService prodService = ProductService.INSTANCE;
	OrderListService orderService = OrderListService.INSTANCE;
	UserService userService = UserService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<ProductDTO> prodList = prodService.findAll(0,3);
		List<OrderItemDTO> orderList = orderService.findAll(0,3);
		List<UserDTO> userList = userService.selectAll(0,3);
		
		req.setAttribute("prodList", prodList);
		req.setAttribute("orderList", orderList);
		req.setAttribute("userList", userList);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/index.jsp");
		dispatcher.forward(req, resp);
	}
}

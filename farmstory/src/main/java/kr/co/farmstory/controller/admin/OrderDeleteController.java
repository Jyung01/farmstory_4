package kr.co.farmstory.controller.admin;

import java.io.IOException;
import java.util.Arrays;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.farmstory.service.admin.OrderListService;

@WebServlet("/admin/orderDelete.do")
public class OrderDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	OrderListService service = OrderListService.INSTANCE;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] itemNos = req.getParameterValues("itemNo");
		System.out.println(Arrays.toString(itemNos));
		
		if(itemNos != null) {
			for (String itemNo : itemNos) {
				service.remove(itemNo);
			}
		}
		resp.sendRedirect("/farmstory/admin/orderList.do?delete=success");
		
	}
}

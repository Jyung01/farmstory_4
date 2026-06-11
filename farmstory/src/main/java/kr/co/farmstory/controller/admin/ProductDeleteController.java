package kr.co.farmstory.controller.admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.farmstory.service.admin.ProductService;

@WebServlet("/admin/prodDelete.do")
public class ProductDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ProductService service = ProductService.INSTANCE;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] prodNos = req.getParameterValues("prodNo");

		if(prodNos != null) {
			for (String prodNo : prodNos) {
				service.remove(prodNo);
			}
		}
		resp.sendRedirect("/farmstory/admin/prodList.do?delete=success");
		
	}
}

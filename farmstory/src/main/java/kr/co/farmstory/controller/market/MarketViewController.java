package kr.co.farmstory.controller.market;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.farmstory.dto.product.ProductDTO;
import kr.co.farmstory.service.market.MarketService;

@WebServlet("/market/view.do")
public class MarketViewController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private MarketService service = MarketService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String prodNo = req.getParameter("prodNo");
		
		ProductDTO product = service.selectProduct(prodNo);
		req.setAttribute("product", product);
		
		req.getRequestDispatcher("/WEB-INF/views/Market/view.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
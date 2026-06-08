package kr.co.farmstory.controller.market;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.farmstory.dto.page.PageGroupDTO;
import kr.co.farmstory.dto.product.ProductDTO;
import kr.co.farmstory.service.market.MarketService;

@WebServlet("/market/list.do")
public class MarketListController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private MarketService service = MarketService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pg = req.getParameter("page");
		String cate = req.getParameter("cate"); 
		
		int currentPage = service.getCurrentPage(pg);
		int start = service.getStart(currentPage);
		
		int total = 0;
		List<ProductDTO> products = null;

		if(cate == null || cate.equals("")) {
			total = service.selectCount();
			products = service.selectAll(start);
		} else {

			total = service.selectCountCate(cate);
			products = service.selectAllCate(cate, start);
		}
		
		int lastPageNum = service.getLastPageNum(total);
		PageGroupDTO pageGroup = service.getCurrentPageGroup(currentPage, lastPageNum);
		
		req.setAttribute("products", products);
		req.setAttribute("totalCount", total);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageGroup", pageGroup); 
		
		req.getRequestDispatcher("/WEB-INF/views/Market/list.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
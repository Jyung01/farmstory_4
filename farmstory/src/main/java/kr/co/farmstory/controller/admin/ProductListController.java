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
import kr.co.farmstory.dto.page.PageGroupDTO;
import kr.co.farmstory.service.admin.ProductService;

@WebServlet("/admin/prodList.do")
public class ProductListController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	ProductService service = ProductService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 전체 목록 갯수 
		int total = service.getCount();
		
		// 현재 페이지 번호
		String page = req.getParameter("page");
		int currentPage = service.getCurrentPage(page);
		
		// 마지막 페이지 번호
		int lastPage = service.getLastPage(total);
		
		// 현재 페이지 그룹 
		PageGroupDTO pageGroupDTO = service.getPageGroup(currentPage, lastPage);
		
		// 현재 페이지의 목록 시작번호
		int listStartNo = service.getListStartNo(currentPage, total);
		
		// 목록 10개 limit
		int start = service.getStart(currentPage);
		
		List<ProductDTO> dtoList = service.findAll(start, 10);
		
		req.setAttribute("dtoList", dtoList);
		req.setAttribute("pageGroupDTO", pageGroupDTO);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("lastPage", lastPage);
		req.setAttribute("listStartNo", listStartNo);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/productList.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}

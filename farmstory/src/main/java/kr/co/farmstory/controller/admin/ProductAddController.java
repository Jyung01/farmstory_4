package kr.co.farmstory.controller.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.farmstory.dto.admin.FileDTO;
import kr.co.farmstory.dto.admin.ProductDTO;
import kr.co.farmstory.service.FileService;
import kr.co.farmstory.service.ProductService;

@WebServlet("/admin/register.do")
public class ProductAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ProductService service = ProductService.INSTANCE;
	FileService fileService = FileService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/productAdd.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String category = req.getParameter("category");
		String price = req.getParameter("price");
		String point = req.getParameter("point");
		String discount = req.getParameter("discount");
		String delivery = req.getParameter("delivery");
		String stock = req.getParameter("stock");
		String discript = req.getParameter("discript");
		
		
		List<FileDTO> fileList = fileService.upload(req);
		
		
		ProductDTO dto = new ProductDTO();
		dto.setProdName(name);
		dto.setCate(category);
		dto.setPrice(price);
		dto.setPoint(point);
		dto.setDiscount(discount);
		dto.setDelivery(delivery);
		dto.setStock(stock);
		dto.setDiscript(discript);
		
		dto.setThumb(fileList.get(0).getSfname());
		dto.setInfo(fileList.get(1).getSfname());
		dto.setDetail(fileList.get(2).getSfname());
		
		service.register(dto);
		
		
		
		resp.sendRedirect("/farmstory/admin/list.do?register=success");
		
	}

}

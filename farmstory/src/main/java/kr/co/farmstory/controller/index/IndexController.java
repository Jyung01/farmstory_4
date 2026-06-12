package kr.co.farmstory.controller.index;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.farmstory.service.article.ArticleService;
import kr.co.farmstory.dto.product.ProductDTO;
import kr.co.farmstory.service.product.ProductService;


@WebServlet("/index.do")
public class IndexController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    ArticleService articleService = ArticleService.INSTANCE;
    ProductService productService = ProductService.INSTANCE;
    

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	List<ProductDTO> products = productService.selectBest(0, 6);
        req.setAttribute("products", products);

        req.setAttribute("growList", articleService.findLatest("crop", "grow"));
        req.setAttribute("schoolList", articleService.findLatest("crop", "school"));
        req.setAttribute("storyList", articleService.findLatest("crop", "story"));
        req.setAttribute("noticeList", articleService.findLatest("community", "notice"));

        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }
}

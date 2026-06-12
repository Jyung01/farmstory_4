package kr.co.farmstory.service.product;

import java.util.List;
import kr.co.farmstory.dao.product.ProductDAO;
import kr.co.farmstory.dto.product.ProductDTO; 

public enum ProductService {

    INSTANCE;

    private ProductDAO dao = ProductDAO.getInstance();

    public List<ProductDTO> selectBest(int start, int count) {
        return dao.selectBest(start, count);
    }
}
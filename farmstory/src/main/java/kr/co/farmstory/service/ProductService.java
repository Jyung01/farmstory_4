package kr.co.farmstory.service;


import java.util.List;

import kr.co.farmstory.dao.ProductDAO;
import kr.co.farmstory.dto.admin.ProductDTO;

public enum ProductService {
	INSTANCE;
	
	ProductDAO dao = ProductDAO.getInstance();
	
	public void register(ProductDTO dto) {
		dao.insert(dto);
	}
	
	public List<ProductDTO> findAll() {
		return dao.selectAll();
	}
}

package kr.co.farmstory.dao.product;

import java.util.ArrayList;
import java.util.List;

import kr.co.farmstory.dto.product.ProductDTO;
import kr.co.farmstory.util.DBHelper;
import kr.co.farmstory.util.SQL.MarketSQL;

public class ProductDAO extends DBHelper {
	
	public static ProductDAO instance = new ProductDAO();
	public static ProductDAO getInstance() {
		return instance;
	}
	private ProductDAO() {};
	
	
	public List<ProductDTO> selectBest(int start, int count) {
		List<ProductDTO> dtoList = new ArrayList<>();
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(MarketSQL.SELECT_BEST_PRODUCT );
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				
				dto.setProdNo(rs.getInt("prodNo"));
	            dto.setThumb(rs.getString("thumb"));
	            dto.setCate(rs.getString("cate"));
	            dto.setProdName(rs.getString("prodName"));
	            dto.setPrice(rs.getInt("price"));
	            dto.setDiscount(rs.getInt("discount"));
	            dto.setSalePrice(rs.getInt("sale_price"));
				
				dtoList.add(dto);
			}
			closeAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}
}

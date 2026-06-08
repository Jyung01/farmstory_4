package kr.co.farmstory.dao.market;

import java.util.ArrayList;
import java.util.List;

import kr.co.farmstory.dto.cart.CartDTO;
import kr.co.farmstory.dto.product.ProductDTO;
import kr.co.farmstory.util.DBHelper;
import kr.co.farmstory.util.SQL.MarketSQL;

public class MarketDAO extends DBHelper {

	private static MarketDAO instance = new MarketDAO();
	public static MarketDAO getInstance() {
		return instance;
	}
	
	private MarketDAO() {}

	public int selectCount() {
		int total = 0;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(MarketSQL.SELECT_MARKET_PRODUCT_COUNT);		
			
			if(rs.next()) {
				total = rs.getInt(1);				
			}			
			closeAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}
	
	public List<ProductDTO> selectAll(int start) {
		List<ProductDTO> dtoList = new ArrayList<>();
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(MarketSQL.SELECT_MARKET_PRODUCT);
			psmt.setInt(1, start); 
			
			rs = psmt.executeQuery();
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setProdNo(rs.getInt("prodNo"));
				dto.setCate(rs.getString("cate"));
				dto.setProdName(rs.getString("prodName"));
				dto.setPrice(rs.getInt("price"));
				dto.setDiscount(rs.getInt("discount"));
				dto.setThumb(rs.getString("thumb")); 
				dtoList.add(dto);
			}
			closeAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}
	
	public int selectCountCate(String cate) {
		int total = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(MarketSQL.SELECT_MARKET_CATE_COUNT);
			psmt.setString(1, cate);
			rs = psmt.executeQuery();
			if(rs.next()) total = rs.getInt(1);
			closeAll();
		} catch (Exception e) { e.printStackTrace(); }
		return total;
	}

	public List<ProductDTO> selectAllCate(String cate, int start) {
	    List<ProductDTO> dtoList = new ArrayList<>();
	    try {
	        conn = getConnection();
	        psmt = conn.prepareStatement(MarketSQL.SELECT_MARKET_CATE);
	        psmt.setString(1, cate);
	        psmt.setInt(2, start);
	        
	        rs = psmt.executeQuery();
	        while(rs.next()) {
	            ProductDTO dto = new ProductDTO();

	            dto.setProdNo(rs.getInt("prodNo"));
				dto.setCate(rs.getString("cate"));
				dto.setProdName(rs.getString("prodName"));
				dto.setPrice(rs.getInt("price"));
				dto.setDiscount(rs.getInt("discount"));
				dto.setThumb(rs.getString("thumb"));

	            dtoList.add(dto);
	        }
	        closeAll();
	    } catch (Exception e) { 
	        e.printStackTrace(); 
	    }
	    return dtoList;
	}
	
	public ProductDTO selectProduct(String prodNo) {
	    
	    ProductDTO dto = null;
	    
	    try {
	        conn = getConnection();
	        
	        psmt = conn.prepareStatement(MarketSQL.SELECT_PRODUCT_DETAIL);
	        
	        psmt.setString(1, prodNo);
	        
	        rs = psmt.executeQuery();
	        
	        if(rs.next()) {
	            dto = new ProductDTO();
	            
	            dto.setProdNo(rs.getInt("prodNo"));
	            dto.setCate(rs.getString("cate"));
	            dto.setProdName(rs.getString("prodName"));
	            dto.setPrice(rs.getInt("price"));
	            dto.setDiscount(rs.getInt("discount"));
	            dto.setPoint(rs.getInt("point"));
	            dto.setDelivery(rs.getInt("delivery"));
	            dto.setStock(rs.getInt("stock"));
	            dto.setDescript(rs.getString("descript"));
	            dto.setThumb(rs.getString("thumb")); 
	            dto.setInfoImg(rs.getString("infoImg"));
	            dto.setDetailImg(rs.getString("detailImg"));
	            dto.setRegDate(rs.getString("regDate"));
	        }
	        closeAll();
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return dto;
	}
	
	public int insertCart(CartDTO dto) {
		
		int result = 0;
		try {
	        conn = getConnection();
	        psmt = conn.prepareStatement(MarketSQL.INSERT_CART);
	        
	        psmt.setString(1, dto.getUserid());
	        psmt.setInt(2, dto.getProdNo());
	        psmt.setInt(3, dto.getCount());
	        
	        result = psmt.executeUpdate();
	        
	        closeAll();
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		return result;
	}
}
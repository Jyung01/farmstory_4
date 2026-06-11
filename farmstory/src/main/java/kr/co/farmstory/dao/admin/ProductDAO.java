package kr.co.farmstory.dao.admin;

import java.util.ArrayList;
import java.util.List;

import kr.co.farmstory.dto.admin.ProductDTO;
import kr.co.farmstory.util.DBHelper;
import kr.co.farmstory.util.SQL.ProductSQL;

public class ProductDAO extends DBHelper {
	
	public static ProductDAO instance = new ProductDAO();
	public static ProductDAO getInstance() {
		return instance;
	}
	private ProductDAO() {};
	
	// 상품 등록
	public void insert(ProductDTO dto) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(ProductSQL.INSERT_PRODUCT);
			psmt.setString(1, dto.getCate());
			psmt.setString(2, dto.getProdName());
			psmt.setInt(3, dto.getPrice());
			psmt.setInt(4, dto.getDiscount());
			psmt.setInt(5, dto.getPoint());
			psmt.setInt(6, dto.getDelivery());
			psmt.setInt(7, dto.getStock());
			psmt.setString(8, dto.getDiscript());
			psmt.setString(9, dto.getThumb());
			psmt.setString(10, dto.getInfo());
			psmt.setString(11, dto.getDetail());
			psmt.executeUpdate();
			closeAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 상품 전체 조회
	public List<ProductDTO> selectAll(int start, int count) {
		List<ProductDTO> dtoList = new ArrayList<>();
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(ProductSQL.SELECT_ALL_PRODUCT);
			psmt.setInt(1, start);
			psmt.setInt(2, count);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setProdNo(rs.getInt(1));
				dto.setCate(rs.getString(2));
				dto.setProdName(rs.getString(3));
				dto.setPrice(rs.getInt(4));
				dto.setDiscount(rs.getInt(5));
				dto.setPoint(rs.getInt(6));
				dto.setDelivery(rs.getInt(7));
				dto.setStock(rs.getInt(8));
				dto.setDiscript(rs.getString(9));
				dto.setThumb(rs.getString(10));
				dto.setRegDate(rs.getString(11));
				dto.setInfo(rs.getString(12));
				dto.setDetail(rs.getString(13));
				dtoList.add(dto);
			}
			closeAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}
	
	// 상품 갯수 조회
	public int selectCount() {
		int total = 0;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(ProductSQL.SELECT_COUNT_PRODUCT);
			if(rs.next()) {
				total = rs.getInt(1);
			}
			closeAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}
	
	// 상품 삭제
	public void delete(String prodNo) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(ProductSQL.DELETE_PRODUCT);
			psmt.setString(1, prodNo);
			psmt.executeUpdate();
			closeAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

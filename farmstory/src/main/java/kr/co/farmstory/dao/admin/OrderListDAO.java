package kr.co.farmstory.dao.admin;

import java.util.ArrayList;
import java.util.List;

import kr.co.farmstory.dto.orderItem.OrderItemDTO;
import kr.co.farmstory.util.DBHelper;
import kr.co.farmstory.util.SQL.ProductSQL;

public class OrderListDAO extends DBHelper {
	public static OrderListDAO instance = new OrderListDAO();
	public static OrderListDAO getInstance() {
		return instance;
	}
	private OrderListDAO() {}
	
	public List<OrderItemDTO> selectAll(int start, int count) {
		List<OrderItemDTO> dtoList = new ArrayList<>();
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(ProductSQL.SELECT_PRODUCT_JOIN_ORDER);
			psmt.setInt(1, start);
			psmt.setInt(2, count);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				OrderItemDTO dto = new OrderItemDTO();
				dto.setItemNo(rs.getInt(1));
				dto.setOrderNo(rs.getInt(2));
				dto.setProdName(rs.getString(3));
				dto.setPrice(rs.getInt(4));
				dto.setCount(rs.getInt(5));
				dto.setDelivery(rs.getInt(6));
				dto.setTotalPrice(rs.getInt(7));
				dto.setName(rs.getString(8));
				dto.setRegDate(rs.getString(9));
				dtoList.add(dto);
			}
			closeAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}
	
	// 주문 갯수 조회
	public int selectCount() {
		int total = 0;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(ProductSQL.SELECT_COUNT_ORDER);
			if(rs.next()) {
				total = rs.getInt(1);
			}
			closeAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}
	
	// 주문 삭제
	public void delete(String itemNo) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(ProductSQL.DELETE_ORDER);
			psmt.setString(1, itemNo);
			psmt.executeUpdate();
			closeAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}

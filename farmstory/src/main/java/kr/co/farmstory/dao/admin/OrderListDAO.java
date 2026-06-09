package kr.co.farmstory.dao.admin;

import java.sql.ResultSet;
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
	
	public List<OrderItemDTO> selectAll() {
		List<OrderItemDTO> dtoList = new ArrayList<>();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(ProductSQL.SELECT_PRODUCT_JOIN_ORDER);
			while(rs.next()) {
				OrderItemDTO dto = new OrderItemDTO();
				dto.setOrderNo(rs.getInt(1));
				dto.setProdName(rs.getString(2));
				dto.setPrice(rs.getInt(3));
				dto.setCount(rs.getInt(4));
				dto.setDelivery(rs.getInt(5));
				dto.setTotalPrice(rs.getInt(6));
				dto.setName(rs.getString(7));
				dto.setRegDate(rs.getString(8));
				dtoList.add(dto);
			}
			closeAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}
	

}

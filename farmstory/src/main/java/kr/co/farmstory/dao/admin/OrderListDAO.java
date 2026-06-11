package kr.co.farmstory.dao.admin;

import java.util.ArrayList;
import java.util.List;

import kr.co.farmstory.dto.orderItem.OrderItemDTO;
import kr.co.farmstory.dto.orders.OrdersDTO;
import kr.co.farmstory.util.DBHelper;
import kr.co.farmstory.util.SQL.ProductSQL;

public class OrderListDAO extends DBHelper {
	public static OrderListDAO instance = new OrderListDAO();
	public static OrderListDAO getInstance() {
		return instance;
	}
	private OrderListDAO() {}
	
	// 주문 목록 조회
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
				dto.setProdNo(rs.getInt(10));
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
	
	// 주문 상세 조회
	public OrdersDTO select(String orderNo) {
		OrdersDTO dto = null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(ProductSQL.SELECT_ORDER_FOR_DETAIL);
			psmt.setString(1, orderNo);
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new OrdersDTO();
				dto.setOrderNo(rs.getInt(1));
				dto.setUserid(rs.getString(2));
				dto.setOrderer(rs.getString(3));
				dto.setHp(rs.getString(4));
				dto.setReceiver(rs.getString(5));
				dto.setReceiverHp(rs.getString(6));
				dto.setZip(rs.getString(7));
				dto.setAddr1(rs.getString(8));
				dto.setAddr2(rs.getString(9));
				dto.setUsedPoint(rs.getInt(10));
				dto.setSavePoint(rs.getInt(11));
				dto.setDelivery(rs.getInt(12));
				dto.setTotalPrice(rs.getInt(13));
				dto.setPayment(rs.getString(14));
				dto.setMemo(rs.getString(15));
				dto.setStatus(rs.getString(16));
				dto.setRegDate(rs.getString(17));
			}
			
			closeAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	
	public List<OrderItemDTO> selectProd(String orderNo) {
		List<OrderItemDTO> dtoList = new ArrayList<>();
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(ProductSQL.SELECT_PRODUCT_FOR_DETAIL);
			psmt.setString(1, orderNo);
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				OrderItemDTO dto = new OrderItemDTO();
				dto.setThumb(rs.getString(1));
				dto.setProdName(rs.getString(2));
				dto.setCate(rs.getString(3));
				dto.setCount(rs.getInt(4));
				dto.setPrice(rs.getInt(5));
				dtoList.add(dto);
			}
			
			closeAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}

}

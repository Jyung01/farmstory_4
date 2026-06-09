package kr.co.farmstory.service.admin;

import java.util.List;

import kr.co.farmstory.dao.admin.OrderListDAO;
import kr.co.farmstory.dto.orderItem.OrderItemDTO;

public enum OrderListService {
	INSTANCE;
	
	OrderListDAO dao = OrderListDAO.getInstance();
	
	public List<OrderItemDTO> findAll() {
		return dao.selectAll();
	}
}

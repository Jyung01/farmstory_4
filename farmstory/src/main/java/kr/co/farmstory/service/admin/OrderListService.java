package kr.co.farmstory.service.admin;

import java.util.List;

import kr.co.farmstory.dao.admin.OrderListDAO;
import kr.co.farmstory.dto.orderItem.OrderItemDTO;
import kr.co.farmstory.dto.page.PageGroupDTO;

public enum OrderListService {
	INSTANCE;
	
	OrderListDAO dao = OrderListDAO.getInstance();
	
	// 주문 전체 조회
	public List<OrderItemDTO> findAll(int start, int count) {
		return dao.selectAll(start, count);
	}
	
	// 주문 갯수 조회
	public int getCount() {
		return dao.selectCount();
	}
	
	// 주문 삭제
	public void remove(String itemNo) {
		dao.delete(itemNo);
	}
		
	// 현재 페이지 번호
	public int getCurrentPage(String page) {
		int currentPage = 1;
	    if(page != null) {
	        currentPage = Integer.parseInt(page);
	    }
		return currentPage;
	}

	// 마지막 페이지 번호
	public int getLastPage(int total) {
		  int lastPage = (int) Math.ceil(total / 10.0);
		return lastPage;
	}
	
	// 현재 페이지 그룹 
	public PageGroupDTO getPageGroup(int currentPage, int lastPage) {
		int pageGroup = (currentPage - 1) / 10 + 1;
		int pageGroupStart = (pageGroup - 1) * 10 + 1;
		int pageGroupLast = pageGroup * 10;
		
		if(pageGroupLast > lastPage) {
			pageGroupLast = lastPage;
		}
		
		return new PageGroupDTO(pageGroupStart, pageGroupLast);
	}
	
	
	// 현재 페이지의 목록 시작번호
	public int getListStartNo(int currentPage, int total) {
		int listStartNo = (currentPage - 1) * 10 + 1;
		return total - listStartNo;
	}
	
	// 목록 10개 limit
	public int getStart(int currentPage) {
		return (currentPage - 1) * 10;
	}
}

package kr.co.farmstory.service.orders;

import java.util.List;
import kr.co.farmstory.dao.orders.OrdersDAO;
import kr.co.farmstory.dto.orders.OrdersDTO;
import kr.co.farmstory.dto.page.PageGroupDTO;
import kr.co.farmstory.dto.orderItem.OrderItemDTO;

public enum OrdersService {

    INSTANCE;

    private OrdersDAO dao = OrdersDAO.getInstance();

    public int insertOrder(OrdersDTO dto) {
        return dao.insertOrder(dto);
    }

    public int insertOrderItem(OrderItemDTO dto) {
        return dao.insertOrderItem(dto);
    }

    public int updateUserUsePoint(int usedPoint, String userid) {
        return dao.updateUserUsePoint(usedPoint, userid);
    }
    
    public int deleteOrderedCart(int cartNo) {
        return dao.deleteOrderedCart(cartNo);
    }
    

    public boolean receiveAndProcessOrder(OrdersDTO ordersDTO, List<OrderItemDTO> itemDTOList, String[] cartNoArr) {
        
        int orderNo = dao.insertOrder(ordersDTO);
        
        if (orderNo > 0) {

            for (OrderItemDTO item : itemDTOList) {
                item.setOrderNo(orderNo);
                dao.insertOrderItem(item);
            }
            
            if (ordersDTO.getUsedPoint() > 0) {
                dao.updateUserUsePoint(ordersDTO.getUsedPoint(), ordersDTO.getUserid());
            }

            if (cartNoArr != null) {
                for (String cartNoStr : cartNoArr) {
                    if (cartNoStr != null && !cartNoStr.trim().isEmpty()) {
                        int cartNo = Integer.parseInt(cartNoStr.trim());
                        dao.deleteOrderedCart(cartNo);
                    }
                }
            }
            
            return true; 
        }
        return false; 
    }
    
    public List<OrdersDTO> selectOrdersList(String userid) {
        return dao.selectOrdersList(userid);
    }
    
    public OrdersDTO selectOrder(int orderNo) {
        return dao.selectOrder(orderNo);
    }

    public List<OrderItemDTO> selectOrderItems(int orderNo) {
        return dao.selectOrderItems(orderNo);
    }
    
    public int selectCountOrders(String userid) {
        return dao.selectCountOrders(userid);
    }

    public List<OrdersDTO> selectOrdersList(String userid, int start) {
        return dao.selectOrdersList(userid, start);
    }
    
    public int getCurrentPage(String page) {
		int currentPage = 1;
		if(page != null) {
			currentPage = Integer.parseInt(page);	
		}
		return currentPage;
	}
	
	public int getLastPageNum(int total) {
		int lastPageNum = (int) Math.ceil(total / 10.0);
		return lastPageNum;	
	}
	
	public int getStart(int currentPage) {
		return (currentPage - 1) * 10;
	}
	
	public int getCurrentStartNum(int total, int currentPage) {
		int start = (currentPage - 1) * 10;
		return total - start;
	}
	
	public PageGroupDTO getCurrentPageGroup(int currentPage, int lastPageNum) {
		int currentPageGroup = (int) Math.ceil(currentPage / 10.0);
		int pageGroupStart = (currentPageGroup - 1) * 10 + 1;
		int pageGroupEnd = currentPageGroup * 10;
		
		if(pageGroupEnd > lastPageNum) {
			pageGroupEnd = lastPageNum;			
		}
		return new PageGroupDTO(pageGroupStart, pageGroupEnd);	
	}
}
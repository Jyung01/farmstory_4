package kr.co.farmstory.service.orders;

import java.util.List;
import kr.co.farmstory.dao.orders.OrdersDAO;
import kr.co.farmstory.dto.orders.OrdersDTO;
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
}
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
    
    /**
     * 컨트롤러(99번째 줄) 에러 해결을 위해 String[] cartNoArr를 파라미터에 명확히 추가했습니다.
     */
    public boolean receiveAndProcessOrder(OrdersDTO ordersDTO, List<OrderItemDTO> itemDTOList, String[] cartNoArr) {
        
        // 1. orders 테이블 삽입 후 주문번호 가져오기
        int orderNo = dao.insertOrder(ordersDTO);
        
        if (orderNo > 0) {
            
            // 2. order_item 테이블 상품 개수만큼 루프 돌며 삽입
            for (OrderItemDTO item : itemDTOList) {
                item.setOrderNo(orderNo);
                dao.insertOrderItem(item);
            }
            
            // 3. 포인트 차감 처리
            if (ordersDTO.getUsedPoint() > 0) {
                dao.updateUserUsePoint(ordersDTO.getUsedPoint(), ordersDTO.getUserid());
            }
            
            // 4. 주문 완료된 장바구니 품목들 비우기
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
}
package kr.co.farmstory.dao.orders;

import java.sql.Statement;
import kr.co.farmstory.dto.orders.OrdersDTO;
import kr.co.farmstory.dto.orderItem.OrderItemDTO;
import kr.co.farmstory.util.DBHelper;
import kr.co.farmstory.util.SQL.OrdersSQL;

public class OrdersDAO extends DBHelper {

    private static OrdersDAO instance = new OrdersDAO();
    public static OrdersDAO getInstance() {
        return instance;
    }
    
    private OrdersDAO() {}

    public int insertOrder(OrdersDTO dto) {
        int generatedOrderNo = 0;
        try {
            conn = getConnection();
            
            psmt = conn.prepareStatement(OrdersSQL.INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
            psmt.setString(1, dto.getUserid());
            psmt.setString(2, dto.getOrderer());
            psmt.setString(3, dto.getHp());
            psmt.setString(4, dto.getReceiver());
            psmt.setString(5, dto.getReceiverHp());
            psmt.setString(6, dto.getZip());
            psmt.setString(7, dto.getAddr1());
            psmt.setString(8, dto.getAddr2());
            psmt.setInt(9, dto.getUsedPoint());
            psmt.setInt(10, dto.getSavePoint());
            psmt.setInt(11, dto.getDelivery());
            psmt.setInt(12, dto.getTotalPrice());
            psmt.setString(13, dto.getPayment());
            psmt.setString(14, dto.getMemo());
            
            psmt.executeUpdate();

            rs = psmt.getGeneratedKeys();
            if (rs.next()) {
                generatedOrderNo = rs.getInt(1);
            }
            
            closeAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generatedOrderNo;
    }


    public int insertOrderItem(OrderItemDTO dto) {
        int result = 0;
        try {
            conn = getConnection();
            psmt = conn.prepareStatement(OrdersSQL.INSERT_ORDER_ITEM);
            psmt.setInt(1, dto.getOrderNo());
            psmt.setInt(2, dto.getProdNo());
            psmt.setInt(3, dto.getCount());
            psmt.setInt(4, dto.getPrice());
            
            result = psmt.executeUpdate();
            closeAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int updateUserUsePoint(int usedPoint, String userid) {
        int result = 0;
        try {
            conn = getConnection();
            psmt = conn.prepareStatement(OrdersSQL.UPDATE_USER_USE_POINT);
            psmt.setInt(1, usedPoint);
            psmt.setString(2, userid);
            
            result = psmt.executeUpdate();
            closeAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public int deleteOrderedCart(int cartNo) {
        int result = 0;
        try {
            conn = getConnection();
            psmt = conn.prepareStatement(OrdersSQL.DELETE_ORDERED_CART);
            psmt.setInt(1, cartNo);
            
            result = psmt.executeUpdate();
            closeAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
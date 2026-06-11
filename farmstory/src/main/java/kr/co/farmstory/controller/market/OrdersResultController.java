package kr.co.farmstory.controller.market;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.farmstory.dto.orderItem.OrderItemDTO;
import kr.co.farmstory.dto.orders.OrdersDTO;
import kr.co.farmstory.dto.user.UserDTO;
import kr.co.farmstory.service.orders.OrdersService;

@WebServlet("/market/ordersResult.do")
public class OrdersResultController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/market/cart.do");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        
        HttpSession session = req.getSession();
        UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");

        if (sessUser == null) {
            resp.sendRedirect(req.getContextPath() + "/user/login.do"); 
            return;
        }

        // 0. market.js 전송 가상 폼에서 넘겨준 장바구니 번호배열 낚아채기 선언 완료
        String[] cartNoArr = req.getParameterValues("cartNo");
        
        // 1. 메인 주문 DTO 바인딩
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.setUserid(req.getParameter("userid"));
        ordersDTO.setOrderer(req.getParameter("orderer"));
        ordersDTO.setHp(req.getParameter("ordererHp"));
        ordersDTO.setReceiver(req.getParameter("receiver"));
        ordersDTO.setReceiverHp(req.getParameter("receiverHp"));
        ordersDTO.setZip(req.getParameter("zip"));
        ordersDTO.setAddr1(req.getParameter("addr1"));
        ordersDTO.setAddr2(req.getParameter("addr2"));
        ordersDTO.setPayment(req.getParameter("payment"));
        ordersDTO.setMemo(req.getParameter("memo"));

        int usedPoint = req.getParameter("usedPoint") != null ? Integer.parseInt(req.getParameter("usedPoint")) : 0;
        int savePoint = req.getParameter("savePoint") != null ? Integer.parseInt(req.getParameter("savePoint")) : 0;
        int delivery  = req.getParameter("delivery") != null ? Integer.parseInt(req.getParameter("delivery")) : 0;
        
        int totalPrice = 0;
        if(req.getParameter("totalPrice") != null) {
            totalPrice = Integer.parseInt(req.getParameter("totalPrice"));
        }
        
        ordersDTO.setUsedPoint(usedPoint);
        ordersDTO.setSavePoint(savePoint);
        ordersDTO.setDelivery(delivery);
        ordersDTO.setTotalPrice(totalPrice);

        // 2. 상세 상품 목록 수집
        String[] prodNoArr = req.getParameterValues("prodNo");
        String[] countArr  = req.getParameterValues("count");
        String[] priceArr  = req.getParameterValues("price");

        List<OrderItemDTO> itemDTOList = new ArrayList<>();
        if (prodNoArr != null) {
            for (int i = 0; i < prodNoArr.length; i++) {
                OrderItemDTO itemDTO = new OrderItemDTO();
                itemDTO.setProdNo(Integer.parseInt(prodNoArr[i]));
                
                if (countArr != null && countArr.length > i) {
                    itemDTO.setCount(Integer.parseInt(countArr[i]));
                }
                
                int itemPrice = (priceArr != null && priceArr.length > i) ? Integer.parseInt(priceArr[i]) : 0;
                itemDTO.setPrice(itemPrice);

                itemDTOList.add(itemDTO);
            }
        }

        boolean isSuccess = OrdersService.INSTANCE.receiveAndProcessOrder(ordersDTO, itemDTOList, cartNoArr);

        if (isSuccess) {
            resp.sendRedirect(req.getContextPath() + "/myinfo/ordered.do");
        } else {
            resp.sendRedirect(req.getContextPath() + "/market/cart.do?error=order_failed");
        }
    }
}
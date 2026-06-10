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
import kr.co.farmstory.dto.cart.CartDTO;
import kr.co.farmstory.dto.user.UserDTO;
import kr.co.farmstory.service.market.MarketService;

@WebServlet("/market/order.do")
public class OrdersController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private MarketService service = MarketService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 주문 페이지는 보통 장바구니에서 POST로만 진입해야 하므로, 
        // GET으로 직접 접속 시 장바구니 페이지로 돌려보내는 것이 안전합니다.
    	
    	List<CartDTO> cartList = MarketService.INSTANCE.selectCartList(user.getUserid());
        req.setAttribute("cartList", cartList);
    	
        resp.sendRedirect(req.getContextPath() + "/market/cart.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDTO user = (UserDTO) session.getAttribute("sessUser");

        // 1. 로그인 여부 체크
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/test/login.do"); 
            return;
        }

        // 2. cart.jsp에서 체크되어 넘어온 cartNo 배열 수신
        String[] cartNoArr = req.getParameterValues("cartNo");
        
        List<CartDTO> orderList = new ArrayList<>();
        
        if (cartNoArr != null && cartNoArr.length > 0) {
            try {
                // 방법 A: service에 배열을 통째로 넘겨 한 번에 IN 쿼리로 조회 (추천)
                // 예: SELECT * FROM cart WHERE cartNo IN (12, 13, 14);
               // orderList = service.selectCartListByCartNo(cartNoArr);
                
                /* // 만약 위 메서드가 없다면 임시로 반복문을 돌려 하나씩 가져올 수도 있습니다.
                for (String cartNoStr : cartNoArr) {
                    int cartNo = Integer.parseInt(cartNoStr);
                    CartDTO dto = service.selectCart(cartNo); // 개별 조회 메서드
                    if(dto != null) {
                        orderList.add(dto);
                    }
                }
                */
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 3. 주문서 화면에 뿌려줄 선택된 상품 리스트 저장
        req.setAttribute("orderList", orderList);
        
        // 로그인한 유저 정보도 주문서(주문자 명, 연락처, 주소 등)에 기본값으로 채우기 위해 보냅니다.
        req.setAttribute("sessUser", user); 

        // 4. 주문서 작성 화면(JSP)으로 포워드 처리
        // 파일명을 order.jsp 혹은 orders.jsp 중 프로젝트 실제 파일명과 정확히 맞춰주세요.
        req.getRequestDispatcher("/WEB-INF/views/Market/order.jsp").forward(req, resp);
    }
}
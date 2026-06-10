package kr.co.farmstory.controller.market;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/market/cart.do")
public class CartController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private MarketService service = MarketService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDTO user = (UserDTO) session.getAttribute("sessUser");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/test/login.do"); 
            return;
        }

        List<CartDTO> cartList = MarketService.INSTANCE.selectCartList(user.getUserid());
        req.setAttribute("cartList", cartList);

        req.getRequestDispatcher("/WEB-INF/views/Market/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDTO user = (UserDTO) session.getAttribute("sessUser");

        // 1. 로그인 여부 체크
        if (user == null) {
            resp.setContentType("application/json;charset=UTF-8");
            resp.getWriter().print("{'result': 'NOT_LOGGED_IN'}".replace("'", "\""));
            return;
        }

        String userid = user.getUserid();
        
        // 파라미터 수신
        String cartNoStr = req.getParameter("cartNo");
        String isCartPage = req.getParameter("isCartPage");
        String checkOri = req.getParameter("checkOri");
        String confirmed = req.getParameter("confirmed");
        String prodNo = req.getParameter("prodNo");
        String count = req.getParameter("count");

        // ========================================================
        // 분기 1: 장바구니 [선택 삭제] 요청 (cartNo 파라미터가 들어온 경우)
        // ========================================================
        if (cartNoStr != null && !cartNoStr.isEmpty()) {
            resp.setContentType("text/plain;charset=UTF-8");
            int result = 0;
            try {
                int cartNo = Integer.parseInt(cartNoStr);
                System.out.println("======> CartController로 들어온 삭제 요청 cartNo: " + cartNo);
                
                service.deleteCart(cartNo); // DB 삭제 실행
                
                result = 1; // 에러 없이 성공 시 1
                System.out.println("======> DB 삭제 성공!");
            } catch (Exception e) {
                System.out.println("======> DB 삭제 실패 (에러 발생)");
                e.printStackTrace();
            }
            resp.getWriter().print(result);
            return; // 삭제 처리 후 종료
        }

        // ========================================================
        // 분기 2: 장바구니 페이지에서 [수량 변경] 요청
        // ========================================================
        if ("true".equals(isCartPage)) {
            resp.setContentType("application/json;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            
            int pNo = Integer.parseInt(prodNo);
            int qty = Integer.parseInt(count);

            List<CartDTO> currentCart = MarketService.INSTANCE.selectCartList(userid);
            int currentCount = 0;
            if (currentCart != null) {
                for (CartDTO c : currentCart) {
                    if (c.getProdNo() == pNo) {
                        currentCount = c.getCount();
                        break;
                    }
                }
            }

            if (MarketService.INSTANCE.checkStockLack(userid, pNo, (qty - currentCount)) > 0) {
                out.print("{'result': 'LACK'}".replace("'", "\""));
                return;
            }

            int result = MarketService.INSTANCE.updateCartCount2(qty, userid, pNo);

            if (result > 0) {
                out.print("{'result': 'SUCCESS'}".replace("'", "\""));
            } else {
                out.print("{'result': 'FAILED'}".replace("'", "\""));
            }
            return; 
        }

        // ========================================================
        // 분기 3: 일반 상품 상세보기 페이지에서 [장바구니 담기] 요청
        // ========================================================
        if ("true".equals(checkOri) || "true".equals(confirmed)) {
            resp.setContentType("application/json;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            
            int pNo = Integer.parseInt(prodNo);
            int qty = Integer.parseInt(count);
            
            if (MarketService.INSTANCE.checkSoldOut(userid, pNo) > 0) {
                out.print("{'result': 'SOLDOUT'}".replace("'", "\""));
                return;
            }
            
            if (MarketService.INSTANCE.checkStockLack(userid, pNo, qty) > 0) {
                out.print("{'result': 'LACK'}".replace("'", "\""));
                return;
            }

            if ("true".equals(checkOri)) {
                if (MarketService.INSTANCE.checkDuplicateCart(userid, pNo) > 0) {
                    out.print("{'result': 'DUPLICATE'}".replace("'", "\""));
                    return;
                }
            }

            if (MarketService.INSTANCE.checkDuplicateCart(userid, pNo) > 0) {
                MarketService.INSTANCE.updateCartCount(qty, userid, pNo);
            } else {
                CartDTO dto = new CartDTO();
                dto.setUserid(userid);
                dto.setProdNo(pNo);
                dto.setCount(qty);
                MarketService.INSTANCE.insertCart(dto);
            }

            out.print("{'result': 'SUCCESS'}".replace("'", "\""));
            return;
        }
    }
}
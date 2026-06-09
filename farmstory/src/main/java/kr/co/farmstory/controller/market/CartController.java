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

        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        if (user == null) {
            out.print("{'result': 'NOT_LOGGED_IN'}".replace("'", "\""));
            return;
        }

        String prodNo = req.getParameter("prodNo");
        String count = req.getParameter("count");
        String isCartPage = req.getParameter("isCartPage");
        
        String checkOri = req.getParameter("checkOri");
        String confirmed = req.getParameter("confirmed");

        String userid = user.getUserid();
        int pNo = Integer.parseInt(prodNo);
        int qty = Integer.parseInt(count);

        if ("true".equals(isCartPage)) {
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

        if ("true".equals(checkOri) || "true".equals(confirmed)) {
            
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
        }
    }
}
package kr.co.farmstory.controller.market;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.farmstory.dto.page.PageGroupDTO;
import kr.co.farmstory.dto.orders.OrdersDTO;
import kr.co.farmstory.dto.user.UserDTO;
import kr.co.farmstory.service.orders.OrdersService;

@WebServlet("/user/ordered.do")
public class OrderListController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private OrdersService service = OrdersService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");
        if (sessUser == null) {
            resp.sendRedirect(req.getContextPath() + "/user/login.do");
            return;
        }

        String pg = req.getParameter("page");
        int currentPage = service.getCurrentPage(pg);
        int start = service.getStart(currentPage);

        int total = service.selectCountOrders(sessUser.getUserid());

        List<OrdersDTO> ordersList = service.selectOrdersList(sessUser.getUserid(), start);

        int lastPageNum = service.getLastPageNum(total);
        PageGroupDTO pageGroup = service.getCurrentPageGroup(currentPage, lastPageNum);

        req.setAttribute("ordersList", ordersList);
        req.setAttribute("totalCount", total);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("lastPageNum", lastPageNum);
        req.setAttribute("pageGroup", pageGroup);

        req.getRequestDispatcher("/WEB-INF/views/myinfo/ordered.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
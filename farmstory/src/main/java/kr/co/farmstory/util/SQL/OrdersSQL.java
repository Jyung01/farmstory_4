package kr.co.farmstory.util.SQL;

public class OrdersSQL {
	
		//장바구니 주문 상품
		public static final String SELECT_ORDER_PRODUCT = "SELECT a.*, b.prodName, b.price, b.discount, b.point, b.thumb, b.cate, b.stock "
											            + "FROM cart AS a JOIN product AS b ON a.prodNo = b.prodNo "
											            + "WHERE a.cartNo = ?";
		
		//주문 삽입 - 주문테이블
		public static final String INSERT_ORDER = "INSERT INTO orders SET"
												+ " userid = ?,"
												+ " orderer = ?,"
												+ " hp = ?,"
												+ " receiver = ?,"
												+ " receiverHp = ?,"
												+ " zip = ?,"
												+ " addr1 = ?,"
												+ " addr2 = ?,"
												+ " usedPoint = ?,"
												+ " savePoint = ?,"
												+ " delivery = ?,"
												+ " totalPrice = ?,"
												+ " payment = ?,"
												+ " memo = ?,"
												+ " regDate = NOW()";
		
		//주문 삽입 - 주문상품테이블
		public static final String INSERT_ORDER_ITEM = "INSERT INTO order_item SET"
													   + " orderNo = ?,"
													   + " prodNo  = ?,"
													   + " count   = ?,"
													   + " price   = ?";
		//포인트변경
		public static final String UPDATE_USER_USE_POINT ="UPDATE user SET point = point - ? WHERE userid = ?";
		
		//주문자데이터 가져오기
		public static final String SELECT_ORDER_USER = "SELECT name, hp, point, zip, addr1, addr2 from user where userid=?";
		
		//주문상품삭제
		public static final String DELETE_ORDERED_CART = "DELETE FROM cart WHERE cartNo = ?";
		
		//주문내역보기
		public static final String SELECT_ORDERS_LIST = "SELECT "
													    + "    o.orderNo, "
													    + "    o.regDate, "
													    + "    o.totalPrice, "
													    + "    o.orderer, "
													    + "    CASE "
													    + "        WHEN COUNT(oi.itemNo) > 1 THEN CONCAT(MIN(p.prodName), ' 외 ', COUNT(oi.itemNo) - 1, '건') "
													    + "        ELSE MIN(p.prodName) "
													    + "    END AS displayProdName, "
													    + "    MIN(p.thumb) AS displayThumb, "
													    + "    SUM(oi.count) AS totalCount "
													    + "FROM orders AS o "
													    + "JOIN order_item AS oi ON o.orderNo = oi.orderNo "
													    + "JOIN product AS p     ON oi.prodNo = p.prodNo "
													    + "WHERE o.userid = ? "
													    + "GROUP BY o.orderNo "
													    + "ORDER BY o.orderNo DESC LIMIT ?, 10";
		
		public static final String SELECT_COUNT_ORDERS = "SELECT COUNT(*) FROM `orders` WHERE `userid` = ?";
		
		// 상세확인
		public static final String SELECT_ORDER = 
		      "SELECT orderNo, userid, orderer, hp, receiver, receiverHp, zip, addr1, addr2, "
		    + "       usedPoint, savePoint, delivery, totalPrice, payment, memo, status, regDate "
		    + "FROM orders WHERE orderNo = ?";

		public static final String SELECT_ORDER_ITEMS = 
		      "SELECT oi.itemNo, oi.orderNo, oi.prodNo, oi.count, oi.price, p.prodName "
		    + "FROM order_item AS oi "
		    + "JOIN product AS p ON oi.prodNo = p.prodNo "
		    + "WHERE oi.orderNo = ?";
		
}

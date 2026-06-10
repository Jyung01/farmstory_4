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
}

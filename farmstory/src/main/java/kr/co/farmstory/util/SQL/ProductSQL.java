package kr.co.farmstory.util.SQL;

public class ProductSQL {
	// 관리자 상품 등록
	public static final String INSERT_PRODUCT = "INSERT INTO product SET "
												+ "cate=?, "
												+ "prodName=?, "
												+ "price=?, "
												+ "discount=?, "
												+ "point=?, "
												+ "delivery=?, "
												+ "stock=?, "
												+ "descript=?, "
												+ "thumb=?, "
												+ "info=?, "
												+ "detail=?, "
												+ "regDate=NOW()";
	
	// 관리자 상품 목록
	public static final String SELECT_ALL_PRODUCT = "SELECT * FROM product";

	// 관리자 주문 목록
	public static final String SELECT_PRODUCT_JOIN_ORDER = "SELECT i.orderNo, p.prodName, i.price, i.count,  o.delivery, o.totalPrice, u.name , o.regDate "
															+ "FROM order_item AS i "
															+ "JOIN product AS p ON i.prodNo = p.prodNo "
															+ "JOIN orders AS o ON i.orderNo = o.orderNo "
															+ "JOIN user AS u ON o.userid = u.userid"; 
	
}

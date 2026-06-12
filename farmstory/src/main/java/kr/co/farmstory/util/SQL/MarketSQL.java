package kr.co.farmstory.util.SQL;

public class MarketSQL {
	//전체 조회
	public static final String SELECT_MARKET_PRODUCT = "SELECT prodNo, cate, prodName, price, discount, point, thumb FROM `product` ORDER BY `regDate` DESC LIMIT ?, 5";
	public static final String SELECT_MARKET_PRODUCT_COUNT = "SELECT COUNT(*) FROM `product`";
	
	//카테고리별 조회
	public static final String SELECT_MARKET_CATE = "SELECT prodNo, cate, prodName, price, discount, point, thumb FROM `product` WHERE `cate` = ? ORDER BY `regDate` DESC LIMIT ?, 5";
	public static final String SELECT_MARKET_CATE_COUNT = "SELECT COUNT(*) FROM `product` WHERE `cate` = ?";
	
	//상세조회
	public static final String SELECT_PRODUCT_DETAIL = "SELECT * FROM `product` WHERE `prodNo`= ?";
	
	//장바구니
	public static final String INSERT_CART = "INSERT INTO `cart` SET "
								+ "userid = ?, "
								+ "prodNo = ?, "
								+ "count = ?, "
								+ "regDate = NOW()";
	
	//동일상품 추가구매
	public static final String UPDATE_CART = "UPDATE cart SET count = count + ? WHERE userid = ? and prodNo = ?";
	public static final String SELECT_DUPLICATE_CART_PRODUCT = "SELECT COUNT(*) FROM cart WHERE userid = ? AND prodNo = ?";
	
	
	//장바구니 뿌리기 + 주문
	public static final String SELECT_CART = "SELECT c.*, p.stock, p.cate, p.prodName, p.thumb, p.discount, p.point, p.price, (p.price - (p.price * p.discount / 100)) * c.count AS total "
											+ "FROM cart AS c JOIN product AS p "
											+ "ON c.prodNo = p.prodNo WHERE c.userid = ?";
	
	//수량 변경
	public static final String UPDATE_CART_COUNT = "UPDATE cart SET count = ? WHERE userid = ? and prodNo = ?";
	
	//품절체크
	public static final String SELECT_CHECK_SOLDOUT = "SELECT COUNT(*) FROM cart AS c "
												+ "JOIN product AS p ON c.prodNo = p.prodNo "
												+ "WHERE c.userid = ? AND c.prodNo = ? AND p.stock <= 0";
	
	//재고체크
	public static final String SELECT_CHECK_STOCK = "SELECT COUNT(*) FROM cart AS c " 
													+ "JOIN product AS p ON c.prodNo = p.prodNo " 
													+ "WHERE c.userid = ? AND c.prodNo = ? AND (c.count + ?) > p.stock";

	
	//장바구니 삭제
	public static final String DELETE_CART = "DELETE FROM cart WHERE cartNo = ?";
	
	//바로구매
	public static final String SELECT_PRODUCT_FOR_BUY_NOW = "SELECT * FROM product WHERE prodNo = ?";
	
	//메인페이지 상품 노출
	public static final String SELECT_BEST_PRODUCT = "SELECT prodNo, thumb, cate, prodName, price, discount, (price - (price * discount / 100)) AS sale_price FROM `product` ORDER BY regDate DESC LIMIT 0, 6";
}
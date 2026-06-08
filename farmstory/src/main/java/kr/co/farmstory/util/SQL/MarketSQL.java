package kr.co.farmstory.util.SQL;

public class MarketSQL {
	//전체 조회
	public static final String SELECT_MARKET_PRODUCT = "SELECT prodNo, cate, prodName, price, discount, point, thumb FROM `product` LIMIT ?, 5";
	public static final String SELECT_MARKET_PRODUCT_COUNT = "SELECT COUNT(*) FROM `product`";
	
	//카테고리별 조회
	public static final String SELECT_MARKET_CATE = "SELECT prodNo, cate, prodName, price, discount, point, thumb FROM `product` WHERE `cate` = ? LIMIT ?, 5";
	public static final String SELECT_MARKET_CATE_COUNT = "SELECT COUNT(*) FROM `product` WHERE `cate` = ?";
	
	//상세조회
	public static final String SELECT_PRODUCT_DETAIL = "SELECT * FROM `product` WHERE `prodNo`= ?";
	
	//장바구니
	public static final String INSERT_CART = "INSERT INTO `cart` SET "
								            + "userid = ?, "
								            + "prodNo = ?, "
								            + "count = ?, "
								            + "regDate = NOW()";
	
	public static final String SELECT_CART = "SELECT c.cartNo, c.userid, c.prodNo, c.count, p.cate, p.prodName, p.thumb, p.discount, p.point, p.price, (p.price - (p.price * p.discount / 100)) * c.count AS total "
											+ "FROM cart AS c JOIN product AS p "
											+ "ON c.prodNo = p.prodNo WHERE c.userid = ?";
}
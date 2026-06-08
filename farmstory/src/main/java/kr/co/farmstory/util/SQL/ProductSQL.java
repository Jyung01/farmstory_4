package kr.co.farmstory.util.SQL;

public class ProductSQL {
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
	
	public static final String SELECT_ALL_PRODUCT = "SELECT * FROM product";
}

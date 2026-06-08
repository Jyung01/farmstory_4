package kr.co.farmstory.util.SQL;

public class ArticleSQL {
	
	// 게시판
	public static final String INSERT_ARTICLE =  "INSERT INTO article "
											      + "(groupName, cate, title, content, writer, regip, fileCount) "
											      + "VALUES (?, ?, ?, ?, ?, ?, ?)";
	
	public static final String SELECT_ALL_ARTICLES = "SELECT * FROM article "
													+ "WHERE groupName=? "
													+ "AND cate=? "
													+ "ORDER BY ano DESC";
	
	
	// 파일
	public static final String INSERT_FILE = "INSERT INTO file "
											      + "(ano, ofname, sfname) "
											      + "VALUES (?, ?, ?)";
	
	
}

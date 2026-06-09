package kr.co.farmstory.util.SQL;

public class ArticleSQL {
	
	// 게시판
	public static final String INSERT_ARTICLE =  "INSERT INTO article "
											      + "(groupName, cate, title, content, writer, regip, fileCount) "
											      + "VALUES (?, ?, ?, ?, ?, ?, ?)";
	
	public static final String SELECT_ALL_BASE ="SELECT a.*, u.nick "
											      + "FROM article AS a "
											      + "JOIN user AS u "
											      + "ON a.writer = u.userid "
											      + "WHERE a.groupName=? "
											      + "AND a.cate=? ";

	
	public static final String SELECT_ARTICLE = "SELECT a.*, u.nick "
											      + "FROM article AS a "
											      + "JOIN user AS u "
											      + "ON a.writer = u.userid "
											      + "WHERE ano=?";
	
	public static final String UPDATE_HIT ="UPDATE article "
											      + "SET hit = hit + 1 "
											      + "WHERE ano=?";
	
	
	public static final String SELECT_COUNT_BASE = "SELECT COUNT(*) "
												      + "FROM article AS a "
												      + "JOIN user AS u "
												      + "ON a.writer = u.userid "
												      + "WHERE a.groupName=? "
												      + "AND a.cate=? ";
	
	public static final String ORDER_LIMIT = "ORDER BY a.ano DESC "
												+ "LIMIT ?, 10";
	
	public static final String SEARCH_TITLE = "AND a.title LIKE ? ";

	public static final String SEARCH_CONTENT = "AND a.content LIKE ? ";

	public static final String SEARCH_WRITER = "AND u.nick LIKE ? ";
	
	
	// 파일
	public static final String INSERT_FILE = "INSERT INTO file "
											      + "(ano, ofname, sfname) "
											      + "VALUES (?, ?, ?)";
	
	public static final String SELECT_FILES = "SELECT * FROM file WHERE ano=?";
	
	public static final String SELECT_FILE = "SELECT * FROM file WHERE fno=?";

	public static final String UPDATE_DOWNLOAD = "UPDATE file SET download = download + 1 WHERE fno=?";
	
}

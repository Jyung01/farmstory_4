package kr.co.farmstory.util.SQL;

public class ArticleSQL {
	// ------------------------------
	// 게시판
	// ------------------------------
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
	
	public static final String UPDATE_ARTICLE = "UPDATE article SET title=?, content=? WHERE ano=?";
	
	public static final String UPDATE_FILE_COUNT = "UPDATE article SET fileCount=? WHERE ano=?";
	
	public static final String DELETE_ARTICLE = "DELETE FROM article WHERE ano = ?";
	
	public static final String SELECT_LATEST = "SELECT ano, groupName, cate, title, wdate FROM article"
												+ " WHERE groupName=? AND cate=? ORDER BY ano DESC LIMIT 5";
	
	// ------------------------------
	// 파일
	// ------------------------------
	public static final String INSERT_FILE = "INSERT INTO file "
											      + "(ano, ofname, sfname) "
											      + "VALUES (?, ?, ?)";
	
	public static final String SELECT_FILES = "SELECT * FROM file WHERE ano=?";
	
	public static final String SELECT_FILE = "SELECT * FROM file WHERE fno=?";

	public static final String UPDATE_DOWNLOAD = "UPDATE file SET download = download + 1 WHERE fno=?";
	
	public static final String DELETE_FILE = "DELETE FROM file WHERE fno=?";

	public static final String COUNT_FILES = "SELECT COUNT(*) FROM file WHERE ano=?";
	
	
	// ------------------------------
	// 댓글
	// ------------------------------
	
	public static final String INSERT_COMMENT = "INSERT INTO comment "
											      + "(parent, content, writer, regip) "
											      + "VALUES (?, ?, ?, ?)";
	
	public static final String UPDATE_COMMENT_COUNT = "UPDATE article "
													      + "SET commentCount = commentCount + 1 "
													      + "WHERE ano=?";
	
	public static final String SELECT_ALL_COMMENT = "SELECT c.*, u.nick "
												      + "FROM comment AS c "
												      + "JOIN user AS u "
												      + "ON c.writer = u.userid "
												      + "WHERE c.parent=? "
												      + "ORDER BY c.cno ASC";
	
	public static final String SELECT_COMMENT = "SELECT * FROM comment WHERE cno=?";
	
	public static final String UPDATE_COMMENT = "UPDATE comment SET content=? WHERE cno=?";
	
	public static final String DELETE_COMMENT = "DELETE FROM comment WHERE cno=?";
	
	public static final String DECREASE_COMMENT_COUNT = "UPDATE article SET commentCount = commentCount - 1 WHERE ano=?";
	
}

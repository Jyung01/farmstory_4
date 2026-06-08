package kr.co.farmstory.util.SQL;

public class ArticleSQL {
	public static final String INSERT_ARTICLE =  "INSERT INTO article "
											      + "(groupName, cate, title, content, writer, regip) "
											      + "VALUES (?, ?, ?, ?, ?, ?)";
}

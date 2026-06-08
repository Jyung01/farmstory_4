package kr.co.farmstory.dao.article;

import kr.co.farmstory.dto.article.ArticleDTO;
import kr.co.farmstory.util.DBHelper;
import kr.co.farmstory.util.SQL.ArticleSQL;

public class ArticleDAO extends DBHelper {
	
	// 싱글톤
	private static ArticleDAO instance = new ArticleDAO();
	public static ArticleDAO getInstance() {
		return instance;
	}
	private ArticleDAO() {}
	
	// 게시판 CRUD
	public void insert(ArticleDTO dto) {
		
		try {
			conn = getConnection();
			 
			psmt = conn.prepareStatement(ArticleSQL.INSERT_ARTICLE);
			psmt.setString(1, dto.getGroupName());
	        psmt.setString(2, dto.getCate());
	        psmt.setString(3, dto.getTitle());
	        psmt.setString(4, dto.getContent());
	        psmt.setString(5, dto.getWriter());
	        psmt.setString(6, dto.getRegip());
	        
	        psmt.executeUpdate();

	        closeAll();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
}

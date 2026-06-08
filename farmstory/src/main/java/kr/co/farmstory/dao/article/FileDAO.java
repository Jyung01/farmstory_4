package kr.co.farmstory.dao.article;

import kr.co.farmstory.dto.article.FileDTO;
import kr.co.farmstory.util.DBHelper;
import kr.co.farmstory.util.SQL.ArticleSQL;

public class FileDAO extends DBHelper {
	
	// 싱글톤
	private static FileDAO instance = new FileDAO();
	public static FileDAO getInstance() {
		return instance;
	}
	private FileDAO() {}
	
	// 게시판 CRUD
	public void insert(FileDTO dto) {
		
		try {
			conn = getConnection();
			 
			psmt = conn.prepareStatement(ArticleSQL.INSERT_FILE);
			psmt.setInt(1, dto.getAno());
			psmt.setString(2, dto.getOfname());
			psmt.setString(3, dto.getSfname());
			
	        psmt.executeUpdate();

	        closeAll();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
}

package kr.co.farmstory.dao.article;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

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
	public int insert(ArticleDTO dto) {
		
		int ano = 0;
		
		try {
			conn = getConnection();
			 
			psmt = conn.prepareStatement(ArticleSQL.INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
			psmt.setString(1, dto.getGroupName());
	        psmt.setString(2, dto.getCate());
	        psmt.setString(3, dto.getTitle());
	        psmt.setString(4, dto.getContent());
	        psmt.setString(5, dto.getWriter());
	        psmt.setString(6, dto.getRegip());
	        psmt.setInt(7, dto.getFileCount());
	        
	        psmt.executeUpdate();
	        
	        rs = psmt.getGeneratedKeys();
	        
	        if(rs.next()) {
	        	ano = rs.getInt(1);
	        }

	        closeAll();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ano;
	}
	
	public List<ArticleDTO> selectAll(String groupName, String cate) {
		List<ArticleDTO> dtoList = new ArrayList<>();
		
		try {
			conn = getConnection();
			 
			psmt = conn.prepareStatement(ArticleSQL.SELECT_ALL_ARTICLES);
			
			psmt.setString(1, groupName);
	        psmt.setString(2, cate);
	        
	        rs = psmt.executeQuery();
	        
	        while(rs.next()) {

	            ArticleDTO dto =new ArticleDTO();

	            dto.setAno(rs.getInt("ano"));
	            dto.setTitle(rs.getString("title"));
	            dto.setWriter(rs.getString("writer"));
	            dto.setWdate(rs.getString("wdate"));
	            dto.setHit(rs.getInt("hit"));
	            dto.setCommentCount(rs.getInt("commentCount"));
	            dtoList.add(dto);
	        }

	        


	        closeAll();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return dtoList;
	}
}

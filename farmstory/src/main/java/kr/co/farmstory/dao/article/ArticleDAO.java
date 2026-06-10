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
	
	// 글 목록 조회 list
	public List<ArticleDTO> selectAll(String groupName, String cate, int start, String searchType, String keyword) {
		List<ArticleDTO> dtoList = new ArrayList<>();
		
		try {
			conn = getConnection();
			 
			String sql = ArticleSQL.SELECT_ALL_BASE;
			
			if(keyword != null && !keyword.equals("")) {
	            if("title".equals(searchType)) sql += ArticleSQL.SEARCH_TITLE;
	            else if("content".equals(searchType)) sql += ArticleSQL.SEARCH_CONTENT;
	            else if("writer".equals(searchType)) sql += ArticleSQL.SEARCH_WRITER;
	        }
			
			sql += ArticleSQL.ORDER_LIMIT;
	        psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, groupName);
	        psmt.setString(2, cate);
	        if(keyword != null && !keyword.equals("")) {
	            psmt.setString(3, "%" + keyword + "%");
	            psmt.setInt(4, start);
	        } else {
	            psmt.setInt(3, start);
	        }
	        
	        rs = psmt.executeQuery();
	        
	        while(rs.next()) {

	            ArticleDTO dto =new ArticleDTO();

	            dto.setAno(rs.getInt("ano"));
	            dto.setTitle(rs.getString("title"));
	            dto.setWriter(rs.getString("writer"));
	            dto.setWdate(rs.getString("wdate"));
	            dto.setHit(rs.getInt("hit"));
	            dto.setCommentCount(rs.getInt("commentCount"));
	            dto.setNick(rs.getString("nick"));
	            dtoList.add(dto);
	        }
	        closeAll();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dtoList;
	}
	
	// view
	public ArticleDTO select(int ano) {

	    ArticleDTO dto = null;

	    try {

	        conn = getConnection();

	        psmt = conn.prepareStatement(ArticleSQL.SELECT_ARTICLE);

	        psmt.setInt(1, ano);

	        rs = psmt.executeQuery();

	        if(rs.next()) {
	            dto = new ArticleDTO();
	            dto.setAno(rs.getInt("ano"));
	            dto.setTitle(rs.getString("title"));
	            dto.setContent(rs.getString("content"));
	            dto.setWriter(rs.getString("writer"));
	            dto.setNick(rs.getString("nick"));
	            dto.setHit(rs.getInt("hit"));
	            dto.setWdate(rs.getString("wdate"));
	        }

	        closeAll();

	    } catch (Exception e) {

	        e.printStackTrace();
	    }

	    return dto;
	}
	
	// 글 수정 modify
	public void update(ArticleDTO dto) {

	    try {
	        conn = getConnection();

	        psmt = conn.prepareStatement(ArticleSQL.UPDATE_ARTICLE);
	        psmt.setString(1, dto.getTitle());
	        psmt.setString(2, dto.getContent());
	        psmt.setInt(3, dto.getAno());

	        psmt.executeUpdate();

	        closeAll();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	// 글 수정시 파일 갯수 다시 계산
	public void updateFileCount(int ano, int fileCount) {

	    try {
	        conn = getConnection();

	        psmt = conn.prepareStatement(ArticleSQL.UPDATE_FILE_COUNT);
	        psmt.setInt(1, fileCount);
	        psmt.setInt(2, ano);

	        psmt.executeUpdate();

	        closeAll();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	// 글 삭제 delete
	public void delete(int ano) {
		try {
			conn = getConnection();
	        psmt = conn.prepareStatement(ArticleSQL.DELETE_ARTICLE);
	        psmt.setInt(1, ano);
	        psmt.executeUpdate();
	        
	        closeAll();
	        
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 조회수 증가
	public void updateHit(int ano) {

	    try {
	        conn = getConnection();
	        psmt = conn.prepareStatement(ArticleSQL.UPDATE_HIT);
	        psmt.setInt(1, ano);
	        psmt.executeUpdate();
	        
	        closeAll();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
	// 게시판 글 갯수 가져오기
	public int selectCount(String groupName, String cate, String searchType, String keyword) {

	    int total = 0;

	    try {
	        conn = getConnection();
	        
	        String sql = ArticleSQL.SELECT_COUNT_BASE;
	        
	        if(keyword != null && !keyword.equals("")) {
	            if("title".equals(searchType)) sql += ArticleSQL.SEARCH_TITLE;
	            else if("content".equals(searchType)) sql += ArticleSQL.SEARCH_CONTENT;
	            else if("writer".equals(searchType)) sql += ArticleSQL.SEARCH_WRITER;
	        }
	        
	        psmt = conn.prepareStatement(sql);
	        psmt.setString(1, groupName);
	        psmt.setString(2, cate);
	        
	        if(keyword != null && !keyword.equals("")) {
	            psmt.setString(3, "%" + keyword + "%");
	        }

	        rs = psmt.executeQuery();

	        if(rs.next()) {
	            total = rs.getInt(1);
	        }

	        closeAll();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return total;
	}
}

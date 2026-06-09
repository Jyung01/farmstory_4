package kr.co.farmstory.dao.article;

import java.util.ArrayList;
import java.util.List;

import kr.co.farmstory.dto.article.CommentDTO;
import kr.co.farmstory.dto.article.FileDTO;
import kr.co.farmstory.util.DBHelper;
import kr.co.farmstory.util.SQL.ArticleSQL;

public class CommentDAO extends DBHelper {
	
	// 싱글톤
	private static CommentDAO instance = new CommentDAO();
	public static CommentDAO getInstance() {
		return instance;
	}
	private CommentDAO() {}
	
	// 댓글 가져오기
	public CommentDTO select(int cno) {

	    CommentDTO dto = null;

	    try {
	        conn = getConnection();

	        psmt = conn.prepareStatement(ArticleSQL.SELECT_COMMENT);
	        psmt.setInt(1, cno);

	        rs = psmt.executeQuery();

	        if(rs.next()) {
	            dto = new CommentDTO();
	            dto.setCno(rs.getInt("cno"));
	            dto.setParent(rs.getInt("parent"));
	            dto.setContent(rs.getString("content"));
	            dto.setWriter(rs.getString("writer"));
	        }

	        closeAll();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return dto;
	}
	
	
	// 댓글 등록
	public void insert(CommentDTO dto) {

	    try {

	        conn = getConnection();

	        psmt = conn.prepareStatement(ArticleSQL.INSERT_COMMENT);

	        psmt.setInt(1, dto.getParent());
	        psmt.setString(2, dto.getContent());
	        psmt.setString(3, dto.getWriter());
	        psmt.setString(4, dto.getRegip());

	        psmt.executeUpdate();

	        closeAll();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	// 댓글 증가
	public void updateCommentCount(int parent) {

	    try {

	        conn = getConnection();

	        psmt = conn.prepareStatement(ArticleSQL.UPDATE_COMMENT_COUNT);

	        psmt.setInt(1, parent);

	        psmt.executeUpdate();

	        closeAll();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	// 모든 댓글 가져오기
	public List<CommentDTO> selectAll(int parent) {

	    List<CommentDTO> dtoList = new ArrayList<>();

	    try {
	        conn = getConnection();

	        psmt = conn.prepareStatement(ArticleSQL.SELECT_ALL_COMMENT);
	        psmt.setInt(1, parent);

	        rs = psmt.executeQuery();

	        while(rs.next()) {
	            CommentDTO dto = new CommentDTO();

	            dto.setCno(rs.getInt("cno"));
	            dto.setParent(rs.getInt("parent"));
	            dto.setContent(rs.getString("content"));
	            dto.setWriter(rs.getString("writer"));
	            dto.setNick(rs.getString("nick"));
	            dto.setRegip(rs.getString("regip"));
	            dto.setWdate(rs.getString("wdate"));

	            dtoList.add(dto);
	        }

	        closeAll();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return dtoList;
	}
	
	// 댓글 수정
	public void update(CommentDTO dto) {

	    try {
	        conn = getConnection();

	        psmt = conn.prepareStatement(ArticleSQL.UPDATE_COMMENT);
	        psmt.setString(1, dto.getContent());
	        psmt.setInt(2, dto.getCno());

	        psmt.executeUpdate();

	        closeAll();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	// 댓글 삭제
	public void delete(int cno) {

	    try {
	        conn = getConnection();

	        psmt = conn.prepareStatement(ArticleSQL.DELETE_COMMENT);
	        psmt.setInt(1, cno);

	        psmt.executeUpdate();

	        closeAll();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	// 댓글 수 감소
	public void decreaseCommentCount(int parent) {

	    try {
	        conn = getConnection();

	        psmt = conn.prepareStatement(ArticleSQL.DECREASE_COMMENT_COUNT);
	        psmt.setInt(1, parent);

	        psmt.executeUpdate();

	        closeAll();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}

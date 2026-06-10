package kr.co.farmstory.service.article;

import java.util.List;

import kr.co.farmstory.dao.article.CommentDAO;
import kr.co.farmstory.dto.article.CommentDTO;
import kr.co.farmstory.dto.user.UserDTO;

public enum CommentService {
	
	// 열거 상수 객체(싱글톤)
	INSTANCE;
	
	// DAO 가져오기
	private CommentDAO dao = CommentDAO.getInstance();
	
	public void register(CommentDTO dto) {
	    dao.insert(dto);
	    dao.updateCommentCount(dto.getParent());
	}
	
	public List<CommentDTO> findAll(int parent) {
	    return dao.selectAll(parent);
	}
	
	public void modify(CommentDTO dto) {
	    dao.update(dto);
	}
	
	public void remove(int cno, int parent) {
	    dao.delete(cno);
	    dao.decreaseCommentCount(parent);
	}
	
	public CommentDTO findById(int cno) {
	    return dao.select(cno);
	}
	
	public boolean isOwnerOrAdmin(int cno, UserDTO sessUser) {

	    CommentDTO origin = dao.select(cno);

	    if(origin == null) {
	        return false;
	    }

	    if(sessUser.getUserid().equals(origin.getWriter())) {
	        return true;
	    }

	    if("admin".equals(sessUser.getRole())) {
	        return true;
	    }

	    return false;
	}
	
}

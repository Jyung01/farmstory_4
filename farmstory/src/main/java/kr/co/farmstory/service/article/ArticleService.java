package kr.co.farmstory.service.article;

import kr.co.farmstory.dao.article.ArticleDAO;
import kr.co.farmstory.dto.article.ArticleDTO;

public enum ArticleService {
	
	// 열거 상수 객체(싱글톤)
	INSTANCE;
	
	// DAO 가져오기
	private ArticleDAO dao = ArticleDAO.getInstance();
	
	public void register(ArticleDTO dto) {
		dao.insert(dto);
	}
}

package kr.co.farmstory.service.article;

import kr.co.farmstory.dao.article.FileDAO;
import kr.co.farmstory.dto.article.FileDTO;

public enum FileService {
	
	// 열거 상수 객체(싱글톤)
	INSTANCE;
	
	// DAO 가져오기
	private FileDAO dao = FileDAO.getInstance();
	
	public void register(FileDTO dto) {
		dao.insert(dto);
	}
}

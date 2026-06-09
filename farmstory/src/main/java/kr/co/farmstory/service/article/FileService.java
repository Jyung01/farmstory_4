package kr.co.farmstory.service.article;

import java.util.List;

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
	
	public List<FileDTO> findFiles(int ano) {
	    return dao.selectFiles(ano);
	}
	
	public FileDTO findFile(int fno) {
	    return dao.select(fno);
	}
	
	public void updateDownload(int fno) {
	    dao.updateDownload(fno);
	}
}

package kr.co.farmstory.service.article;

import java.util.List;

import kr.co.farmstory.dao.article.ArticleDAO;
import kr.co.farmstory.dto.article.ArticleDTO;
import kr.co.farmstory.dto.page.PageGroupDTO;

public enum ArticleService {
	
	// 열거 상수 객체(싱글톤)
	INSTANCE;
	
	// DAO 가져오기
	private ArticleDAO dao = ArticleDAO.getInstance();
	
	public int register(ArticleDTO dto) {
		return dao.insert(dto);
	}
	
	public List<ArticleDTO> findAll(String groupName, String cate, int start, String searchType, String keyword) {
		return dao.selectAll(groupName, cate, start, searchType, keyword);
	}
	
	// 현재 페이지 번호 계산
	public int getCurrentPage(String page) {

	    int currentPage = 1;

	    if(page != null) {
	        currentPage = Integer.parseInt(page);
	    }

	    return currentPage;
	}
	
	// 게시판 전체 글 개수 조회
	public int selectCount(String groupName, String cate, String searchType, String keyword) {
	    return dao.selectCount(groupName, cate, searchType, keyword);
	}
	
	// 목록 번호 출력용 시작 번호 계산
	public int getListNumStart(int total, int currentPage) {
	    return total - ((currentPage - 1) * 10);
	}
	
	// 페이지 그룹 계산 (1~10, 11~20 ...)
	public PageGroupDTO getCurrentPageGroup(int currentPage, int lastPageNum) {

	    int currentPageGroup =
	            (int)Math.ceil(currentPage / 10.0);

	    int start =
	            (currentPageGroup - 1) * 10 + 1;

	    int end =
	            currentPageGroup * 10;

	    if(end > lastPageNum) {
	        end = lastPageNum;
	    }

	    return new PageGroupDTO(start, end);
	}
	
	// LIMIT 시작 인덱스 계산
	public int getLimitStart(int currentPage) {
	    return (currentPage - 1) * 10;
	}
	
	// 마지막 페이지 번호 계산
	public int getLastPageNum(int total) {
	    return (int)Math.ceil(total / 10.0);
	}
	
	public void updateHit(int ano) {
	    dao.updateHit(ano);
	}
	
	public ArticleDTO findById(int ano) {
	    return dao.select(ano);
	}
}

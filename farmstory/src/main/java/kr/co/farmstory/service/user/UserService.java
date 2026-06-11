package kr.co.farmstory.service.user;

import java.util.List;

import kr.co.farmstory.dao.user.UserDAO;
import kr.co.farmstory.dto.page.PageGroupDTO;
import kr.co.farmstory.dto.user.UserDTO;

public enum UserService {
	INSTANCE;
	
	private UserDAO dao = new UserDAO();

	public int insertUser(UserDTO dto) {
		return dao.insertUser(dto);
	}
	
	public UserDTO selectUser(String userid) {
	    return dao.selectUser(userid);
	}
	
	public UserDTO selectUser(String userid, String pass) {
	    return dao.selectUser(userid, pass);
	}
	
	public int checkUser(String type, String value) {
		return dao.selectUserCheck(type, value);
	}
	
	public int updateLeaveDate(String userid) {
		return dao.updateLeaveDate(userid);
	}
	
	public int updateUser(UserDTO dto) {
	    return dao.updateUser(dto);
	}
	
	public int selectUserCheck(String type, String value) {
	    return dao.selectUserCheck(type, value);
	}
	
	public int updatePass(String userid, String pass) {
	    return dao.updatePass(userid, pass);
	}
	
	public String selectUserIdByNameEmail(String name, String email) {
		return dao.selectUserIdByNameEmail(name, email);
	}

	public String selectUserByNameEmail(String name, String email) {
		return dao.selectUserByNameEmail(name, email);
	}
	
	// =========================
	// 관리자 - 회원 목록 조회
	// =========================
	public List<UserDTO> selectAll(int start, int count) {
		return dao.selectAll(start, count);
	}
	
	// =========================
	// 관리자 - 회원 수 조회
	// =========================
	public int getCount() {
		return dao.selectCount();
	}
	
	// =========================
	// 관리자 - 현재 페이지 번호
	// =========================
	public int getCurrentPage(String page) {
		int currentPage = 1;
		
		if(page != null) {
			currentPage = Integer.parseInt(page);
		}
		
		return currentPage;
	}

	// =========================
	// 관리자 - 마지막 페이지 번호
	// =========================
	public int getLastPage(int total) {
		return (int) Math.ceil(total / 10.0);
	}
	
	// =========================
	// 관리자 - 현재 페이지 그룹
	// =========================
	public PageGroupDTO getPageGroup(int currentPage, int lastPage) {
		
		int pageGroup = (currentPage - 1) / 10 + 1;
		int pageGroupStart = (pageGroup - 1) * 10 + 1;
		int pageGroupLast = pageGroup * 10;
		
		if(pageGroupLast > lastPage) {
			pageGroupLast = lastPage;
		}
		
		return new PageGroupDTO(pageGroupStart, pageGroupLast);
	}
	
	// =========================
	// 관리자 - 현재 페이지의 목록 시작번호
	// =========================
	public int getListStartNo(int currentPage, int total) {
		
		int listStartNo = (currentPage - 1) * 10 + 1;
		return total - listStartNo;
	}
	
	// =========================
	// 관리자 - LIMIT 시작값
	// =========================
	public int getStart(int currentPage) {
		return (currentPage - 1) * 10;
	}
}
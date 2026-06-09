package kr.co.farmstory.service.user;

import java.util.List;

import kr.co.farmstory.dao.user.UserDAO;
import kr.co.farmstory.dto.user.UserDTO;


public enum UserService {
	INSTANCE;
	
	private UserDAO dao = new UserDAO();

	public int insertUser(UserDTO dto) {
		return dao.insertUser(dto);
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
	public List<UserDTO> selectAll() {
		return dao.selectAll();
	}
	
}

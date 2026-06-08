package kr.co.farmstory.dao.user;

import kr.co.farmstory.dto.user.UserDTO;
import kr.co.farmstory.util.DBHelper;
import kr.co.farmstory.util.SQL.UserSQL;

public class UserDAO extends DBHelper{
	public int insertUser(UserDTO dto) {
		int result = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(UserSQL.INSERT_USER);
			 psmt.setString(1, dto.getUserid());
		     psmt.setString(2, dto.getPass());
		     psmt.setString(3, dto.getName());
		     psmt.setString(4, dto.getNick());
		     psmt.setString(5, dto.getEmail());
		     psmt.setString(6, dto.getHp());
		     psmt.setString(7, dto.getZip());
		     psmt.setString(8, dto.getAddr1());
		     psmt.setString(9, dto.getAddr2());
		     psmt.setString(10, dto.getRegip());
			 result = psmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally {
			try { closeAll();} catch (Exception e) {e.printStackTrace();}
		}
		return result;
	}

	public UserDTO selectUser(String userid) {
		UserDTO dto = null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(UserSQL.SELECT_USER_LOGIN);
			psmt.setString(1, userid);
			rs = psmt.executeQuery();
			if(rs.next()) {
				dto = new UserDTO();
				dto.setUserid(rs.getString("userid"));
	            dto.setPass(rs.getString("pass"));
	            dto.setName(rs.getString("name"));
	            dto.setNick(rs.getString("nick"));
	            dto.setEmail(rs.getString("email"));
	            dto.setHp(rs.getString("hp"));
	            dto.setRole(rs.getString("role"));
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
	        try { closeAll(); } catch(Exception e) { e.printStackTrace(); }
	    }
	    return dto;
	}
	
	public int selectUserCheck(String type, String value) {
	    int result = 0;
	    try {
	        conn = getConnection();
	        if(type.equals("userid")) {
	            psmt = conn.prepareStatement(UserSQL.SELECT_USERID_CHECK);
	        } else if(type.equals("nick")) {
	            psmt = conn.prepareStatement(UserSQL.SELECT_NICK_CHECK);
	        }
	        psmt.setString(1, value);
	        rs = psmt.executeQuery();
	        if(rs.next()) {
	            result = 1;
	        }
	    } catch(Exception e) {
	        e.printStackTrace();
	    } finally {
	        try { closeAll(); } catch(Exception e) { e.printStackTrace(); }
	    }
	    return result;
	}
	
	
}




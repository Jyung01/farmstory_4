package kr.co.farmstory.dao.user;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.co.farmstory.dto.user.UserDTO;
import kr.co.farmstory.util.DBHelper;
import kr.co.farmstory.util.SQL.UserSQL;

public class UserDAO extends DBHelper {

    // =========================
    // 회원가입
    // =========================
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

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try { closeAll(); } catch(Exception e) { e.printStackTrace(); }
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


	public List<UserDTO> selectAll() {
		List<UserDTO> dtoList = new ArrayList<>();
		try {
	        conn = getConnection();
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(UserSQL.SELECT_USER_FOR_ADMIN);
	        while(rs.next()) {
	        	UserDTO dto = new UserDTO();
	        	dto.setUserid(rs.getString(1));
	        	dto.setName(rs.getString(2));
	        	dto.setNick(rs.getString(3));
	        	dto.setEmail(rs.getString(4));
	        	dto.setHp(rs.getString(5));
	        	dto.setRole(rs.getString(6));
	        	dto.setRegDate(rs.getString(7));
	        	dtoList.add(dto);
	        }
		} catch(Exception e) {
	        e.printStackTrace();
	    } finally {
	        try { closeAll(); } catch(Exception e) { e.printStackTrace(); }
	    }
	    return dtoList;
	}

    // =========================
    // 로그인
    // =========================
    public UserDTO selectUser(String userid, String pass) {

        UserDTO dto = null;

        try {
            conn = getConnection();
            psmt = conn.prepareStatement(UserSQL.SELECT_USER_LOGIN);

            psmt.setString(1, userid);
            psmt.setString(2, pass);

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
                dto.setZip(rs.getString("zip"));
                dto.setAddr1(rs.getString("addr1"));
                dto.setAddr2(rs.getString("addr2"));
                dto.setRegDate(rs.getString("regDate"));
            }

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try { closeAll(); } catch(Exception e) {}
        }

        return dto;
    }

    // =========================
    // 중복 체크 (아이디/닉네임/이메일)
    // =========================
    public int selectUserCheck(String type, String value) {
        int result = 0;

        try {
            conn = getConnection();

            if(type.equals("userid")) {
                psmt = conn.prepareStatement(UserSQL.SELECT_USERID_CHECK);
            } 
            else if(type.equals("nick")) {
                psmt = conn.prepareStatement(UserSQL.SELECT_NICK_CHECK);
            } 
            else if(type.equals("email")) {
                psmt = conn.prepareStatement(UserSQL.SELECT_EMAIL_CHECK);
            } 
            else {
                return 0;
            }

            psmt.setString(1, value);
            rs = psmt.executeQuery();

            if(rs.next()) {
                result = 1; // 이미 존재 (중복)
            }

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try { closeAll(); } catch(Exception e) {}
        }

        return result;
    }

    // =========================
    // 회원 탈퇴 (핵심 수정 완료)
    // =========================
    public int updateLeaveDate(String userid) {
        int result = 0;

        try {
            conn = getConnection();

            psmt = conn.prepareStatement(UserSQL.UPDATE_LEAVE_DATE);
            psmt.setString(1, userid);

            result = psmt.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try { closeAll(); } catch(Exception e) { e.printStackTrace(); }
        }

        return result;
    }

    // =========================
    // 회원정보 수정
    // =========================
    public int updateUser(UserDTO dto) {
        int result = 0;

        try {
            conn = getConnection();

            String sql = "UPDATE user SET "
                       + "name=?, nick=?, email=?, hp=?, zip=?, addr1=?, addr2=? "
                       + "WHERE userid=?";

            psmt = conn.prepareStatement(sql);

            psmt.setString(1, dto.getName());
            psmt.setString(2, dto.getNick());
            psmt.setString(3, dto.getEmail());
            psmt.setString(4, dto.getHp());
            psmt.setString(5, dto.getZip());
            psmt.setString(6, dto.getAddr1());
            psmt.setString(7, dto.getAddr2());
            psmt.setString(8, dto.getUserid());

            result = psmt.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try { closeAll(); } catch(Exception e) { e.printStackTrace(); }
        }

        return result;
    }
}
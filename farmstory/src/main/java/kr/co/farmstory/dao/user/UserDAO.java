package kr.co.farmstory.dao.user;

import java.util.ArrayList;
import java.util.List;

import kr.co.farmstory.dto.user.UserDTO;
import kr.co.farmstory.util.DBHelper;
import kr.co.farmstory.util.SQL.UserSQL;

public class UserDAO extends DBHelper {

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

    public int selectUserCheck(String type, String value) {
        int result = 0;
        try {
            conn = getConnection();

            if(type.equals("userid")) {
                psmt = conn.prepareStatement(UserSQL.SELECT_USERID_CHECK);
            } else if(type.equals("nick")) {
                psmt = conn.prepareStatement(UserSQL.SELECT_NICK_CHECK);
            } else if(type.equals("email")) {
                psmt = conn.prepareStatement(UserSQL.SELECT_EMAIL_CHECK);
            } else {
                return 0;
            }

            psmt.setString(1, value);
            rs = psmt.executeQuery();

            if(rs.next()) {
                result = 1;
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try { closeAll(); } catch(Exception e) {}
        }
        return result;
    }

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

    public int updateUser(UserDTO dto) {
        int result = 0;
        try {
            conn = getConnection();
            psmt = conn.prepareStatement(UserSQL.UPDATE_USER);
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

    public int updatePass(String userid, String pass) {
        int result = 0;
        try {
            conn = getConnection();
            psmt = conn.prepareStatement(UserSQL.UPDATE_PASS);
            psmt.setString(1, pass);
            psmt.setString(2, userid);
            result = psmt.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try { closeAll(); } catch(Exception e) { e.printStackTrace(); }
        }
        return result;
    }

    public String selectUserIdByNameEmail(String name, String email) {
        String userid = null;
        try {
            conn = getConnection();
            psmt = conn.prepareStatement(UserSQL.SELECT_USERID_BY_NAME_EMAIL);
            psmt.setString(1, name);
            psmt.setString(2, email);
            rs = psmt.executeQuery();

            if(rs.next()) {
                userid = rs.getString("userid");
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try { closeAll(); } catch(Exception e) { e.printStackTrace(); }
        }
        return userid;
    }

    public String selectUserByNameEmail(String name, String email) {
        String userid = null;
        try {
            conn = getConnection();
            psmt = conn.prepareStatement(UserSQL.SELECT_USERID_BY_NAME_EMAIL);
            psmt.setString(1, name);
            psmt.setString(2, email);
            rs = psmt.executeQuery();

            if(rs.next()) {
                userid = rs.getString("userid");
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try { closeAll(); } catch(Exception e) { e.printStackTrace(); }
        }
        return userid;
    }

    public int selectCount() {
        int total = 0;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(UserSQL.SELECT_COUNT_FOR_ADMIN);

            if(rs.next()) {
                total = rs.getInt(1);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try { closeAll(); } catch(Exception e) { e.printStackTrace(); }
        }
        return total;
    }

    public List<UserDTO> selectAll(int start, int count) {
        List<UserDTO> dtoList = new ArrayList<>();

        try {
            conn = getConnection();
            psmt = conn.prepareStatement(UserSQL.SELECT_USER_FOR_ADMIN);
            psmt.setInt(1, start);
            psmt.setInt(2, count);
            rs = psmt.executeQuery();

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
}
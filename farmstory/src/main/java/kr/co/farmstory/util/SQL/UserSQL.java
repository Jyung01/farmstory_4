package kr.co.farmstory.util.SQL;

public class UserSQL {
    // 회원가입
    public static final String INSERT_USER = 
        "INSERT INTO user (userid, pass, name, nick, email, hp, zip, addr1, addr2, regip) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    // 로그인
    public static final String SELECT_USER_LOGIN =
    		"SELECT * FROM user WHERE userid = ? AND leaveDate IS NULL";
    
    // 아이디 중복확인
    public static final String SELECT_USERID_CHECK = 
        "SELECT userid FROM user WHERE userid = ?";
    
    // 닉네임 중복확인
    public static final String SELECT_NICK_CHECK =
    	    "SELECT nick FROM user WHERE nick = ?";
    
    
    // 이메일 중복확인
    public static final String SELECT_EMAIL_CHECK =
        "SELECT email FROM user WHERE email = ?";
    
    // 회원 탈퇴
    public static final String UPDATE_LEAVE_DATE = 
    	"UPDATE user SET leaveDate = NOW() WHERE userid = ?";
    
    
    // 관리자 - 회원목록 조회
    public static final String SELECT_USER_FOR_ADMIN =
    	"SELECT userid, name, nick, email, hp, role, regDate FROM user";
    
    // 회원정보 수정
    public static final String UPDATE_USER =
    		 "UPDATE user SET name=?, nick=?, email=?, hp=?, zip=?, addr1=?, addr2=? WHERE userid=?";
    
    // 비밀번호 수정
    public static final String UPDATE_PASS =
        "UPDATE user SET pass=? WHERE userid=?";
    
 // 아이디 찾기
    public static final String SELECT_USERID_BY_NAME_EMAIL =
        "SELECT userid FROM user WHERE name=? AND email=?";
    
    // 비밀번호 찾기(이름 + 이메일로 유저 확인)
    public static final String SELECT_USER_BY_NAME_EMAIL = 
    		"SELECT userid FROM user WHERE name=? AND email=?";
    
    // 임시 비밀번호 업데이트
    public static final String UPDATE_TEMP_PASS=
    		"UPDATE user SET pass=? WHERE userid=?";
    
    
}
package kr.co.farmstory.util.SQL;

public class UserSQL {
    // 회원가입
    public static final String INSERT_USER = 
        "INSERT INTO user (userid, pass, name, nick, email, hp, zip, addr1, addr2, regip) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    // 로그인
    public static final String SELECT_USER_LOGIN = 
        "SELECT * FROM user WHERE userid = ?";
    
    // 아이디 중복확인
    public static final String SELECT_USERID_CHECK = 
        "SELECT userid FROM user WHERE userid = ?";
    
    // 닉네임 중복확인
    public static final String SELECT_NICK_CHECK = 
        "SELECT nick FROM user WHERE nick = ?";
    
    
    // 이메일 중복확인
    public static final String SELECT_EMAIL_CHECK =
        "SELECT email FROM user WHERE email = ?";
    
    
    // 관리자 - 회원목록 조회
    public static final String SELECT_USER_FOR_ADMIN =
    	"SELECT userid, name, nick, email, hp, role, regDate FROM user";
    
}
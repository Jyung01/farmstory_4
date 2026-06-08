package kr.co.farmstory.dao.terms;

import kr.co.farmstory.dto.terms.TermsDTO;
import kr.co.farmstory.util.DBHelper;
import kr.co.farmstory.util.SQL.TermsSQL;

public class TermsDAO extends DBHelper {
	
	public TermsDTO selectTerms() {
		TermsDTO dto = null;
		try {
			
			conn = getConnection();
			psmt = conn.prepareStatement(TermsSQL.SELECT_TERMS);
			rs = psmt.executeQuery();
			if(rs.next()) {
				dto = new TermsDTO();
				dto.setNo(rs.getInt("no"));
				dto.setBasic(rs.getString("basic"));
				dto.setPrivacy(rs.getString("privacy"));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {closeAll();} catch (Exception e) { e.printStackTrace();}
		}
		
		return dto;
	}
}

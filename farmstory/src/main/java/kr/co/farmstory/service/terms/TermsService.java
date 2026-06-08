package kr.co.farmstory.service.terms;

import kr.co.farmstory.dao.terms.TermsDAO;
import kr.co.farmstory.dto.terms.TermsDTO;

public enum TermsService {
	INSTANCE;
	
	
	private TermsDAO dao = new TermsDAO();
	
	public TermsDTO selectTerms() {
		return dao.selectTerms();
	}
	

}

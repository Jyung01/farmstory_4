package kr.co.farmstory.dto.admin;

public class FileDTO {
	private String ofname;
	private String sfname;
	
	
	public String getOfname() {
		return ofname;
	}
	
	public void setOfname(String ofname) {
		this.ofname = ofname;
	}
	
	public String getSfname() {
		return sfname;
	}
	
	public void setSfname(String sfname) {
		this.sfname = sfname;
	}

	@Override
	public String toString() {
		return "FileDTO [ofname=" + ofname + ", sfname=" + sfname + "]";
	}
	
}

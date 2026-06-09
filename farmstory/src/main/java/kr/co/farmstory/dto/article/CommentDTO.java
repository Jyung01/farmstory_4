package kr.co.farmstory.dto.article;

public class CommentDTO {
	private int cno;
	private int parent;
	private String content;
	private String writer;
	private String nick;
	private String regip;
	private String wdate;
	
	
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getRegip() {
		return regip;
	}
	public void setRegip(String regip) {
		this.regip = regip;
	}
	public String getWdate() {
		return wdate.substring(2, 16);
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	@Override
	public String toString() {
		return "CommentDTO [cno=" + cno + ", parent=" + parent + ", content=" + content + ", writer=" + writer
				+ ", nick=" + nick + ", regip=" + regip + ", wdate=" + wdate + "]";
	}
	
	
}

package kr.co.farmstory.dao.article;

import java.util.ArrayList;
import java.util.List;

import kr.co.farmstory.dto.article.FileDTO;
import kr.co.farmstory.util.DBHelper;
import kr.co.farmstory.util.SQL.ArticleSQL;

public class FileDAO extends DBHelper {
	
	// 싱글톤
	private static FileDAO instance = new FileDAO();
	public static FileDAO getInstance() {
		return instance;
	}
	private FileDAO() {}
	
	// 게시판 CRUD
	public void insert(FileDTO dto) {
		
		try {
			conn = getConnection();
			 
			psmt = conn.prepareStatement(ArticleSQL.INSERT_FILE);
			psmt.setInt(1, dto.getAno());
			psmt.setString(2, dto.getOfname());
			psmt.setString(3, dto.getSfname());
			
	        psmt.executeUpdate();

	        closeAll();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	public FileDTO select(int fno) {

	    FileDTO dto = null;

	    try {
	        conn = getConnection();
	        psmt = conn.prepareStatement(ArticleSQL.SELECT_FILE);
	        psmt.setInt(1, fno);
	        rs = psmt.executeQuery();

	        if(rs.next()) {
	            dto = new FileDTO();
	            dto.setFno(rs.getInt("fno"));
	            dto.setAno(rs.getInt("ano"));
	            dto.setOfname(rs.getString("ofname"));
	            dto.setSfname(rs.getString("sfname"));
	            dto.setDownload(rs.getInt("download"));
	        }

	        closeAll();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return dto;
	}
	
	public List<FileDTO> selectFiles(int ano) {

	    List<FileDTO> fileList = new ArrayList<>();

	    try {
	        conn = getConnection();
	        psmt = conn.prepareStatement(ArticleSQL.SELECT_FILES);
	        psmt.setInt(1, ano);
	        rs = psmt.executeQuery();

	        while(rs.next()) {
	            FileDTO dto = new FileDTO();
	            dto.setFno(rs.getInt("fno"));
	            dto.setAno(rs.getInt("ano"));
	            dto.setOfname(rs.getString("ofname"));
	            dto.setSfname(rs.getString("sfname"));
	            dto.setDownload(rs.getInt("download"));

	            fileList.add(dto);
	        }

	        closeAll();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return fileList;
	}
	
	public void updateDownload(int fno) {

	    try {
	        conn = getConnection();
	        psmt = conn.prepareStatement(ArticleSQL.UPDATE_DOWNLOAD);
	        psmt.setInt(1, fno);
	        psmt.executeUpdate();
	        closeAll();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}

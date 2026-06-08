//package kr.co.farmstory.dao;
//
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//
//import kr.co.farmstory.dto.admin.FileDTO;
//import kr.co.farmstory.util.DBHelper;
//import kr.co.farmstory.util.SQL.FileSQL;
//
//public class FileDAO extends DBHelper {
//	public static FileDAO instance = new FileDAO();
//	public static FileDAO getInstance() {
//		return instance;
//	}
//	private FileDAO() {};
//	
//	public void insert(FileDTO dto) {
//		try {
//			conn = getConnection();
//			psmt = conn.prepareStatement(FileSQL.INSERT_FILE);
//			psmt.setInt(1, dto.getAno());
//			psmt.setString(2, dto.getOfname());
//			psmt.setString(3, dto.getSfname());
//			psmt.setInt(4, dto.getDownload());
//			psmt.executeUpdate();
//			closeAll();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//}

package kr.co.farmstory.dao.event;

import java.util.ArrayList;
import java.util.List;

import kr.co.farmstory.dto.event.EventDTO;
import kr.co.farmstory.util.DBHelper;
import kr.co.farmstory.util.SQL.EventSQL;

public class EventDAO extends DBHelper {

    private static EventDAO instance = new EventDAO();

    public static EventDAO getInstance() {
        return instance;
    }

    private EventDAO() {}
    
    // 이벤트 추가
    public int insert(EventDTO dto) {

        int result = 0;

        try {
            conn = getConnection();

            psmt = conn.prepareStatement(EventSQL.INSERT_EVENT);
            psmt.setString(1, dto.getTitle());
            psmt.setString(2, dto.getStartDate());
            psmt.setString(3, dto.getEndDate());

            result = psmt.executeUpdate();

            closeAll();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    
    
    // 이벤트 목록 불러오기
    public List<EventDTO> selectAll() {

        List<EventDTO> dtoList = new ArrayList<>();

        try {
            conn = getConnection();

            psmt = conn.prepareStatement(EventSQL.SELECT_ALL_EVENT);
            rs = psmt.executeQuery();

            while(rs.next()) {
                EventDTO dto = new EventDTO();

                dto.setEventNo(rs.getInt("eventNo"));
                dto.setTitle(rs.getString("title"));
                dto.setStartDate(rs.getString("startDate"));
                dto.setEndDate(rs.getString("endDate"));
                dto.setRegDate(rs.getString("regDate"));

                dtoList.add(dto);
            }

            closeAll();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dtoList;
    }
    
    // 이벤트 삭제
    public int delete(int eventNo) {
    	
    	int result = 0;
    	
    	try {
            conn = getConnection();

            psmt = conn.prepareStatement(EventSQL.DELETE_EVENT);
            psmt.setInt(1, eventNo);

            result = psmt.executeUpdate();

            closeAll();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    
    // 이벤트 수정
    public int update(EventDTO dto) {
    	int result = 0;
    	
    	try {
            conn = getConnection();

            psmt = conn.prepareStatement(EventSQL.UPDATE_EVENT);
            psmt.setString(1, dto.getTitle());
            psmt.setString(2, dto.getStartDate());
            psmt.setString(3, dto.getEndDate());
            psmt.setInt(4, dto.getEventNo());

            result = psmt.executeUpdate();

            closeAll();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
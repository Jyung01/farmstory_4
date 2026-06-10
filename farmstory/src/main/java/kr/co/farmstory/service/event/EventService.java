package kr.co.farmstory.service.event;

import java.util.List;

import kr.co.farmstory.dao.event.EventDAO;
import kr.co.farmstory.dto.event.EventDTO;

public enum EventService {

    INSTANCE;

    private EventDAO dao = EventDAO.getInstance();

    public int register(EventDTO dto) {
        return dao.insert(dto);
    }

    public List<EventDTO> findAll() {
        return dao.selectAll();
    }
    
    public int remove(int eventNo) {
        return dao.delete(eventNo);
    }
    
    public int modify(EventDTO dto) {
    	return dao.update(dto);
    }
}
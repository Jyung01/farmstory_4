package kr.co.farmstory.util.SQL;

public class EventSQL {
	
	public static final String INSERT_EVENT = "INSERT INTO event "
									          + "(title, startDate, endDate) "
									          + "VALUES (?, ?, ?)";

    public static final String SELECT_ALL_EVENT = "SELECT * FROM event "
												+ "ORDER BY startDate ASC";
    
    public static final String DELETE_EVENT = "delete from event where eventNo = ?";
    
    public static final String UPDATE_EVENT = "UPDATE event set title = ?, startDate = ?, endDate = ? "
    											+ "where eventNo = ?";
}

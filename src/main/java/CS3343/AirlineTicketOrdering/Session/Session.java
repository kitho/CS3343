package CS3343.AirlineTicketOrdering.Session;

import java.util.HashMap;
import java.util.Map;

public class Session {
	
	private Map<String, Object> sessionMap;
	private static Session session;
	
	private Session(){
		sessionMap = new HashMap<String, Object>();
	}
	
	public static Session getInstance() {
		if(session == null)
			session = new Session();
		return session;
	}
	
	public void setAttribute(String key, Object value) {
		sessionMap.put(key, value);
	}
	
	public Object getAttribute(String key) {
		return sessionMap.get(key);
	}
	
	public void removeAttribute(String key){
		sessionMap.remove(key);
	}
	
	public void clear() {
		sessionMap.clear();
	}

}

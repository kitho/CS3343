package CS3343.AirlineTicketOrdering.Session;

import java.util.HashMap;
import java.util.Map;

public class Session {
	
	private Map<String, Object> processMap;
	private static Session process;
	
	private Session(){
		processMap = new HashMap<String, Object>();
	}
	
	public static Session getInstance() {
		if(process == null)
			process = new Session();
		return process;
	}
	
	public void setAttribute(String key, Object value) {
		processMap.put(key, value);
	}
	
	public Object getAttribute(String key) {
		return processMap.get(key);
	}
	
	public void removeAttribute(String key){
		processMap.remove(key);
	}
	
	public void clear() {
		processMap.clear();
	}

}

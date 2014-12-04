package CS3343.AirlineTicketOrdering.Session;

import java.util.HashMap;
import java.util.Map;

/**
 * The Session is used to store different objects with
 * specified key and value, singleton pattern is applied to
 * make sure there should be at most one session in the system
 */
public class Session {
	
	/** The process map to store key and value */
	private Map<String, Object> processMap;
	
	/** The only one session */
	private static Session process;
	
	/**
	 * Instantiates a new session.
	 */
	private Session(){
		processMap = new HashMap<String, Object>();
	}
	
	/**
	 * Gets the single instance of Session.
	 *
	 * @return single instance of Session
	 */
	public static Session getInstance() {
		if(process == null)
			process = new Session();
		return process;
	}
	
	/**
	 * Sets the attribute.
	 *
	 * @param key 
	 * 		  Session key
	 * @param value 
	 * 		  Session value
	 */
	public void setAttribute(String key, Object value) {
		processMap.put(key, value);
	}
	
	/**
	 * Gets the attribute.
	 *
	 * @param key
	 * 		  Session key
	 * @return value
	 * 		 Session value
	 */
	public Object getAttribute(String key) {
		return processMap.get(key);
	}
	
	/**
	 * Removes the attribute.
	 *
	 * @param key the key
	 */
	public void removeAttribute(String key){
		processMap.remove(key);
	}
	
	/**
	 * Clear all the stored attrbutes in the session object
	 */
	public void clear() {
		processMap.clear();
	}

}

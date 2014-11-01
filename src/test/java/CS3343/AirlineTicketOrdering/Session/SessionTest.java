package CS3343.AirlineTicketOrdering.Session;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SessionTest {
	
	@Test
	public void getInstanceTest() {
		Session session1 = Session.getInstance();
		Session session2 = Session.getInstance();
		
		assertThat(session1, is(session2));
	}

	@Test
	public void checkAttributeExistedTest(){
		Session session = Session.getInstance();
		
		String key= "key";
		String value = "ABC123";
		session.setAttribute(key, value);
		
		assertThat(value, is(session.getAttribute(key)));
	}
	
	@Test
	public void checkAttributeNotExistedTest(){
		Session session = Session.getInstance();
		
		String key= "key";
		String nonExistedKey = "nonExistedKey";
		String value = "ABC123";
		session.setAttribute(key, value);
		
		assertThat(value, is(not(session.getAttribute(nonExistedKey))));
		assertThat(null, is(session.getAttribute(nonExistedKey)));
	}
	
	@Test
	public void removeAttributeTest(){
		Session session = Session.getInstance();
		
		String key= "key";
		String value = "ABC123";
		session.setAttribute(key, value);
		assertThat(value, is(session.getAttribute(key)));
		
		session.removeAttribute(key);
		assertThat(null, is(session.getAttribute(key)));
	}
	
	@Test
	public void clearAllAttributeTest(){
		Session session = Session.getInstance();
		
		String key1= "key1";
		String value1 = "ABC123";
		
		String key2= "key2";
		String value2 = "DEF456";
		
		session.setAttribute(key1, value1);
		session.setAttribute(key2, value2);
		assertThat(value1, is(session.getAttribute(key1)));
		assertThat(value2, is(session.getAttribute(key2)));
		
		session.clear();
		assertThat(null, is(session.getAttribute(key1)));
		assertThat(null, is(session.getAttribute(key2)));
		
	}
}

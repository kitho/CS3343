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
	public void checkAttributeExisted(){
		Session session = Session.getInstance();
		
		String key= "key";
		String value = "ABC123";
		session.setAttribute(key, value);
		
		assertThat(value, is(session.getAttribute(key)));
	}
	
	@Test
	public void checkAttributeNotExisted(){
		Session session = Session.getInstance();
		
		String key= "key";
		String nonExistedKey = "nonExistedKey";
		String value = "ABC123";
		session.setAttribute(key, value);
		
		assertThat(value, is(not(session.getAttribute(nonExistedKey))));
		assertThat(null, is(session.getAttribute(nonExistedKey)));
	}
}

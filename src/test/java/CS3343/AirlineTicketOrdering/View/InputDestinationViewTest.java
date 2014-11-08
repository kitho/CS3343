package CS3343.AirlineTicketOrdering.View;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import CS3343.AirlineTicketOrdering.Session.Session;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class InputDestinationViewTest {
	
	private ByteArrayOutputStream outContent;
	private Session session;
	
	@Before
	public void setUp() {
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		session = Session.getInstance();
	}
	
	@Test
	public void displayTest() {	
		System.setIn(new ByteArrayInputStream("2014-01-01\nHong Kong\nTaiwan".getBytes()));
		View inputDestinationView = new InputDestinationView();
		inputDestinationView.display(session);
		
		assertThat("Please Input your depature date, deapture and destination to search\nDate (YYYY-MM-DD):DepatureDestination", is(outContent.toString()));
		
		System.setIn(System.in);
		System.setIn(System.in);
		System.setIn(System.in);
	}

}

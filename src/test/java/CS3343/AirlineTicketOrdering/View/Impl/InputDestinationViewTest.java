package CS3343.AirlineTicketOrdering.View.Impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;
import CS3343.AirlineTicketOrdering.View.Impl.InputDestinationView;
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
		assertThat("Please Input your depature date, deapture and destination to search\nDate (YYYY-MM-DD): Depature: Destination: ", is(outContent.toString()));
	}

}

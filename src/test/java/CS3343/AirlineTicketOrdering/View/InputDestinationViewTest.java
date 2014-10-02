package CS3343.AirlineTicketOrdering.View;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class InputDestinationViewTest {
	
	private ByteArrayOutputStream outContent;
	
	@Before
	public void setUp() {
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	}
	
	@Test
	public void displayTest() {
		System.setIn(new ByteArrayInputStream("2014-01-01".getBytes()));
//		System.setIn(new ByteArrayInputStream("Hong Kong".getBytes()));
//		System.setIn(new ByteArrayInputStream("Taiwan".getBytes()));
		
		AirlineTicketOrderingView inputDestinationView = new InputDestinationView();
		inputDestinationView.display();
				
		String welcomeString = "Welcome to Airline Ticket Ordering System";
		String dateString = "Date (YYYY-MM-DD): ";
		String depatureString = "Depature: ";
		String destinationString = "Destination: ";
		
		assertThat(welcomeString + "\n" + dateString, is(outContent.toString()));
//		assertThat(depatureString, is(outContent.toString()));
//		assertThat(destinationString, is(outContent.toString()));
		System.setIn(System.in);
	}

}

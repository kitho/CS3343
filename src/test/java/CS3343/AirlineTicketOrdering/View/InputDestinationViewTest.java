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
		System.setIn(new ByteArrayInputStream("2014-01-01 Hong Kong Taiwan".getBytes()));
		
		View inputDestinationView = new InputDestinationView();
		//inputDestinationView.display();
		
		assertThat("[Date (YYYY-MM-DD)] [Depature] [Destination]: ", is(outContent.toString()));
		System.setIn(System.in);
	}

}

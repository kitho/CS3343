package CS3343.AirlineTicketOrdering.View;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import CS3343.AirlineTicketOrdering.Session.Session;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class EnquireCreditCardViewTest {
	
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
		System.setIn(new ByteArrayInputStream("HSBC\nVISA\n1234-1234-1234-1234".getBytes()));
		View enquireCreditCardView = new EnquireCreditCardView();
		enquireCreditCardView.display(session);
		assertThat("Please input your credit card information\nBank: Type: Number: ", is(outContent.toString()));
	}

}

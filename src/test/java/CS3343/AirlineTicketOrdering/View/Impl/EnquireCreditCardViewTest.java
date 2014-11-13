package CS3343.AirlineTicketOrdering.View.Impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;
import CS3343.AirlineTicketOrdering.View.Impl.EnquireCreditCardView;
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
		Scanner scanner = new Scanner(System.in);
		
		View enquireCreditCardView = new EnquireCreditCardView(scanner);
		enquireCreditCardView.display(session);
		assertThat("Please input your credit card information\nBank: Type: Number: ", is(outContent.toString()));
	
		scanner.close();
	}

}

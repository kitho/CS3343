package CS3343.AirlineTicketOrdering.View.Impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import CS3343.AirlineTicketOrdering.Model.Flight;
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
	public void displayTest() throws IOException {	
		
		BufferedReader bufferedReader = org.mockito.Mockito.mock(BufferedReader.class);
		Mockito.when(bufferedReader.readLine()).thenReturn("2014-01-01").thenReturn("Hong Kong").thenReturn("Taiwan");
		
		View inputDestinationView = new InputDestinationView(bufferedReader);
		inputDestinationView.display(session);

		boolean checkOutPrint = outContent.toString().equals("Please Input your depature date, depature and destination to search\nDate (YYYY-MM-DD): Depature: Destination: ") || 
								outContent.toString().equals("Please Input your depature date, depature and destination to search\r\nDate (YYYY-MM-DD): Depature: Destination: ");

		assertThat(checkOutPrint, is(true));
		assertThat(((String)session.getAttribute("deapture")),is("Hong Kong"));
		assertThat(((String)session.getAttribute("destination")),is("Taiwan"));
		assertThat(((String)session.getAttribute("depatureDate")),is("2014-01-01"));
	}

}

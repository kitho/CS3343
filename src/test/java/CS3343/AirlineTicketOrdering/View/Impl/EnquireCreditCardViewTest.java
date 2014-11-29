package CS3343.AirlineTicketOrdering.View.Impl;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import CS3343.AirlineTicketOrdering.Model.CreditCard;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.Util.LineSeparatorUtil;
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
	public void displayTest() throws IOException {	
		
		BufferedReader bufferedReader = org.mockito.Mockito.mock(BufferedReader.class);
		View enquireCreditCardView = new EnquireCreditCardView(bufferedReader);
		Mockito.when(bufferedReader.readLine()).thenReturn("HSBC").thenReturn("VISA").thenReturn("1234-1234-1234-1234");

		enquireCreditCardView.display(session);
		
		String separator = LineSeparatorUtil.newLine();

		
		assertThat("Please input your credit card information"+separator+"Bank: Type: Number: " 
			, is(outContent.toString()));
		assertThat(((CreditCard)session.getAttribute("creditCard")).getBank(),is("HSBC"));
		assertThat(((CreditCard)session.getAttribute("creditCard")).getCreditCardType(),is("VISA"));
		assertThat(((CreditCard)session.getAttribute("creditCard")).getCreditCardNumber(),is("1234-1234-1234-1234"));
		
	}

}

package CS3343.AirlineTicketOrdering.View.Impl;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;

public class BaggageFeeCalculationViewTest {

	@Test
	public void testViewWithDisplayingFee() throws IOException {
		Session session = mock(Session.class);
		when(session.getAttribute("orgFreeUnit")).thenReturn(new HashMap<String, Float>());
		when(session.getAttribute("remainingFreeUnit")).thenReturn(new HashMap<String, Float>());
		when(session.getAttribute("basicBaggageFee")).thenReturn(-100f);
		when(session.getAttribute("extraBaggageFee")).thenReturn(-100f);
		when(session.getAttribute("petFee")).thenReturn(-100f);
		when(session.getAttribute("extraPetFee")).thenReturn(-100f);
		when(session.getAttribute("totalFee")).thenReturn(-400f);
		
		BufferedReader bufferedReader = mock(BufferedReader.class);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		View view = new BaggageFeeCalculationView(bufferedReader);
		view.display(session);
		
		boolean checkOutPrint = outContent.toString().equals("\nCalculated Baggage Fee Info:\r\nYou can enjoy       \t{}\r\nYour remaining unit \t{}\r\nBasic Baggage Fee   \t$-100.0\r\nExtra Baggage Fee   \t$-100.0\r\nBasic Pet Fee       \t$-100.0\r\nExtra Pet Fee       \t$-100.0\r\nTotal Baggage Fee   \t$-400.0\r\n") || 
				outContent.toString().equals("\nCalculated Baggage Fee Info:\nYou can enjoy       \t{}\nYour remaining unit \t{}\nBasic Baggage Fee   \t$-100.0\nExtra Baggage Fee   \t$-100.0\nBasic Pet Fee       \t$-100.0\nExtra Pet Fee       \t$-100.0\nTotal Baggage Fee   \t$-400.0\n");

		
		assertThat(checkOutPrint, is(true));

		
		outContent.reset();
	}

}
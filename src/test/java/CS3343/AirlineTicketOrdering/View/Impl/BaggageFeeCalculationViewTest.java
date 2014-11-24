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
		
		String separator = System.getProperty("line.separator");

			
		assertThat(""+separator+"Calculated Baggage Fee Info:"+separator+"You can enjoy       \t{}"+separator+"Your remaining unit \t{}"+separator+"Basic Baggage Fee   \t$-100.0"+separator+"Extra Baggage Fee   \t$-100.0"+separator+"Basic Pet Fee       \t$-100.0"+separator+"Extra Pet Fee       \t$-100.0"+separator+"Total Baggage Fee   \t$-400.0"+separator+""
				, is(outContent.toString()));

		
		outContent.reset();
	}

}
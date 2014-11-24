package CS3343.AirlineTicketOrdering.View.Impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import CS3343.AirlineTicketOrdering.Model.CreditCard;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Model.Order;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;
import CS3343.AirlineTicketOrdering.View.Impl.OrderConfirmationView;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.hamcrest.CoreMatchers.is;

public class OrderCompletionViewTest {
	
	private ByteArrayOutputStream outContent;
	private Session session;
	private String separator = System.getProperty("line.separator");
	
	@Before
	public void setUp() {
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		session = Session.getInstance();
	}
	
	@Test
	public void displayTestWithOrder() throws IOException {			
		Order order = mock(Order.class);
		order.setId(0);
		session.setAttribute("orders", order);
		
		View orderCompletionView = new OrderCompletionView();
		
		orderCompletionView.display(session);
		

		assertThat("Order Success "+separator+" Order Id: 0", 
				is(outContent.toString()));

	}
	
	@Test
	public void displayTestWithCancel() throws IOException {			
		Order order = null;
		session.setAttribute("orders", order);
		
		View orderCompletionView = new OrderCompletionView();
		
		orderCompletionView.display(session);
		
		boolean checkOutPrint = outContent.toString().equals("Order Canceled");

		assertThat(checkOutPrint, is(true));
	}

}

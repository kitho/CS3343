package CS3343.AirlineTicketOrdering.Controller.Impl;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import CS3343.AirlineTicketOrdering.Calculator.Calculator;
import CS3343.AirlineTicketOrdering.Controller.AirlineTicketOrderingController;
import CS3343.AirlineTicketOrdering.DataQuery.OrderQuery;
import CS3343.AirlineTicketOrdering.Discount.Discount;
import CS3343.AirlineTicketOrdering.Model.CreditCard;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Model.Order;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

public class OrderCompletionControllerTest {
	
	private SimpleDateFormat formatter;

	@Before
	public void setUp() throws IOException{
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	@Test
	public void testExecuteWithAnsYes() throws Exception {
		Session session = mock(Session.class);
		View view = mock(View.class);
		Discount discount = mock(Discount.class);
		Calculator calculator= mock(Calculator.class);
		CreditCard creditCard = mock(CreditCard.class);
		OrderQuery orderQuery = mock(OrderQuery.class);
		
		
		Flight flight = new Flight();
		flight.setAirline("Cathay Pacific Airways");
		flight.setFlightNumber("CP001");
		flight.setTravelClass("FIRST");
		flight.setDepature("Hong Kong");
		flight.setDestination("Taiwan");
		flight.setDepatureDateTime(formatter.parse("2014-01-01 14:30:00"));
		flight.setArrivalDateTime(formatter.parse("2014-01-01 17:30:00"));
		flight.setAvailable(30);
		flight.setOneWayPrice(2500.00);		
		List<Flight> flights = new ArrayList<Flight>();
		flights.add(flight);

		int numberOfTicket = 1;
		double[] discountOff = {0.2,0.4};
		double totalPrice = 100.0;
		
		when(session.getAttribute("creditCard")).thenReturn(creditCard);
		when(session.getAttribute("flights")).thenReturn(flights);
		when(session.getAttribute("numberOfTicket")).thenReturn(numberOfTicket);
		when(session.getAttribute("confirmed")).thenReturn("Yes");
		when(discount.getDiscount(anyList(), any(CreditCard.class))).thenReturn(discountOff);
		when(calculator.calculate(anyList(), anyInt(), any(double[].class))).thenReturn(totalPrice);
		when(orderQuery.getMaxOrderId()).thenReturn(0);
		
		AirlineTicketOrderingController orderConfirmationController = new OrderCompletionController(session, view,orderQuery);
		orderConfirmationController.setNext(null);
		orderConfirmationController.execute();
		
		Order order= new Order();
		order.setId(1);
		order.setFlight(flight);
		order.setNumberOfTicket(1);
		
		verify(session, times(1)).setAttribute("orders", order);
		verify(orderQuery, times(1)).newOrder(order);
	}
	
	@Test
	public void testExecuteWithAnsNo() throws Exception {
		Session session = mock(Session.class);
		View view = mock(View.class);
		Discount discount = mock(Discount.class);
		Calculator calculator= mock(Calculator.class);
		CreditCard creditCard = mock(CreditCard.class);
		OrderQuery orderQuery = mock(OrderQuery.class);
		
		
		Flight flight = new Flight();
		flight.setAirline("Cathay Pacific Airways");
		flight.setFlightNumber("CP001");
		flight.setTravelClass("FIRST");
		flight.setDepature("Hong Kong");
		flight.setDestination("Taiwan");
		flight.setDepatureDateTime(formatter.parse("2014-01-01 14:30:00"));
		flight.setArrivalDateTime(formatter.parse("2014-01-01 17:30:00"));
		flight.setAvailable(30);
		flight.setOneWayPrice(2500.00);		
		List<Flight> flights = new ArrayList<Flight>();
		flights.add(flight);

		int numberOfTicket = 1;
		double[] discountOff = {0.2,0.4};
		double totalPrice = 100.0;
		
		when(session.getAttribute("creditCard")).thenReturn(creditCard);
		when(session.getAttribute("flights")).thenReturn(flights);
		when(session.getAttribute("numberOfTicket")).thenReturn(numberOfTicket);
		when(session.getAttribute("confirmed")).thenReturn("No");
		when(discount.getDiscount(anyList(), any(CreditCard.class))).thenReturn(discountOff);
		when(calculator.calculate(anyList(), anyInt(), any(double[].class))).thenReturn(totalPrice);
		when(orderQuery.getMaxOrderId()).thenReturn(0);
		
		AirlineTicketOrderingController orderConfirmationController = new OrderCompletionController(session, view,orderQuery);
		orderConfirmationController.setNext(null);
		orderConfirmationController.execute();
		
		Order order= new Order();
		order.setId(1);
		order.setFlight(flight);
		order.setNumberOfTicket(1);
		
		verify(session, times(0)).setAttribute("orders", order);
		verify(orderQuery, times(0)).newOrder(order);
	}

}

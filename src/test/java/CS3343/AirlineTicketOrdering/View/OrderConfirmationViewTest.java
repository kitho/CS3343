package CS3343.AirlineTicketOrdering.View;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import CS3343.AirlineTicketOrdering.Model.CreditCard;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Session.Session;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class OrderConfirmationViewTest {
	
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
		System.setIn(new ByteArrayInputStream("Yes".getBytes()));
		CreditCard creditCard = new CreditCard();
		creditCard.setBank("HSBC");
		creditCard.setCreditCardType("VISA");
		creditCard.setCreditCardNumber("0000-0000-0000-0000");
		
		List<Flight> flights = new ArrayList<>();
		Flight flight = new Flight();
		flight.setAirline("AirLine");
		flight.setArrivalDateTime(new Date(1415472252));
		flight.setAvailable(100);
		flight.setDepature("Hong Kong");
		flight.setDepatureDateTime(new Date(1415482252));
		flight.setDestination("USA");
		flight.setFlightNumber("LE1234");
		flight.setOneWayPrice(10000.0);
		flight.setTravelClass("First Class");
		flights.add(flight);
				
		session.setAttribute("creditCard", creditCard);
		session.setAttribute("flights", flights);
		session.setAttribute("numberOfTicket", 1);
		session.setAttribute("totalPrice", 10000.0);
		
		View orderConfirmationView = new OrderConfirmationView();
		orderConfirmationView.display(session);
		assertThat("Here is your order detail:\n==========Payment Method==========\nBank: HSBC\nType: VISA\nNumber: 0000-0000-0000-0000\n\n==========Ticket Information==========\nAirline             FlightNumber        TravelClass         Depature            Destination         DepatureDateTime         ArrivalDateTime          Available           OneWayPrice\nAirLine             LE1234              First Class         Hong Kong           USA                 1970-01-17 05:11:22      1970-01-17 05:11:12      100                 10000.0\n\n======================================\nNumber of Ticket: 1\nTotal Price: 10000.0\n======================================\n\nConfirm to order? (Yes/No)", is(outContent.toString()));
	}

}

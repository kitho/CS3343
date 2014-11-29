
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

import CS3343.AirlineTicketOrdering.FlightPathFinding.FlightPath;
import CS3343.AirlineTicketOrdering.Model.CreditCard;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Model.Route;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;
import CS3343.AirlineTicketOrdering.View.Impl.OrderConfirmationView;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class OrderConfirmationViewTest {
	
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
	public void displayTest() throws IOException {			
		CreditCard creditCard = new CreditCard();
		creditCard.setBank("HSBC");
		creditCard.setCreditCardType("VISA");
		creditCard.setCreditCardNumber("0000-0000-0000-0000");
		
		List<Flight> flights = new ArrayList<Flight>();
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
		
		Route route = new Route();
		route.addFlight(flight);
		route.setDeparture("Hong Kong");
		route.setDestination("USA");
		route.setDistance(500);
		FlightPath fPath = new FlightPath();
		fPath.addFlighPath(route);	
		
		session.setAttribute("selectedRoute", fPath);

		session.setAttribute("creditCard", creditCard);
		session.setAttribute("flights", flights);
		session.setAttribute("numberOfTicket", 1);
		session.setAttribute("totalPrice", 10000.0);
		
		BufferedReader bufferedReader = org.mockito.Mockito.mock(BufferedReader.class);
		View orderConfirmationView = new OrderConfirmationView(bufferedReader);
		Mockito.when(bufferedReader.readLine()).thenReturn("Yes");
		
		orderConfirmationView.display(session);
		
		assertThat("Here is your order detail:"+separator+"==========Payment Method=========="+separator+"Bank: HSBC"+separator+"Type: VISA"+separator+"Number: 0000-0000-0000-0000"+separator+""+separator+"==========Ticket Information=========="+separator+"Airline             FlightNumber             TravelClass         Depature            Destination         DepatureDateTime         ArrivalDateTime          Available           OneWayPrice"+separator+"AirLine             LE1234                   First Class         Hong Kong           USA                 1970-01-17 05:11:22      1970-01-17 05:11:12      100                 10000.0"+separator+""+separator+"======================================"+separator+"Number of Ticket: 1"+separator+"Total Price: 10000.0"+separator+"Total award flyer miles: 1500"+separator+"======================================"+separator+""+separator+"Confirm to order? (Yes/No)"
				, is(outContent.toString()));
		assertThat((String)session.getAttribute("confirmed"),is("Yes"));
		

		
	}

}
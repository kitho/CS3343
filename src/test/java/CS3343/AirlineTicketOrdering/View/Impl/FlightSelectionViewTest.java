package CS3343.AirlineTicketOrdering.View.Impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;
import CS3343.AirlineTicketOrdering.View.Impl.FlightSelectionView;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class FlightSelectionViewTest {
	
	private ByteArrayOutputStream outContent;
	private Session session;
	
	@Before
	public void setUp() {
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		session = Session.getInstance();
	}
	
	@Test
	public void displayTestWithNotFlightInSession() {	
		List<Flight> flights = new ArrayList<Flight>();
		session.setAttribute("flights",flights);

		View flightSelectionView = new FlightSelectionView();
		flightSelectionView.display(session);
		assertThat("Not Suitable Flight\n", is(outContent.toString()));
	}
	
	@Test
	public void displayTestWithOneFlightInSession() {	
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
		session.setAttribute("flights",flights);

		System.setIn(new ByteArrayInputStream("0\n2".getBytes()));
		View flightSelectionView = new FlightSelectionView();
		flightSelectionView.display(session);
		assertThat("=====================\nNo.  Airline             FlightNumber        TravelClass         Depature            Destination         DepatureDateTime         ArrivalDateTime          Available           OneWayPrice\n0    AirLine             LE1234              First Class         Hong Kong           USA                 1970-01-17 05:11:22      1970-01-17 05:11:12      100                 10000.0\n=====================\nPlease select flight\nPlease input number of tickets that you need?", is(outContent.toString()));
	}

	@Test
	public void displayTestWithManyFlightInSession() {	
		Flight flight1 = new Flight();
		flight1.setAirline("AirLine");
		flight1.setArrivalDateTime(new Date(1415472252));
		flight1.setAvailable(100);
		flight1.setDepature("Hong Kong");
		flight1.setDepatureDateTime(new Date(1415482252));
		flight1.setDestination("USA");
		flight1.setFlightNumber("LE1234");
		flight1.setOneWayPrice(10000.0);
		flight1.setTravelClass("First Class");
		
		Flight flight2 = new Flight();
		flight2.setAirline("AirLine");
		flight2.setArrivalDateTime(new Date(1415472252));
		flight2.setAvailable(100);
		flight2.setDepature("Hong Kong");
		flight2.setDepatureDateTime(new Date(1415482252));
		flight2.setDestination("USA");
		flight2.setFlightNumber("LE1234");
		flight2.setOneWayPrice(10000.0);
		flight2.setTravelClass("First Class");
		
		Flight flight3 = new Flight();
		flight3.setAirline("AirLine");
		flight3.setArrivalDateTime(new Date(1415472252));
		flight3.setAvailable(100);
		flight3.setDepature("Hong Kong");
		flight3.setDepatureDateTime(new Date(1415482252));
		flight3.setDestination("USA");
		flight3.setFlightNumber("LE1234");
		flight3.setOneWayPrice(10000.0);
		flight3.setTravelClass("First Class");
		
		
		List<Flight> flights = new ArrayList<Flight>();
		flights.add(flight1);
		flights.add(flight2);
		flights.add(flight3);
		session.setAttribute("flights",flights);

		System.setIn(new ByteArrayInputStream("0\n2".getBytes()));
		View flightSelectionView = new FlightSelectionView();
		flightSelectionView.display(session);
		assertThat("=====================\nNo.  Airline             FlightNumber        TravelClass         Depature            Destination         DepatureDateTime         ArrivalDateTime          Available           OneWayPrice\n0    AirLine             LE1234              First Class         Hong Kong           USA                 1970-01-17 05:11:22      1970-01-17 05:11:12      100                 10000.0\n1    AirLine             LE1234              First Class         Hong Kong           USA                 1970-01-17 05:11:22      1970-01-17 05:11:12      100                 10000.0\n2    AirLine             LE1234              First Class         Hong Kong           USA                 1970-01-17 05:11:22      1970-01-17 05:11:12      100                 10000.0\n=====================\nPlease select flight\nPlease input number of tickets that you need?", is(outContent.toString()));
	}

}

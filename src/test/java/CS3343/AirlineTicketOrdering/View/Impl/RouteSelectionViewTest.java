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
import CS3343.AirlineTicketOrdering.Util.LineSeparatorUtil;
import CS3343.AirlineTicketOrdering.View.View;
import CS3343.AirlineTicketOrdering.View.Impl.FlightSelectionView;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class RouteSelectionViewTest {
	
	private ByteArrayOutputStream outContent;
	private Session session;
	private String separator = LineSeparatorUtil.newLine();

	
	@Before
	public void setUp() {
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		session = Session.getInstance();
	}
	

	@Test
	public void displayTestWithOneRouteInSession() throws IOException {	
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
		
		
		Route route = new Route();
		route.addFlight(flight);

		FlightPath fPath = new FlightPath();
		fPath.addFlighPath(route);
		
		session.setAttribute("flights",flights);
		session.setAttribute("selectedRoute",fPath);
		
		ArrayList<FlightPath> fPaths = new ArrayList<FlightPath>();
		fPaths.add(fPath);
		session.setAttribute("FlightPaths",fPaths);

		
		
		BufferedReader bufferedReader = org.mockito.Mockito.mock(BufferedReader.class);
		Mockito.when(bufferedReader.readLine()).thenReturn("1").thenReturn("10");

		View routeSelectionView = new RouteSelectionView(bufferedReader);
		routeSelectionView.display(session);
		System.out.println(session.getAttribute("numberOfTicket"));
		

		assertThat(((List<Flight>)session.getAttribute("flights")).get(0),is(flight));
		

	}
	

	@Test
	public void displayTestWithManyRouteInSession() throws IOException {	
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
		flight2.setOneWayPrice(5000.0);
		flight2.setTravelClass("First Class");
		
		Flight flight3 = new Flight();
		flight3.setAirline("AirLine");
		flight3.setArrivalDateTime(new Date(1415472252));
		flight3.setAvailable(100);
		flight3.setDepature("Hong Kong");
		flight3.setDepatureDateTime(new Date(1415482252));
		flight3.setDestination("USA");
		flight3.setFlightNumber("LE1234");
		flight3.setOneWayPrice(1000.0);
		flight3.setTravelClass("First Class");
		
		
		List<Flight> flights = new ArrayList<Flight>();
		flights.add(flight1);
		flights.add(flight2);
		flights.add(flight3);
		Route route = new Route();
		route.addFlight(flight1);
		route.addFlight(flight2);
		Route route1 = new Route();

		route1.addFlight(flight3);
		FlightPath fPath = new FlightPath();
		fPath.addFlighPath(route);
		fPath.addFlighPath(route1);

		session.setAttribute("flights",flights);
		session.setAttribute("selectedRoute",fPath);
		
		ArrayList<FlightPath> fPaths = new ArrayList<FlightPath>();
		fPaths.add(fPath);
		session.setAttribute("FlightPaths",fPaths);
		
		
		
		BufferedReader bufferedReader = org.mockito.Mockito.mock(BufferedReader.class);
		Mockito.when(bufferedReader.readLine()).thenReturn("1").thenReturn("1").thenReturn("1");
		
		View routeSelectionView = new RouteSelectionView(bufferedReader);
		routeSelectionView.display(session);
		

		
	
		
		assertThat(((List<Flight>)session.getAttribute("flights")).get(0),is(flight2));

	}
	
	

	@Test
	public void displayTestWithNoRouteInSession() throws IOException {	
		
		FlightPath fPath = new FlightPath();
		
		session.setAttribute("selectedRoute",fPath);
		
		ArrayList<FlightPath> fPaths = new ArrayList<FlightPath>();
		session.setAttribute("FlightPaths",fPaths);
		
		
		ArrayList<FlightPath> resultRoutes = (ArrayList<FlightPath>) session.getAttribute("FlightPaths");

		
		
		BufferedReader bufferedReader = org.mockito.Mockito.mock(BufferedReader.class);
		Mockito.when(bufferedReader.readLine()).thenReturn("1").thenReturn("1").thenReturn("1");
		
		View routeSelectionView = new RouteSelectionView(bufferedReader);
		routeSelectionView.display(session);
		assertThat("No Route Found"+separator,  	is(outContent.toString()));


	}

}

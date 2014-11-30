package CS3343.AirlineTicketOrdering.Controller.Impl;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import CS3343.AirlineTicketOrdering.Controller.AirlineTicketOrderingController;
import CS3343.AirlineTicketOrdering.DataQuery.FlightQuery;
import CS3343.AirlineTicketOrdering.DataQuery.RouteQuery;
import CS3343.AirlineTicketOrdering.DataReader.SourceReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.AirlineCompanyCSVFileReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.FlightCSVFileReader;
import CS3343.AirlineTicketOrdering.DataWriter.SourceWriter;
import CS3343.AirlineTicketOrdering.DataWriter.Impl.FlightCSVFileWriter;
import CS3343.AirlineTicketOrdering.Model.AirlineCompany;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Model.Route;
import CS3343.AirlineTicketOrdering.Parser.Parser;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

public class RouteSelectionControllerTest {

	private SimpleDateFormat formatter;

	@Before
	public void setUp() throws IOException{
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	@Test
	public void testExecute() throws Exception {
		Session session = Session.getInstance();
		View view = mock(View.class);
		SourceReader<AirlineCompany> airlineCompanyReader = mock(AirlineCompanyCSVFileReader.class);
		SourceReader<Flight> flightReader = mock(FlightCSVFileReader.class);

		String depature = "Hong Kong";
		String destination = "Taiwan"; 
		Date depatureDate = formatter.parse("2014-01-01 00:00:00");
		
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
		
		Flight flight1 = new Flight();
		flight1.setAirline("Cathay Pacific Airways");
		flight1.setFlightNumber("CP001");
		flight1.setTravelClass("FIRST");
		flight1.setDepature("Hong Kong");
		flight1.setDestination("Tokyo");
		flight1.setDepatureDateTime(formatter.parse("2014-01-01 14:30:00"));
		flight1.setArrivalDateTime(formatter.parse("2014-01-01 17:30:00"));
		flight1.setAvailable(30);
		flight1.setOneWayPrice(2500.00);
		
		List<Flight> flights = new ArrayList<Flight>();
		flights.add(flight);
		flights.add(flight1);
		session.setAttribute("deapture", "Hong Kong");
		session.setAttribute("destination", "Taiwan");
		
		
		RouteQuery rQuery = new RouteQuery();
		
		
		AirlineCompany airlineCompany = new AirlineCompany();
		airlineCompany.setAirline("Cathay Pacific Airways");
		
		List<AirlineCompany> airlineCompanies= new ArrayList<AirlineCompany>();
		airlineCompanies.add(airlineCompany);
		
		when(airlineCompanyReader.read((Parser<AirlineCompany>) any())).thenReturn(airlineCompanies);
		when(flightReader.read((Parser<Flight>) any())).thenReturn(flights);
		
		FlightQuery flightQuery = new FlightQuery(airlineCompanyReader,flightReader, (SourceWriter<List<Flight>>)mock(FlightCSVFileWriter.class));


		AirlineTicketOrderingController next = mock(AirlineTicketOrderingController.class); 
		AirlineTicketOrderingController routeSelectionController = new RouteSelectionController(session, view, flightQuery, rQuery);
		
		
		routeSelectionController.setNext(next);
		routeSelectionController.execute();

	}
	
	@Test
	public void testExecuteWithNoNext() throws Exception {
		Session session = Session.getInstance();
		View view = mock(View.class);
		SourceReader<AirlineCompany> airlineCompanyReader = mock(AirlineCompanyCSVFileReader.class);
		SourceReader<Flight> flightReader = mock(FlightCSVFileReader.class);

		String depature = "Hong Kong";
		String destination = "Taiwan"; 
		Date depatureDate = formatter.parse("2014-01-01 00:00:00");
		
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
		
		Flight flight1 = new Flight();
		flight1.setAirline("Cathay Pacific Airways");
		flight1.setFlightNumber("CP001");
		flight1.setTravelClass("FIRST");
		flight1.setDepature("Hong Kong");
		flight1.setDestination("Tokyo");
		flight1.setDepatureDateTime(formatter.parse("2014-01-01 14:30:00"));
		flight1.setArrivalDateTime(formatter.parse("2014-01-01 17:30:00"));
		flight1.setAvailable(30);
		flight1.setOneWayPrice(2500.00);
		
		List<Flight> flights = new ArrayList<Flight>();
		flights.add(flight);
		flights.add(flight1);
		session.setAttribute("deapture", "Hong Kong");
		session.setAttribute("destination", "Singapore");
		
		
		RouteQuery rQuery = new RouteQuery();
		
		
		AirlineCompany airlineCompany = new AirlineCompany();
		airlineCompany.setAirline("Cathay Pacific Airways");
		
		List<AirlineCompany> airlineCompanies= new ArrayList<AirlineCompany>();
		airlineCompanies.add(airlineCompany);
		
		when(airlineCompanyReader.read((Parser<AirlineCompany>) any())).thenReturn(airlineCompanies);
		when(flightReader.read((Parser<Flight>) any())).thenReturn(flights);
		
		FlightQuery flightQuery = new FlightQuery(airlineCompanyReader,flightReader, (SourceWriter<List<Flight>>)mock(FlightCSVFileWriter.class));


		AirlineTicketOrderingController next = mock(AirlineTicketOrderingController.class); 
		AirlineTicketOrderingController routeSelectionController = new RouteSelectionController(session, view, flightQuery, rQuery);
		
		
		routeSelectionController.setNext(next);
		routeSelectionController.execute();

	}

}

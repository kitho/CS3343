package CS3343.AirlineTicketOrdering.DataQuery;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import CS3343.AirlineTicketOrdering.DataReader.AirlineCompanyCSVFileReader;
import CS3343.AirlineTicketOrdering.DataReader.SourceReader;
import CS3343.AirlineTicketOrdering.Model.AirlineCompany;
import CS3343.AirlineTicketOrdering.Model.Flight;

public class AirlineQueryTest {
	
	private SimpleDateFormat formatter;

	@Before
	public void setUp() throws IOException{
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	@Test
	public void findFlightsByDepatureAndDestinationAndDateForSingleAirlineAndSingleFlightTest() throws ParseException, IOException {
		SourceReader<AirlineCompany> airlineCompanyReader = mock(AirlineCompanyCSVFileReader.class);
		
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
		
		AirlineCompany airlineCompany = new AirlineCompany("Cathay Pacific Airways");
		airlineCompany.addFlight(flight);
		
		List<AirlineCompany> airlineCompanies= new ArrayList<AirlineCompany>();
		airlineCompanies.add(airlineCompany);
		
		when(airlineCompanyReader.read()).thenReturn(airlineCompanies);
		
		AirlineQuery airlineQuery = new AirlineQuery(airlineCompanyReader);
		List<Flight> flights = airlineQuery.findFlightsByDepatureAndDestinationAndDate(depature, destination, depatureDate);
		
		Flight flightResult = flights.get(0);
		
		assertThat(depature, is(flightResult.getDepature()));
		assertThat(destination, is(flightResult.getDestination()));
		assertThat(true, is(DateUtils.isSameDay(depatureDate, flightResult.getDepatureDateTime())));
		
		assertThat(flight.getAirline(), is(flightResult.getAirline()));
		assertThat(flight.getFlightNumber(), is(flightResult.getFlightNumber()));
		assertThat(flight.getTravelClass(), is(flightResult.getTravelClass()));
		assertThat(flight.getDepature(), is(flightResult.getDepature()));
		assertThat(flight.getDestination(), is(flightResult.getDestination()));
		assertThat(flight.getDepatureDateTime(), is(flightResult.getDepatureDateTime()));
		assertThat(flight.getArrivalDateTime(), is(flightResult.getArrivalDateTime()));
		assertThat(flight.getAvailable(), is(flightResult.getAvailable()));
		assertThat(flight.getOneWayPrice(), is(flightResult.getOneWayPrice()));
	}
	
	
	@Test
	public void findFlightsByDepatureAndDestinationAndDateForSingleAirlineAndMultipleFlightsTest() throws ParseException, IOException {
		SourceReader<AirlineCompany> airlineCompanyReader = mock(AirlineCompanyCSVFileReader.class);
		
		String depature = "Hong Kong";
		String destination = "Taiwan"; 
		Date depatureDate = formatter.parse("2014-01-01 00:00:00");
		
		Flight flight1 = new Flight();
		
		flight1.setAirline("Cathay Pacific Airways");
		flight1.setFlightNumber("CP001");
		flight1.setTravelClass("FIRST");
		flight1.setDepature(depature);
		flight1.setDestination(destination);
		flight1.setDepatureDateTime(formatter.parse("2014-01-01 09:30:00"));
		flight1.setArrivalDateTime(formatter.parse("2014-01-01 11:30:00"));
		flight1.setAvailable(30);
		flight1.setOneWayPrice(2500.00);
		
		Flight flight2 = new Flight();
		
		flight2.setAirline("Cathay Pacific Airways");
		flight2.setFlightNumber("CP002");
		flight2.setTravelClass("FIRST");
		flight2.setDepature(depature);
		flight2.setDestination(destination);
		flight2.setDepatureDateTime(formatter.parse("2014-01-01 12:30:00"));
		flight2.setArrivalDateTime(formatter.parse("2014-01-01 14:30:00"));
		flight2.setAvailable(30);
		flight2.setOneWayPrice(2500.00);
		
		Flight flight3 = new Flight();
		
		flight3.setAirline("Cathay Pacific Airways");
		flight3.setFlightNumber("CP003");
		flight3.setTravelClass("FIRST");
		flight3.setDepature(depature);
		flight3.setDestination(destination);
		flight3.setDepatureDateTime(formatter.parse("2014-01-01 19:30:00"));
		flight3.setArrivalDateTime(formatter.parse("2014-01-01 21:30:00"));
		flight3.setAvailable(30);
		flight3.setOneWayPrice(2500.00);
		
		List<Flight> flights = new ArrayList<Flight>();
		flights.add(flight1);
		flights.add(flight2);
		flights.add(flight3);
		
		AirlineCompany airlineCompany = new AirlineCompany("Cathay Pacific Airways");
		airlineCompany.addFlight(flight1);
		airlineCompany.addFlight(flight2);
		airlineCompany.addFlight(flight3);
		
		List<AirlineCompany> airlineCompanies= new ArrayList<AirlineCompany>();
		airlineCompanies.add(airlineCompany);
		
		when(airlineCompanyReader.read()).thenReturn(airlineCompanies);
		
		AirlineQuery airlineQuery = new AirlineQuery(airlineCompanyReader);
		List<Flight> flightsResult = airlineQuery.findFlightsByDepatureAndDestinationAndDate(depature, destination, depatureDate);
		
		assertThat(flights.size(), is(flightsResult.size()));
		
		for (Flight flightResult : flightsResult) {
			assertThat(depature, is(flightResult.getDepature()));
			assertThat(destination, is(flightResult.getDestination()));
			assertThat(true, is(DateUtils.isSameDay(depatureDate, flightResult.getDepatureDateTime())));
		}
		
		for (int i = 0; i < flights.size(); i++) {
			assertThat(flights.get(i).getAirline(), is(flightsResult.get(i).getAirline()));
			assertThat(flights.get(i).getFlightNumber(), is(flightsResult.get(i).getFlightNumber()));
			assertThat(flights.get(i).getTravelClass(), is(flightsResult.get(i).getTravelClass()));
			assertThat(flights.get(i).getDepature(), is(flightsResult.get(i).getDepature()));
			assertThat(flights.get(i).getDestination(), is(flightsResult.get(i).getDestination()));
			assertThat(flights.get(i).getDepatureDateTime(), is(flightsResult.get(i).getDepatureDateTime()));
			assertThat(flights.get(i).getArrivalDateTime(), is(flightsResult.get(i).getArrivalDateTime()));
			assertThat(flights.get(i).getAvailable(), is(flightsResult.get(i).getAvailable()));
			assertThat(flights.get(i).getOneWayPrice(), is(flightsResult.get(i).getOneWayPrice()));
		}

		
	}

}

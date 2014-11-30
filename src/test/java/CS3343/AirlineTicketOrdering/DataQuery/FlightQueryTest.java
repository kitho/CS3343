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
import CS3343.AirlineTicketOrdering.DataReader.SourceReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.AirlineCompanyCSVFileReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.FlightCSVFileReader;
import CS3343.AirlineTicketOrdering.DataWriter.SourceWriter;
import CS3343.AirlineTicketOrdering.DataWriter.Impl.FlightCSVFileWriter;
import CS3343.AirlineTicketOrdering.Model.AirlineCompany;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Parser.Parser;
import CS3343.AirlineTicketOrdering.Parser.Impl.AirlineCompanyParser;
import CS3343.AirlineTicketOrdering.Parser.Impl.FlightParser;

public class FlightQueryTest {
	
	private SimpleDateFormat formatter;

	@Before
	public void setUp() throws IOException{
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	@Test
	public void findFlightsByDepatureAndDestinationAndDateWithoutSuitableFlightTest() throws ParseException, IOException {
		SourceReader<AirlineCompany> airlineCompanyReader = mock(AirlineCompanyCSVFileReader.class);
		SourceReader<Flight> flightReader = mock(FlightCSVFileReader.class);

		String depature = "Japan";
		String destination = "Australia"; 
		Date depatureDate = formatter.parse("2014-12-31 00:00:00");
		
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
		
		AirlineCompany airlineCompany = new AirlineCompany();
		airlineCompany.setAirline("Cathay Pacific Airways");
		
		List<AirlineCompany> airlineCompanies= new ArrayList<AirlineCompany>();
		airlineCompanies.add(airlineCompany);
		
		when(airlineCompanyReader.read((Parser<AirlineCompany>) any())).thenReturn(airlineCompanies);
		when(flightReader.read((Parser<Flight>) any())).thenReturn(flights);
		
		FlightQuery FlightQuery = new FlightQuery(airlineCompanyReader,flightReader, (SourceWriter<List<Flight>>)mock(FlightCSVFileWriter.class));
		List<Flight> resultFlights = FlightQuery.findFlightsByDepatureAndDestinationAndDate(depature, destination, depatureDate);
		
		assertThat(0, is(resultFlights.size()));
	}
	
	@Test
	public void findFlightsByDepatureAndDestinationAndDateForSingleAirlineAndSingleFlightTest() throws ParseException, IOException {
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
		
		List<Flight> flights = new ArrayList<Flight>();
		flights.add(flight);
		
		AirlineCompany airlineCompany = new AirlineCompany();
		airlineCompany.setAirline("Cathay Pacific Airways");
		
		List<AirlineCompany> airlineCompanies= new ArrayList<AirlineCompany>();
		airlineCompanies.add(airlineCompany);
		
		when(airlineCompanyReader.read((Parser<AirlineCompany>) any())).thenReturn(airlineCompanies);
		when(flightReader.read((Parser<Flight>) any())).thenReturn(flights);

		
		FlightQuery flightQuery = new FlightQuery(airlineCompanyReader,flightReader, (SourceWriter<List<Flight>>)mock(FlightCSVFileWriter.class));
		List<Flight> resultFlights = flightQuery.findFlightsByDepatureAndDestinationAndDate(depature, destination, depatureDate);
		
		assertThat(1, is(flights.size()));
		
		Flight flightResult = resultFlights.get(0);
		
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
		SourceReader<Flight> flightReader = mock(FlightCSVFileReader.class);
		FlightParser flightParser = new FlightParser();
		AirlineCompanyParser airlineCompanyParser = new AirlineCompanyParser();
		
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
		
		AirlineCompany airlineCompany = new AirlineCompany();
		airlineCompany.setAirline("Cathay Pacific Airways");
		
		List<AirlineCompany> airlineCompanies= new ArrayList<AirlineCompany>();
		airlineCompanies.add(airlineCompany);
		
		when(airlineCompanyReader.read((Parser<AirlineCompany>) any())).thenReturn(airlineCompanies);
		when(flightReader.read((Parser<Flight>) any())).thenReturn(flights);
		
		
		FlightQuery flightQuery = new FlightQuery(airlineCompanyReader,flightReader, (SourceWriter<List<Flight>>)mock(FlightCSVFileWriter.class));
		List<Flight> flightsResult = flightQuery.findFlightsByDepatureAndDestinationAndDate(depature, destination, depatureDate);
				
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
	
	@Test
	public void findFlightsByDepatureAndDestinationAndDateForMultipleAirlinesAndMultipleFlightsTest() throws ParseException, IOException {
		SourceReader<AirlineCompany> airlineCompanyReader = mock(AirlineCompanyCSVFileReader.class);
		SourceReader<Flight> flightReader = mock(FlightCSVFileReader.class);
		
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
		
		flight2.setAirline("China Airlines");
		flight2.setFlightNumber("CA001");
		flight2.setTravelClass("FIRST");
		flight2.setDepature(depature);
		flight2.setDestination(destination);
		flight2.setDepatureDateTime(formatter.parse("2014-01-01 12:30:00"));
		flight2.setArrivalDateTime(formatter.parse("2014-01-01 14:30:00"));
		flight2.setAvailable(30);
		flight2.setOneWayPrice(2500.00);
		
		Flight flight3 = new Flight();
		
		flight3.setAirline("Hong Kong Airlines");
		flight3.setFlightNumber("HKA001");
		flight3.setTravelClass("FIRST");
		flight3.setDepature(depature);
		flight3.setDestination(destination);
		flight3.setDepatureDateTime(formatter.parse("2014-01-02 19:30:00"));
		flight3.setArrivalDateTime(formatter.parse("2014-01-02 21:30:00"));
		flight3.setAvailable(30);
		flight3.setOneWayPrice(2500.00);
		
		List<Flight> flights = new ArrayList<Flight>();
		flights.add(flight1);
		flights.add(flight2);
		flights.add(flight3);
		
		AirlineCompany airlineCompany1 = new AirlineCompany();
		airlineCompany1.setAirline("Cathay Pacific Airways");
		AirlineCompany airlineCompany2 = new AirlineCompany();
		airlineCompany2.setAirline("China Airlines");
		AirlineCompany airlineCompany3 = new AirlineCompany();
		airlineCompany3.setAirline("Hong Kong Airlines");
		
		List<AirlineCompany> airlineCompanies= new ArrayList<AirlineCompany>();
		airlineCompanies.add(airlineCompany1);
		airlineCompanies.add(airlineCompany2);
		airlineCompanies.add(airlineCompany3);
		
		when(airlineCompanyReader.read((Parser<AirlineCompany>) any())).thenReturn(airlineCompanies);
		when(flightReader.read((Parser<Flight>) any())).thenReturn(flights);
		
		FlightQuery flightQuery = new FlightQuery(airlineCompanyReader,flightReader, (SourceWriter<List<Flight>>)mock(FlightCSVFileWriter.class));
		List<Flight> flightsResults = flightQuery.findFlightsByDepatureAndDestinationAndDate(depature, destination, depatureDate);
		
		assertThat(flightsResults.size(), is(2));
		
		for (int i = 0; i < flightsResults.size(); i++) {
			Flight flightResult = flightsResults.get(i);
			assertThat(depature, is(flightResult.getDepature()));
			assertThat(destination, is(flightResult.getDestination()));
			assertThat(true, is(DateUtils.isSameDay(depatureDate, flightResult.getDepatureDateTime())));
		}
		
		for (int i = 0; i < flightsResults.size(); i++) {
			assertThat(flights.get(i).getAirline(), is(flightsResults.get(i).getAirline()));
			assertThat(flights.get(i).getFlightNumber(), is(flightsResults.get(i).getFlightNumber()));
			assertThat(flights.get(i).getTravelClass(), is(flightsResults.get(i).getTravelClass()));
			assertThat(flights.get(i).getDepature(), is(flightsResults.get(i).getDepature()));
			assertThat(flights.get(i).getDestination(), is(flightsResults.get(i).getDestination()));
			assertThat(flights.get(i).getDepatureDateTime(), is(flightsResults.get(i).getDepatureDateTime()));
			assertThat(flights.get(i).getArrivalDateTime(), is(flightsResults.get(i).getArrivalDateTime()));
			assertThat(flights.get(i).getAvailable(), is(flightsResults.get(i).getAvailable()));
			assertThat(flights.get(i).getOneWayPrice(), is(flightsResults.get(i).getOneWayPrice()));
		}
	}
	
	@Test
	public void updateFlightAvailableByFlightAndReducingNumberTest() throws ParseException, IOException {
		SourceReader<AirlineCompany> airlineCompanyReader = mock(AirlineCompanyCSVFileReader.class);
		SourceReader<Flight> flightReader = mock(FlightCSVFileReader.class);
		SourceWriter<List<Flight>> flightWriter = mock(FlightCSVFileWriter.class);
		
		Flight flight = new Flight();
		flight.setAirline("Cathay Pacific Airways");
		flight.setFlightNumber("CP001");
		flight.setTravelClass("FIRST");
		flight.setDepature("Hong Kong");
		flight.setDestination("Taiwan");
		flight.setDepatureDateTime(formatter.parse("2014-01-01 09:30:00"));
		flight.setArrivalDateTime(formatter.parse("2014-01-01 11:30:00"));
		flight.setAvailable(30);
		flight.setOneWayPrice(2500.00);
		
		List<Flight> flights = new ArrayList<Flight>();
		flights.add(flight);
		
		AirlineCompany airlineCompany = new AirlineCompany();
		airlineCompany.setAirline("Cathay Pacific Airways");
		
		List<AirlineCompany> airlineCompanies= new ArrayList<AirlineCompany>();
		airlineCompanies.add(airlineCompany);
		
		when(airlineCompanyReader.read((Parser<AirlineCompany>) any())).thenReturn(airlineCompanies);
		when(flightReader.read((Parser<Flight>) any())).thenReturn(flights);
		doNothing().when(flightWriter).write(null);;
		
		FlightQuery flightQuery = new FlightQuery(airlineCompanyReader,flightReader,flightWriter);
		flightQuery.updateFlightAvailableByFlightAndReducingNumber(flight, 1);
		
		int count = 0;
		for (AirlineCompany airlineCompanyResult : airlineCompanies) {
			for (Flight flightResult : airlineCompanyResult.getFlights()) {
				if (flight.equals(flightResult)) {
					count++;
					assertThat(flightResult.getAvailable(), is(29));
				}
			}
		}
		
		assertThat(count, is(1));
	}
	
	@Test
	public void findAllFlight() throws ParseException, IOException {
		SourceReader<AirlineCompany> airlineCompanyReader = mock(AirlineCompanyCSVFileReader.class);
		SourceReader<Flight> flightReader = mock(FlightCSVFileReader.class);
		
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
		
		flight2.setAirline("China Airlines");
		flight2.setFlightNumber("CA001");
		flight2.setTravelClass("FIRST");
		flight2.setDepature(depature);
		flight2.setDestination(destination);
		flight2.setDepatureDateTime(formatter.parse("2014-01-01 12:30:00"));
		flight2.setArrivalDateTime(formatter.parse("2014-01-01 14:30:00"));
		flight2.setAvailable(30);
		flight2.setOneWayPrice(2500.00);
		
		Flight flight3 = new Flight();
		
		flight3.setAirline("Hong Kong Airlines");
		flight3.setFlightNumber("HKA001");
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
		
		AirlineCompany airlineCompany1 = new AirlineCompany();
		airlineCompany1.setAirline("Cathay Pacific Airways");
		AirlineCompany airlineCompany2 = new AirlineCompany();
		airlineCompany2.setAirline("China Airlines");
		AirlineCompany airlineCompany3 = new AirlineCompany();
		airlineCompany3.setAirline("Hong Kong Airlines");
		
		List<AirlineCompany> airlineCompanies= new ArrayList<AirlineCompany>();
		airlineCompanies.add(airlineCompany1);
		airlineCompanies.add(airlineCompany2);
		airlineCompanies.add(airlineCompany3);
		
		when(airlineCompanyReader.read((Parser<AirlineCompany>) any())).thenReturn(airlineCompanies);
		when(flightReader.read((Parser<Flight>) any())).thenReturn(flights);
		
		FlightQuery flightQuery = new FlightQuery(airlineCompanyReader,flightReader, (SourceWriter<List<Flight>>)mock(FlightCSVFileWriter.class));
		List<Flight> flightsResult = flightQuery.getFlights();
		
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

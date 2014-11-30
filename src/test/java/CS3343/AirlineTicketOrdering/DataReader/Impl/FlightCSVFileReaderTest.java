package CS3343.AirlineTicketOrdering.DataReader.Impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import TestingTool.DataWriter.CSVFileTest;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import CS3343.AirlineTicketOrdering.CustomDateUtil.CustomDateFormatter;
import CS3343.AirlineTicketOrdering.DataReader.SourceReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.FlightCSVFileReader;
import CS3343.AirlineTicketOrdering.DataWriter.SourceWriter;
import CS3343.AirlineTicketOrdering.DataWriter.Impl.FlightCSVFileWriter;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Parser.Impl.FlightParser;

public class FlightCSVFileReaderTest {

	private File projectPath;
	private CustomDateFormatter formatter;
	
	@Before
	public void setUp() throws IOException{
		projectPath = new File(".").getCanonicalFile(); 
		formatter = new CustomDateFormatter();
	}

	
	@Test
	public void readFlightCSVFileWithOneRecordTest() throws IOException, ParseException{		
		Files.deleteIfExists(Paths.get(projectPath + CSVFileTest.FLIGHTCSV.value()));
		
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
		
		ArrayList<Flight> flights = new ArrayList<Flight>();
		flights.add(flight);
		
		SourceWriter<List<Flight>> flightCsvFileWriter = new FlightCSVFileWriter(projectPath + CSVFileTest.FLIGHTCSV.value());
		flightCsvFileWriter.write(flights);
		flightCsvFileWriter.close();
		
		SourceReader<Flight> flightCsvFileReader = new FlightCSVFileReader(projectPath + CSVFileTest.FLIGHTCSV.value());
		List<Flight> resultList = flightCsvFileReader.read(new FlightParser());
		flightCsvFileReader.close();
		
		assertThat(flight.getAirline(), is(resultList.get(0).getAirline()));
		assertThat(flight.getFlightNumber(), is(resultList.get(0).getFlightNumber()));
		assertThat(flight.getTravelClass(), is(resultList.get(0).getTravelClass()));
		assertThat(flight.getDepature(), is(resultList.get(0).getDepature()));
		assertThat(flight.getDestination(), is(resultList.get(0).getDestination()));
		assertThat(flight.getDepatureDateTime(), is(resultList.get(0).getDepatureDateTime()));
		assertThat(flight.getArrivalDateTime(), is(resultList.get(0).getArrivalDateTime()));
		assertThat(flight.getAvailable(), is(resultList.get(0).getAvailable()));
		assertThat(flight.getOneWayPrice(), is(resultList.get(0).getOneWayPrice()));

	}
	
	@Test
	public void readFlightCSVFileWithThreeRecordTest() throws IOException, ParseException{
		Files.deleteIfExists(Paths.get(projectPath + CSVFileTest.FLIGHTCSV.value()));
		
		Flight flight1 = new Flight();
		
		flight1.setAirline("Cathay Pacific Airways");
		flight1.setFlightNumber("CP001");
		flight1.setTravelClass("FIRST");
		flight1.setDepature("Hong Kong");
		flight1.setDestination("Taiwan");
		flight1.setDepatureDateTime(formatter.parse("2014-01-01 14:30:00"));
		flight1.setArrivalDateTime(formatter.parse("2014-01-01 17:30:00"));
		flight1.setAvailable(30);
		flight1.setOneWayPrice(2500.00);

		Flight flight2 = new Flight();
		
		flight2.setAirline("China Airlines");
		flight2.setFlightNumber("CA001");
		flight2.setTravelClass("FIRST");
		flight2.setDepature("Hong Kong");
		flight2.setDestination("Shanghai");
		flight2.setDepatureDateTime(formatter.parse("2014-01-02 01:30:00"));
		flight2.setArrivalDateTime(formatter.parse("2014-01-02 03:30:00"));
		flight2.setAvailable(25);
		flight2.setOneWayPrice(1500.00);
		
		Flight flight3 = new Flight();
		
		flight3.setAirline("Hong Kong Airlines");
		flight3.setFlightNumber("HKA001");
		flight3.setTravelClass("BUSINESS");
		flight3.setDepature("Canada");
		flight3.setDestination("Hong Kong");
		flight3.setDepatureDateTime(formatter.parse("2014-12-30 19:30:00"));
		flight3.setArrivalDateTime(formatter.parse("2014-12-31 15:30:00"));
		flight3.setAvailable(100);
		flight3.setOneWayPrice(10500.00);
		
		ArrayList<Flight> flights = new ArrayList<Flight>();
		flights.add(flight1);
		flights.add(flight2);
		flights.add(flight3);
		
		SourceWriter<List<Flight>> flightCsvFileWriter = new FlightCSVFileWriter(projectPath + CSVFileTest.FLIGHTCSV.value());
		flightCsvFileWriter.write(flights);
		flightCsvFileWriter.close();
		
		SourceReader<Flight> flightCsvFileReader = new FlightCSVFileReader(projectPath + CSVFileTest.FLIGHTCSV.value());
		List<Flight> resultList = flightCsvFileReader.read(new FlightParser());
		flightCsvFileReader.close();

		
		assertThat(flights.size(), is(resultList.size()));
		
		for (int i = 0; i < flights.size(); i++) {
			assertThat(flights.get(i).getAirline(), is(resultList.get(i).getAirline()));
			assertThat(flights.get(i).getFlightNumber(), is(resultList.get(i).getFlightNumber()));
			assertThat(flights.get(i).getTravelClass(), is(resultList.get(i).getTravelClass()));
			assertThat(flights.get(i).getDepature(), is(resultList.get(i).getDepature()));
			assertThat(flights.get(i).getDestination(), is(resultList.get(i).getDestination()));
			assertThat(flights.get(i).getDepatureDateTime(), is(resultList.get(i).getDepatureDateTime()));
			assertThat(flights.get(i).getArrivalDateTime(), is(resultList.get(i).getArrivalDateTime()));
			assertThat(flights.get(i).getAvailable(), is(resultList.get(i).getAvailable()));
			assertThat(flights.get(i).getOneWayPrice(), is(resultList.get(i).getOneWayPrice()));
		}
		

	}
	
	
}

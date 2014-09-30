package CS3343.AirlineTicketOrdering.DataSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import CS3343.AirlineTicketOrdering.DataQuery.AirlineQuery;
import CS3343.AirlineTicketOrdering.FileReader.AirlineCompanyCSVFileReader;
import CS3343.AirlineTicketOrdering.FileReader.FlightCSVFileReader;
import CS3343.AirlineTicketOrdering.FileReader.SourceReader;
import CS3343.AirlineTicketOrdering.Model.AirlineCompany;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.TestingTools.AirlineCompanyCSVFileWriter;
import CS3343.AirlineTicketOrdering.TestingTools.FlightCSVFileWriter;
import CS3343.AirlineTicketOrdering.TestingTools.SourceWriter;

public class AirlineCompanyCSVFileReaderTest {

	private File projectPath;
	private SimpleDateFormat formatter;
	
	@Before
	public void setUp() throws IOException{
		projectPath = new File(".").getCanonicalFile(); 
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	@Test
	public void readAirlineCompanyCSVFileWhenFileNotExisted() throws IOException{
		Files.deleteIfExists(Paths.get(projectPath + AirlineQuery.AIRLINECSV));
		
		SourceReader<AirlineCompany> airlineCompanyCsvReader;
		try {
			airlineCompanyCsvReader = new AirlineCompanyCSVFileReader(projectPath + AirlineQuery.AIRLINECSV, 
					new FlightCSVFileReader(projectPath + AirlineQuery.FLIGHTCSV));
			fail("File not existed");
		} catch (FileNotFoundException e) {
			assertThat(e.getMessage().toString(), is(not(nullValue())));
		}
	}

	@Test
	public void readAirlineCompanyCSVFileWithOneRecordTest() throws IOException, ParseException{
		Files.deleteIfExists(Paths.get(projectPath + AirlineQuery.AIRLINECSV));
		Files.deleteIfExists(Paths.get(projectPath + AirlineQuery.FLIGHTCSV));
		
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
		
		SourceWriter<List<Flight>> flightCSVFileWriter = new FlightCSVFileWriter(projectPath + AirlineQuery.FLIGHTCSV);
		flightCSVFileWriter.write(flights);
		flightCSVFileWriter.close();
		
		AirlineCompany airlineCompany = new AirlineCompany("Cathay Pacific Airways");
		airlineCompany.addFlight(flight);
		
		ArrayList<AirlineCompany> airlineCompanies = new ArrayList<AirlineCompany>();
		airlineCompanies.add(airlineCompany);
		
		SourceWriter<List<AirlineCompany>> airlineCompanyCSVFileWriter = new AirlineCompanyCSVFileWriter(projectPath + AirlineQuery.AIRLINECSV);
		airlineCompanyCSVFileWriter.write(airlineCompanies);
		airlineCompanyCSVFileWriter.close();
		
		SourceReader<Flight> flightCSVFileReader = new FlightCSVFileReader(projectPath + AirlineQuery.FLIGHTCSV);
		SourceReader<AirlineCompany> airlineCompanyCSVFileReader = new AirlineCompanyCSVFileReader(projectPath + AirlineQuery.AIRLINECSV, flightCSVFileReader);
		List<AirlineCompany> airlineCompanyResultList = airlineCompanyCSVFileReader.read();
		airlineCompanyCSVFileReader.close();
		
		assertThat(airlineCompany.getAirline(), is(airlineCompanyResultList.get(0).getAirline()));
		
		assertThat(airlineCompany.getFlights().size(), is(airlineCompanyResultList.get(0).getFlights().size()));
		
		assertThat(flight.getAirline(), is(airlineCompanyResultList.get(0).getFlights().get(0).getAirline()));
		assertThat(flight.getFlightNumber(), is(airlineCompanyResultList.get(0).getFlights().get(0).getFlightNumber()));
		assertThat(flight.getTravelClass(), is(airlineCompanyResultList.get(0).getFlights().get(0).getTravelClass()));
		assertThat(flight.getDepature(), is(airlineCompanyResultList.get(0).getFlights().get(0).getDepature()));
		assertThat(flight.getDestination(), is(airlineCompanyResultList.get(0).getFlights().get(0).getDestination()));
		assertThat(flight.getDepatureDateTime(), is(airlineCompanyResultList.get(0).getFlights().get(0).getDepatureDateTime()));
		assertThat(flight.getArrivalDateTime(), is(airlineCompanyResultList.get(0).getFlights().get(0).getArrivalDateTime()));
		assertThat(flight.getAvailable(), is(airlineCompanyResultList.get(0).getFlights().get(0).getAvailable()));
		assertThat(flight.getOneWayPrice(), is(airlineCompanyResultList.get(0).getFlights().get(0).getOneWayPrice()));
		
	}
	
	
	@Test
	public void readAirlineCompanyCSVFileWithThreeRecordTest() throws IOException, ParseException{		
		Files.deleteIfExists(Paths.get(projectPath + AirlineQuery.AIRLINECSV));
		Files.deleteIfExists(Paths.get(projectPath + AirlineQuery.FLIGHTCSV));
		
		Flight flightCP1 = new Flight();
		
		flightCP1.setAirline("Cathay Pacific Airways");
		flightCP1.setFlightNumber("CP001");
		flightCP1.setTravelClass("FIRST");
		flightCP1.setDepature("Hong Kong");
		flightCP1.setDestination("Taiwan");
		flightCP1.setDepatureDateTime(formatter.parse("2014-01-01 14:30:00"));
		flightCP1.setArrivalDateTime(formatter.parse("2014-01-01 17:30:00"));
		flightCP1.setAvailable(30);
		flightCP1.setOneWayPrice(2500.00);
		
		Flight flightCP2 = new Flight();
		
		flightCP2.setAirline("Cathay Pacific Airways");
		flightCP2.setFlightNumber("CP001");
		flightCP2.setTravelClass("BUSINESS");
		flightCP2.setDepature("Hong Kong");
		flightCP2.setDestination("Taiwan");
		flightCP2.setDepatureDateTime(formatter.parse("2014-01-01 14:30:00"));
		flightCP2.setArrivalDateTime(formatter.parse("2014-01-01 17:30:00"));
		flightCP2.setAvailable(50);
		flightCP2.setOneWayPrice(1500.00);
		
		Flight flightCP3 = new Flight();
		
		flightCP3.setAirline("Cathay Pacific Airways");
		flightCP3.setFlightNumber("CP001");
		flightCP3.setTravelClass("ECONOMY");
		flightCP3.setDepature("Hong Kong");
		flightCP3.setDestination("Taiwan");
		flightCP3.setDepatureDateTime(formatter.parse("2014-01-01 14:30:00"));
		flightCP3.setArrivalDateTime(formatter.parse("2014-01-01 17:30:00"));
		flightCP3.setAvailable(250);
		flightCP3.setOneWayPrice(700.00);
		
		ArrayList<Flight> flightsCP = new ArrayList<Flight>();
		flightsCP.add(flightCP1);
		flightsCP.add(flightCP2);
		flightsCP.add(flightCP3);
		
		Flight flightCA1 = new Flight();
		
		flightCA1.setAirline("China Airlines");
		flightCA1.setFlightNumber("CA001");
		flightCA1.setTravelClass("FIRST");
		flightCA1.setDepature("Hong Kong");
		flightCA1.setDestination("Shanghai");
		flightCA1.setDepatureDateTime(formatter.parse("2014-01-02 01:30:00"));
		flightCA1.setArrivalDateTime(formatter.parse("2014-01-02 03:30:00"));
		flightCA1.setAvailable(25);
		flightCA1.setOneWayPrice(1500.00);
		
		Flight flightCA2 = new Flight();
		
		flightCA2.setAirline("China Airlines");
		flightCA2.setFlightNumber("CA001");
		flightCA2.setTravelClass("BUSINESS");
		flightCA2.setDepature("Hong Kong");
		flightCA2.setDestination("Shanghai");
		flightCA2.setDepatureDateTime(formatter.parse("2014-01-02 01:30:00"));
		flightCA2.setArrivalDateTime(formatter.parse("2014-01-02 03:30:00"));
		flightCA2.setAvailable(50);
		flightCA2.setOneWayPrice(1100.00);
		
		Flight flightCA3 = new Flight();
		
		flightCA3.setAirline("China Airlines");
		flightCA3.setFlightNumber("CA001");
		flightCA3.setTravelClass("ECONOMY");
		flightCA3.setDepature("Hong Kong");
		flightCA3.setDestination("Shanghai");
		flightCA3.setDepatureDateTime(formatter.parse("2014-01-02 01:30:00"));
		flightCA3.setArrivalDateTime(formatter.parse("2014-01-02 03:30:00"));
		flightCA3.setAvailable(150);
		flightCA3.setOneWayPrice(650.00);
		
		ArrayList<Flight> flightsCA = new ArrayList<Flight>();
		flightsCA.add(flightCA1);
		flightsCA.add(flightCA2);
		flightsCA.add(flightCA3);
		
		Flight flightHKA1 = new Flight();
		
		flightHKA1.setAirline("Hong Kong Airlines");
		flightHKA1.setFlightNumber("HKA001");
		flightHKA1.setTravelClass("FIRST");
		flightHKA1.setDepature("Canada");
		flightHKA1.setDestination("Hong Kong");
		flightHKA1.setDepatureDateTime(formatter.parse("2014-12-30 19:30:00"));
		flightHKA1.setArrivalDateTime(formatter.parse("2014-12-31 15:30:00"));
		flightHKA1.setAvailable(30);
		flightHKA1.setOneWayPrice(15500.00);
		
		Flight flightHKA2 = new Flight();
		
		flightHKA2.setAirline("Hong Kong Airlines");
		flightHKA2.setFlightNumber("HKA001");
		flightHKA2.setTravelClass("BUSINESS");
		flightHKA2.setDepature("Canada");
		flightHKA2.setDestination("Hong Kong");
		flightHKA2.setDepatureDateTime(formatter.parse("2014-12-30 19:30:00"));
		flightHKA2.setArrivalDateTime(formatter.parse("2014-12-31 15:30:00"));
		flightHKA2.setAvailable(65);
		flightHKA2.setOneWayPrice(10500.00);
		
		Flight flightHKA3 = new Flight();
		
		flightHKA3.setAirline("Hong Kong Airlines");
		flightHKA3.setFlightNumber("HKA001");
		flightHKA3.setTravelClass("ECONOMY");
		flightHKA3.setDepature("Canada");
		flightHKA3.setDestination("Hong Kong");
		flightHKA3.setDepatureDateTime(formatter.parse("2014-12-30 19:30:00"));
		flightHKA3.setArrivalDateTime(formatter.parse("2014-12-31 15:30:00"));
		flightHKA3.setAvailable(300);
		flightHKA3.setOneWayPrice(7500.00);
		
		ArrayList<Flight> flightsHKA = new ArrayList<Flight>();
		flightsHKA.add(flightHKA1);
		flightsHKA.add(flightHKA2);
		flightsHKA.add(flightHKA3);
		
		ArrayList<ArrayList<Flight>> flightLists = new ArrayList<ArrayList<Flight>>();
		flightLists.add(flightsCP);
		flightLists.add(flightsCA);
		flightLists.add(flightsHKA);
		
		
		for (ArrayList<Flight> flightList : flightLists) {
			SourceWriter<List<Flight>> flightCSVFileWriter = new FlightCSVFileWriter(projectPath + AirlineQuery.FLIGHTCSV);
			flightCSVFileWriter.write(flightList);
			flightCSVFileWriter.close();
		}
		
		AirlineCompany airlineCompanyCP = new AirlineCompany("Cathay Pacific Airways");
		airlineCompanyCP.addFlight(flightCP1);
		airlineCompanyCP.addFlight(flightCP2);
		airlineCompanyCP.addFlight(flightCP3);
		
		AirlineCompany airlineCompanyCA = new AirlineCompany("China Airlines");
		airlineCompanyCA.addFlight(flightCA1);
		airlineCompanyCA.addFlight(flightCA2);
		airlineCompanyCA.addFlight(flightCA3);
		
		AirlineCompany airlineCompanyHKA = new AirlineCompany("Hong Kong Airlines");
		airlineCompanyHKA.addFlight(flightHKA1);
		airlineCompanyHKA.addFlight(flightHKA2);
		airlineCompanyHKA.addFlight(flightHKA3);
		
		ArrayList<AirlineCompany> airlineCompanies = new ArrayList<AirlineCompany>();
		airlineCompanies.add(airlineCompanyCP);
		airlineCompanies.add(airlineCompanyCA);
		airlineCompanies.add(airlineCompanyHKA);

		SourceWriter<List<AirlineCompany>> airlineCompanyCSVFileWriter = new AirlineCompanyCSVFileWriter(projectPath + AirlineQuery.AIRLINECSV);
		airlineCompanyCSVFileWriter.write(airlineCompanies);
		airlineCompanyCSVFileWriter.close();
		
		FlightCSVFileReader flightCSVFileReader = new FlightCSVFileReader(projectPath + AirlineQuery.FLIGHTCSV);
		SourceReader<AirlineCompany> airlineCompanyCSVFileReader = new AirlineCompanyCSVFileReader(projectPath + AirlineQuery.AIRLINECSV, flightCSVFileReader);
		List<AirlineCompany> airlineCompanyResultList = airlineCompanyCSVFileReader.read();
		airlineCompanyCSVFileReader.close();
		
		assertThat(airlineCompanies.size(), is(airlineCompanyResultList.size()));
		
		for (int i = 0; i < airlineCompanies.size() ; i++) {
			assertThat(airlineCompanies.get(i).getAirline(), is(airlineCompanyResultList.get(i).getAirline()));
		}
		
		assertThat(airlineCompanyCP.getFlights().size(), is(airlineCompanyResultList.get(0).getFlights().size()));
		
		for (int i = 0; i < airlineCompanyCP.getFlights().size() ; i++) {
			assertThat(airlineCompanyCP.getFlights().get(i).getAirline(), is(airlineCompanyResultList.get(0).getFlights().get(i).getAirline()));
			assertThat(airlineCompanyCP.getFlights().get(i).getFlightNumber(), is(airlineCompanyResultList.get(0).getFlights().get(i).getFlightNumber()));
			assertThat(airlineCompanyCP.getFlights().get(i).getTravelClass(), is(airlineCompanyResultList.get(0).getFlights().get(i).getTravelClass()));
			assertThat(airlineCompanyCP.getFlights().get(i).getDepature(), is(airlineCompanyResultList.get(0).getFlights().get(i).getDepature()));
			assertThat(airlineCompanyCP.getFlights().get(i).getDestination(), is(airlineCompanyResultList.get(0).getFlights().get(i).getDestination()));
			assertThat(airlineCompanyCP.getFlights().get(i).getDepatureDateTime(), is(airlineCompanyResultList.get(0).getFlights().get(i).getDepatureDateTime()));
			assertThat(airlineCompanyCP.getFlights().get(i).getArrivalDateTime(), is(airlineCompanyResultList.get(0).getFlights().get(i).getArrivalDateTime()));
			assertThat(airlineCompanyCP.getFlights().get(i).getAvailable(), is(airlineCompanyResultList.get(0).getFlights().get(i).getAvailable()));
			assertThat(airlineCompanyCP.getFlights().get(i).getOneWayPrice(), is(airlineCompanyResultList.get(0).getFlights().get(i).getOneWayPrice()));
		}
		
		assertThat(airlineCompanyCA.getFlights().size(), is(airlineCompanyResultList.get(1).getFlights().size()));
		
		for (int i = 0; i < airlineCompanyCA.getFlights().size() ; i++) {
			assertThat(airlineCompanyCA.getFlights().get(i).getAirline(), is(airlineCompanyResultList.get(1).getFlights().get(i).getAirline()));
			assertThat(airlineCompanyCA.getFlights().get(i).getFlightNumber(), is(airlineCompanyResultList.get(1).getFlights().get(i).getFlightNumber()));
			assertThat(airlineCompanyCA.getFlights().get(i).getTravelClass(), is(airlineCompanyResultList.get(1).getFlights().get(i).getTravelClass()));
			assertThat(airlineCompanyCA.getFlights().get(i).getDepature(), is(airlineCompanyResultList.get(1).getFlights().get(i).getDepature()));
			assertThat(airlineCompanyCA.getFlights().get(i).getDestination(), is(airlineCompanyResultList.get(1).getFlights().get(i).getDestination()));
			assertThat(airlineCompanyCA.getFlights().get(i).getDepatureDateTime(), is(airlineCompanyResultList.get(1).getFlights().get(i).getDepatureDateTime()));
			assertThat(airlineCompanyCA.getFlights().get(i).getArrivalDateTime(), is(airlineCompanyResultList.get(1).getFlights().get(i).getArrivalDateTime()));
			assertThat(airlineCompanyCA.getFlights().get(i).getAvailable(), is(airlineCompanyResultList.get(1).getFlights().get(i).getAvailable()));
			assertThat(airlineCompanyCA.getFlights().get(i).getOneWayPrice(), is(airlineCompanyResultList.get(1).getFlights().get(i).getOneWayPrice()));
		}
		
		assertThat(airlineCompanyHKA.getFlights().size(), is(airlineCompanyResultList.get(2).getFlights().size()));
		
		for (int i = 0; i < airlineCompanyHKA.getFlights().size() ; i++) {
			assertThat(airlineCompanyHKA.getFlights().get(i).getAirline(), is(airlineCompanyResultList.get(2).getFlights().get(i).getAirline()));
			assertThat(airlineCompanyHKA.getFlights().get(i).getFlightNumber(), is(airlineCompanyResultList.get(2).getFlights().get(i).getFlightNumber()));
			assertThat(airlineCompanyHKA.getFlights().get(i).getTravelClass(), is(airlineCompanyResultList.get(2).getFlights().get(i).getTravelClass()));
			assertThat(airlineCompanyHKA.getFlights().get(i).getDepature(), is(airlineCompanyResultList.get(2).getFlights().get(i).getDepature()));
			assertThat(airlineCompanyHKA.getFlights().get(i).getDestination(), is(airlineCompanyResultList.get(2).getFlights().get(i).getDestination()));
			assertThat(airlineCompanyHKA.getFlights().get(i).getDepatureDateTime(), is(airlineCompanyResultList.get(2).getFlights().get(i).getDepatureDateTime()));
			assertThat(airlineCompanyHKA.getFlights().get(i).getArrivalDateTime(), is(airlineCompanyResultList.get(2).getFlights().get(i).getArrivalDateTime()));
			assertThat(airlineCompanyHKA.getFlights().get(i).getAvailable(), is(airlineCompanyResultList.get(2).getFlights().get(i).getAvailable()));
			assertThat(airlineCompanyHKA.getFlights().get(i).getOneWayPrice(), is(airlineCompanyResultList.get(2).getFlights().get(i).getOneWayPrice()));
		}
		
		
	}
	
	
}

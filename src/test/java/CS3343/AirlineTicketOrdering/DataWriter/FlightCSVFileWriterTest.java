package CS3343.AirlineTicketOrdering.DataWriter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import CS3343.AirlineTicketOrdering.DataReader.CSVFile;
import CS3343.AirlineTicketOrdering.DataReader.SourceReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.FlightCSVFileReader;
import CS3343.AirlineTicketOrdering.DataWriter.Impl.FlightCSVFileWriter;
import CS3343.AirlineTicketOrdering.Model.Flight;

public class FlightCSVFileWriterTest {
	
	private File projectPath;
	private SimpleDateFormat formatter;
	
	@Before
	public void setUp() throws IOException {
		projectPath = new File(".").getCanonicalFile(); 
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	@Test
	public void writeFlightCSVFileOnce() throws IOException, ParseException {
		Files.deleteIfExists(Paths.get(projectPath + CSVFile.FLIGHTCSV.value()));
		
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

		SourceWriter<List<Flight>> flightCSVFileWriter = new FlightCSVFileWriter(projectPath + CSVFile.FLIGHTCSV.value());
		flightCSVFileWriter.write(flights);
		flightCSVFileWriter.close();
		
		SourceReader<Flight> flightCSVFileReader = new FlightCSVFileReader(projectPath + CSVFile.FLIGHTCSV.value());
		List<Flight> flightResultList = (ArrayList<Flight>) flightCSVFileReader.read();

		assertThat(true, is(Files.exists(Paths.get(projectPath + CSVFile.FLIGHTCSV.value()))));
		assertThat(1, is(flightResultList.size()));
		for (Flight flightResult : flightResultList) {
			assertThat(flightResult.getAirline(), is(flight.getAirline()));
			assertThat(flightResult.getFlightNumber(), is(flight.getFlightNumber()));
			assertThat(flightResult.getTravelClass(), is(flight.getTravelClass()));
			assertThat(flightResult.getDepature(), is(flight.getDepature()));
			assertThat(flightResult.getDestination(), is(flight.getDestination()));
			assertThat(flightResult.getDepatureDateTime(), is(flight.getDepatureDateTime()));
			assertThat(flightResult.getArrivalDateTime(), is(flight.getArrivalDateTime()));
			assertThat(flightResult.getAvailable(), is(flight.getAvailable()));
			assertThat(flightResult.getOneWayPrice(), is(flight.getOneWayPrice()));
		}
	}
	
	@Test
	public void writeFlightCSVFileThreeTimes() throws IOException, ParseException {
		Files.deleteIfExists(Paths.get(projectPath + CSVFile.FLIGHTCSV.value()));
		
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
		
		List<Flight> flightsCP = new ArrayList<Flight>();
		flightsCP.add(flightCP1);
		flightsCP.add(flightCP2);
		flightsCP.add(flightCP3);
		
		SourceWriter<List<Flight>> flightCSVFileWriter = new FlightCSVFileWriter(projectPath + CSVFile.FLIGHTCSV.value());
		flightCSVFileWriter.write(flightsCP);
		flightCSVFileWriter.close();
		
		SourceReader<Flight> flightCSVFileReader = new FlightCSVFileReader(projectPath + CSVFile.FLIGHTCSV.value());
		List<Flight> flightResultList = (ArrayList<Flight>) flightCSVFileReader.read();

		assertThat(true, is(Files.exists(Paths.get(projectPath + CSVFile.FLIGHTCSV.value()))));
		assertThat(3, is(flightResultList.size()));
		for (int i = 0 ; i < flightsCP.size() ; i++) {
			assertThat(flightsCP.get(i).getAirline(), is(flightResultList.get(i).getAirline()));
			assertThat(flightsCP.get(i).getFlightNumber(), is(flightResultList.get(i).getFlightNumber()));
			assertThat(flightsCP.get(i).getTravelClass(), is(flightResultList.get(i).getTravelClass()));
			assertThat(flightsCP.get(i).getDepature(), is(flightResultList.get(i).getDepature()));
			assertThat(flightsCP.get(i).getDestination(), is(flightResultList.get(i).getDestination()));
			assertThat(flightsCP.get(i).getDepatureDateTime(), is(flightResultList.get(i).getDepatureDateTime()));
			assertThat(flightsCP.get(i).getArrivalDateTime(), is(flightResultList.get(i).getArrivalDateTime()));
			assertThat(flightsCP.get(i).getAvailable(), is(flightResultList.get(i).getAvailable()));
			assertThat(flightsCP.get(i).getOneWayPrice(), is(flightResultList.get(i).getOneWayPrice()));
		}
	}
	
}

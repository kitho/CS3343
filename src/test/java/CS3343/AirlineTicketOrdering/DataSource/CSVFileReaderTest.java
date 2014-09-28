package CS3343.AirlineTicketOrdering.DataSource;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import CS3343.AirlineTicketOrdering.Model.AirlineCompany;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.TestingTools.AirlineCompanyCSVFileWriter;
import CS3343.AirlineTicketOrdering.TestingTools.FlightCSVFileWriter;

public class CSVFileReaderTest {

	private File projectPath;
	private SimpleDateFormat formatter;
	
	@Before
	public void setUp() throws IOException{
		projectPath = new File(".").getCanonicalFile(); 
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	@Test
	public void readAirlineCompanyCSVFileWithOneRecordTest() throws IOException, ParseException{
		
		AirlineCompany airlineCompany = new AirlineCompany("Cathay Pacific Airways");
		
		ArrayList<AirlineCompany> airlineCompanies = new ArrayList<AirlineCompany>();
		airlineCompanies.add(airlineCompany);
		
		AirlineCompanyCSVFileWriter csvWriter = new AirlineCompanyCSVFileWriter(projectPath + AirlineCSVDataSource.AIRLINECSV);
		csvWriter.write(airlineCompanies);
		csvWriter.close();
		
		AirlineCompanyCSVFileReader csvReader = new AirlineCompanyCSVFileReader(projectPath + AirlineCSVDataSource.AIRLINECSV);
		List<AirlineCompany> resultList = csvReader.read();
		csvReader.close();
		
		assertThat(airlineCompany.getAirline(), is(resultList.get(0).getAirline()));
			
	}
	
	
	@Test
	public void readFlightCSVFileWithOneRecordTest() throws IOException, ParseException{

		Flight flight = new Flight();
		
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
		
		FlightCSVFileWriter csvWriter = new FlightCSVFileWriter(projectPath + AirlineCSVDataSource.FLIGHTCSV);
		csvWriter.write(flights);
		csvWriter.close();
		
		FlightCSVFileReader csvReader= new FlightCSVFileReader(projectPath + AirlineCSVDataSource.FLIGHTCSV);
		List<Flight> resultList = csvReader.read();
		csvReader.close();
		
		assertThat(flight.getFlightNumber(), is(resultList.get(0).getFlightNumber()));
		assertThat(flight.getTravelClass(), is(resultList.get(0).getTravelClass()));
		assertThat(flight.getDepature(), is(resultList.get(0).getDepature()));
		assertThat(flight.getDestination(), is(resultList.get(0).getDestination()));
		assertThat(flight.getDepatureDateTime(), is(resultList.get(0).getDepatureDateTime()));
		assertThat(flight.getArrivalDateTime(), is(resultList.get(0).getArrivalDateTime()));
		assertThat(flight.getAvailable(), is(resultList.get(0).getAvailable()));
		assertThat(flight.getOneWayPrice(), is(resultList.get(0).getOneWayPrice()));

	}
}

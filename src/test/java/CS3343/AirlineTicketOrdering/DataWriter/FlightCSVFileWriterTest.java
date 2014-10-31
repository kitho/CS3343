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
	public void writeFlightCSVFile() throws IOException, ParseException {
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
		
		ArrayList<Flight> flights = new ArrayList<Flight>();
		flights.add(flight);

		SourceWriter<List<Flight>> flightCSVFileWriter = new FlightCSVFileWriter(projectPath + CSVFile.FLIGHTCSV.value());
		flightCSVFileWriter.write(flights);
		flightCSVFileWriter.close();

		assertThat(true, is(Files.exists(Paths.get(projectPath + CSVFile.FLIGHTCSV.value()))));
		
	}
	
}

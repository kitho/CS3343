package CS3343.AirlineTicketOrdering.DataSource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import CS3343.AirlineTicketOrdering.Model.Flight;

public class FlightCSVFileReader extends CSVFileReader<Flight> {

	public FlightCSVFileReader(String path) throws FileNotFoundException {
		super(path);
	}

	@Override
	public List<Flight> read() throws IOException, ParseException {
		
		SimpleDateFormat formatter = new SimpleDateFormat(DateFormat.getDateInstance(DateFormat.FULL, Locale.getDefault()).format(new Date(0)));
		List<Flight> flights = new ArrayList<Flight>();
		
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			String[] dataStr = line.split(",");
			Flight flight = new Flight();
			flight.setFlightNumber(dataStr[0]);	
			flight.setTravelClass(dataStr[1]);
			flight.setDepature(dataStr[2]);
			flight.setDestination(dataStr[3]);
			flight.setDepatureDateTime(formatter.parse(dataStr[4]));
			flight.setArrivalDateTime(formatter.parse(dataStr[5]));
			flight.setAvailable(Integer.parseInt(dataStr[6]));
			flight.setOneWayPrice(Double.parseDouble(dataStr[7]));
			flights.add(flight);
		}
		
		return flights;
	}

}

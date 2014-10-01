package CS3343.AirlineTicketOrdering.DataReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import CS3343.AirlineTicketOrdering.Model.Flight;

public class FlightCSVFileReader extends FileReader<Flight> {

	public FlightCSVFileReader(String path) throws FileNotFoundException {
		super(path);
	}

	@Override
	public List<Flight> read() throws IOException, ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat(
				"EEE MMM dd HH:mm:ss Z yyyy");
		List<Flight> flights = new ArrayList<Flight>();

		String line;
		while ((line = bufferedReader.readLine()) != null) {
			String[] dataStr = line.split(",");
			Flight flight = new Flight();
			flight.setAirline(dataStr[0]);
			flight.setFlightNumber(dataStr[1]);
			flight.setTravelClass(dataStr[2]);
			flight.setDepature(dataStr[3]);
			flight.setDestination(dataStr[4]);
			flight.setDepatureDateTime(formatter.parse(dataStr[5]));
			flight.setArrivalDateTime(formatter.parse(dataStr[6]));
			flight.setAvailable(Integer.parseInt(dataStr[7]));
			flight.setOneWayPrice(Double.parseDouble(dataStr[8]));
			flight.setModel(dataStr[9]);
			flights.add(flight);
		}

		return flights;
	}

}

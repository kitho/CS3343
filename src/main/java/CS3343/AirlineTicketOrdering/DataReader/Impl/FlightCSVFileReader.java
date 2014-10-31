package CS3343.AirlineTicketOrdering.DataReader.Impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import CS3343.AirlineTicketOrdering.DataReader.FileReader;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Parser.ModelParser;
import CS3343.AirlineTicketOrdering.Parser.Impl.FlightParser;

public class FlightCSVFileReader extends FileReader<Flight> {

	public FlightCSVFileReader(String path) throws FileNotFoundException {
		super(path);
	}

	@Override
	public List<Flight> read() throws IOException, ParseException {

		List<Flight> flights = new ArrayList<Flight>();
		ModelParser<Flight> flightParser = new FlightParser();

		String line;
		while ((line = bufferedReader.readLine()) != null) {
			Flight flight = flightParser.parse(line);
			flights.add(flight);
		}

		return flights;
	}

}

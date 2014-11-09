package CS3343.AirlineTicketOrdering.DataReader.Impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import CS3343.AirlineTicketOrdering.DataReader.CSVFileReader;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Parser.Parser;

public class FlightCSVFileReader extends CSVFileReader<Flight> {

	public FlightCSVFileReader(String path) throws FileNotFoundException {
		super(path);
	}

	@Override
	public List<Flight> read(Parser<Flight> parser) throws IOException, ParseException {

		List<Flight> flights = new ArrayList<Flight>();

		String line;
		while ((line = bufferedReader.readLine()) != null) {
			flights.add(parser.parseString(line));
		}

		return flights;
	}

}

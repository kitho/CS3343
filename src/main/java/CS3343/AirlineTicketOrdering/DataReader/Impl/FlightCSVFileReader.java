package CS3343.AirlineTicketOrdering.DataReader.Impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import CS3343.AirlineTicketOrdering.DataReader.CSVFileReader;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Parser.Parser;

/**
 * The Class FlightCSVFileReader.
 */
public class FlightCSVFileReader extends CSVFileReader<Flight> {

	/**
	 * Instantiates a new flight csv file reader.
	 *
	 * @param path the path
	 * @throws FileNotFoundException the file not found exception
	 */
	public FlightCSVFileReader(String path) throws FileNotFoundException {
		super(path);
	}

	/**
	 * Read the flight from CSV file
	 * and return the all the flights
	 * 
	 * @param parser
	 * @return list of flight
	 */
	@Override
	public List<Flight> read(Parser<Flight> parser) throws IOException, ParseException {

		List<Flight> flights = new ArrayList<Flight>();

		String line;
		while ((line = bufferedReader.readLine()) != null) {
			flights.add(parser.parseString(line));
		}
		bufferedReader.close();
		fileReader.close();
		return flights;
	}

}

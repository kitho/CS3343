package CS3343.AirlineTicketOrdering.DataReader.Impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import CS3343.AirlineTicketOrdering.DataReader.CSVFileReader;
import CS3343.AirlineTicketOrdering.Model.Route;
import CS3343.AirlineTicketOrdering.Parser.Parser;

/**
 * The Class RouteCSVFileReader.
 */
public class RouteCSVFileReader extends CSVFileReader<Route> {

	/**
	 * Instantiates a new route csv file reader.
	 *
	 * @param path the path
	 * @throws FileNotFoundException the file not found exception
	 */
	public RouteCSVFileReader(String path) throws FileNotFoundException {
		super(path);
	}

	/**
	 * Read the Route from CSV file
	 * and return the all the Routes
	 * 
	 * @param parser
	 * @return list of Routes
	 */
	@Override
	public ArrayList<Route> read(Parser<Route> parser) throws IOException, ParseException {

		ArrayList<Route> routes = new ArrayList<Route>();

		String line;
		while ((line = bufferedReader.readLine()) != null) {
			routes.add(parser.parseString(line));
		}
		bufferedReader.close();
		fileReader.close();
		return routes;
	}

}

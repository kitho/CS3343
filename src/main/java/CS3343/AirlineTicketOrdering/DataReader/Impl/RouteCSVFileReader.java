package CS3343.AirlineTicketOrdering.DataReader.Impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import CS3343.AirlineTicketOrdering.DataReader.CSVFileReader;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Model.Route;
import CS3343.AirlineTicketOrdering.Parser.Parser;

public class RouteCSVFileReader extends CSVFileReader<Route> {

	public RouteCSVFileReader(String path) throws FileNotFoundException {
		super(path);
	}

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

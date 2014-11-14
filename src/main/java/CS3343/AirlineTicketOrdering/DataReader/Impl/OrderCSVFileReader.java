package CS3343.AirlineTicketOrdering.DataReader.Impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import CS3343.AirlineTicketOrdering.DataReader.CSVFileReader;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Model.Order;
import CS3343.AirlineTicketOrdering.Parser.Parser;

public class OrderCSVFileReader extends CSVFileReader<Order> {

	public OrderCSVFileReader(String path) throws FileNotFoundException {
		super(path);
	}

	@Override
	public List<Order> read(Parser<Order> parser) throws IOException, ParseException {

		List<Order> orders = new ArrayList<Order>();
		
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			orders.add(parser.parseString(line));
		}

		return orders;
	}


}

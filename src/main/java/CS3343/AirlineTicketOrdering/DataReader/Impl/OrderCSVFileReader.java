package CS3343.AirlineTicketOrdering.DataReader.Impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import CS3343.AirlineTicketOrdering.DataReader.CSVFileReader;
import CS3343.AirlineTicketOrdering.Model.Order;
import CS3343.AirlineTicketOrdering.Parser.Parser;

/**
 * The Class OrderCSVFileReader.
 */
public class OrderCSVFileReader extends CSVFileReader<Order> {

	/**
	 * Instantiates a new order csv file reader.
	 *
	 * @param path the path
	 * @throws FileNotFoundException the file not found exception
	 */
	public OrderCSVFileReader(String path) throws FileNotFoundException {
		super(path);
	}

	/**
	 * Read the Order from CSV file
	 * and return the all the Orders
	 * 
	 * @param parser
	 * @return list of Orders
	 */
	@Override
	public List<Order> read(Parser<Order> parser) throws IOException, ParseException {

		List<Order> orders = new ArrayList<Order>();
		
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			orders.add(parser.parseString(line));
		}
		bufferedReader.close();
		fileReader.close();
		return orders;
	}


}

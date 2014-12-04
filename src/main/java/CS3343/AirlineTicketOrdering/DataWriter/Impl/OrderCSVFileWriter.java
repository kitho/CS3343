package CS3343.AirlineTicketOrdering.DataWriter.Impl;

import java.io.IOException;
import java.util.List;

import CS3343.AirlineTicketOrdering.DataWriter.CSVFileWriter;
import CS3343.AirlineTicketOrdering.Model.Order;
import CS3343.AirlineTicketOrdering.Parser.Parser;
import CS3343.AirlineTicketOrdering.Parser.Impl.OrderParser;

/**
 * The Class OrderCSVFileWriter.
 */
public class OrderCSVFileWriter extends CSVFileWriter<List<Order>> {

	/**
	 * Instantiates a new order csv file writer.
	 *
	 * @param path the path
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public OrderCSVFileWriter(String path) throws IOException {
		super(path);
	}

	/**
	 * Save all the orders into the CSV file
	 * 
	 * @param List of flights
	 */
	@Override
	public void write(List<Order> orders) throws IOException {
		Parser<Order> orderParser = new OrderParser();
		
		for (Order order : orders) {
			String dataString = orderParser.parseObject(order); 
			bufferedWriter.write(dataString);
			bufferedWriter.newLine();
		}
		bufferedWriter.close();
		fileWriter.close();
	}

}

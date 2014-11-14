package CS3343.AirlineTicketOrdering.DataWriter.Impl;

import java.io.IOException;
import java.util.List;

import CS3343.AirlineTicketOrdering.DataWriter.CSVFileWriter;
import CS3343.AirlineTicketOrdering.Model.Order;
import CS3343.AirlineTicketOrdering.Parser.Parser;
import CS3343.AirlineTicketOrdering.Parser.Impl.OrderParser;

public class OrderCSVFileWriter extends CSVFileWriter<List<Order>> {

	public OrderCSVFileWriter(String path) throws IOException {
		super(path);
	}

	@Override
	public void write(List<Order> orders) throws IOException {
		Parser<Order> orderParser = new OrderParser();
		
		for (Order order : orders) {
			String dataString = orderParser.parseObject(order); 
			bufferedWriter.write(dataString);
			bufferedWriter.newLine();
		}
	}

}

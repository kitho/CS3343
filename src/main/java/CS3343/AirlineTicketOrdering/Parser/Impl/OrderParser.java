package CS3343.AirlineTicketOrdering.Parser.Impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import CS3343.AirlineTicketOrdering.CustomDateUtil.CustomDateFormatter;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Model.Order;
import CS3343.AirlineTicketOrdering.Parser.Parser;

/**
 * The Class OrderParser.
 */
public class OrderParser implements Parser<Order> {
	
	/** The formatter. */
	private CustomDateFormatter formatter;
	
	/**
	 * Instantiates a new order parser.
	 */
	public OrderParser(){
		formatter = new CustomDateFormatter();
	}
	
	/**
	 * Parse the String into the Order object
	 * 
	 * @param line
	 * 
	 * @return Order
	 */
	public Order parseString(String line) throws ParseException {
		String[] dataStr = line.split(",");
		Order order = new Order();
		Flight flight = new Flight();
		order.setId(Integer.parseInt(dataStr[0]));
		flight.setAirline(dataStr[1]);
		flight.setFlightNumber(dataStr[2]);
		flight.setTravelClass(dataStr[3]);
		flight.setDepature(dataStr[4]);
		flight.setDestination(dataStr[5]);
		flight.setDepatureDateTime(formatter.parse(dataStr[6]));
		flight.setArrivalDateTime(formatter.parse(dataStr[7]));
		flight.setAvailable(Integer.parseInt(dataStr[8]));
		flight.setOneWayPrice(Double.parseDouble(dataStr[9]));
		order.setFlight(flight);
		order.setNumberOfTicket(Integer.parseInt(dataStr[10]));
		return order;
	}

	/**
	 * Parse the Order Object into the string
	 * 
	 * @param Order
	 * 
	 * @return line
	 * 
	 */
	public String parseObject(Order order) {
		List<String> dataList = new ArrayList<String>();
		Flight flight = order.getFlight();
		dataList.add(order.getId() + "");
		dataList.add(flight.getAirline());
		dataList.add(flight.getFlightNumber());
		dataList.add(flight.getTravelClass());
		dataList.add(flight.getDepature());
		dataList.add(flight.getDestination());
		dataList.add(formatter.format(flight.getDepatureDateTime()));
		dataList.add(formatter.format(flight.getArrivalDateTime()));
		dataList.add(String.valueOf(flight.getAvailable()));
		dataList.add(String.valueOf(flight.getOneWayPrice()));
		dataList.add(order.getNumberOfTicket()+"");

		String line = StringUtils.join(dataList, ",");
		
		return line;

	}

}

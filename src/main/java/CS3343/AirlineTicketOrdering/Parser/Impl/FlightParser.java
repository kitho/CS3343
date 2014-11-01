package CS3343.AirlineTicketOrdering.Parser.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Parser.Parser;

public class FlightParser implements Parser<Flight> {
	
	private SimpleDateFormat formatter;
	
	public FlightParser(){
		formatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
	}

	public Flight parseString(String line) throws ParseException {
		
		String[] dataStr = line.split(",");
		Flight flight = new Flight();
		flight.setAirline(dataStr[0]);
		flight.setFlightNumber(dataStr[1]);
		flight.setTravelClass(dataStr[2]);
		flight.setDepature(dataStr[3]);
		flight.setDestination(dataStr[4]);
		flight.setDepatureDateTime(formatter.parse(dataStr[5]));
		flight.setArrivalDateTime(formatter.parse(dataStr[6]));
		flight.setAvailable(Integer.parseInt(dataStr[7]));
		flight.setOneWayPrice(Double.parseDouble(dataStr[8]));
		flight.setModel(dataStr[9]);
		flight.setMealIds(dataStr[10]);
		flight.setFoodIds(dataStr[11]);
		
		return flight;
	}

	public String parseObject(Flight flight) {

		List<String> dataList = new ArrayList<String>();
		dataList.add(flight.getAirline());
		dataList.add(flight.getFlightNumber());
		dataList.add(flight.getTravelClass());
		dataList.add(flight.getDepature());
		dataList.add(flight.getDestination());
		dataList.add(formatter.format(flight.getDepatureDateTime()));
		dataList.add(formatter.format(flight.getArrivalDateTime()));
		dataList.add(String.valueOf(flight.getAvailable()));
		dataList.add(String.valueOf(flight.getOneWayPrice()));
		dataList.add(flight.getModel());
		dataList.add(flight.getMealIds());
		dataList.add(flight.getFoodIds());
		
		String line = StringUtils.join(dataList, ",");
		
		return line;
	}

	
	
}

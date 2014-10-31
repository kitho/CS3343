package CS3343.AirlineTicketOrdering.Parser.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Parser.ModelParser;

public class FlightParser implements ModelParser<Flight> {
	
	private SimpleDateFormat formatter;
	
	public FlightParser(){
		formatter = new SimpleDateFormat(
				"EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
	}

	public Flight parse(String line) throws ParseException {
		
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

}

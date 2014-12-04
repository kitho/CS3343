package CS3343.AirlineTicketOrdering.Parser.Impl;

import java.text.ParseException;

import CS3343.AirlineTicketOrdering.Model.Route;
import CS3343.AirlineTicketOrdering.Parser.Parser;

/**
 * The Class RouteParser.
 */
public class RouteParser implements Parser<Route> {
	
	/**
	 * Parse the String into the Route object
	 * 
	 * @param line
	 * 
	 * @return Route
	 */
	public Route parseString(String line) throws ParseException {
		String[] dataStr = line.split(",");
		Route route = new Route();
		route.setDeparture(dataStr[0]);
		route.setDestination(dataStr[1]);
		route.setDistance(Integer.parseInt(dataStr[2]));
		
		return route;
	}

	/**
	 * Parse the Route Object into the string
	 * 
	 * @param Route
	 * 
	 * @return null
	 * 
	 */
	public String parseObject(Route route) {

		return null;
	}

	
	
}

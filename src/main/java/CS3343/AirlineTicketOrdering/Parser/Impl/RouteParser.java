package CS3343.AirlineTicketOrdering.Parser.Impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import CS3343.AirlineTicketOrdering.CustomDateUtil.CustomDateFormatter;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Model.Route;
import CS3343.AirlineTicketOrdering.Parser.Parser;

public class RouteParser implements Parser<Route> {
	
	public Route parseString(String line) throws ParseException {
		String[] dataStr = line.split(",");
		Route route = new Route();
		route.setDeparture(dataStr[0]);
		route.setDestination(dataStr[1]);
		route.setDistance(Integer.parseInt(dataStr[2]));
		
		return route;
	}

	public String parseObject(Route route) {

		return null;
	}

	
	
}

package CS3343.AirlineTicketOrdering.DataQuery;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import CS3343.AirlineTicketOrdering.DataReader.SourceReader;
import CS3343.AirlineTicketOrdering.DataWriter.SourceWriter;
import CS3343.AirlineTicketOrdering.Model.*;
import CS3343.AirlineTicketOrdering.Parser.Impl.AirlineCompanyParser;
import CS3343.AirlineTicketOrdering.Parser.Impl.FlightParser;
import CS3343.AirlineTicketOrdering.Parser.Impl.RouteParser;


/**
 * The Class RouteQuery.
 */
public class RouteQuery {
	
	/** The routes. */
	private ArrayList<Route> routes;
	
	/**
	 * Instantiates a new route query.
	 *
	 * @param routeReader the route reader
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 */
	public RouteQuery(SourceReader<Route> routeReader) throws IOException, ParseException{
		routes = (ArrayList<Route>) routeReader.read(new RouteParser());

	}
	
	/**
	 * Gets the all route.
	 *
	 * @param flights the flights
	 * @return the all route
	 */
	public List<Route> getAllRoute(List<Flight> flights){
		for(int i = 0; i < flights.size(); i++){
			Flight flight = flights.get(i);
			boolean exist = false;
			for(int j = 0 ; j < routes.size(); j++){
				Route route = routes.get(j);
				if(flight.getDepature().equals(route.getDeparture()) && flight.getDestination().equals(route.getDestination())){
					routes.get(j).addFlight(flight);
					exist = true;
				}
			}
			
		}
		return routes;
	}



}

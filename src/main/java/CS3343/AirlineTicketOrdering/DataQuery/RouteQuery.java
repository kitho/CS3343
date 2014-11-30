package CS3343.AirlineTicketOrdering.DataQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import CS3343.AirlineTicketOrdering.Model.*;

public class RouteQuery {
	public List<Route> getAllRoute(List<Flight> flights){
		ArrayList<Route> routes = new ArrayList<Route>();
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
			
			if (!exist) {
				Route temp = new Route();
				temp.setDeparture(flight.getDepature());
				temp.setDestination(flight.getDestination());
				temp.setDistance((int)(Math.random() * 3000 + 1));
				temp.addFlight(flight);
				routes.add(temp);
			}
		}
		return routes;
	}

}

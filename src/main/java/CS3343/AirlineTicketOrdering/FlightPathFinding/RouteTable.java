package CS3343.AirlineTicketOrdering.FlightPathFinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import CS3343.AirlineTicketOrdering.Model.*;

public class RouteTable {
	private ArrayList<Route> routeList;
	/**
	 * Instantiates a new Path finding
	 */
	public RouteTable(){
		routeList = new ArrayList<Route>();		
	}
	
	/**
	 * Instantiates a new Path finding
	 *
	 * @param departure
	 * @param destination
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void addRouteRow(String depart, String dest, int distance){
		Route r = new Route();
		r.setDeparture(depart);
		r.setDestination(dest);
		r.setDistance(distance);
		/* Data for testing, should use the real data in CSV file.
		List<Flight> fList = fetchAllFlights();
		
		FlightListFilter flf = new FlightListFilter(fList);
		r.setFlights(flf.filterByDepartNDest(depart, dest));
		Data for testing, should use the real data in CSV file. */
		
		routeList.add(r);
	}
	
	
	public ArrayList<Route> getRouteList() {
		return routeList;
	}
	public void setRouteList(ArrayList<Route> routeList) {
		this.routeList = routeList;
	}
	
	/**
	 * find all route that contain that departure
  	 * @param Departure String
	 * @return Route ArrayList
	 */
	public ArrayList<Route> findRouteDepart(String departure){
		ArrayList<Route> resultRoutes = new ArrayList<Route>();
		for (int i = 0; i < routeList.size(); i++){
			Route tempRoute = routeList.get(i);
			if (tempRoute.getDeparture().equals(departure))
				resultRoutes.add(tempRoute);
		}
		return resultRoutes;		
	}
	
	/**
	 * check the route is valid or not
	 *
	 * @return Route
	 * 
	 */
	public Route findRoute(String departure, String destination){
		for (int i = 0; i < routeList.size(); i++){
			Route tempRoute = routeList.get(i);
			if (tempRoute.getDeparture() == departure && tempRoute.getDestination() == destination)
				return tempRoute;
		}
		return null;		
	}
	
	
}

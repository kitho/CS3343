package CS3343.AirlineTicketOrdering.FlightPathFinding;

import java.util.ArrayList;
import java.util.List;

import CS3343.AirlineTicketOrdering.Filter.FlightListFilter;
import CS3343.AirlineTicketOrdering.Model.*;

public class RouteTable {
	private ArrayList<Route> routeList;
	public RouteTable(){
		routeList = new ArrayList<Route>();
		addRouteRow("Hong Kong", "Tokyo",2887);
		addRouteRow("Tokyo", "Taiwan",2101);
		addRouteRow("Tokyo", "Thailand",4426);
		addRouteRow("Hong Kong", "Thailand",1695);
		addRouteRow("Hong Kong", "Taiwan",816);
		addRouteRow("Thailand", "Taiwan",2374);
		addRouteRow("Tokyo", "Singapore",5329);
		addRouteRow("Hong Kong", "Singapore",2600);
		addRouteRow("Singapore", "Taiwan",3263);
		addRouteRow("Tokyo", "USA",10123);
		addRouteRow("Singapore", "Hong Kong",1000);
		
		
	}
	
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
		//this.routeList = routeList;
	}
}

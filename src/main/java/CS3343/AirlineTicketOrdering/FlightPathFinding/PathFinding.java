package CS3343.AirlineTicketOrdering.FlightPathFinding;

import java.util.ArrayList;

import CS3343.AirlineTicketOrdering.Model.Route;

public class PathFinding {
	private String from;
	private String to;
	private RouteTable routeTable;
	
	
	public PathFinding(String f, String t){
		from = f;
		to = t;
		routeTable = new RouteTable();
	}
	
	public ArrayList<Route> getDirectFlight(){
		ArrayList<Route> routeList = routeTable.getRouteList();
		ArrayList<Route> resultRouteList = new ArrayList<Route>();
		resultRouteList.add(findRoute(from, to, routeList));
		return resultRouteList;
	}
	
	public Route findRoute(String departure, String destination, ArrayList<Route> rl){
		for (int i = 0; i < rl.size(); i++){
			Route tempRoute = rl.get(i);
			if (tempRoute.getDeparture() == departure && tempRoute.getDestination() == destination)
				return tempRoute;
		}
		return null;		
	}
}

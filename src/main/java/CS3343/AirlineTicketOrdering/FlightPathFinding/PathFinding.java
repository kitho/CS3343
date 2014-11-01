package CS3343.AirlineTicketOrdering.FlightPathFinding;

import java.util.ArrayList;

import sun.org.mozilla.javascript.internal.ast.NewExpression;
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
	
	public ArrayList<FlightPath> getDirectFlight(){
		ArrayList<Route> routeList = routeTable.getRouteList();
		ArrayList<FlightPath> resultRouteList = new ArrayList<FlightPath>();
		Route route = findRoute(from, to, routeList);
		if (route != null){
			FlightPath fp = new FlightPath();
			fp.addFlighPath(route);
			resultRouteList.add(fp);
		}
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
	
	public ArrayList<FlightPath> getIndirectFlight(){
		ArrayList<Route> routeList = routeTable.getRouteList();
		ArrayList<FlightPath> resultRouteList = new ArrayList<FlightPath>();
		Route route = findRoute(from, to, routeList);
		if (route != null)
			resultRouteList.add(new FlightPath());
		return resultRouteList;
	}	
	
	public Route findRouteDepart(String departure,ArrayList<Route> rl){
		for (int i = 0; i < rl.size(); i++){
			Route tempRoute = rl.get(i);
			if (tempRoute.getDeparture() == departure)
				return tempRoute;
		}
		return null;		
	}
	
}

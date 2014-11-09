package CS3343.AirlineTicketOrdering.FlightPathFinding;

import java.util.ArrayList;
import CS3343.AirlineTicketOrdering.Model.*;

public class RouteTable {
	private ArrayList<Route> routeList;
	public RouteTable(){
		routeList = new ArrayList<Route>();
		Route r = new Route();
		r.setDeparture("Hong Kong");
		r.setDestination("Tokyo");
		routeList.add(r);
		Route r1 = new Route();
		r1.setDeparture("Tokyo");
		r1.setDestination("Taiwan");
		routeList.add(r1);
		Route r2 = new Route();
		r2.setDeparture("Tokyo");
		r2.setDestination("Thailand");
		routeList.add(r2);
		Route r3 = new Route();
		r3.setDeparture("Hong Kong");
		r3.setDestination("Thailand");
		routeList.add(r3);
		Route r4 = new Route();
		r4.setDeparture("Hong Kong");
		r4.setDestination("Taiwan");
		routeList.add(r4);
		Route r5 = new Route();
		r5.setDeparture("Thailand");
		r5.setDestination("Taiwan");
		routeList.add(r5);
		
	}
	public ArrayList<Route> getRouteList() {
		return routeList;
	}
	public void setRouteList(ArrayList<Route> routeList) {
		this.routeList = routeList;
	}
}

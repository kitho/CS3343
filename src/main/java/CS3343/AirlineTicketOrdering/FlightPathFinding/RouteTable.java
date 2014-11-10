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
		Route r6 = new Route();
		r6.setDeparture("Tokyo");
		r6.setDestination("Singapore");
		routeList.add(r6);
		Route r7 = new Route();
		r7.setDeparture("Hong Kong");
		r7.setDestination("Singapore");
		routeList.add(r7);
		Route r8 = new Route();
		r8.setDeparture("Singapore");
		r8.setDestination("Taiwan");
		routeList.add(r8);
		Route r9 = new Route();
		r9.setDeparture("Tokyo");
		r9.setDestination("USA");
		routeList.add(r9);
		Route r10 = new Route();
		r10.setDeparture("USA");
		r10.setDestination("England");
		routeList.add(r10);
		
		Route r11 = new Route();
		r11.setDeparture("Taiwan");
		r11.setDestination("England");
		routeList.add(r11);
	}
	public ArrayList<Route> getRouteList() {
		return routeList;
	}
	public void setRouteList(ArrayList<Route> routeList) {
		this.routeList = routeList;
	}
}

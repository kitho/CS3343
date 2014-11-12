package CS3343.AirlineTicketOrdering.FlightPathFinding;

import java.util.ArrayList;
import java.util.List;

import CS3343.AirlineTicketOrdering.AirlineCompany.AirlineCompanyShort;
import CS3343.AirlineTicketOrdering.Filter.FlightListFilter;
import CS3343.AirlineTicketOrdering.Model.*;

public class RouteTable {
	private ArrayList<Route> routeList;
	public RouteTable(){		
		routeList = new ArrayList<Route>();
		addRouteRow("Hong Kong", "Tokyo");
		addRouteRow("Tokyo", "Taiwan");
		addRouteRow("Tokyo", "Thailand");
		addRouteRow("Hong Kong", "Thailand");
		addRouteRow("Hong Kong", "Taiwan");
		addRouteRow("Thailand", "Taiwan");
		addRouteRow("Tokyo", "Singapore");
		addRouteRow("Hong Kong", "Singapore");
		addRouteRow("Singapore", "Taiwan");
		addRouteRow("Tokyo", "USA");
		addRouteRow("USA", "England");
		addRouteRow("Taiwan", "England");
		
	}
	
	public void addRouteRow(String depart, String dest){
		Route r = new Route();
		r.setDeparture(depart);
		r.setDestination(dest);
		
		//Data for testing, should use the real data in CSV file.
		List<Flight> fList = fetchAllFlights();
		//Data for testing, should use the real data in CSV file.
		
		FlightListFilter flf = new FlightListFilter(fList);
		r.setFlights(flf.filterByDepartNDest(depart, dest));
		
		routeList.add(r);
	}
	
	private List<Flight> fetchAllFlights(){
		List<Flight> flightList = new ArrayList<Flight>();
		
		Flight f1 = new Flight();
		f1.setAirline(AirlineCompanyShort.CPA.value());
		f1.setDepature("Hong Kong");
		f1.setDestination("Tokyo");
		f1.setOneWayPrice(2000);
		flightList.add(f1);
		
		Flight f2 = new Flight();
		f2.setAirline(AirlineCompanyShort.CRK.value());
		f2.setDepature("Hong Kong");
		f2.setDestination("Tokyo");
		f2.setOneWayPrice(1900);
		flightList.add(f2);
		
		Flight f3 = new Flight();
		f3.setAirline(AirlineCompanyShort.HDA.value());
		f3.setDepature("Tokyo");
		f3.setDestination("Taiwan");
		f3.setOneWayPrice(1800);
		flightList.add(f3);
		
		Flight f4 = new Flight();
		f4.setAirline(AirlineCompanyShort.CPA.value());
		f4.setDepature("Tokyo");
		f4.setDestination("Taiwan");
		f4.setOneWayPrice(2000);
		flightList.add(f4);
		
		Flight f5 = new Flight();
		f5.setAirline(AirlineCompanyShort.CRK.value());
		f5.setDepature("Tokyo");
		f5.setDestination("Taiwan");
		f5.setOneWayPrice(1700);
		flightList.add(f5);
		
		Flight f6 = new Flight();
		f6.setAirline(AirlineCompanyShort.CPA.value());
		f6.setDepature("Hong Kong");
		f6.setDestination("Thailand");
		f6.setOneWayPrice(2100);
		flightList.add(f6);
		
		return flightList;
	}
	
	public ArrayList<Route> getRouteList() {
		return routeList;
	}
	public void setRouteList(ArrayList<Route> routeList) {
		this.routeList = routeList;
	}
}

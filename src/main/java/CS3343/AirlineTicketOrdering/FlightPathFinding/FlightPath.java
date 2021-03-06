package CS3343.AirlineTicketOrdering.FlightPathFinding;

import java.io.IOException;
import java.util.ArrayList;

import CS3343.AirlineTicketOrdering.Model.*;

public class FlightPath {
	private ArrayList<Route> flightPath;
	/**
	 * Instantiates a new Flight Path
	 */
	public FlightPath(){
		flightPath = new ArrayList<Route>();
	}
	public ArrayList<Route> getFlightList() {
		return flightPath;
	}
	public void addFlighPath(Route r) {
		flightPath.add(r);
	}
	public void removeFlightPath(Route r){
		flightPath.remove(r);
	}
}

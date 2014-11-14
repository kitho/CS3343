package CS3343.AirlineTicketOrdering.Model;
import java.util.ArrayList;


public class Route {
	private String destination;
	private String departure;
	private ArrayList<Flight> flights;
	
	public ArrayList<Flight> getFlights() {
		return flights;
	}
	public void setFlights(ArrayList<Flight> flights) {
		this.flights = flights;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getDeparture() {
		return departure;
	}
	public void setDeparture(String departure) {
		this.departure = departure;
	}
}

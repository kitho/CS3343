//THIS CLASS IS TEMPORARY ONLY

package CS3343.AirlineTicketOrdering.Baggage.Temp;
import java.util.ArrayList;

public class Route {
	private String destination;
	private String departure;
	private BaggagePlan baggagePlan;
	private ArrayList<Flight> flights;
	
	public BaggagePlan getBaggagePlan() {
		return baggagePlan;
	}
	public void setBaggagePlan(BaggagePlan baggagePlan) {
		this.baggagePlan = baggagePlan;
	}
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

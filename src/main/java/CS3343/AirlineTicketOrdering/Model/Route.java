package CS3343.AirlineTicketOrdering.Model;
import java.util.ArrayList;


/**
 * The Class Route.
 */
public class Route {
	
	/** The destination. */
	private String destination;
	
	/** The departure. */
	private String departure;
	
	/** The flights. */
	private ArrayList<Flight> flights;
	
	/** The distance. */
	private int distance;
	
	/**
	 * Instantiates a new route.
	 */
	public Route (){
		flights = new ArrayList<Flight>();
	}
	
	/**
	 * Gets the flights.
	 *
	 * @return the flights
	 */
	public ArrayList<Flight> getFlights() {
		return flights;
	}
	
	/**
	 * Sets the flights.
	 *
	 * @param flights the new flights
	 */
	public void setFlights(ArrayList<Flight> flights) {
		this.flights = flights;
	}
	
	/**
	 * Gets the destination.
	 *
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}
	
	/**
	 * Sets the destination.
	 *
	 * @param destination the new destination
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	/**
	 * Gets the departure.
	 *
	 * @return the departure
	 */
	public String getDeparture() {
		return departure;
	}
	
	/**
	 * Sets the departure.
	 *
	 * @param departure the new departure
	 */
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	
	/**
	 * Gets the distance.
	 *
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}
	
	/**
	 * Sets the distance.
	 *
	 * @param distance the new distance
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	/**
	 * Adds the flight.
	 *
	 * @param flight the flight
	 */
	public void addFlight(Flight flight){
		flights.add(flight);
	}
}

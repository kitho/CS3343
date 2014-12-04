package CS3343.AirlineTicketOrdering.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class AirlineCompany.
 */
public class AirlineCompany {
	
	/** The airline. */
	private String airline;
	
	/** The flights. */
	private List<Flight> flights;
	
	/**
	 * Instantiates a new airline company.
	 */
	public AirlineCompany(){
		flights = new ArrayList<Flight>();
	}
	
	/**
	 * Sets the airline.
	 *
	 * @param airline the new airline
	 */
	public void setAirline(String airline){
		this.airline = airline;
	}
	
	/**
	 * Gets the airline.
	 *
	 * @return the airline
	 */
	public String getAirline(){
		return airline;
	}
	
	/**
	 * Adds the flight.
	 *
	 * @param flight the flight
	 */
	public void addFlight(Flight flight){
		if(flight.getAirline().equals(airline))
			flights.add(flight);
	}
	
	/**
	 * Gets the flights.
	 *
	 * @return the flights
	 */
	public List<Flight> getFlights() {
		return flights;
	}

}

package CS3343.AirlineTicketOrdering.Model;

import java.util.ArrayList;
import java.util.List;

public class AirlineCompany {
	
	private String airline;
	private List<Flight> flights;
	
	public AirlineCompany(String airline){
		this.airline = airline;
		flights = new ArrayList<Flight>();
	}
	
	public String getAirline(){
		return airline;
	}
	
	public void addFlight(Flight flight){
		flights.add(flight);
	}

}

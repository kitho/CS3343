package CS3343.AirlineTicketOrdering.Model;

import java.util.List;

public class AirlineCompany {
	
	private String airline;
	private List<Flight> flights;
	
	public AirlineCompany(String airline, List<Flight> flights){
		this.airline = airline;
		this.flights = flights;
	}
	
	public String getAirline(){
		return airline;
	}

}

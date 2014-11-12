package CS3343.AirlineTicketOrdering.Filter;

import java.util.ArrayList;
import java.util.List;

import CS3343.AirlineTicketOrdering.Model.Flight;

public class FlightListFilter {
	private List<Flight> fList;
	
	public FlightListFilter(List<Flight> fList){
		this.fList = fList;
	}
	
	public List<Flight> filterByDepartNDest(String depart, String dest) {
		// TODO Auto-generated method stub
		List<Flight> tempList = new ArrayList<Flight>();
		for(Flight f : fList){
			if(f.getDepature().equals(depart) && f.getDestination().equals(dest)){
				tempList.add(f);
			}
		}
		
		return (tempList.size()==0)?null:tempList;
	}

}

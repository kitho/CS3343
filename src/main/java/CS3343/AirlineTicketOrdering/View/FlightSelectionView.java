package CS3343.AirlineTicketOrdering.View;

import java.util.List;
import java.util.Scanner;

import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Session.Session;

public class FlightSelectionView implements View{

	@Override
	public void display(Session session) {
		Scanner scanner = new Scanner(System.in);
		List<Flight> flights = (List<Flight>) session.getAttribute("flights");
		
		if(flights.size() == 0){
			System.out.println("Not Suitable Fight");
			session.setAttribute("selectedFlight", null);
		}
		

		System.out.println("=====================");
		System.out.print("Airline\tFlightNumber\tTravelClass\tDepature\tDestination\tDepatureDateTime\tArrivalDateTime\tAvailable\tOneWayPrice");
		for(int i = 0; i < flights.size(); i++){
			System.out.println(i + ":\t" + flights.get(i).toString());
		}
		System.out.println("=====================");
		
		
		System.out.println("Please select flight");
		session.setAttribute("selectedFlight", scanner.nextInt());
	}
}

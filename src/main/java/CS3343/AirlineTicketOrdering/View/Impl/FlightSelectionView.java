package CS3343.AirlineTicketOrdering.View.Impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

public class FlightSelectionView implements View{

	public void display(Session session) {
		Scanner scanner = new Scanner(System.in);
		List<Flight> flights = (List<Flight>) session.getAttribute("flights");
		
		if(flights.size() == 0){
			System.out.println("Not Suitable Flight");
			session.setAttribute("flights", null);
		}else{
	        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			System.out.println("=====================");
			String format = "%-5s%-20s%-20s%-20s%-20s%-20s%-25s%-25s%-20s%s%n";
			System.out.printf(format, "No.","Airline","FlightNumber","TravelClass","Depature","Destination","DepatureDateTime","ArrivalDateTime","Available","OneWayPrice");
			for(int i = 0; i < flights.size(); i++){
				System.out.printf(format,i,flights.get(i).getAirline(),flights.get(i).getFlightNumber(),flights.get(i).getTravelClass(),flights.get(i).getDepature(),flights.get(i).getDestination(),dt.format(flights.get(i).getDepatureDateTime()),dt.format(flights.get(i).getArrivalDateTime()),flights.get(i).getAvailable(),flights.get(i).getOneWayPrice());
			}
			System.out.println("=====================");
			
			
			System.out.println("Please select flight");
			session.setAttribute("flights", flights.get(scanner.nextInt()));
			System.out.print("Please input number of tickets that you need?");
			session.setAttribute("numberOfTicket", scanner.nextInt());
		}
	}
}

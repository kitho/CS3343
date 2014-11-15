package CS3343.AirlineTicketOrdering.View.Impl;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import CS3343.AirlineTicketOrdering.Model.CreditCard;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

public class OrderConfirmationView implements View{

	private BufferedReader bufferedReader;
	
	public OrderConfirmationView(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}
	
	public void display(Session session) {
		
		CreditCard creditCard = (CreditCard)session.getAttribute("creditCard");
		List<Flight> flights = (List<Flight>)(session.getAttribute("flights")); 
		int numberOfTicket = (Integer)(session.getAttribute("numberOfTicket"));
		double totalPrice = (Double)(session.getAttribute("totalPrice"));

		System.out.println("Here is your order detail:");
		System.out.println("==========Payment Method==========");
		System.out.println("Bank: " + creditCard.getBank());
		System.out.println("Type: " + creditCard.getCreditCardType());
		System.out.println("Number: " + creditCard.getCreditCardNumber());

		System.out.println("\n==========Ticket Information==========");
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String format = "%-20s%-25s%-20s%-20s%-20s%-25s%-25s%-20s%s%n";
		System.out.printf(format,"Airline","FlightNumber","TravelClass","Depature","Destination","DepatureDateTime","ArrivalDateTime","Available","OneWayPrice");
		for(int i = 0; i < flights.size(); i++){
			System.out.printf(format,flights.get(i).getAirline(),flights.get(i).getFlightNumber(),flights.get(i).getTravelClass(),flights.get(i).getDepature(),flights.get(i).getDestination(),dt.format(flights.get(i).getDepatureDateTime()),dt.format(flights.get(i).getArrivalDateTime()),flights.get(i).getAvailable(),flights.get(i).getOneWayPrice());
		}
		
		System.out.println("\n======================================");
		System.out.println("Number of Ticket: " + numberOfTicket);
		System.out.println("Total Price: " + totalPrice);
		System.out.println("======================================");

		System.out.print("\nConfirm to order? (Yes/No)");

	}
}

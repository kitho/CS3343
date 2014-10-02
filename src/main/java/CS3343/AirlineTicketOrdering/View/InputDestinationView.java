package CS3343.AirlineTicketOrdering.View;

import java.util.Scanner;


public class InputDestinationView implements AirlineTicketOrderingView {

	public void display(){
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Welcome to Airline Ticket Ordering System");
		
		System.out.print("Date (YYYY-MM-DD): ");
		String depatureDate = scanner.next();
		
//		System.out.print("Depature: ");
//		String depature = scanner.next();
//		System.out.println("depature: " + depature);
//		
//		System.out.print("Destination: ");
//		String destination = scanner.next();
//		System.out.println("destination: " + destination);
		
		scanner.close();
	}
}

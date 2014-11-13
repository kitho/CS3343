package CS3343.AirlineTicketOrdering.View.Impl;

import java.util.Scanner;

import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

public class InputDestinationView implements View {
	
	private Scanner scanner;
	
	public InputDestinationView(Scanner scanner) {
		this.scanner = scanner;
	}

	public void display(Session session){
		
		String deapture = "";
		String destination = "";
		String depatureDate = "";

		System.out.println("Please Input your depature date, depature and destination to search");
		System.out.print("Date (YYYY-MM-DD): ");
		depatureDate = scanner.nextLine();
		System.out.print("Depature: ");
		deapture = scanner.nextLine();
		System.out.print("Destination: ");
		destination = scanner.nextLine();
		
		session.setAttribute("deapture", deapture);
		session.setAttribute("destination", destination);
		session.setAttribute("depatureDate", depatureDate);
		
	}
}

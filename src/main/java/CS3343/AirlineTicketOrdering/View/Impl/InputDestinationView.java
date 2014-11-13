package CS3343.AirlineTicketOrdering.View.Impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

public class InputDestinationView implements View {
	
	private BufferedReader bufferedReader;
	
	public InputDestinationView(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}

	public void display(Session session) throws IOException{
		
		String deapture = "";
		String destination = "";
		String depatureDate = "";

		System.out.println("Please Input your depature date, depature and destination to search");
		System.out.print("Date (YYYY-MM-DD): ");
		depatureDate = bufferedReader.readLine();
		System.out.print("Depature: ");
		deapture = bufferedReader.readLine();
		System.out.print("Destination: ");
		destination = bufferedReader.readLine();
		
		session.setAttribute("deapture", deapture);
		session.setAttribute("destination", destination);
		session.setAttribute("depatureDate", depatureDate);
		
	}
}

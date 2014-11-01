package CS3343.AirlineTicketOrdering.View;

import java.util.Scanner;

import CS3343.AirlineTicketOrdering.Session.Session;

public class InputDestinationView implements View {

	public void display(Session session){
		Scanner scanner = new Scanner(System.in);
		
		String[] splitString = null;
		System.out.print("[Date (YYYY-MM-DD)] [Depature] [Destination]: ");
		splitString = scanner.nextLine().split(" ");

		scanner.close();
	}
}

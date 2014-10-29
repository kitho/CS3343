package CS3343.AirlineTicketOrdering.View;

import java.util.Scanner;

public class InputDestinationView implements AirlineTicketOrderingView {

	public void display(){
		Scanner scanner = new Scanner(System.in);
		
		String[] splitString = null;
		System.out.print("[Date (YYYY-MM-DD)] [Depature] [Destination]: ");
		splitString = scanner.nextLine().split(" ");

		scanner.close();
	}
}

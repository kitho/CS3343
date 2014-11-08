package CS3343.AirlineTicketOrdering.View;

import java.util.Scanner;

import CS3343.AirlineTicketOrdering.Model.CreditCard;
import CS3343.AirlineTicketOrdering.Session.Session;

public class EnquireCreditCardView implements View {

	public void display(Session session){
		CreditCard creditCard = new CreditCard();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please input your credit card information");
		System.out.print("Bank: ");
		creditCard.setBank(scanner.nextLine());
		System.out.print("Type: ");
		creditCard.setCreditCardType(scanner.nextLine());
		System.out.print("Number: ");
		creditCard.setCreditCardNumber(scanner.nextLine());
		scanner.close();
	}
}

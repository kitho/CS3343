package CS3343.AirlineTicketOrdering.View.Impl;

import java.util.Scanner;

import CS3343.AirlineTicketOrdering.Model.CreditCard;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

public class EnquireCreditCardView implements View {
	
	private Scanner scanner;
	
	public EnquireCreditCardView(Scanner scanner) {
		this.scanner = scanner;
	}

	public void display(Session session){
		CreditCard creditCard = new CreditCard();
		System.out.println("Please input your credit card information");
		System.out.print("Bank: ");
		creditCard.setBank(scanner.nextLine());
		System.out.print("Type: ");
		creditCard.setCreditCardType(scanner.nextLine());
		System.out.print("Number: ");
		creditCard.setCreditCardNumber(scanner.nextLine());
	}
}

package CS3343.AirlineTicketOrdering.View.Impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import CS3343.AirlineTicketOrdering.Model.CreditCard;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

public class EnquireCreditCardView implements View {
	private BufferedReader bufferedReader;
	
	public EnquireCreditCardView(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}

	public void display(Session response) throws IOException{
		CreditCard creditCard = new CreditCard();
		System.out.println("Please input your credit card information");
		System.out.print("Bank: ");
		creditCard.setBank(bufferedReader.readLine());
		System.out.print("Type: ");
		creditCard.setCreditCardType(bufferedReader.readLine());
		System.out.print("Number: ");
		creditCard.setCreditCardNumber(bufferedReader.readLine());
		response.setAttribute("creditCard", creditCard);
	}
}

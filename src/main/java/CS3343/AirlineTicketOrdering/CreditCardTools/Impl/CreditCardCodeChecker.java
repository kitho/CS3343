package CS3343.AirlineTicketOrdering.CreditCardTools.Impl;

import CS3343.AirlineTicketOrdering.CreditCardTools.CodeChecker;
import CS3343.AirlineTicketOrdering.Model.CreditCard;

//A class for checking whether the type of credit card and the type user inputed are same
//e.g. user input: VISA, check the format of credit card number
public class CreditCardCodeChecker implements CodeChecker {
	private String typeinput;
	private CreditCard card;
	
	public CreditCardCodeChecker(String typeinput, CreditCard card){
		this.typeinput = typeinput;
		this.card = card;
	}
	
	public boolean check() {
		// TODO Auto-generated method stub
		String tempString[] = card.getCreditCardNumber().split("-");
		String cardnum = "";
		for(int i = 0; i < tempString.length; i++){
			cardnum += tempString[i];
		}
		 
		switch(typeinput){
			case "VISA":
				return cardnum.matches("\\b(4[0-9]{12}(?:[0-9]{3})?)\\b");
			case "Master Card":
				return cardnum.matches("\\b(5[1-5][0-9]{14})\\b");
			case "American Express":
				return cardnum.matches("\\b(3[47][0-9]{13})\\b");
			default:
				return false;
		}

	}
}

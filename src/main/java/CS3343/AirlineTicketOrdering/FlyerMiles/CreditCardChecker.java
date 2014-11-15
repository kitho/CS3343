package CS3343.AirlineTicketOrdering.FlyerMiles;

import CS3343.AirlineTicketOrdering.Model.CreditCard;

public class CreditCardChecker {
	private CreditCard card;
	
	public CreditCardChecker(CreditCard card){
		this.card = card;
	}
	
	public double getBounsRate(){
		String bank = card.getBank();
		if(bank.equals("HSBC")){
			switch(card.getCreditCardType()){
				case "VISA":
					return 0.1;
				case "Master Card":
					return 0.1;
				case "American Express":
					return 0.2;
			}
		}
		if(bank.equals("DBS")){
			switch(card.getCreditCardType()){
				case "VISA":
					return 0.09;
				case "Master Card":
					return 0.09;
				case "American Express":
					return 0.15;
			}
		}
		return 0.0;
	}
}

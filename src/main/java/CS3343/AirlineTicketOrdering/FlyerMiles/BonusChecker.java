package CS3343.AirlineTicketOrdering.FlyerMiles;

import CS3343.AirlineTicketOrdering.Model.*;

public class BonusChecker {
	private CreditCard card;
	private String flightclass;
	private String company;
	
	public BonusChecker(CreditCard card, String flightclass, String company){
		this.card = card;
		this.flightclass = flightclass;
		this.company = company;
	}
	
	//check the credit card bank and type, return different bouns rate
	public double getCreditCardBounsRate(){
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
	
	public double getFlightClassBounsRate(){
		switch(flightclass){
			case FlightClass.FIRST_CLASS:
				return 0.4;
			case FlightClass.BUSINESS_CLASS:
				return 0.3;
			case FlightClass.PREMIUM_ECONOMY_CLASS:
				return 0.1;
			case FlightClass.ECONOMY_CLASS:
				return 0.0;	
		}
		return 0.0;
	}
	
	public double getCompanyBounsRate(){
		switch(company){
		case "Cathay Pacific Airways":
			return 0.1;
		case "Dragonair":
			return 0.05;
		}
		return 0.0;
	}
	
}

package CS3343.AirlineTicketOrdering.FlyerMiles;

import CS3343.AirlineTicketOrdering.Model.*;

public class BonusChecker {
	private CreditCard card;
	private String flightclass;
	private String company;
	
	/**
	 * Calculate the bonus rate
	 * @param card
	 * @param flightclass
	 * @param company
	 */
	public BonusChecker(CreditCard card, String flightclass, String company){
		this.card = card;
		this.flightclass = flightclass;
		this.company = company;
	}

	/**
	 * check the credit card bank and type, return different bonus rate
	 * @return double rate
	 */
	public double getCreditCardBounsRate(){
		String bank = card.getBank();
		if(bank.equals("HSBC")){
			if(card.getCreditCardType().equals("VISA"))
					return 0.1;
			if(card.getCreditCardType().equals("Master Card"))
					return 0.1;
			if(card.getCreditCardType().equals("American Express"))
					return 0.2;
			return 0.0;
		}
		if(bank.equals("DBS")){
			 if(card.getCreditCardType().equals("VISA"))
					return 0.09;
			 if(card.getCreditCardType().equals("Master Card"))
					return 0.09;
			 if(card.getCreditCardType().equals("American Express"))
					return 0.15;
			return 0.0;
		}
		return 0.0;
	}
	
	/**
	 * check the flight class, return different bonus rate
	 * @return double rate
	 */
	public double getFlightClassBounsRate(){
		if(flightclass.equals(FlightClass.FIRST_CLASS))
				return 0.4;
		if(flightclass.equals(FlightClass.BUSINESS_CLASS))
				return 0.3;
		if(flightclass.equals(FlightClass.PREMIUM_ECONOMY_CLASS))
				return 0.1;
		if(flightclass.equals(FlightClass.ECONOMY_CLASS))
				return 0.0;	
		return 0.0;
		
	}
	
	/**
	 * check the flight company, return different bonus rate
	 * @return double rate
	 */
	public double getCompanyBounsRate(){
		if(company.equals("Cathay Pacific Airways"))
			return 0.1;
		if(company.equals("Dragonair"))
			return 0.05;
		return 0.0;	
	}
	
}

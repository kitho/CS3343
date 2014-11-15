package CS3343.AirlineTicketOrdering.CreditCardTools.Impl;

import CS3343.AirlineTicketOrdering.CreditCardTools.DiscountChecker;
import CS3343.AirlineTicketOrdering.Model.CreditCard;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.AirlineCompany.AirlineCompanyShort;

public class CreditCardAirlineDiscountChecker implements DiscountChecker {
	private Flight flight;
	private CreditCard card;
	
	public CreditCardAirlineDiscountChecker(Flight flight,CreditCard card){
		this.flight = flight;
		this.card = card;
	}
	
	public double check() {
		// TODO Auto-generated method stub
		String company = this.flight.getAirline();
		if(company.equals(AirlineCompanyShort.CPA.value())){
			switch(card.getCreditCardType()){
				case "VISA":
					return 0.8;
				case "Master Card":
					return 0.85;
				case "American Express":
					return 0.75;
			}
		}else if(company.equals(AirlineCompanyShort.CRK.value())){
			switch(card.getCreditCardType()){
				case "VISA":
					return 0.9;
				case "Master Card":
					return 0.9;
				case "American Express":
					return 0.85;
			}
		}else if(company.equals(AirlineCompanyShort.HDA.value())){
			switch(card.getCreditCardType()){
				case "VISA":
					return 0.8;
				case "Master Card":
					return 0.8;
				case "American Express":
					return 0.7;
			}
		}else{
			return 0.0;
		}
		return 0.0;
	}

}

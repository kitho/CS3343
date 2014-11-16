package CS3343.AirlineTicketOrdering.CreditCardTools.Impl;

import java.util.ArrayList;
import java.util.List;

import CS3343.AirlineTicketOrdering.CreditCardTools.CreditCardDiscountChecker;
import CS3343.AirlineTicketOrdering.Discount.Impl.AirlineDiscount;
import CS3343.AirlineTicketOrdering.Model.CreditCard;
import CS3343.AirlineTicketOrdering.Model.Flight;

public class CreditCardAirlineDiscountChecker implements CreditCardDiscountChecker {
	
	private List<AirlineDiscount> airlineDiscounts;
	
	public CreditCardAirlineDiscountChecker(List<AirlineDiscount> airlineDiscounts){
		this.airlineDiscounts = airlineDiscounts;
	}
	
	@Override
	public double[] check(List<Flight> flights, CreditCard card) {
		// TODO Auto-generated method stub
		double[] discounts = new double[flights.size()];
		
		for(int i = 0; i < flights.size(); i++){
			discounts[i] = check(flights.get(i),card);
		}
		return discounts;
	}
	

	@Override
	public double check(Flight flight, CreditCard card) {
		// TODO Auto-generated method stub
		for(AirlineDiscount ad : airlineDiscounts){
			if(ad.getAirline().equals(flight.getAirline()) && ad.getCreditCardType().equals(card.getCreditCardType())){
				return ad.getDiscount();
			}
		}
		
		return 1.0;
	}
}

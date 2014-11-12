package CS3343.AirlineTicketOrdering.Discount.Impl;

import java.util.List;

import CS3343.AirlineTicketOrdering.CreditCardTools.DiscountChecker;
import CS3343.AirlineTicketOrdering.CreditCardTools.Impl.CreditCardAirlineDiscountChecker;
import CS3343.AirlineTicketOrdering.Discount.Discount;
import CS3343.AirlineTicketOrdering.Model.CreditCard;
import CS3343.AirlineTicketOrdering.Model.Flight;

public class StubDiscount implements Discount {

	public double[] getDiscount(List<Flight> flights, CreditCard creditCard) {
		// TODO Auto-generated method stub
		double[] discounts = new double[flights.size()];
		for(int i = 0; i < discounts.length; i++){
			discounts[i] = getDiscount(flights.get(i),creditCard);
		}
		
		return discounts;
	}

	@Override
	public double getDiscount(Flight flight, CreditCard creditCard) {
		// TODO Auto-generated method stub
		DiscountChecker ccadc = new CreditCardAirlineDiscountChecker(flight, creditCard);
		return ccadc.check();
	}

}

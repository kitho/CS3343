package CS3343.AirlineTicketOrdering.CreditCardTools;


import java.util.List;

import CS3343.AirlineTicketOrdering.Model.CreditCard;
import CS3343.AirlineTicketOrdering.Model.Flight;

public interface CreditCardDiscountChecker {
	
	/**
	 * check credit card discounts
	 * @param flights
	 * @param card
	 * @return double[] discounts
	 */
	public double[] check(List<Flight> flights, CreditCard card);
	
	/**
	 * check credit card discount
	 * @param flight
	 * @param card
	 * @return discount 
	 */
	public double check(Flight flight, CreditCard card);
}

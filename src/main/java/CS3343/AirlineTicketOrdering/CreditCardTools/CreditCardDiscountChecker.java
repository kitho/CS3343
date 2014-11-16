package CS3343.AirlineTicketOrdering.CreditCardTools;


import java.util.List;

import CS3343.AirlineTicketOrdering.Model.CreditCard;
import CS3343.AirlineTicketOrdering.Model.Flight;

public interface CreditCardDiscountChecker {
	public double[] check(List<Flight> flights, CreditCard card);
	public double check(Flight flight, CreditCard card);
}

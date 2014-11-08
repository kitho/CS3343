package CS3343.AIrlineTickerOrdering.Calculator;

import java.util.List;

import CS3343.AirlineTicketOrdering.Model.Flight;
import CSC3343.AirlineTicketOrdering.Discount.Discount;

public interface Calculator {

	public double calculate(List<Flight> flights, int numberOfTicket, Discount discount);
	
}

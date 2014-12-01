package CS3343.AirlineTicketOrdering.Calculator;


import java.util.List;

import CS3343.AirlineTicketOrdering.Model.Flight;

public interface Calculator {

	/**
	 * calculate operation
	 * @param flights
	 * @param numberOfTicket
	 * @param discount
	 * @return double totalAmount
	 */
	public double calculate(List<Flight> flights, int numberOfTicket, double[] discount);
	
}

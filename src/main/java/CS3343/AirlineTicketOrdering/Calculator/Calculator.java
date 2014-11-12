package CS3343.AirlineTicketOrdering.Calculator;


import java.util.List;

import CS3343.AirlineTicketOrdering.Model.Flight;

public interface Calculator {

	public double calculate(List<Flight> flights, int numberOfTicket, double[] discount);
	
}

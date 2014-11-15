package CS3343.AirlineTicketOrdering.Calculator.Impl;

import java.util.List;

import CS3343.AirlineTicketOrdering.Calculator.Calculator;
import CS3343.AirlineTicketOrdering.Model.Flight;

public class StubCalculator implements Calculator {

	public double calculate(List<Flight> flights, int numberOfTicket,
			double[] discount) {
		double totalAmount = 0.00;
		
		for(int i = 0; i < flights.size(); i++){
			Flight f = flights.get(i);
			totalAmount += f.getOneWayPrice() * numberOfTicket * discount[i];
		}
		
		return totalAmount;
	}

}

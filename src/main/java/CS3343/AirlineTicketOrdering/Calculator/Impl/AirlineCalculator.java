package CS3343.AirlineTicketOrdering.Calculator.Impl;

import java.util.List;

import CS3343.AirlineTicketOrdering.Calculator.Calculator;
import CS3343.AirlineTicketOrdering.Model.Flight;

public class AirlineCalculator implements Calculator {

	/**
	 * calculate the total amount
	 * @param flights
	 * @param numberOfTicket
	 * @param discount
	 * @return double totalAmount
	 */
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

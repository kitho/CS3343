package CS3343.AirlineTicketOrdering.Baggage;

import CS3343.AirlineTicketOrdering.Model.Flight;

public class BaggageFeeCalculator {
	private Flight flight;
	
	public BaggageFeeCalculator (Flight flight){
		
	}
	
	public float calFee(float baggageWeight, String travelClass){
		float baggageFee;
		
		//float maxBaggageWeight = flight.getMaxBaggageWeight(travelClass);
		float maxBaggageWeight = 10;
		
		if(baggageWeight < maxBaggageWeight){
			baggageFee = 0;
		}else{
			
		}
		return 0;
	}
	
	
}

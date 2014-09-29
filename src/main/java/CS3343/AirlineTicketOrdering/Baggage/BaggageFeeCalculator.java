/*
 * A baggage fee calculator to calculate baggage fee
 * by passing multiple routes and baggage information.
 */

package CS3343.AirlineTicketOrdering.Baggage;

import java.util.ArrayList;
import CS3343.AirlineTicketOrdering.Baggage.Temp.*;

public class BaggageFeeCalculator {
	private static BaggageFeeCalculator calculator;
	private ArrayList<Route> routes;
	
	//Singleton Pattern
	private BaggageFeeCalculator(){}
	
	public static BaggageFeeCalculator getInstance(){
		if (calculator == null)
			calculator = new BaggageFeeCalculator();
		return calculator;
	}
	
	public float calFee(){
		return 0;
	}
}

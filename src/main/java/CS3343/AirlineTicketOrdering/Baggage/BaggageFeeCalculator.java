/*
 * A baggage fee calculator to calculate baggage fee
 * by passing multiple routes and baggage information.
 */

package CS3343.AirlineTicketOrdering.Baggage;

import java.util.ArrayList;
import CS3343.AirlineTicketOrdering.Baggage.Temp.*;

public class BaggageFeeCalculator {
	private static BaggageFeeCalculator calculator;
	public static final int FIRSTCLASS = 0;
	public static final int BUSINESSCLASS = 1;
	public static final int PREMIUMECONOMYCLASS = 2;
	public static final int ECONOMYCLASS = 3;
	
	//Singleton Pattern
	private BaggageFeeCalculator(){}
	
	public static BaggageFeeCalculator getInstance(){
		if (calculator == null)
			calculator = new BaggageFeeCalculator();
		return calculator;
	}
	
	public float calBaggageFee(Route route, int classType){
		return 0;
	}
}

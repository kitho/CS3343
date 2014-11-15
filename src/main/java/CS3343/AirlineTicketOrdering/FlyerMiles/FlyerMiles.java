package CS3343.AirlineTicketOrdering.FlyerMiles;

import CS3343.AirlineTicketOrdering.Model.*;

public class FlyerMiles {
	
	public FlyerMiles(){
		
	}
	
	//calculate the aware miles points
	public int awareMiles(Route route, String FlightClass, CreditCard card){
		if(route !=null){
			int point = 0;
			MilesCalculator mc = new MilesCalculator();
			BonusChecker bc = new BonusChecker(card,FlightClass);
			point = mc.findBasePoints(route.getDistance());
			point = (int) (point * (1 + bc.getCreditCardBounsRate() + bc.getFlightClassBounsRate()));
			
			return point;
		}
		return 0;
	}
}

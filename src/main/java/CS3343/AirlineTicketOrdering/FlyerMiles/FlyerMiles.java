package CS3343.AirlineTicketOrdering.FlyerMiles;

import java.util.List;

import CS3343.AirlineTicketOrdering.FlightPathFinding.FlightPath;
import CS3343.AirlineTicketOrdering.Model.*;

public class FlyerMiles {
	
	public FlyerMiles(){
		
	}
	
	//calculate the aware miles points
	public int awareMiles(Route route, String FlightClass, CreditCard card){
		if(route !=null){
			/*int point = 0;
			MilesCalculator mc = new MilesCalculator();
			BonusChecker bc = new BonusChecker(card,FlightClass);
			point = mc.findBasePoints(route.getDistance());
			point = (int) (point * (1 + bc.getCreditCardBounsRate() + bc.getFlightClassBounsRate()));
			
			return point;*/
		}
		return 0;
	}
	
	public int awardMiles(FlightPath fPath, List<Flight> flight, CreditCard card){
		int point = 0;
		MilesCalculator mc = new MilesCalculator();
		for (int i = 0; i < fPath.getFlightList().size(); i++){
			int route_point = 0;
			Route route = fPath.getFlightList().get(i);
			BonusChecker bc = new BonusChecker(card,flight.get(i).getTravelClass(),flight.get(i).getAirline());
			route_point = mc.findBasePoints(route.getDistance());
			route_point = (int) (route_point * (1 + bc.getCreditCardBounsRate() + bc.getFlightClassBounsRate() + bc.getCompanyBounsRate()));
			point += route_point;
			
		}
		return point;
	}
}

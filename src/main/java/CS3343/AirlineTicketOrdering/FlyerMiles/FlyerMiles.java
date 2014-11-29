package CS3343.AirlineTicketOrdering.FlyerMiles;

import java.util.List;

import CS3343.AirlineTicketOrdering.FlightPathFinding.FlightPath;
import CS3343.AirlineTicketOrdering.Model.*;

public class FlyerMiles {
	
	public FlyerMiles(){
		
	}
	

	//calculate the award miles points
	public int awardMiles(List<Route> routeList, List<Flight> flight, CreditCard card){
		int point = 0;
		MilesCalculator mc = new MilesCalculator();
		for (int i = 0; i < routeList.size(); i++){
			int route_point = 0;
			Route route = routeList.get(i);
			BonusChecker bc = new BonusChecker(card,flight.get(i).getTravelClass(),flight.get(i).getAirline());
			route_point = mc.findBasePoints(route.getDistance());
			route_point = (int) (route_point * (1 + bc.getCreditCardBounsRate() + bc.getFlightClassBounsRate() + bc.getCompanyBounsRate()));
			point += route_point;
			
		}
		return point;
	}
	
	
}

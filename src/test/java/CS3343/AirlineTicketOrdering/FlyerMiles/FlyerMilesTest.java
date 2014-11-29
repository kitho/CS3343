package CS3343.AirlineTicketOrdering.FlyerMiles;

import static org.junit.Assert.*;

import org.junit.Test;

import CS3343.AirlineTicketOrdering.FlightPathFinding.PathFinding;
import CS3343.AirlineTicketOrdering.FlightPathFinding.RouteTable;
import CS3343.AirlineTicketOrdering.Model.CreditCard;
import CS3343.AirlineTicketOrdering.Model.FlightClass;
import CS3343.AirlineTicketOrdering.Model.Route;

public class FlyerMilesTest {

	@Test
	public void test01() {
		CreditCard card = new CreditCard();
		card.setBank("HSBC");
		card.setCreditCardType("VISA");
		
		FlyerMiles fm = new FlyerMiles();
		
		String from = "Hong Kong";
		String to = "Thailand";
		RouteTable routeTable = new RouteTable();
		PathFinding	pathFinding = new PathFinding(from, to, new RouteTable());
		Route r = pathFinding.findRoute(from, to, routeTable.getRouteList());
		
		int result1 = fm.awareMiles(r, FlightClass.FIRST_CLASS , card);
		
		int expect1 = (int)(2500 * (1+0.1+0.4));
		System.out.println(result1);
		assertEquals(result1,0);
	}

}

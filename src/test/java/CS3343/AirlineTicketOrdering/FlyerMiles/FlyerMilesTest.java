package CS3343.AirlineTicketOrdering.FlyerMiles;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import org.junit.Test;

import CS3343.AirlineTicketOrdering.Discount.Discount;
import CS3343.AirlineTicketOrdering.Discount.Impl.StubDiscount;
import CS3343.AirlineTicketOrdering.FlightPathFinding.*;
import CS3343.AirlineTicketOrdering.Model.*;

public class FlyerMilesTest {

	@Test
	public void FlyerMilesTest01() {
		RouteTable rt = new RouteTable();
		String from = "Hong Kong";
		String to = "Thailand";
		PathFinding pf = new PathFinding(from, to);
		Route route = pf.findRoute(from, to, rt.getRouteList());
		
		CreditCard card = new CreditCard();
		card.setBank("HSBC");
		card.setCreditCardType("VISA");
		
		FlyerMiles fm = new FlyerMiles();
		int total = fm.awareMiles(route, "", card);
		//distance = 1695 -> basepoints = 2000 & card = 1.1
		int expectedResult = (int) (2000 *( 1 + 0.1));	
		assertThat(expectedResult, is(total));
	}

}

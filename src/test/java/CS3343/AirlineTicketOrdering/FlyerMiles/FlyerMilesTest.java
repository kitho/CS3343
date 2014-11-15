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
		int total1 = fm.awareMiles(route, FlightClass.FIRST_CLASS, card);
		int total2 = fm.awareMiles(route, FlightClass.BUSINESS_CLASS, card);
		int total3 = fm.awareMiles(route, FlightClass.PREMIUM_ECONOMY_CLASS, card);
		int total4 = fm.awareMiles(route, FlightClass.ECONOMY_CLASS, card);
		
		//distance = 1695 -> basepoints = 2000 & card = 0.1 & class = 0.4 
		int expectedResult = (int) (2000 *( 1 + 0.1 + 0.4));	
		assertThat(expectedResult, is(total1));
		//distance = 1695 -> basepoints = 2000 & card = 0.1 & class = 0.3 
		expectedResult = (int) (2000 *( 1 + 0.1 + 0.3));	
		assertThat(expectedResult, is(total2));
		//distance = 1695 -> basepoints = 2000 & card = 0.1 & class = 0.1 
		expectedResult = (int) (2000 *( 1 + 0.1 + 0.1));	
		assertThat(expectedResult, is(total3));
		//distance = 1695 -> basepoints = 2000 & card = 0.1 & class = 0.0 
		expectedResult = (int) (2000 *( 1 + 0.1 + 0.0));	
		assertThat(expectedResult, is(total4));
	}
	
	@Test
	public void FlyerMilesTest02() {
		RouteTable rt = new RouteTable();
		String from = "Hong Kong";
		String to = "Tokyo";
		PathFinding pf = new PathFinding(from, to);
		Route route = pf.findRoute(from, to, rt.getRouteList());
		
		CreditCard card = new CreditCard();
		card.setBank("DBS");
		card.setCreditCardType("VISA");
		
		FlyerMiles fm = new FlyerMiles();
		int total1 = fm.awareMiles(route, FlightClass.FIRST_CLASS, card);
		int total2 = fm.awareMiles(route, FlightClass.BUSINESS_CLASS, card);
		int total3 = fm.awareMiles(route, FlightClass.PREMIUM_ECONOMY_CLASS, card);
		int total4 = fm.awareMiles(route, FlightClass.ECONOMY_CLASS, card);
		
		//distance = 2887 -> basepoints = 2500 & card = 0.09 & class = 0.4 
		int expectedResult = (int) (2500 *( 1 + 0.09 + 0.4));	
		assertThat(expectedResult, is(total1));
		//distance = 2887 -> basepoints = 2500 & card = 0.09 & class = 0.3 
		expectedResult = (int) (2500 *( 1 + 0.09 + 0.3));	
		assertThat(expectedResult, is(total2));
		//distance = 2887 -> basepoints = 2500 & card = 0.09 & class = 0.1 
		expectedResult = (int) (2500 *( 1 + 0.09 + 0.1));	
		assertThat(expectedResult, is(total3));
		//distance = 2887 -> basepoints = 2500 & card = 0.09 & class = 0.0 
		expectedResult = (int) (2500 *( 1 + 0.09 + 0.0));	
		assertThat(expectedResult, is(total4));
	}
	
	@Test
	public void FlyerMilesTest03() {
		RouteTable rt = new RouteTable();
		String from = "Hong Kong";
		String to = "USA";
		PathFinding pf = new PathFinding(from, to);
		Route route = pf.findRoute(from, to, rt.getRouteList());
		
		CreditCard card = new CreditCard();
		card.setBank("DBS");
		card.setCreditCardType("VISA");
		
		FlyerMiles fm = new FlyerMiles();
		int total1 = fm.awareMiles(route, FlightClass.FIRST_CLASS, card);
		
		//route = null, return 0
		int expectedResult = 0;	
		assertThat(expectedResult, is(total1));
	}
	
	@Test
	public void FlyerMilesTest04() {
		RouteTable rt = new RouteTable();
		String from = "Hong Kong";
		String to = "Taiwan";
		PathFinding pf = new PathFinding(from, to);
		Route route = pf.findRoute(from, to, rt.getRouteList());
		
		CreditCard card = new CreditCard();
		card.setBank("ABC");
		card.setCreditCardType("ABC");
		
		FlyerMiles fm = new FlyerMiles();
		int total1 = fm.awareMiles(route, FlightClass.FIRST_CLASS, card);
		int total2 = fm.awareMiles(route, FlightClass.BUSINESS_CLASS, card);
		int total3 = fm.awareMiles(route, FlightClass.PREMIUM_ECONOMY_CLASS, card);
		int total4 = fm.awareMiles(route, FlightClass.ECONOMY_CLASS, card);
		
		//distance = 816 -> basepoints = 1500 & card = 0.0 & class = 0.4
		int expectedResult = (int) (1500 *( 1 + 0.0 + 0.4));;	
		assertThat(expectedResult, is(total1));
		//distance = 2887 -> basepoints = 1500 & card = 0.0 & class = 0.3 
		expectedResult = (int) (1500 *( 1 + 0.0 + 0.3));	
		assertThat(expectedResult, is(total2));
		//distance = 2887 -> basepoints = 1500 & card = 0.0 & class = 0.1 
		expectedResult = (int) (1500 *( 1 + 0.0 + 0.1));	
		assertThat(expectedResult, is(total3));
		//distance = 2887 -> basepoints = 1500 & card = 0.0 & class = 0.0 
		expectedResult = (int) (1500 *( 1 + 0.0 + 0.0));	
		assertThat(expectedResult, is(total4));
	}
	

}

package CS3343.AirlineTicketOrdering.FlyerMiles;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import CS3343.AirlineTicketOrdering.FlightPathFinding.PathFinding;
import CS3343.AirlineTicketOrdering.FlightPathFinding.RouteTable;
import CS3343.AirlineTicketOrdering.Model.CreditCard;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Model.FlightClass;
import CS3343.AirlineTicketOrdering.Model.Route;

public class FlyerMilesTest {

	@Test
	public void test01() {
		CreditCard card = new CreditCard();
		card.setBank("HSBC");
		card.setCreditCardType("VISA");
		ArrayList<Route> routeList = new ArrayList<Route>();
		ArrayList<Flight> flight = new ArrayList<Flight>();
		FlyerMiles fm = new FlyerMiles();
		
		Route r = new Route();
		r.setDeparture("Hong Kong");
		r.setDestination("Taiwan");
		r.setDistance(800);
		routeList.add(r);
		
		Flight f = new Flight();
		f.setAirline("Cathay Pacific Airways");
		f.setTravelClass("");
		flight.add(f);
		
		int result1 = fm.awardMiles(routeList, flight, card);
		
		int expect1 = (int)(1500 * (1+0.1+0.1+0.0));
		assertEquals(result1,expect1);
	}
	
	@Test
	public void test02() {
		CreditCard card = new CreditCard();
		card.setBank("ABC");
		card.setCreditCardType("Master Card");
		ArrayList<Route> routeList = new ArrayList<Route>();
		ArrayList<Flight> flight = new ArrayList<Flight>();
		FlyerMiles fm = new FlyerMiles();
		
		Route r = new Route();
		r.setDeparture("Hong Kong");
		r.setDestination("Taiwan");
		r.setDistance(800);
		routeList.add(r);
		
		Flight f = new Flight();
		f.setAirline("TEST");
		f.setTravelClass("");
		flight.add(f);
		
		int result1 = fm.awardMiles(routeList, flight, card);
		
		int expect1 = (int)(1500 * (1+0.0+0.0+0.0));
		assertEquals(result1,expect1);
	}
	
	@Test
	public void test03() {
		CreditCard card = new CreditCard();
		card.setBank("DBS");
		card.setCreditCardType("Master Card");
		ArrayList<Route> routeList = new ArrayList<Route>();
		ArrayList<Flight> flight = new ArrayList<Flight>();
		FlyerMiles fm = new FlyerMiles();
		
		Route r = new Route();
		r.setDeparture("Hong Kong");
		r.setDestination("Taiwan");
		r.setDistance(5000);
		routeList.add(r);
		
		Flight f = new Flight();
		f.setAirline("Dragonair");
		f.setTravelClass(FlightClass.FIRST_CLASS);
		flight.add(f);
		
		int result1 = fm.awardMiles(routeList, flight, card);
		
		int expect1 = (int)(2500 * (1+0.09+0.05+0.4));
		assertEquals(result1,expect1);
	}
	
	@Test
	public void test04() {
		CreditCard card = new CreditCard();
		card.setBank("DBS");
		card.setCreditCardType("American Express");
		ArrayList<Route> routeList = new ArrayList<Route>();
		ArrayList<Flight> flight = new ArrayList<Flight>();
		FlyerMiles fm = new FlyerMiles();
		
		Route r = new Route();
		r.setDeparture("Hong Kong");
		r.setDestination("Taiwan");
		r.setDistance(5000);
		routeList.add(r);
		
		Flight f = new Flight();
		f.setAirline("Dragonair");
		f.setTravelClass(FlightClass.BUSINESS_CLASS);
		flight.add(f);
		int result1 = fm.awardMiles(routeList, flight, card);
		
		int expect1 = (int)(2500 * (1+0.15+0.05+0.3));
		assertEquals(result1,expect1);
	}
	
	@Test
	public void test05() {
		CreditCard card = new CreditCard();
		card.setBank("HSBC");
		card.setCreditCardType("American Express");
		ArrayList<Route> routeList = new ArrayList<Route>();
		ArrayList<Flight> flight = new ArrayList<Flight>();
		FlyerMiles fm = new FlyerMiles();
		
		
		Flight f = new Flight();
		f.setAirline("Dragonair");
		f.setTravelClass(FlightClass.BUSINESS_CLASS);
		flight.add(f);
		
		int result1 = fm.awardMiles(routeList, flight, card);
		int expect1 = (int)(0 * (1+0.15+0.05+0.3));
		assertEquals(result1,expect1);
	}
	
	@Test
	public void test06() {
		CreditCard card = new CreditCard();
		card.setBank("HSBC");
		card.setCreditCardType("American Express");
		ArrayList<Route> routeList = new ArrayList<Route>();
		ArrayList<Flight> flight = new ArrayList<Flight>();
		FlyerMiles fm = new FlyerMiles();
		
		Route r = new Route();
		r.setDeparture("Hong Kong");
		r.setDestination("Taiwan");
		r.setDistance(5000);
		routeList.add(r);
		
		Flight f = new Flight();
		f.setAirline("ABC");
		f.setTravelClass(FlightClass.PREMIUM_ECONOMY_CLASS);
		flight.add(f);
		
		int result1 = fm.awardMiles(routeList, flight, card);
		int expect1 = (int)(2500 * (1+0.2+0.1+0.0));
		assertEquals(result1,expect1);
	}
	
	@Test
	public void test07() {
		CreditCard card = new CreditCard();
		card.setBank("HSBC");
		card.setCreditCardType("Master Card");
		ArrayList<Route> routeList = new ArrayList<Route>();
		ArrayList<Flight> flight = new ArrayList<Flight>();
		FlyerMiles fm = new FlyerMiles();
		
		Route r = new Route();
		r.setDeparture("Hong Kong");
		r.setDestination("Taiwan");
		r.setDistance(5000);
		routeList.add(r);
		
		Flight f = new Flight();
		f.setAirline("ABC");
		f.setTravelClass(FlightClass.ECONOMY_CLASS);
		flight.add(f);
		
		int result1 = fm.awardMiles(routeList, flight, card);
		int expect1 = (int)(2500 * (1+0.1+0.0+0.0));
		assertEquals(result1,expect1);
	}
	
	@Test
	public void test08() {
		CreditCard card = new CreditCard();
		card.setBank("DBS");
		card.setCreditCardType("VISA");
		ArrayList<Route> routeList = new ArrayList<Route>();
		ArrayList<Flight> flight = new ArrayList<Flight>();
		FlyerMiles fm = new FlyerMiles();
		
		Route r = new Route();
		r.setDeparture("Hong Kong");
		r.setDestination("Taiwan");
		r.setDistance(5000);
		routeList.add(r);
		
		Flight f = new Flight();
		f.setAirline("Dragonair");
		f.setTravelClass(FlightClass.BUSINESS_CLASS);
		flight.add(f);
		int result1 = fm.awardMiles(routeList, flight, card);
		
		int expect1 = (int)(2500 * (1+0.09+0.05+0.3));
		assertEquals(result1,expect1);
	}

}

package CS3343.AirlineTicketOrdering.Parser;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import CS3343.AirlineTicketOrdering.CustomDateUtil.CustomDateFormatter;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Parser.Impl.FlightParser;

public class FlightParserTest {
	
	private CustomDateFormatter formatter;
	
	@Before
	public void setUp(){
		formatter = new CustomDateFormatter();
	}
	
	@Test
	public void parseStringTest() throws ParseException {
		String airline = "Cathay Pacific Airways";
		String flightNumber = "CP001";
		String travelClass = "FIRST";
		String depature = "Hong Kong";
		String destination = "Taiwan";
		String depatureDateTime = "2014-01-01 14:30:00";
		String arrivalDateTime = "2014-01-01 17:30:00";
		int available = 30;
		double oneWayPrice = 2500;
		String model = "737-900";
		String mealIds = "M1";
		String foodIds = "4-3-6-11-12-1-5";
		
		
		String line = airline + "," + flightNumber + "," + travelClass + "," + depature + "," + destination + ","
				+ depatureDateTime + "," + arrivalDateTime + "," + String.valueOf(available) + "," + String.valueOf(oneWayPrice) + 
				"," + model + "," + mealIds + "," + foodIds;
		
		Parser<Flight> flightParser = new FlightParser();
		Flight flight = flightParser.parseString(line);
		
		assertThat(airline, is(flight.getAirline()));
		assertThat(flightNumber, is(flight.getFlightNumber()));
		assertThat(travelClass, is(flight.getTravelClass()));
		assertThat(depature, is(flight.getDepature()));
		assertThat(destination, is(flight.getDestination()));
		assertThat(formatter.parse(depatureDateTime), is(flight.getDepatureDateTime()));
		assertThat(formatter.parse(arrivalDateTime), is(flight.getArrivalDateTime()));
		assertThat(available, is(flight.getAvailable()));
		assertThat(oneWayPrice, is(flight.getOneWayPrice()));
		
	}
	
	@Test
	public void parseObjectTest() throws ParseException {
		Flight flight = new Flight();
		
		flight.setAirline("Cathay Pacific Airways");
		flight.setFlightNumber("CP001");
		flight.setTravelClass("FIRST");
		flight.setDepature("Hong Kong");
		flight.setDestination("Taiwan");
		flight.setDepatureDateTime(formatter.parse("2014-01-01 14:30:00"));
		flight.setArrivalDateTime(formatter.parse("2014-01-01 17:30:00"));
		flight.setAvailable(30);
		flight.setOneWayPrice(2500.00);
		
		Parser<Flight> flightParser = new FlightParser();
		String line = flightParser.parseObject(flight);
		
		String flightString =  flight.getAirline() + "," + flight.getFlightNumber() + "," + flight.getTravelClass() + "," + flight.getDepature()
				+ "," + flight.getDestination() + "," + formatter.format(flight.getDepatureDateTime()) + "," + formatter.format(flight.getArrivalDateTime())
				+ "," + flight.getAvailable() + "," + flight.getOneWayPrice();
		assertThat(flightString, is(line));
		
	}

}

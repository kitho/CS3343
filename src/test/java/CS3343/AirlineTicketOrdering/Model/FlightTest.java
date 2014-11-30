package CS3343.AirlineTicketOrdering.Model;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import CS3343.AirlineTicketOrdering.CustomDateUtil.CustomDateFormatter;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FlightTest {
	private CustomDateFormatter formatter;
	
	@Before
	public void setUp() throws IOException{
		formatter = new CustomDateFormatter();
	}
	
	@Test
	public void testFlight() throws ParseException{
		Flight f = new Flight();
		f.setAirline("Cathay Pacific Airways");
		f.setFlightNumber("CP001");
		f.setTravelClass("FIRST");
		f.setDepature("Hong Kong");
		f.setDestination("Taiwan");
		f.setDepatureDateTime(formatter.parse("2014-01-01 14:30:00"));
		f.setArrivalDateTime(formatter.parse("2014-01-01 17:30:00"));
		f.setAvailable(30);
		f.setOneWayPrice(2500.00);
		
		assertThat("Cathay Pacific Airways",is(f.getAirline()));
		assertThat("CP001",is(f.getFlightNumber()));
		assertThat("FIRST",is(f.getTravelClass()));
		assertThat("Hong Kong", is(f.getDepature()));
		assertThat("Taiwan",is(f.getDestination()));
		assertThat(formatter.parse("2014-01-01 14:30:00"),is(f.getDepatureDateTime()));
		assertThat(formatter.parse("2014-01-01 17:30:00"),is(f.getArrivalDateTime()));
		assertThat(30,is(f.getAvailable()));
		assertThat(2500.00,is(f.getOneWayPrice()));
	}
}

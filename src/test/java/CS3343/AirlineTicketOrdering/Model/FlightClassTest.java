package CS3343.AirlineTicketOrdering.Model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FlightClassTest {
	@Test
	public void testFlightClass(){
		FlightClass fc = new FlightClass();
		assertThat("First Class",is(FlightClass.FIRST_CLASS));
		assertThat("Business Class",is(FlightClass.BUSINESS_CLASS));
		assertThat("Premium Economy Class",is(FlightClass.PREMIUM_ECONOMY_CLASS));
		assertThat("Economy Class",is(FlightClass.ECONOMY_CLASS));
	}
}

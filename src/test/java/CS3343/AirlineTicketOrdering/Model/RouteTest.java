package CS3343.AirlineTicketOrdering.Model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RouteTest {
	@Test
	public void testRoute(){
		Route r = new Route();
		ArrayList<Flight> fList = new ArrayList<Flight>();
		
		r.setFlights(fList);
		r.setDeparture("Hong Kong");
		r.setDestination("Taiwan");
		r.setDistance(2500);
		
		assertThat(fList,is(r.getFlights()));
		assertThat("Hong Kong", is(r.getDeparture()));
		assertThat("Taiwan", is(r.getDestination()));
		assertThat(2500, is(r.getDistance()));
	}
}

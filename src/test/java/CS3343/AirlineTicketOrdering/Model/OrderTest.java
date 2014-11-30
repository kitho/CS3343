package CS3343.AirlineTicketOrdering.Model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class OrderTest {
	@Test
	public void testOrder(){
		Order o = new Order();
		Flight f = new Flight();
		
		o.setFlight(f);
		o.setId(1);
		o.setNumberOfTicket(1);
		
		assertThat(f, is(o.getFlight()));
		assertThat(1, is(o.getId()));
		assertThat(1, is(o.getNumberOfTicket()));
	}
}

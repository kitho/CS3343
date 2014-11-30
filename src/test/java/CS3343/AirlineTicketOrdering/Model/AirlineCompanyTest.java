package CS3343.AirlineTicketOrdering.Model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AirlineCompanyTest {

	@Test
	public void testAirlineCompany(){
		AirlineCompany ac = new AirlineCompany();
		List<Flight> expected = new ArrayList<Flight>();
		
		Flight f = new Flight();
		f.setAirline("Cathay Pacific Airways");
		Flight f2 = new Flight();
		f2.setAirline("");
		
		expected.add(f);
		
		ac.setAirline("Cathay Pacific Airways");
		ac.addFlight(f);
		ac.addFlight(f2);
		assertThat("Cathay Pacific Airways",is(ac.getAirline()));
		assertThat(expected,is(ac.getFlights()));
	}
}

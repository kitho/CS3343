package CS3343.AirlineTicketOrdering.FlightPathFinding;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;


import org.junit.Before;
import org.junit.Test;

import CS3343.AirlineTicketOrdering.Model.Route;
import CS3343.AirlineTicketOrdering.FlightPathFinding.*;


public class PathFindingTest {
	PathFinding pathFinding;
	
	@Before
	public void setUp(){
		pathFinding = new PathFinding("Hong Kong", "Thailand");
	}
	
	@Test
	public void testDirectFlight(){
		ArrayList<Route> resultRoutes = pathFinding.getDirectFlight();
		assertThat(resultRoutes.size(), is(1));		
	}	
}

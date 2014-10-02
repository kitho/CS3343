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
	
	
	@Test
	public void testDirectFlight1(){
		PathFinding	pathFinding = new PathFinding("Hong Kong", "Thailand");

		ArrayList<Route> resultRoutes = pathFinding.getDirectFlight();
		assertThat(resultRoutes.size(), is(1));		
	}	
	
	@Test
	public void testDirectFlight2(){
		PathFinding	pathFinding = new PathFinding("Hong Kong", "Tokyo");

		ArrayList<Route> resultRoutes = pathFinding.getDirectFlight();
		assertThat(resultRoutes.size(), is(1));		
	}
	
	@Test
	public void testDirectFlight3(){
		PathFinding	pathFinding = new PathFinding("Hong Kong", "Taiwan");
		ArrayList<Route> resultRoutes = pathFinding.getDirectFlight();
		assertThat(resultRoutes.size(), is(0));		
	}	
	
	@Test
	public void testDirectFlight4(){
		PathFinding	pathFinding = new PathFinding("Tokyo", "Taiwan");
		ArrayList<Route> resultRoutes = pathFinding.getDirectFlight();
		assertThat(resultRoutes.size(), is(1));		
	}	
	
}

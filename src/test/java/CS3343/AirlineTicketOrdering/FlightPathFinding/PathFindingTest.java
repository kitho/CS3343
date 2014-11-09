package CS3343.AirlineTicketOrdering.FlightPathFinding;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;



import org.junit.Before;
import org.junit.Test;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import CS3343.AirlineTicketOrdering.Model.Route;
import CS3343.AirlineTicketOrdering.FlightPathFinding.*;


public class PathFindingTest {
	
	
	@Test
	public void testDirectFlight1(){
		PathFinding	pathFinding = new PathFinding("Hong Kong", "Thailand");

		ArrayList<FlightPath> resultRoutes = pathFinding.getDirectFlight();
		assertThat(resultRoutes.size(), is(1));
		assertThat(resultRoutes.get(0).getFlightList().size(), is(1));
	}	
	
	@Test
	public void testDirectFlight2(){
		PathFinding	pathFinding = new PathFinding("Hong Kong", "Tokyo");

		ArrayList<FlightPath> resultRoutes = pathFinding.getDirectFlight();
		assertThat(resultRoutes.size(), is(1));
		assertThat(resultRoutes.get(0).getFlightList().size(), is(1));
	}
	
	@Test
	public void testDirectFlight3(){
		PathFinding	pathFinding = new PathFinding("Hong Kong", "Taiwan");
		ArrayList<FlightPath> resultRoutes = pathFinding.getDirectFlight();
		assertThat(resultRoutes.size(), is(1));
	}	
	
	@Test
	public void testDirectFlight4(){
		PathFinding	pathFinding = new PathFinding("Tokyo", "Taiwan");
		ArrayList<FlightPath> resultRoutes = pathFinding.getDirectFlight();
		assertThat(resultRoutes.size(), is(1));
		assertThat(resultRoutes.get(0).getFlightList().size(), is(1));
	}	
	
	@Test
	public void testDirectFlight5(){
		PathFinding	pathFinding = new PathFinding("Thailand", "Taiwan");
		ArrayList<FlightPath> resultRoutes = pathFinding.getDirectFlight();
		assertThat(resultRoutes.size(), is(1));
	}	
	
	@Test
	public void testDirectFlight6(){
		PathFinding	pathFinding = new PathFinding("Hong Kong", "Taiwan");
		ArrayList<FlightPath> resultRoutes = pathFinding.getIndirectFlight(new ArrayList<FlightPath>());
		
		System.out.println(resultRoutes.size());
		for (int i = 0; i < resultRoutes.size(); i++){
			ArrayList<Route> routes = resultRoutes.get(i).getFlightList();
			for (int j = 0; j < routes.size(); j++)
				System.out.print(routes.get(j).getDeparture() + " --> " + routes.get(j).getDestination() + ", ");
			System.out.println();
		}
		assertThat(0, is(0));
	}
	
	
}

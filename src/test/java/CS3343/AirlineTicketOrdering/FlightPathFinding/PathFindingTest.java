package CS3343.AirlineTicketOrdering.FlightPathFinding;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

















import org.junit.Test;

import TestingTool.DataWriter.CSVFileTest;
import CS3343.AirlineTicketOrdering.Controller.AirlineTicketOrderingController;
import CS3343.AirlineTicketOrdering.Controller.Impl.RouteSelectionController;
import CS3343.AirlineTicketOrdering.DataQuery.FlightQuery;
import CS3343.AirlineTicketOrdering.DataQuery.RouteQuery;
import CS3343.AirlineTicketOrdering.DataReader.Impl.AirlineCompanyCSVFileReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.FlightCSVFileReader;
import CS3343.AirlineTicketOrdering.DataWriter.Impl.FlightCSVFileWriter;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Model.Route;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.Impl.RouteSelectionView;


public class PathFindingTest {
	
	private RouteTable rTable;
	private String separator = System.getProperty("line.separator");

	public PathFindingTest(){
		rTable = new RouteTable();
		rTable.addRouteRow("Hong Kong", "Tokyo",2887);
		rTable.addRouteRow("Tokyo", "Taiwan",2101);
		rTable.addRouteRow("Tokyo", "Thailand",4426);
		rTable.addRouteRow("Hong Kong", "Thailand",1695);
		rTable.addRouteRow("Hong Kong", "Taiwan",816);
		rTable.addRouteRow("Thailand", "Taiwan",2374);
		rTable.addRouteRow("Tokyo", "Singapore",5329);
		rTable.addRouteRow("Hong Kong", "Singapore",2600);
		rTable.addRouteRow("Singapore", "Taiwan",3263);
		rTable.addRouteRow("Tokyo", "USA",10123);
		rTable.addRouteRow("Singapore", "Hong Kong",1000);
		
	}
	
	@Test
	public void testDirectFlight1(){
		
		PathFinding	pathFinding = new PathFinding("Hong Kong", "Thailand", rTable);

		ArrayList<FlightPath> resultRoutes = pathFinding.getDirectFlight();
		assertThat(resultRoutes.size(), is(1));
		assertThat(resultRoutes.get(0).getFlightList().size(), is(1));
	}	
	
	@Test
	public void testDirectFlight2(){
		PathFinding	pathFinding = new PathFinding("Hong Kong", "Tokyo", rTable);

		ArrayList<FlightPath> resultRoutes = pathFinding.getDirectFlight();
		assertThat(resultRoutes.size(), is(1));
		assertThat(resultRoutes.get(0).getFlightList().size(), is(1));
	}
	
	@Test
	public void testDirectFlight3(){
		PathFinding	pathFinding = new PathFinding("Hong Kong", "Taiwan", rTable);
		ArrayList<FlightPath> resultRoutes = pathFinding.getDirectFlight();
		assertThat(resultRoutes.size(), is(1));
	}	
	
	@Test
	public void testDirectFlight4(){
		PathFinding	pathFinding = new PathFinding("Tokyo", "Taiwan", rTable);
		ArrayList<FlightPath> resultRoutes = pathFinding.getDirectFlight();
		assertThat(resultRoutes.size(), is(1));
		assertThat(resultRoutes.get(0).getFlightList().size(), is(1));
	}	
	
	@Test
	public void testDirectFlight5(){
		PathFinding	pathFinding = new PathFinding("Thailand", "Taiwan", rTable);
		ArrayList<FlightPath> resultRoutes = pathFinding.getDirectFlight();
		assertThat(resultRoutes.size(), is(1));
	}	
	
	@Test
	public void testInDirectFlight1(){
		PathFinding	pathFinding = new PathFinding("Tokyo", "Taiwan", rTable);
		ArrayList<FlightPath> resultRoutes = pathFinding.getIndirectFlight(new ArrayList<FlightPath>());
/*
		for (int i = 0; i < resultRoutes.size(); i++){
			ArrayList<Route> routes = resultRoutes.get(i).getFlightList();
			for (int j = 0; j < routes.size(); j++)
				System.out.print(routes.get(j).getDeparture() + " --> " + routes.get(j).getDestination() + ", ");
			System.out.println();
		}
		*/
		assertThat(resultRoutes.size(), is(5));
	}
	
	
	@Test
	public void testInDirectFlight2(){
		PathFinding	pathFinding = new PathFinding("Hong Kong", "Taiwan", rTable);
		ArrayList<FlightPath> resultRoutes = pathFinding.getIndirectFlight(new ArrayList<FlightPath>());
		assertThat(resultRoutes.size(), is(6));
	}
	
	@Test
	public void testInDirectFlight3(){
		PathFinding	pathFinding = new PathFinding("Hong Kong", "China", rTable);
		ArrayList<FlightPath> resultRoutes = pathFinding.getIndirectFlight(new ArrayList<FlightPath>());
		assertThat(resultRoutes.size(), is(0));
	}
	
	@Test
	public void testDirectFlight9(){
		PathFinding	pathFinding = new PathFinding("Hong Kong", "China", rTable);
		ArrayList<FlightPath> resultRoutes = pathFinding.getDirectFlight();
		assertThat(resultRoutes.size(), is(0));
	}
	
}

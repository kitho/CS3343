package CS3343.AirlineTicketOrdering.Controller.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import CS3343.AirlineTicketOrdering.Controller.AirlineTicketOrderingController;
import CS3343.AirlineTicketOrdering.DataQuery.FlightQuery;
import CS3343.AirlineTicketOrdering.DataQuery.RouteQuery;
import CS3343.AirlineTicketOrdering.FlightPathFinding.FlightPath;
import CS3343.AirlineTicketOrdering.FlightPathFinding.PathFinding;
import CS3343.AirlineTicketOrdering.FlightPathFinding.RouteTable;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Model.Route;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

public class RouteSelectionController extends AirlineTicketOrderingController {

	private FlightQuery flightQuery;
	private RouteQuery routeQuery;
	
	public RouteSelectionController(Session session,View view, FlightQuery flightQuery, RouteQuery routeQuery){
		super(session, view);
		this.flightQuery = flightQuery;
		this.routeQuery = routeQuery;
	}

	@Override
	public void execute() throws Exception {
		List<Flight> flights = flightQuery.getFlights();

		ArrayList<Route> routes = (ArrayList<Route>) routeQuery.getAllRoute(flights);
		System.out.println(routes.size());
		RouteTable rTable = new RouteTable();
		rTable.setRouteList(routes);
		if (((String)session.getAttribute("deapture")).equals("Hong Kong"))
			System.out.println("same");
		
		if (((String)session.getAttribute("destination")).equals("Taiwan"))
			System.out.println("samesame");
		
		String departure = session.getAttribute("deapture").toString();
		String destination = (String)session.getAttribute("destination").toString();
		PathFinding pFinding = new PathFinding(departure, destination, rTable);
		ArrayList<FlightPath> fPaths = pFinding.getIndirectFlight(new ArrayList<FlightPath>());
		System.out.println(fPaths.size());

		session.setAttribute("FlightPaths", fPaths);
		
		view.display(session);
		
		if(flights.size() > 0 ){
			next();
		}
	}
	
}
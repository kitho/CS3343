package CS3343.AirlineTicketOrdering.Controller.Impl;

import java.text.SimpleDateFormat;
import java.util.List;

import CS3343.AirlineTicketOrdering.Controller.AirlineTicketOrderingController;
import CS3343.AirlineTicketOrdering.DataQuery.FlightQuery;
import CS3343.AirlineTicketOrdering.DataQuery.RouteQuery;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Model.Route;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

public class FlightSelectionController extends AirlineTicketOrderingController {

	private FlightQuery flightQuery;
	private RouteQuery routeQuery;
	
	public FlightSelectionController(Session session,View view, FlightQuery flightQuery, RouteQuery routeQuery){
		super(session, view);
		this.flightQuery = flightQuery;
		this.routeQuery = routeQuery;
	}

	@Override
	public void execute() throws Exception {
		List<Flight> flights = flightQuery.findFlightsByDepatureAndDestinationAndDate((String)session.getAttribute("deapture")
						, (String)session.getAttribute("destination"), 
						new SimpleDateFormat("yyyy-MM-dd").parse((String)session.getAttribute("depatureDate")));
		
		List<Route> routes = routeQuery.findAllRoutes();
		
		session.setAttribute("flights", flights);
		session.setAttribute("routes", routes);
		
		//***TESTING ONLY. THERE SHOULD BE A SELECTED ROUTES AND A SELECTED FIGHT CLASS IN SESSION***//
		session.setAttribute("selectedRoute", routes.get(0));
		session.setAttribute("selectedFlightClass", "Economy Class");
		
		view.display(session);
		
		next();
	}
	
}

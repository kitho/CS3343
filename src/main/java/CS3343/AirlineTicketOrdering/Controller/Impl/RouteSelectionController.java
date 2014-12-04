package CS3343.AirlineTicketOrdering.Controller.Impl;

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


/**
 * The Class RouteSelectionController is used to retrieve all the flights and routes
 * by querying and get the flight path for the view to display them
 */
public class RouteSelectionController extends AirlineTicketOrderingController {

	/** The flight query. */
	private FlightQuery flightQuery;
	
	/** The route query. */
	private RouteQuery routeQuery;
	
	/**
	 * Instantiates a new route selection controller.
	 *
	 * @param session
	 *        Pass the session object to be used in controller to retrieve or store the
	 *        data
	 * @param view
	 * 		  The view is connected to display data or ask for the input from user
	 * @param flightQuery
	 *        Query the flight
	 * @param routeQuery
	 *        Query the route
	 */
	public RouteSelectionController(Session session,View view, FlightQuery flightQuery, RouteQuery routeQuery){
		super(session, view);
		this.flightQuery = flightQuery;
		this.routeQuery = routeQuery;
	}

    /**
     * Retrieve the different flight path that match the user's departure and destination
     * and assign it to session for the view to display it
     */
	@Override
	public void execute() throws Exception {
		List<Flight> flights = flightQuery.getFlights();

		ArrayList<Route> routes = (ArrayList<Route>) routeQuery.getAllRoute(flights);
		RouteTable rTable = new RouteTable();
		rTable.setRouteList(routes);

		String departure = session.getAttribute("deapture").toString();
		String destination = session.getAttribute("destination").toString();
		
		PathFinding pFinding = new PathFinding(departure, destination, rTable);
		ArrayList<FlightPath> fPaths = pFinding.getIndirectFlight(new ArrayList<FlightPath>());
		System.out.println();

		session.setAttribute("FlightPaths", fPaths);
		
		view.display(session);
		
		if(fPaths.size() != 0){
			next();
		}
	}
	
}
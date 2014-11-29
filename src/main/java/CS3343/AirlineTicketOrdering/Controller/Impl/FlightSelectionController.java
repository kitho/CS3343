package CS3343.AirlineTicketOrdering.Controller.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import CS3343.AirlineTicketOrdering.Controller.AirlineTicketOrderingController;
import CS3343.AirlineTicketOrdering.DataQuery.FlightQuery;
import CS3343.AirlineTicketOrdering.FlightPathFinding.FlightPath;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Model.Route;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

public class FlightSelectionController extends AirlineTicketOrderingController {

	private FlightQuery flightQuery;
	
	public FlightSelectionController(Session session,View view, FlightQuery flightQuery){
		super(session, view);
		this.flightQuery = flightQuery;
	}

	@Override
	public void execute() throws Exception {
		//List<Flight> flights = flightQuery.findFlightsByDepatureAndDestinationAndDate((String)session.getAttribute("deapture")
		//				, (String)session.getAttribute("destination"), 
		//				new SimpleDateFormat("yyyy-MM-dd").parse((String)session.getAttribute("depatureDate")));
		//session.setAttribute("flights", flights);
		
		
		FlightPath fPath = (FlightPath) session.getAttribute("selectedRoute");
		List<Route> routeList = fPath.getFlightList();
		ArrayList<List<Flight>> result = new ArrayList<List<Flight>>();
		for (int i = 0; i < routeList.size(); i++)
		{
			Route route = routeList.get(i);
			List<Flight> flightList = route.getFlights();
			List<Flight> resultFlights = new ArrayList<Flight>();
			for (int j = 0; j < flightList.size(); j++){
				resultFlights.add(flightList.get(j));
			}
			result.add(resultFlights);
		}
		List<Flight> selectedFlightList = new ArrayList<Flight>();

		view.display(session);
		
		//if(flights.size() > 0 ){
			next();
		//}
	}
	

}

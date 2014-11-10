package CS3343.AirlineTicketOrdering.Controller.Impl;

import java.text.SimpleDateFormat;
import java.util.List;

import CS3343.AirlineTicketOrdering.Controller.AirlineTicketOrderingController;
import CS3343.AirlineTicketOrdering.DataQuery.FlightQuery;
import CS3343.AirlineTicketOrdering.Model.Flight;
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
		List<Flight> flights = flightQuery.findFlightsByDepatureAndDestinationAndDate((String)session.getAttribute("deapture")
						, (String)session.getAttribute("destination"), 
						new SimpleDateFormat("yyyy-MM-dd").parse((String)session.getAttribute("depatureDate")));
		
		session.setAttribute("flights", flights);
		view.display(session);
		
		next();
	}
	
}

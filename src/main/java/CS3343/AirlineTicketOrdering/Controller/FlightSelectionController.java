package CS3343.AirlineTicketOrdering.Controller;

import java.util.List;

import CS3343.AirlineTicketOrdering.CustomDateUtil.CustomDateFormatter;
import CS3343.AirlineTicketOrdering.DataQuery.FlightQuery;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.View.View;

public class FlightSelectionController extends AirlineTicketOrderingController {

	private FlightQuery flightQuery;
	
	public FlightSelectionController(View view, FlightQuery flightQuery){
		super(view);
		this.flightQuery = flightQuery;
	}

	@Override
	public void execute() throws Exception {
		List<Flight> flights = flightQuery.findFlightsByDepatureAndDestinationAndDate((String)session.getAttribute("deapture")
						, (String)session.getAttribute("destination"), 
						new CustomDateFormatter().parse((String)session.getAttribute("depatureDate")));
		
		session.setAttribute("flights", flights);
		view.display(session);
		
		next.next();
	}
	
}

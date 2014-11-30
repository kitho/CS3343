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
	
	public FlightSelectionController(Session session,View view){
		super(session, view);
	}

	@Override
	public void execute() throws Exception {
		view.display(session);
		
		next();
	}
	

}

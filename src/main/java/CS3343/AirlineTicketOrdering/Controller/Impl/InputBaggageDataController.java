package CS3343.AirlineTicketOrdering.Controller.Impl;

import CS3343.AirlineTicketOrdering.Baggage.BaggageFeeCalculator;
import CS3343.AirlineTicketOrdering.Baggage.Impl.BaggageFeeCalculatorImpl;
import CS3343.AirlineTicketOrdering.Controller.AirlineTicketOrderingController;
import CS3343.AirlineTicketOrdering.DataQuery.BaggageQuery;
import CS3343.AirlineTicketOrdering.Model.BaggagePlan;
import CS3343.AirlineTicketOrdering.Model.FlightClass;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

public class InputBaggageDataController extends AirlineTicketOrderingController {

	private BaggageQuery baggageQuery;
	
	public InputBaggageDataController(Session session, View view, BaggageQuery baggageQuery) {
		super(session, view);
		this.baggageQuery = baggageQuery;
	}

	@Override
	public void execute() throws Exception {
		//***TEMP DATA***//
		if(session.getAttribute("flightClass") == null)
			session.setAttribute("flightClass", FlightClass.ECONOMY_CLASS);
		if(session.getAttribute("numberOfTicket") == null)
			session.setAttribute("numberOfTicket", 1);
		//***TEMP DATA***//

		//Get baggage plan
		String deapture = (String) session.getAttribute("deapture");
		String destination = (String) session.getAttribute("destination");
		BaggagePlan plan = baggageQuery.findPlanByLocation(deapture, destination);
		if(plan != null)
			session.setAttribute("baggagePlan", plan);
		
		view.display(session);
		
		if(plan != null)
			next();
	}
}

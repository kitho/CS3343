package CS3343.AirlineTicketOrdering.Controller.Impl;

import CS3343.AirlineTicketOrdering.Controller.AirlineTicketOrderingController;
import CS3343.AirlineTicketOrdering.DataQuery.BaggageQuery;
import CS3343.AirlineTicketOrdering.Model.BaggagePlan;
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
		view.display(session);
		
		//Get baggage plan
		String locationFrom = (String) session.getAttribute("locationFrom");
		String locationTo = (String) session.getAttribute("locationTo");
		BaggagePlan plan = baggageQuery.findPlanByLocation(locationFrom, locationTo);
		if(plan != null)
			session.setAttribute("baggagePlan", plan);
		
		next();
	}

}

package CS3343.AirlineTicketOrdering.Controller.Impl;

import CS3343.AirlineTicketOrdering.Baggage.BaggageFeeCalculator;
import CS3343.AirlineTicketOrdering.Baggage.BaggageFeeCalculatorImpl;
import CS3343.AirlineTicketOrdering.Baggage.BaggageRulePrinter;
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
		session.setAttribute("flightClass", FlightClass.ECONOMY_CLASS);
		session.setAttribute("amountOfPassenger", 1);
		//***TEMP DATA***//

		//Get baggage plan
		String deapture = (String) session.getAttribute("deapture");
		String destination = (String) session.getAttribute("destination");
		BaggagePlan plan = baggageQuery.findPlanByLocation(deapture, destination);
		if(plan != null)
			session.setAttribute("baggagePlan", plan);
		
		BaggageRulePrinter rulePrinter = new BaggageRulePrinter();
		String flightClass = (String) session.getAttribute("flightClass");
		session.setAttribute("baggagePlanRule", rulePrinter.printRule(plan, flightClass));
		
		view.display(session);
		next();
	}

}

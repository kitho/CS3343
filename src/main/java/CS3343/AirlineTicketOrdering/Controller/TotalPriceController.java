package CS3343.AirlineTicketOrdering.Controller;

import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

public class TotalPriceController extends AirlineTicketOrderingController {

	public TotalPriceController(Session session, View view) {
		super(session, view);
	}

	@Override
	public void execute() throws Exception {
		Flight flight = (Flight)session.getAttribute("flight");
		int numberOfTicket = (Integer)session.getAttribute("numberOfTicket"); 
	}

}

package CS3343.AirlineTicketOrdering.Controller.Impl;
import CS3343.AirlineTicketOrdering.Controller.AirlineTicketOrderingController;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

public class InputDestinationController extends AirlineTicketOrderingController {
	
	public InputDestinationController(Session session, View view) {
		super(session, view);
	}
	
	@Override
	public void execute() throws Exception {
		view.display(session);
		
		next.next();
	}

}

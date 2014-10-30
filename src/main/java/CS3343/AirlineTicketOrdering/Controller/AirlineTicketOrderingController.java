package CS3343.AirlineTicketOrdering.Controller;

import CS3343.AirlineTicketOrdering.Session.Session;

public abstract class AirlineTicketOrderingController extends ControllerChain implements Controller {

	protected Session session;
	protected AirlineTicketOrderingController next;
	
	public AirlineTicketOrderingController(){
		session = Session.getInstance();
	}
	
	public abstract void execute();
	
}

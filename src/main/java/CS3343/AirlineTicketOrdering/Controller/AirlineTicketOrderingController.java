package CS3343.AirlineTicketOrdering.Controller;

import CS3343.AirlineTicketOrdering.Session.Session;

public abstract class AirlineTicketOrderingController implements Controller {

	protected Session session;
	
	public AirlineTicketOrderingController(){
		session = Session.getInstance();
	}
	
	public abstract void execute();
	
}

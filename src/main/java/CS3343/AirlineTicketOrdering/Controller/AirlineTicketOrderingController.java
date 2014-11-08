package CS3343.AirlineTicketOrdering.Controller;

import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

public abstract class AirlineTicketOrderingController extends ControllerChain implements Controller {

	protected Session session;
	protected View view;
	protected ControllerChain next;
	
	public AirlineTicketOrderingController(Session session, View view){
		this.session = session;
		this.view = view;
	}
	
	public abstract void execute() throws Exception;
	
}

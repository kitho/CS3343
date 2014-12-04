package CS3343.AirlineTicketOrdering.Controller;

import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

/**
 * The AirlineTicketOrderingController is implemented the controller interface and
 * also the controller chain pattern, all the controller in the airline ticket ordering system
 * should extend this class
 */
public abstract class AirlineTicketOrderingController extends ControllerChain implements Controller {

	/** The session to store different object */
	protected Session session;
	
	/** The view. */
	protected View view;
	
	/** The next. */
	protected ControllerChain next;
	
	/**
	 * Instantiates a new airline ticket ordering controller.
	 *
	 * @param session
	 * 	      Pass the session object to be used in controller to retrieve or store the
	 *        data
	 * @param view
	 * 		  The view is connected to display data or ask for the input from user
	 */
	public AirlineTicketOrderingController(Session session, View view){
		this.session = session;
		this.view = view;
	}
	
	/**
	 * Execute the task that it needs to perform
	 *
	 * @throws Exception the exception
	 */
	public abstract void execute() throws Exception;
	
}

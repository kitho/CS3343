package CS3343.AirlineTicketOrdering.Controller.Impl;
import CS3343.AirlineTicketOrdering.Controller.AirlineTicketOrderingController;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

/**
 * The Class InputDestinationController is used to control the view to perform
 * action to ask the user input the departure, destination and date
 */
public class InputDestinationController extends AirlineTicketOrderingController {
	
	/**
	 * Instantiates a new input destination controller.
	 *
	 * @param session
	 *        Pass the session object to be used in controller to retrieve or store the
	 *        data
	 * @param view
	 *        The view is connected to display data or ask for the input from user
	 */
	public InputDestinationController(Session session, View view) {
		super(session, view);
	}
	
	/**
	 * To invoke the view to display the view for user to input the credit card
	 * information
	 */
	@Override
	public void execute() throws Exception {
		session.clear();
		view.display(session);
		next();
	}

}

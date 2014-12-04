package CS3343.AirlineTicketOrdering.Controller.Impl;

import CS3343.AirlineTicketOrdering.Controller.AirlineTicketOrderingController;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

/**
 * The Class EnquireCreditCardController is used to control the view to perform
 * action for any credit card input from user
 */
public class EnquireCreditCardController extends AirlineTicketOrderingController {

	/**
	 * Instantiates a new enquire credit card controller.
	 *
	 * @param session
	 *        Pass the session object to be used in controller to retrieve or store the
	 *        data
	 * @param view
	 *        The view is connected to display data or ask for the input from user
	 */
	public EnquireCreditCardController(Session session, View view) {
		super(session, view);
	}

	
	/**
	 * To invoke the view to display the view for user to input the credit card
	 * information
	 */
	@Override
	public void execute() throws Exception {		
		view.display(session);
		next();
	}

}

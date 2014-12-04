package CS3343.AirlineTicketOrdering.Controller.Impl;

import CS3343.AirlineTicketOrdering.Controller.AirlineTicketOrderingController;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;


/**
 * The FlightSelectionController is used to control the view to perform
 * action to ask the user select the flight
 */
public class FlightSelectionController extends AirlineTicketOrderingController {
	
	/**
	 * Instantiates a new flight selection controller.
	 *
	 * @param session
	 *        Pass the session object to be used in controller to retrieve or store the
	 *        data
	 * @param view
	 *        The view is connected to display data or ask for the input from user
	 */
	public FlightSelectionController(Session session,View view){
		super(session, view);
	}

	/**
	 * To invoke the view to display the view for user to select the flight
	 */
	@Override
	public void execute() throws Exception {
		view.display(session);
		
		next();
	}
	

}

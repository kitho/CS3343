package CS3343.AirlineTicketOrdering.View;

import java.io.IOException;

import CS3343.AirlineTicketOrdering.Session.Session;

/**
 * The Interface View.
 */
public interface View {

	/**
	 * Display all the content
	 *
	 * @param response the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void display(Session response)throws IOException;

}

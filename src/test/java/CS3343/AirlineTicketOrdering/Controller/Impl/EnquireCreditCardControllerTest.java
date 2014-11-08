package CS3343.AirlineTicketOrdering.Controller.Impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import CS3343.AirlineTicketOrdering.Controller.AirlineTicketOrderingController;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

public class EnquireCreditCardControllerTest {

	@Test
	public void testExecute() throws Exception {
		Session session = mock(Session.class);
		View view = mock(View.class);
		AirlineTicketOrderingController next = mock(AirlineTicketOrderingController.class); 
		AirlineTicketOrderingController enquireCreditCardController = new EnquireCreditCardController(session, view);
		enquireCreditCardController.setNext(next);
		enquireCreditCardController.execute();
		
		verify(view, times(1)).display(session);
		verify(next,times(1)).execute();
	}

}

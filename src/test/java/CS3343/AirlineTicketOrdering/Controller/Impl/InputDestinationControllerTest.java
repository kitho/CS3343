package CS3343.AirlineTicketOrdering.Controller.Impl;

import org.junit.Test;

import static org.mockito.Mockito.*;
import CS3343.AirlineTicketOrdering.Controller.AirlineTicketOrderingController;
import CS3343.AirlineTicketOrdering.Controller.Impl.InputDestinationController;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

public class InputDestinationControllerTest {

	@Test
	public void testExecute() throws Exception {
		Session session = mock(Session.class);
		View view = mock(View.class);
		AirlineTicketOrderingController next = mock(AirlineTicketOrderingController.class); 
		AirlineTicketOrderingController inputDestinationController = new InputDestinationController(session, view);
		inputDestinationController.setNext(next);
		inputDestinationController.execute();
		
		verify(view, times(1)).display(session);
		verify(next,times(1)).execute();
	}

}

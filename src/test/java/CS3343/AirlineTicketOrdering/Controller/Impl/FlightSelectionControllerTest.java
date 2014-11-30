package CS3343.AirlineTicketOrdering.Controller.Impl;

import static org.mockito.Mockito.*;

import org.junit.Test;

import CS3343.AirlineTicketOrdering.Controller.AirlineTicketOrderingController;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

public class FlightSelectionControllerTest {

	@Test
	public void testExecute() throws Exception {
		Session session = mock(Session.class);
		View view = mock(View.class);
		
		AirlineTicketOrderingController next = mock(AirlineTicketOrderingController.class); 
		AirlineTicketOrderingController flightSelectionController = new FlightSelectionController(session, view);
		
		when(session.getAttribute("depatureDate")).thenReturn("2012-12-01 22:30:15");
		
		flightSelectionController.setNext(next);
		flightSelectionController.execute();
		
		verify(view, times(1)).display(session);
		verify(next,times(1)).execute();
	}


}

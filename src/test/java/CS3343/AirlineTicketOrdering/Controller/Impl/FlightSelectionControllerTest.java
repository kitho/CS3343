package CS3343.AirlineTicketOrdering.Controller.Impl;

import static org.mockito.Mockito.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import CS3343.AirlineTicketOrdering.Controller.AirlineTicketOrderingController;
import CS3343.AirlineTicketOrdering.DataQuery.FlightQuery;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

public class FlightSelectionControllerTest {

	@Test
	public void testExecute() throws Exception {
		Session session = mock(Session.class);
		View view = mock(View.class);
		FlightQuery flightQuery = mock(FlightQuery.class);
		List<Flight> flights = mock(List.class);
		AirlineTicketOrderingController next = mock(AirlineTicketOrderingController.class); 
		AirlineTicketOrderingController inputDestinationController = new FlightSelectionController(session, view, flightQuery);
		
		when(session.getAttribute("depatureDate")).thenReturn("2012-12-01 22:30:15");
		when(flightQuery.findFlightsByDepatureAndDestinationAndDate(any(String.class), any(String.class), any(Date.class))).thenReturn(flights);
		
		inputDestinationController.setNext(next);
		inputDestinationController.execute();
		
		verify(session, times(1)).setAttribute("flights", flights);
		verify(view, times(1)).display(session);
		verify(next,times(1)).execute();
	}

}

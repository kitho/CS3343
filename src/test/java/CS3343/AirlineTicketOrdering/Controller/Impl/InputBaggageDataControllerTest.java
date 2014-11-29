package CS3343.AirlineTicketOrdering.Controller.Impl;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.Test;
import CS3343.AirlineTicketOrdering.Controller.AirlineTicketOrderingController;
import CS3343.AirlineTicketOrdering.DataQuery.BaggageQuery;
import CS3343.AirlineTicketOrdering.Model.BaggagePlan;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

public class InputBaggageDataControllerTest {

	@Test
	public void testExecuteWithPlan() throws Exception  {
		Session session = mock(Session.class);
		session.setAttribute("flightClass", "Economy Class");
		session.setAttribute("numberOfTicket", 1);
		View view = mock(View.class);
		BaggageQuery baggageQuery = mock(BaggageQuery.class);
		BaggagePlan baggagePlan = mock(BaggagePlan.class);
		AirlineTicketOrderingController next = mock(AirlineTicketOrderingController.class); 
		AirlineTicketOrderingController inputBaggageDataController = new InputBaggageDataController(session, view, baggageQuery);
		when(baggageQuery.findPlanByLocation(any(String.class), any(String.class))).thenReturn(baggagePlan);
		
		inputBaggageDataController.setNext(next);
		inputBaggageDataController.execute();
		
		verify(session, times(1)).setAttribute("baggagePlan", baggagePlan);
		verify(view, times(1)).display(session);
		verify(next,times(1)).execute();
	}
	
	@Test
	public void testExecuteWithoutPlan() throws Exception  {
		Session session = mock(Session.class);
		session.setAttribute("flightClass", "Economy Class");
		session.setAttribute("numberOfTicket", 1);
		View view = mock(View.class);
		BaggageQuery baggageQuery = mock(BaggageQuery.class);
		BaggagePlan baggagePlan = mock(BaggagePlan.class);
		AirlineTicketOrderingController next = mock(AirlineTicketOrderingController.class); 
		AirlineTicketOrderingController inputBaggageDataController = new InputBaggageDataController(session, view, baggageQuery);
		when(baggageQuery.findPlanByLocation(any(String.class), any(String.class))).thenReturn(null);
		
		inputBaggageDataController.setNext(next);
		inputBaggageDataController.execute();
		
		verify(session, times(0)).setAttribute("baggagePlan", baggagePlan);
		verify(view, times(1)).display(session);
		verify(next,times(0)).execute();
	}

}

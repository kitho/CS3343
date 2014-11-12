package CS3343.AirlineTicketOrdering.Controller.Impl;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;

import CS3343.AirlineTicketOrdering.Calculator.Calculator;
import CS3343.AirlineTicketOrdering.Controller.AirlineTicketOrderingController;
import CS3343.AirlineTicketOrdering.Discount.Discount;
import CS3343.AirlineTicketOrdering.Model.CreditCard;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

public class OrderConfirmationControllerTest {

	@Test
	public void testExecute() throws Exception {
		Session session = mock(Session.class);
		View view = mock(View.class);
		Discount discount = mock(Discount.class);
		Calculator calculator= mock(Calculator.class);
		CreditCard creditCard = mock(CreditCard.class);
		List<Flight> flights = mock(List.class);
		int numberOfTicket = 1;
		double[] discountOff = {0.2,0.4};
		double totalPrice = 100.0;
		
		when(session.getAttribute("creditCard")).thenReturn(creditCard);
		when(session.getAttribute("flights")).thenReturn(flights);
		when(session.getAttribute("numberOfTicket")).thenReturn(numberOfTicket);
		when(discount.getDiscount(anyList(), any(CreditCard.class))).thenReturn(discountOff);
		when(calculator.calculate(anyList(), anyInt(), any(double[].class))).thenReturn(totalPrice);
		
		AirlineTicketOrderingController next = mock(AirlineTicketOrderingController.class); 
		AirlineTicketOrderingController orderConfirmationController = new OrderConfirmationController(session, view, discount, calculator);
		orderConfirmationController.setNext(next);
		orderConfirmationController.execute();
		
		verify(session, times(1)).setAttribute("totalPrice", totalPrice);
		verify(view, times(1)).display(session);
		verify(next,times(1)).execute();
	}

}

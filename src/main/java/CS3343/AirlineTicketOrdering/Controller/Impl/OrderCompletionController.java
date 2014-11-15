package CS3343.AirlineTicketOrdering.Controller.Impl;

import java.util.List;

import CS3343.AirlineTicketOrdering.Controller.AirlineTicketOrderingController;
import CS3343.AirlineTicketOrdering.DataQuery.OrderQuery;
import CS3343.AirlineTicketOrdering.Model.CreditCard;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Model.Order;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

public class OrderCompletionController extends AirlineTicketOrderingController {
	
	OrderQuery orderQuery;

	public OrderCompletionController(Session session, View view, OrderQuery orderQuery) {
		super(session, view);
		this.orderQuery = orderQuery;
	}

	@Override
	public void execute() throws Exception {
		Order order = new Order();
		if(session.getAttribute("confirmed").equals("Yes")){
			int newOrderId = orderQuery.getMaxOrderId() + 1;
			CreditCard creditCard = (CreditCard)session.getAttribute("creditCard");
			List<Flight> flights = (List<Flight>)(session.getAttribute("flights")); 
			int numberOfTicket = (Integer)(session.getAttribute("numberOfTicket"));
	
			order.setId(newOrderId);
			order.setFlight(flights.get(0));
			order.setNumberOfTicket(numberOfTicket);
			orderQuery.newOrder(order);
			session.setAttribute("orders", order);
		}
		view.display(session);
		next();
	}

}

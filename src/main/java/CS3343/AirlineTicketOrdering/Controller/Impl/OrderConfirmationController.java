package CS3343.AirlineTicketOrdering.Controller.Impl;

import java.util.List;

import CS3343.AirlineTicketOrdering.Calculator.Calculator;
import CS3343.AirlineTicketOrdering.Controller.AirlineTicketOrderingController;
import CS3343.AirlineTicketOrdering.Discount.Discount;
import CS3343.AirlineTicketOrdering.Model.CreditCard;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

public class OrderConfirmationController extends AirlineTicketOrderingController {

	private Discount discount;
	private Calculator calculator;
	
	public OrderConfirmationController(Session session, View view, Discount discount, Calculator calculator) {
		super(session, view);
		this.discount = discount;
		this.calculator = calculator;
	}

	@Override
	public void execute() throws Exception {
		CreditCard creditCard = (CreditCard)session.getAttribute("creditCard");
		List<Flight> flights = (List<Flight>)(session.getAttribute("flights")); 
		int numberOfTicket = (Integer)(session.getAttribute("numberOfTicket"));
		
		double totalPrice = calculator.calculate(flights, numberOfTicket, discount.getDiscount(flights, creditCard));
		
		session.setAttribute("totalPrice", totalPrice);
		view.display(session);

		next();
	}

}

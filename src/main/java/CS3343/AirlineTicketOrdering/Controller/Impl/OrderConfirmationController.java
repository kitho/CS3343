package CS3343.AirlineTicketOrdering.Controller.Impl;

import java.util.List;

import org.omg.CORBA.SystemException;

import CS3343.AirlineTicketOrdering.Calculator.Calculator;
import CS3343.AirlineTicketOrdering.Calculator.Impl.AirlineCalculator;
import CS3343.AirlineTicketOrdering.Controller.AirlineTicketOrderingController;
import CS3343.AirlineTicketOrdering.CreditCardTools.CreditCardDiscountChecker;
import CS3343.AirlineTicketOrdering.CreditCardTools.Impl.CreditCardAirlineDiscountChecker;
import CS3343.AirlineTicketOrdering.DataQuery.AirlineDiscountQuery;
import CS3343.AirlineTicketOrdering.Discount.Discount;
import CS3343.AirlineTicketOrdering.Model.CreditCard;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

public class OrderConfirmationController extends AirlineTicketOrderingController {

	private Calculator calculator;
	private AirlineDiscountQuery airlineDiscountQuery;
	
	public OrderConfirmationController(Session session, View view, AirlineCalculator calculator, AirlineDiscountQuery airlineDiscountQuery) {
		super(session, view);
		this.calculator = calculator;
		this.airlineDiscountQuery = airlineDiscountQuery;
	}

	@Override
	public void execute() throws Exception {
		CreditCard creditCard = (CreditCard)session.getAttribute("creditCard");
		List<Flight> flights = (List<Flight>)(session.getAttribute("flights"));
		int numberOfTicket = (Integer)(session.getAttribute("numberOfTicket"));
		CreditCardDiscountChecker ccadc = new CreditCardAirlineDiscountChecker(this.airlineDiscountQuery.findAllAirlineDiscounts());
		
		double totalPrice = calculator.calculate(flights, numberOfTicket,ccadc.check(flights, creditCard));
		
		session.setAttribute("totalPrice", totalPrice);
		view.display(session);
		
		next();
	}

}

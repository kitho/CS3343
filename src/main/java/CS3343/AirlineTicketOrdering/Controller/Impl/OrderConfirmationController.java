package CS3343.AirlineTicketOrdering.Controller.Impl;

import java.util.List;

import CS3343.AirlineTicketOrdering.Calculator.Calculator;
import CS3343.AirlineTicketOrdering.Calculator.Impl.AirlineCalculator;
import CS3343.AirlineTicketOrdering.Controller.AirlineTicketOrderingController;
import CS3343.AirlineTicketOrdering.CreditCardTools.CreditCardDiscountChecker;
import CS3343.AirlineTicketOrdering.CreditCardTools.Impl.CreditCardAirlineDiscountChecker;
import CS3343.AirlineTicketOrdering.DataQuery.AirlineDiscountQuery;
import CS3343.AirlineTicketOrdering.Model.CreditCard;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

/**
 * The OrderConfirmationController is used to calculate the ticket fees 
 * from the user and ask view to display it
 */
public class OrderConfirmationController extends AirlineTicketOrderingController {

	/** The calculator to calculate the ticket fees */
	private Calculator calculator;
	
	/** The airline discount query discount for the airline */
	private AirlineDiscountQuery airlineDiscountQuery;
	
	/**
	 * Instantiates a new order confirmation controller.
	 *
	 * @param session
	 *        Pass the session object to be used in controller to retrieve or store the
	 *        data
	 * @param view
	 *        The view is connected to display data or ask for the input from user
	 * @param calculator
	 * 		  The calculator to calculate the ticket fees
	 * @param airlineDiscountQuery
	 *        The airline discount query discount for the airline
	 */
	public OrderConfirmationController(Session session, View view, AirlineCalculator calculator, AirlineDiscountQuery airlineDiscountQuery) {
		super(session, view);
		this.calculator = calculator;
		this.airlineDiscountQuery = airlineDiscountQuery;
	}

	/**
	 * Using the calculator object to calculate the fees and ask the view to display it
	 */
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

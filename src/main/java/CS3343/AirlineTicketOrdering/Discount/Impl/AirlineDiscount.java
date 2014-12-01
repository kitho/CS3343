package CS3343.AirlineTicketOrdering.Discount.Impl;

import java.util.ArrayList;
import java.util.List;

import CS3343.AirlineTicketOrdering.CreditCardTools.CreditCardDiscountChecker;
import CS3343.AirlineTicketOrdering.CreditCardTools.Impl.CreditCardAirlineDiscountChecker;
import CS3343.AirlineTicketOrdering.Discount.Discount;
import CS3343.AirlineTicketOrdering.Model.AirlineCompany;
import CS3343.AirlineTicketOrdering.Model.CreditCard;
import CS3343.AirlineTicketOrdering.Model.Flight;

public class AirlineDiscount implements Discount {
	private String airline;
	private String creditCardType;
	private double discount;
	
	/**
	 * Constructs a airline discount
	 * @return 
	 */
	public AirlineDiscount(){
	
	}

	/**
	 * get airline company
	 * @return String airline
	 */
	public String getAirline() {
		return airline;
	}

	/**
	 * set airline company
	 * @param airline
	 * @return 
	 */
	public void setAirline(String airline) {
		this.airline = airline;
	}

	/**
	 * get credit card type
	 * @return String creditCardType
	 */
	public String getCreditCardType() {
		return creditCardType;
	}

	/**
	 * set credit card type
	 * @param creditCardType
	 * @return 
	 */
	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}

	/**
	 * set discount
	 * @param discount
	 * @return 
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	/**
	 * get discount
	 * @return double discount
	 */
	@Override
	public double getDiscount() {
		// TODO Auto-generated method stub
		return discount;
	}

}

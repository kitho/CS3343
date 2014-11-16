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
	
	public AirlineDiscount(){
	
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getCreditCardType() {
		return creditCardType;
	}

	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	@Override
	public double getDiscount() {
		// TODO Auto-generated method stub
		return discount;
	}

}

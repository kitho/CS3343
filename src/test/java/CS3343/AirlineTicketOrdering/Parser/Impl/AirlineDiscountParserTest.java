package CS3343.AirlineTicketOrdering.Parser.Impl;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import CS3343.AirlineTicketOrdering.CustomDateUtil.CustomDateFormatter;
import CS3343.AirlineTicketOrdering.Discount.Impl.AirlineDiscount;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Parser.Parser;
import CS3343.AirlineTicketOrdering.Parser.Impl.AirlineDiscountParser;


public class AirlineDiscountParserTest {

	@Test
	public void parseStringTest() throws ParseException {
		String airline = "Cathay Pacific Airways";
		String creditCardType = "VISA";
		double discount = 0.8;
		String line = airline + "," + creditCardType + "," + discount;
		Parser<AirlineDiscount> airlineDiscountParser = new AirlineDiscountParser();
		AirlineDiscount airlineDiscount = airlineDiscountParser.parseString(line);
		
		assertThat(airline,is(airlineDiscount.getAirline()));
		assertThat(creditCardType,is(airlineDiscount.getCreditCardType()));
		assertThat(discount,is(airlineDiscount.getDiscount()));
	}
	
	@Test
	public void parseObjectTest() throws ParseException {	
		AirlineDiscount airlineDiscount = new AirlineDiscount();
		
		airlineDiscount.setAirline("Cathay Pacific Airways");
		airlineDiscount.setCreditCardType("VISA");
		airlineDiscount.setDiscount(0.8);
		
		Parser<AirlineDiscount> airlineDiscountParser = new AirlineDiscountParser();
		String line = airlineDiscountParser.parseObject(airlineDiscount);
		
		String airlineDiscountString = airlineDiscount.getAirline() + "," + airlineDiscount.getCreditCardType() + "," + airlineDiscount.getDiscount();
		assertThat(airlineDiscountString,is(line));
	}
}

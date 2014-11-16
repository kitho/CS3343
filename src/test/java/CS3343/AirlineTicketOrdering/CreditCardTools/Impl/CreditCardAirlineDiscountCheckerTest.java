package CS3343.AirlineTicketOrdering.CreditCardTools.Impl;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import CS3343.AirlineTicketOrdering.Model.CreditCard;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.CreditCardTools.CreditCardDiscountChecker;
import CS3343.AirlineTicketOrdering.CreditCardTools.Impl.CreditCardAirlineDiscountChecker;
import CS3343.AirlineTicketOrdering.Discount.Impl.AirlineDiscount;

import org.junit.Test;

public class CreditCardAirlineDiscountCheckerTest {
	
	@Test
	public void testDiscountsWithCAandVISA(){
		List<AirlineDiscount> airlineDiscounts = new ArrayList<AirlineDiscount>();
		
		AirlineDiscount ad1 = new AirlineDiscount();
		ad1.setAirline("Cathay Pacific Airways");
		ad1.setCreditCardType("VISA");
		ad1.setDiscount(0.8);
		
		AirlineDiscount ad2 = new AirlineDiscount();
		ad2.setAirline("China Airlines");
		ad2.setCreditCardType("VISA");
		ad2.setDiscount(0.9);
		
		AirlineDiscount ad3 = new AirlineDiscount();
		ad3.setAirline("Hong Kong Airlines");
		ad3.setCreditCardType("VISA");
		ad3.setDiscount(0.8);
		
		airlineDiscounts.add(ad1);
		airlineDiscounts.add(ad2);
		airlineDiscounts.add(ad3);
		
		Flight f1 = new Flight();
		f1.setAirline("Cathay Pacific Airways");
		
		Flight f2 = new Flight();
		f2.setAirline("China Airlines");
		
		Flight f3 = new Flight();
		f3.setAirline("Hong Kong Airlines");
		
		ArrayList<Flight> flights = new ArrayList<Flight>();
		
		flights.add(f1);
		flights.add(f2);
		flights.add(f3);
		
		CreditCard card = new CreditCard();
		card.setBank("HSBC");
		card.setCreditCardType("VISA");
		card.setCreditCardNumber("4890-8500-0000-8888");
		
		CreditCardDiscountChecker ccadc = new CreditCardAirlineDiscountChecker(airlineDiscounts);
		double[] resultDiscounts = ccadc.check(flights, card);
		
		assertThat(0.8,is(resultDiscounts[0]));
		assertThat(0.9,is(resultDiscounts[1]));
		assertThat(0.8,is(resultDiscounts[2]));
		
	}
	
	@Test
	public void testDiscountsWithCAandMasterCard(){
		List<AirlineDiscount> airlineDiscounts = new ArrayList<AirlineDiscount>();
		
		AirlineDiscount ad1 = new AirlineDiscount();
		ad1.setAirline("Cathay Pacific Airways");
		ad1.setCreditCardType("Master Card");
		ad1.setDiscount(0.85);
		
		AirlineDiscount ad2 = new AirlineDiscount();
		ad2.setAirline("China Airlines");
		ad2.setCreditCardType("Master Card");
		ad2.setDiscount(0.9);
		
		AirlineDiscount ad3 = new AirlineDiscount();
		ad3.setAirline("Hong Kong Airlines");
		ad3.setCreditCardType("Master Card");
		ad3.setDiscount(0.8);
		
		airlineDiscounts.add(ad1);
		airlineDiscounts.add(ad2);
		airlineDiscounts.add(ad3);
		
		Flight f1 = new Flight();
		f1.setAirline("Cathay Pacific Airways");
		
		Flight f2 = new Flight();
		f2.setAirline("China Airlines");
		
		Flight f3 = new Flight();
		f3.setAirline("Hong Kong Airlines");
		
		ArrayList<Flight> flights = new ArrayList<Flight>();
		
		flights.add(f1);
		flights.add(f2);
		flights.add(f3);
		
		CreditCard card = new CreditCard();
		card.setBank("HSBC");
		card.setCreditCardType("Master Card");
		card.setCreditCardNumber("5120-4300-8888-8888");
		
		CreditCardDiscountChecker ccadc = new CreditCardAirlineDiscountChecker(airlineDiscounts);
		double[] resultDiscounts = ccadc.check(flights, card);
		
		assertThat(0.85,is(resultDiscounts[0]));
		assertThat(0.9,is(resultDiscounts[1]));
		assertThat(0.8,is(resultDiscounts[2]));
		
	}
	
	@Test
	public void testDiscountsWithCAandAmericanExpress(){
		List<AirlineDiscount> airlineDiscounts = new ArrayList<AirlineDiscount>();
		
		AirlineDiscount ad1 = new AirlineDiscount();
		ad1.setAirline("Cathay Pacific Airways");
		ad1.setCreditCardType("American Express");
		ad1.setDiscount(0.75);
		
		AirlineDiscount ad2 = new AirlineDiscount();
		ad2.setAirline("China Airlines");
		ad2.setCreditCardType("American Express");
		ad2.setDiscount(0.85);
		
		AirlineDiscount ad3 = new AirlineDiscount();
		ad3.setAirline("Hong Kong Airlines");
		ad3.setCreditCardType("American Express");
		ad3.setDiscount(0.7);
		
		airlineDiscounts.add(ad1);
		airlineDiscounts.add(ad2);
		airlineDiscounts.add(ad3);
		
		Flight f1 = new Flight();
		f1.setAirline("Cathay Pacific Airways");
		
		Flight f2 = new Flight();
		f2.setAirline("China Airlines");
		
		Flight f3 = new Flight();
		f3.setAirline("Hong Kong Airlines");
		
		ArrayList<Flight> flights = new ArrayList<Flight>();
		
		flights.add(f1);
		flights.add(f2);
		flights.add(f3);
		
		CreditCard card = new CreditCard();
		card.setBank("HSBC");
		card.setCreditCardType("American Express");
		card.setCreditCardNumber("5120-4300-8888-8888");
		
		CreditCardDiscountChecker ccadc = new CreditCardAirlineDiscountChecker(airlineDiscounts);
		double[] resultDiscounts = ccadc.check(flights, card);
		
		assertThat(0.75,is(resultDiscounts[0]));
		assertThat(0.85,is(resultDiscounts[1]));
		assertThat(0.7,is(resultDiscounts[2]));
		
	}
	
	@Test
	public void testDiscountsWithoutSuitableAirlinesOrCreditCards(){
		List<AirlineDiscount> airlineDiscounts = new ArrayList<AirlineDiscount>();
		
		AirlineDiscount ad1 = new AirlineDiscount();
		ad1.setAirline("Cathay Pacific Airways");
		ad1.setCreditCardType("American Express");
		ad1.setDiscount(0.75);
		
		AirlineDiscount ad2 = new AirlineDiscount();
		ad2.setAirline("China Airlines");
		ad2.setCreditCardType("American Express");
		ad2.setDiscount(0.85);
		
		AirlineDiscount ad3 = new AirlineDiscount();
		ad3.setAirline("Hong Kong Airlines");
		ad3.setCreditCardType("American Express");
		ad3.setDiscount(0.7);
		
		airlineDiscounts.add(ad1);
		airlineDiscounts.add(ad2);
		airlineDiscounts.add(ad3);
		
		Flight f1 = new Flight();
		f1.setAirline("Cathay Pacific Airways");
		
		Flight f2 = new Flight();
		f2.setAirline("China Airlines");
		
		Flight f3 = new Flight();
		f3.setAirline("Hong Kong Airlines");
		
		ArrayList<Flight> flights = new ArrayList<Flight>();
		
		flights.add(f1);
		flights.add(f2);
		flights.add(f3);

		CreditCard card = new CreditCard();
		card.setBank("HSBC");
		card.setCreditCardType("Citizen");
		card.setCreditCardNumber("1290-8500-0000-8888");
		
		CreditCardDiscountChecker ccadc = new CreditCardAirlineDiscountChecker(airlineDiscounts);
		double[] resultDiscounts = ccadc.check(flights, card);
		
		assertThat(1.0,is(resultDiscounts[0]));
		assertThat(1.0,is(resultDiscounts[1]));
		assertThat(1.0,is(resultDiscounts[2]));
		
	}
}

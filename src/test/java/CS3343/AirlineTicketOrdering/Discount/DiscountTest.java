package CS3343.AirlineTicketOrdering.Discount;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import CS3343.AirlineTicketOrdering.AirlineCompany.AirlineCompanyShort;
import CS3343.AirlineTicketOrdering.CreditCardTools.DiscountChecker;
import CS3343.AirlineTicketOrdering.CreditCardTools.Impl.CreditCardAirlineDiscountChecker;
import CS3343.AirlineTicketOrdering.Discount.Impl.StubDiscount;
import CS3343.AirlineTicketOrdering.FlightPathFinding.FlightPath;
import CS3343.AirlineTicketOrdering.FlightPathFinding.PathFinding;
import CS3343.AirlineTicketOrdering.Model.CreditCard;
import CS3343.AirlineTicketOrdering.Model.Flight;

public class DiscountTest {
	private ArrayList<Flight> fList;
	
	@Before
	public void setUp() throws IOException{
		fList = new ArrayList<Flight>();
		
		Flight f1 = new Flight();
		f1.setAirline(AirlineCompanyShort.CPA.value());
		f1.setDepature("Tokyo");
		f1.setDestination("Taiwan");
		f1.setOneWayPrice(2000);
		fList.add(f1);
		
		Flight f2 = new Flight();
		f2.setAirline(AirlineCompanyShort.CRK.value());
		f2.setDepature("Tokyo");
		f2.setDestination("Taiwan");
		f2.setOneWayPrice(1700);
		fList.add(f2);
		
		Flight f3 = new Flight();
		f3.setAirline(AirlineCompanyShort.HDA.value());
		f3.setDepature("Tokyo");
		f3.setDestination("Taiwan");
		f3.setOneWayPrice(1800);
		fList.add(f3);
	}
	
	
	@Test
	public void testDiscountByVISA(){
		CreditCard card = new CreditCard();
		Discount discount = new StubDiscount();
		double[] discounts;
		
		card.setBank("HSBC");
		card.setCreditCardType("VISA");
		card.setCreditCardNumber("4890-8500-0000-8888");
		
		discounts = discount.getDiscount(fList, card);
		
		//Airline - VISA's discount: CPA - 0.8, CRK - 0.9, HDA - 0.8
		assertThat(0.8, is(discounts[0]));
		assertThat(0.9, is(discounts[1]));
		assertThat(0.8, is(discounts[2]));
	}
	
	@Test
	public void testDiscountByMasterCard(){
		CreditCard card = new CreditCard();
		Discount discount = new StubDiscount();
		double[] discounts;
		
		card.setBank("HSBC");
		card.setCreditCardType("Master Card");
		card.setCreditCardNumber("5120-4300-8888-8888");
		
		discounts = discount.getDiscount(fList, card);
		
		//Airline - MasterCard's discount: CPA - 0.85, CRK - 0.9, HDA - 0.8
		assertThat(0.85, is(discounts[0]));
		assertThat(0.9, is(discounts[1]));
		assertThat(0.8, is(discounts[2]));
		
	}
	
	@Test
	public void testDiscountByAmericanExpress(){
		CreditCard card = new CreditCard();
		Discount discount = new StubDiscount();
		double[] discounts;
		
		card.setBank("HSBC");
		card.setCreditCardType("American Express");
		card.setCreditCardNumber("3759-876543-21001");

		discounts = discount.getDiscount(fList, card);
		
		//Airline - AmericanExpress's discount: CPA - 0.75, CRK - 0.85, HDA - 0.7
		assertThat(0.75, is(discounts[0]));
		assertThat(0.85, is(discounts[1]));
		assertThat(0.7, is(discounts[2]));
		
	}
}

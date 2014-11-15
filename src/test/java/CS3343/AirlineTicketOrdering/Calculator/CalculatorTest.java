package CS3343.AirlineTicketOrdering.Calculator;

import java.io.IOException;
import java.util.ArrayList;

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
import CS3343.AirlineTicketOrdering.Calculator.Impl.StubCalculator;
import CS3343.AirlineTicketOrdering.Discount.Discount;
import CS3343.AirlineTicketOrdering.Discount.Impl.StubDiscount;
import CS3343.AirlineTicketOrdering.Model.CreditCard;
import CS3343.AirlineTicketOrdering.Model.Flight;

public class CalculatorTest {
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
	public void testFlightsByVISAForOnePerson(){
		CreditCard card = new CreditCard();
		Calculator sC = new StubCalculator();
		Discount discount = new StubDiscount();
		double[] discounts;
		double expectedResult = 0.0;
		double totalAmount = 0.0;
		
		card.setBank("HSBC");
		card.setCreditCardType("VISA");
		card.setCreditCardNumber("4890-8500-0000-8888");
		
		discounts = discount.getDiscount(fList, card);
		totalAmount = sC.calculate(fList, 1, discounts);

		//Airline - VISA's discount: CPA - 0.8, CRK - 0.9, HDA - 0.8
		expectedResult += 2000 * 1 * 0.8;	//CPA
		expectedResult += 1700 * 1 * 0.9;	//CRK
		expectedResult += 1800 * 1 * 0.8;	//HDA
		assertThat(expectedResult, is(totalAmount));
	}
	
	@Test
	public void testFlightsByMasterCardForOnePerson(){
		CreditCard card = new CreditCard();
		Calculator sC = new StubCalculator();
		Discount discount = new StubDiscount();
		double[] discounts;
		double expectedResult = 0.0;
		double totalAmount = 0.0;
		
		card.setBank("HSBC");
		card.setCreditCardType("Master Card");
		card.setCreditCardNumber("5120-4300-8888-8888");
		
		discounts = discount.getDiscount(fList, card);
		totalAmount = sC.calculate(fList, 1, discounts);

		//Airline - MasterCard's discount: CPA - 0.85, CRK - 0.9, HDA - 0.8
		expectedResult += 2000 * 1 * 0.85;	//CPA
		expectedResult += 1700 * 1 * 0.9;	//CRK
		expectedResult += 1800 * 1 * 0.8;	//HDA
		assertThat(expectedResult, is(totalAmount));
	}
	
	@Test
	public void testFlightsByAmericanExpressForOnePerson(){
		CreditCard card = new CreditCard();
		Calculator sC = new StubCalculator();
		Discount discount = new StubDiscount();
		double[] discounts;
		double expectedResult = 0.0;
		double totalAmount = 0.0;
		
		card.setBank("HSBC");
		card.setCreditCardType("American Express");
		card.setCreditCardNumber("3759-876543-21001");
		
		discounts = discount.getDiscount(fList, card);
		totalAmount = sC.calculate(fList, 1, discounts);

		//Airline - AmericanExpress's discount: CPA - 0.75, CRK - 0.85, HDA - 0.7
		expectedResult += 2000 * 1 * 0.75;	//CPA
		expectedResult += 1700 * 1 * 0.85;	//CRK
		expectedResult += 1800 * 1 * 0.7;	//HDA
		assertThat(expectedResult, is(totalAmount));
	}
	
	@Test
	public void testFlightsByVISAForThreePersons(){
		CreditCard card = new CreditCard();
		Calculator sC = new StubCalculator();
		Discount discount = new StubDiscount();
		double[] discounts;
		double expectedResult = 0.0;
		double totalAmount = 0.0;
		
		card.setBank("HSBC");
		card.setCreditCardType("VISA");
		card.setCreditCardNumber("4890-8500-0000-8888");
		
		discounts = discount.getDiscount(fList, card);
		totalAmount = sC.calculate(fList, 3, discounts);

		//Airline - VISA's discount: CPA - 0.8, CRK - 0.9, HDA - 0.8
		expectedResult += 2000 * 3 * 0.8;	//CPA
		expectedResult += 1700 * 3 * 0.9;	//CRK
		expectedResult += 1800 * 3 * 0.8;	//HDA
		assertThat(expectedResult, is(totalAmount));
	}
	
	@Test
	public void testFlightsByMasterCardForThreePersons(){
		CreditCard card = new CreditCard();
		Calculator sC = new StubCalculator();
		Discount discount = new StubDiscount();
		double[] discounts;
		double expectedResult = 0.0;
		double totalAmount = 0.0;
		
		card.setBank("HSBC");
		card.setCreditCardType("Master Card");
		card.setCreditCardNumber("5120-4300-8888-8888");
		
		discounts = discount.getDiscount(fList, card);
		totalAmount = sC.calculate(fList, 3, discounts);

		//Airline - MasterCard's discount: CPA - 0.85, CRK - 0.9, HDA - 0.8
		expectedResult += 2000 * 3 * 0.85;	//CPA
		expectedResult += 1700 * 3 * 0.9;	//CRK
		expectedResult += 1800 * 3 * 0.8;	//HDA
		assertThat(expectedResult, is(totalAmount));
	}
	
	@Test
	public void testFlightsByAmericanExpressForThreePersons(){
		CreditCard card = new CreditCard();
		Calculator sC = new StubCalculator();
		Discount discount = new StubDiscount();
		double[] discounts;
		double expectedResult = 0.0;
		double totalAmount = 0.0;
		
		card.setBank("HSBC");
		card.setCreditCardType("American Express");
		card.setCreditCardNumber("3759-876543-21001");
		
		discounts = discount.getDiscount(fList, card);
		totalAmount = sC.calculate(fList, 3, discounts);

		//Airline - AmericanExpress's discount: CPA - 0.75, CRK - 0.85, HDA - 0.7
		expectedResult += 2000 * 3 * 0.75;	//CPA
		expectedResult += 1700 * 3 * 0.85;	//CRK
		expectedResult += 1800 * 3 * 0.7;	//HDA
		assertThat(expectedResult, is(totalAmount));
	}
	
	@Test
	public void testFlightsByVISAForFivePersons(){
		CreditCard card = new CreditCard();
		Calculator sC = new StubCalculator();
		Discount discount = new StubDiscount();
		double[] discounts;
		double expectedResult = 0.0;
		double totalAmount = 0.0;
		
		card.setBank("HSBC");
		card.setCreditCardType("VISA");
		card.setCreditCardNumber("4890-8500-0000-8888");
		
		discounts = discount.getDiscount(fList, card);
		totalAmount = sC.calculate(fList, 5, discounts);

		//Airline - VISA's discount: CPA - 0.8, CRK - 0.9, HDA - 0.8
		expectedResult += 2000 * 5 * 0.8;	//CPA
		expectedResult += 1700 * 5 * 0.9;	//CRK
		expectedResult += 1800 * 5 * 0.8;	//HDA
		assertThat(expectedResult, is(totalAmount));
	}
	
	@Test
	public void testFlightsByMasterCardForFivePersons(){
		CreditCard card = new CreditCard();
		Calculator sC = new StubCalculator();
		Discount discount = new StubDiscount();
		double[] discounts;
		double expectedResult = 0.0;
		double totalAmount = 0.0;
		
		card.setBank("HSBC");
		card.setCreditCardType("Master Card");
		card.setCreditCardNumber("5120-4300-8888-8888");
		
		discounts = discount.getDiscount(fList, card);
		totalAmount = sC.calculate(fList, 5, discounts);

		//Airline - MasterCard's discount: CPA - 0.85, CRK - 0.9, HDA - 0.8
		expectedResult += 2000 * 5 * 0.85;	//CPA
		expectedResult += 1700 * 5 * 0.9;	//CRK
		expectedResult += 1800 * 5 * 0.8;	//HDA
		assertThat(expectedResult, is(totalAmount));
	}
	
	@Test
	public void testFlightsByAmericanExpressForFivePersons(){
		CreditCard card = new CreditCard();
		Calculator sC = new StubCalculator();
		Discount discount = new StubDiscount();
		double[] discounts;
		double expectedResult = 0.0;
		double totalAmount = 0.0;
		
		card.setBank("HSBC");
		card.setCreditCardType("American Express");
		card.setCreditCardNumber("3759-876543-21001");
		
		discounts = discount.getDiscount(fList, card);
		totalAmount = sC.calculate(fList, 5, discounts);

		//Airline - AmericanExpress's discount: CPA - 0.75, CRK - 0.85, HDA - 0.7
		expectedResult += 2000 * 5 * 0.75;	//CPA
		expectedResult += 1700 * 5 * 0.85;	//CRK
		expectedResult += 1800 * 5 * 0.7;	//HDA
		assertThat(expectedResult, is(totalAmount));
	}
}

package CS3343.AirlineTicketOrdering.Calculator;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import CS3343.AirlineTicketOrdering.Calculator.Impl.AirlineCalculator;
import CS3343.AirlineTicketOrdering.CreditCardTools.CreditCardDiscountChecker;
import CS3343.AirlineTicketOrdering.CreditCardTools.Impl.CreditCardAirlineDiscountChecker;
import CS3343.AirlineTicketOrdering.Discount.Impl.AirlineDiscount;
import CS3343.AirlineTicketOrdering.Model.CreditCard;
import CS3343.AirlineTicketOrdering.Model.Flight;

public class AirlineCalculatorTest {
	private ArrayList<Flight> fList;
	private ArrayList<AirlineDiscount> airlineDiscounts;
	
	@Before
	public void setUp() throws IOException{
		airlineDiscounts = new ArrayList<AirlineDiscount>();
		AirlineDiscount ad1 = new AirlineDiscount();
		ad1.setAirline("Cathay Pacific Airways");
		ad1.setCreditCardType("VISA");
		ad1.setDiscount(0.8);
		
		AirlineDiscount ad2 = new AirlineDiscount();
		ad2.setAirline("Cathay Pacific Airways");
		ad2.setCreditCardType("Master Card");
		ad2.setDiscount(0.85);
		
		AirlineDiscount ad3 = new AirlineDiscount();
		ad3.setAirline("Cathay Pacific Airways");
		ad3.setCreditCardType("American Express");
		ad3.setDiscount(0.75);
		
		AirlineDiscount ad4 = new AirlineDiscount();
		ad4.setAirline("China Airlines");
		ad4.setCreditCardType("VISA");
		ad4.setDiscount(0.9);
		
		AirlineDiscount ad5 = new AirlineDiscount();
		ad5.setAirline("China Airlines");
		ad5.setCreditCardType("Master Card");
		ad5.setDiscount(0.9);
		
		AirlineDiscount ad6 = new AirlineDiscount();
		ad6.setAirline("China Airlines");
		ad6.setCreditCardType("American Express");
		ad6.setDiscount(0.85);
		
		AirlineDiscount ad7 = new AirlineDiscount();
		ad7.setAirline("Hong Kong Airlines");
		ad7.setCreditCardType("VISA");
		ad7.setDiscount(0.8);
		
		AirlineDiscount ad8 = new AirlineDiscount();
		ad8.setAirline("Hong Kong Airlines");
		ad8.setCreditCardType("Master Card");
		ad8.setDiscount(0.8);
		
		AirlineDiscount ad9 = new AirlineDiscount();
		ad9.setAirline("Hong Kong Airlines");
		ad9.setCreditCardType("American Express");
		ad9.setDiscount(0.7);
		
		airlineDiscounts.add(ad1);
		airlineDiscounts.add(ad2);
		airlineDiscounts.add(ad3);
		airlineDiscounts.add(ad4);
		airlineDiscounts.add(ad5);
		airlineDiscounts.add(ad6);
		airlineDiscounts.add(ad7);
		airlineDiscounts.add(ad8);
		airlineDiscounts.add(ad9);
		
		fList = new ArrayList<Flight>();
		
		Flight f1 = new Flight();
		f1.setAirline("Cathay Pacific Airways");
		f1.setDepature("Tokyo");
		f1.setDestination("Taiwan");
		f1.setOneWayPrice(2000);
		fList.add(f1);
		
		Flight f2 = new Flight();
		f2.setAirline("China Airlines");
		f2.setDepature("Tokyo");
		f2.setDestination("Taiwan");
		f2.setOneWayPrice(1700);
		fList.add(f2);
		
		Flight f3 = new Flight();
		f3.setAirline("Hong Kong Airlines");
		f3.setDepature("Tokyo");
		f3.setDestination("Taiwan");
		f3.setOneWayPrice(1800);
		fList.add(f3);
	}
	
	@Test
	public void testFlightsByVISAForOnePerson() throws FileNotFoundException, IOException, ParseException{
		CreditCard card = new CreditCard();
		Calculator sC = new AirlineCalculator();

		CreditCardDiscountChecker ccadc = new CreditCardAirlineDiscountChecker(airlineDiscounts);
		
		double[] discounts;
		double expectedResult = 0.0;
		double totalAmount = 0.0;
		
		card.setBank("HSBC");
		card.setCreditCardType("VISA");
		card.setCreditCardNumber("4890-8500-0000-8888");
		
		discounts = ccadc.check(fList, card);
		totalAmount = sC.calculate(fList, 1, discounts);

		//Airline - VISA's discount: CP - 0.8, CA - 0.9, HKA - 0.8
		expectedResult += 2000 * 1 * 0.8;	//CP
		expectedResult += 1700 * 1 * 0.9;	//CA
		expectedResult += 1800 * 1 * 0.8;	//HKA
		assertThat(expectedResult, is(totalAmount));
	}
	
	@Test
	public void testFlightsByMasterCardForOnePerson() throws FileNotFoundException, IOException, ParseException{
		CreditCard card = new CreditCard();
		Calculator sC = new AirlineCalculator();

		CreditCardDiscountChecker ccadc = new CreditCardAirlineDiscountChecker(airlineDiscounts);
		
		double[] discounts;
		double expectedResult = 0.0;
		double totalAmount = 0.0;
		
		card.setBank("HSBC");
		card.setCreditCardType("Master Card");
		card.setCreditCardNumber("5120-4300-8888-8888");
		
		discounts = ccadc.check(fList, card);
		totalAmount = sC.calculate(fList, 1, discounts);

		//Airline - Master Card's discount: CP - 0.85, CA - 0.9, HKA - 0.8
		expectedResult += 2000 * 1 * 0.85;	//CP
		expectedResult += 1700 * 1 * 0.9;	//CA
		expectedResult += 1800 * 1 * 0.8;	//HKA
		assertThat(expectedResult, is(totalAmount));
	}
	
	@Test
	public void testFlightsByAmericanExpressForOnePerson() throws FileNotFoundException, IOException, ParseException{
		CreditCard card = new CreditCard();
		Calculator sC = new AirlineCalculator();

		CreditCardDiscountChecker ccadc = new CreditCardAirlineDiscountChecker(airlineDiscounts);
		
		double[] discounts;
		double expectedResult = 0.0;
		double totalAmount = 0.0;
		
		card.setBank("HSBC");
		card.setCreditCardType("American Express");
		card.setCreditCardNumber("3759-876543-21001");
		
		discounts = ccadc.check(fList, card);
		totalAmount = sC.calculate(fList, 1, discounts);

		//Airline - American Express's discount: CP - 0.75, CA - 0.85, HKA - 0.7
		expectedResult += 2000 * 1 * 0.75;	//CP
		expectedResult += 1700 * 1 * 0.85;	//CA
		expectedResult += 1800 * 1 * 0.7;	//HKA
		assertThat(expectedResult, is(totalAmount));
	}
	
	@Test
	public void testFlightsByVISAForThreePersons() throws FileNotFoundException, IOException, ParseException{
		CreditCard card = new CreditCard();
		Calculator sC = new AirlineCalculator();

		CreditCardDiscountChecker ccadc = new CreditCardAirlineDiscountChecker(airlineDiscounts);
		
		double[] discounts;
		double expectedResult = 0.0;
		double totalAmount = 0.0;
		
		card.setBank("HSBC");
		card.setCreditCardType("VISA");
		card.setCreditCardNumber("4890-8500-0000-8888");
		
		discounts = ccadc.check(fList, card);
		totalAmount = sC.calculate(fList, 3, discounts);

		//Airline - VISA's discount: CP - 0.8, CA - 0.9, HKA - 0.8
		expectedResult += 2000 * 3 * 0.8;	//CP
		expectedResult += 1700 * 3 * 0.9;	//CA
		expectedResult += 1800 * 3 * 0.8;	//HKA
		assertThat(expectedResult, is(totalAmount));
	}
	
	@Test
	public void testFlightsByMasterCardForThreePersons() throws FileNotFoundException, IOException, ParseException{
		CreditCard card = new CreditCard();
		Calculator sC = new AirlineCalculator();

		CreditCardDiscountChecker ccadc = new CreditCardAirlineDiscountChecker(airlineDiscounts);
		
		double[] discounts;
		double expectedResult = 0.0;
		double totalAmount = 0.0;
		
		card.setBank("HSBC");
		card.setCreditCardType("Master Card");
		card.setCreditCardNumber("5120-4300-8888-8888");
		
		discounts = ccadc.check(fList, card);
		totalAmount = sC.calculate(fList, 3, discounts);

		//Airline - Master Card's discount: CP - 0.85, CA - 0.9, HKA - 0.8
		expectedResult += 2000 * 3 * 0.85;	//CP
		expectedResult += 1700 * 3 * 0.9;	//CA
		expectedResult += 1800 * 3 * 0.8;	//HKA
		assertThat(expectedResult, is(totalAmount));
	}
	
	@Test
	public void testFlightsByAmericanExpressForThreePersons() throws FileNotFoundException, IOException, ParseException{
		CreditCard card = new CreditCard();
		Calculator sC = new AirlineCalculator();

		CreditCardDiscountChecker ccadc = new CreditCardAirlineDiscountChecker(airlineDiscounts);
		
		double[] discounts;
		double expectedResult = 0.0;
		double totalAmount = 0.0;
		
		card.setBank("HSBC");
		card.setCreditCardType("American Express");
		card.setCreditCardNumber("3759-876543-21001");
		
		discounts = ccadc.check(fList, card);
		totalAmount = sC.calculate(fList, 3, discounts);

		//Airline - American Express's discount: CP - 0.75, CA - 0.85, HKA - 0.7
		expectedResult += 2000 * 3 * 0.75;	//CP
		expectedResult += 1700 * 3 * 0.85;	//CA
		expectedResult += 1800 * 3 * 0.7;	//HKA
		assertThat(expectedResult, is(totalAmount));
	}
	
	@Test
	public void testFlightsByVISAForFivePersons() throws FileNotFoundException, IOException, ParseException{
		CreditCard card = new CreditCard();
		Calculator sC = new AirlineCalculator();

		CreditCardDiscountChecker ccadc = new CreditCardAirlineDiscountChecker(airlineDiscounts);
		
		double[] discounts;
		double expectedResult = 0.0;
		double totalAmount = 0.0;
		
		card.setBank("HSBC");
		card.setCreditCardType("VISA");
		card.setCreditCardNumber("4890-8500-0000-8888");
		
		discounts = ccadc.check(fList, card);
		totalAmount = sC.calculate(fList, 5, discounts);

		//Airline - VISA's discount: CP - 0.8, CA - 0.9, HKA - 0.8
		expectedResult += 2000 * 5 * 0.8;	//CP
		expectedResult += 1700 * 5 * 0.9;	//CA
		expectedResult += 1800 * 5 * 0.8;	//HKA
		assertThat(expectedResult, is(totalAmount));
	}
	
	@Test
	public void testFlightsByMasterCardForFivePersons() throws FileNotFoundException, IOException, ParseException{
		CreditCard card = new CreditCard();
		Calculator sC = new AirlineCalculator();

		CreditCardDiscountChecker ccadc = new CreditCardAirlineDiscountChecker(airlineDiscounts);
		
		double[] discounts;
		double expectedResult = 0.0;
		double totalAmount = 0.0;
		
		card.setBank("HSBC");
		card.setCreditCardType("Master Card");
		card.setCreditCardNumber("5120-4300-8888-8888");
		
		discounts = ccadc.check(fList, card);
		totalAmount = sC.calculate(fList, 5, discounts);

		//Airline - Master Card's discount: CP - 0.85, CA - 0.9, HKA - 0.8
		expectedResult += 2000 * 5 * 0.85;	//CP
		expectedResult += 1700 * 5 * 0.9;	//CA
		expectedResult += 1800 * 5 * 0.8;	//HKA
		assertThat(expectedResult, is(totalAmount));
	}
	
	@Test
	public void testFlightsByAmericanExpressForFivePersons() throws FileNotFoundException, IOException, ParseException{
		CreditCard card = new CreditCard();
		Calculator sC = new AirlineCalculator();

		CreditCardDiscountChecker ccadc = new CreditCardAirlineDiscountChecker(airlineDiscounts);
		
		double[] discounts;
		double expectedResult = 0.0;
		double totalAmount = 0.0;
		
		card.setBank("HSBC");
		card.setCreditCardType("American Express");
		card.setCreditCardNumber("3759-876543-21001");
		
		discounts = ccadc.check(fList, card);
		totalAmount = sC.calculate(fList, 5, discounts);

		//Airline - American Express's discount: CP - 0.75, CA - 0.85, HKA - 0.7
		expectedResult += 2000 * 5 * 0.75;	//CP
		expectedResult += 1700 * 5 * 0.85;	//CA
		expectedResult += 1800 * 5 * 0.7;	//HKA
		assertThat(expectedResult, is(totalAmount));
	}
}

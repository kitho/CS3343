package CS3343.AirlineTicketOrdering.Calculator;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

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

import CS3343.AirlineTicketOrdering.CSVFile.CSVFile;
import CS3343.AirlineTicketOrdering.Calculator.Impl.AirlineCalculator;
import CS3343.AirlineTicketOrdering.CreditCardTools.CreditCardDiscountChecker;
import CS3343.AirlineTicketOrdering.CreditCardTools.Impl.CreditCardAirlineDiscountChecker;
import CS3343.AirlineTicketOrdering.DataQuery.AirlineDiscountQuery;
import CS3343.AirlineTicketOrdering.DataReader.Impl.AirlineDiscountCSVFileReader;
import CS3343.AirlineTicketOrdering.Model.CreditCard;
import CS3343.AirlineTicketOrdering.Model.Flight;

public class AirlineCalculatorTest {
	private ArrayList<Flight> fList;
	private File projectPath;
	
	@Before
	public void setUp() throws IOException{
		projectPath = new File(".").getCanonicalFile();
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
		AirlineDiscountQuery adQuery = new AirlineDiscountQuery(new AirlineDiscountCSVFileReader(projectPath + CSVFile.AIRLINEDISCOUNTCSV.value()));
		CreditCardDiscountChecker ccadc = new CreditCardAirlineDiscountChecker(adQuery.findAllAirlineDiscounts());
		
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
		AirlineDiscountQuery adQuery = new AirlineDiscountQuery(new AirlineDiscountCSVFileReader(projectPath + CSVFile.AIRLINEDISCOUNTCSV.value()));
		CreditCardDiscountChecker ccadc = new CreditCardAirlineDiscountChecker(adQuery.findAllAirlineDiscounts());
		
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
		AirlineDiscountQuery adQuery = new AirlineDiscountQuery(new AirlineDiscountCSVFileReader(projectPath + CSVFile.AIRLINEDISCOUNTCSV.value()));
		CreditCardDiscountChecker ccadc = new CreditCardAirlineDiscountChecker(adQuery.findAllAirlineDiscounts());
		
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
		AirlineDiscountQuery adQuery = new AirlineDiscountQuery(new AirlineDiscountCSVFileReader(projectPath + CSVFile.AIRLINEDISCOUNTCSV.value()));
		CreditCardDiscountChecker ccadc = new CreditCardAirlineDiscountChecker(adQuery.findAllAirlineDiscounts());
		
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
		AirlineDiscountQuery adQuery = new AirlineDiscountQuery(new AirlineDiscountCSVFileReader(projectPath + CSVFile.AIRLINEDISCOUNTCSV.value()));
		CreditCardDiscountChecker ccadc = new CreditCardAirlineDiscountChecker(adQuery.findAllAirlineDiscounts());
		
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
		AirlineDiscountQuery adQuery = new AirlineDiscountQuery(new AirlineDiscountCSVFileReader(projectPath + CSVFile.AIRLINEDISCOUNTCSV.value()));
		CreditCardDiscountChecker ccadc = new CreditCardAirlineDiscountChecker(adQuery.findAllAirlineDiscounts());
		
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
		AirlineDiscountQuery adQuery = new AirlineDiscountQuery(new AirlineDiscountCSVFileReader(projectPath + CSVFile.AIRLINEDISCOUNTCSV.value()));
		CreditCardDiscountChecker ccadc = new CreditCardAirlineDiscountChecker(adQuery.findAllAirlineDiscounts());
		
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
		AirlineDiscountQuery adQuery = new AirlineDiscountQuery(new AirlineDiscountCSVFileReader(projectPath + CSVFile.AIRLINEDISCOUNTCSV.value()));
		CreditCardDiscountChecker ccadc = new CreditCardAirlineDiscountChecker(adQuery.findAllAirlineDiscounts());
		
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
		AirlineDiscountQuery adQuery = new AirlineDiscountQuery(new AirlineDiscountCSVFileReader(projectPath + CSVFile.AIRLINEDISCOUNTCSV.value()));
		CreditCardDiscountChecker ccadc = new CreditCardAirlineDiscountChecker(adQuery.findAllAirlineDiscounts());
		
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

package CS3343.AirlineTicketOrdering.Discount;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

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
	
	@Test
	public void testDiscountByVISA(){
		CreditCard card = new CreditCard();
		PathFinding	pathFinding = new PathFinding("Tokyo", "Taiwan");
		Discount discount = new StubDiscount();
		double[] discounts;
		
		card.setBank("HSBC");
		card.setCreditCardType("VISA");
		card.setCreditCardNumber("4890-8500-0000-8888");
		ArrayList<FlightPath> resultRoutes = pathFinding.getDirectFlight();	//size = 1
		ArrayList<Flight> resultFlights = resultRoutes.get(0).getFlightList().get(0).getFlights();
		
		discounts = discount.getDiscount(resultFlights, card);
		
		//Airline - VISA's discount: CPA - 0.8, CRK - 0.9, HDA - 0.8
		for(int i = 0; i < discounts.length; i++){
			Flight f = resultFlights.get(i);
			if(f.getAirline().equals(AirlineCompanyShort.CPA)){
				assertThat(0.8, is(discounts[i]));
			}else if(f.getAirline().equals(AirlineCompanyShort.CRK)){
				assertThat(0.9, is(discounts[i]));
			}else if(f.getAirline().equals(AirlineCompanyShort.HDA)){
				assertThat(0.8, is(discounts[i]));
			}
		}
	}
	
	@Test
	public void testDiscountByMasterCard(){
		CreditCard card = new CreditCard();
		PathFinding	pathFinding = new PathFinding("Tokyo", "Taiwan");
		Discount discount = new StubDiscount();
		double[] discounts;
		
		card.setBank("HSBC");
		card.setCreditCardType("Master Card");
		card.setCreditCardNumber("5120-4300-8888-8888");
		ArrayList<FlightPath> resultRoutes = pathFinding.getDirectFlight();	//size = 1
		ArrayList<Flight> resultFlights = resultRoutes.get(0).getFlightList().get(0).getFlights();
		
		discounts = discount.getDiscount(resultFlights, card);
		
		//Airline - MasterCard's discount: CPA - 0.85, CRK - 0.9, HDA - 0.8
		for(int i = 0; i < discounts.length; i++){
			Flight f = resultFlights.get(i);
			if(f.getAirline().equals(AirlineCompanyShort.CPA)){
				assertThat(0.85, is(discounts[i]));
			}else if(f.getAirline().equals(AirlineCompanyShort.CRK)){
				assertThat(0.9, is(discounts[i]));
			}else if(f.getAirline().equals(AirlineCompanyShort.HDA)){
				assertThat(0.8, is(discounts[i]));
			}
		}
	}
	
	@Test
	public void testDiscountByAmericanExpress(){
		CreditCard card = new CreditCard();
		PathFinding	pathFinding = new PathFinding("Tokyo", "Taiwan");
		Discount discount = new StubDiscount();
		double[] discounts;
		
		card.setBank("HSBC");
		card.setCreditCardType("American Express");
		card.setCreditCardNumber("3759-876543-21001");
		ArrayList<FlightPath> resultRoutes = pathFinding.getDirectFlight();	//size = 1
		ArrayList<Flight> resultFlights = resultRoutes.get(0).getFlightList().get(0).getFlights();
		
		discounts = discount.getDiscount(resultFlights, card);
		
		//Airline - AmericanExpress's discount: CPA - 0.75, CRK - 0.85, HDA - 0.7
		for(int i = 0; i < discounts.length; i++){
			Flight f = resultFlights.get(i);
			if(f.getAirline().equals(AirlineCompanyShort.CPA)){
				assertThat(0.75, is(discounts[i]));
			}else if(f.getAirline().equals(AirlineCompanyShort.CRK)){
				assertThat(0.85, is(discounts[i]));
			}else if(f.getAirline().equals(AirlineCompanyShort.HDA)){
				assertThat(0.7, is(discounts[i]));
			}
		}
	}
}

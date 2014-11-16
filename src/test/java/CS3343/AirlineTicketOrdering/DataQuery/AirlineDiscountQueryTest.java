package CS3343.AirlineTicketOrdering.DataQuery;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import CS3343.AirlineTicketOrdering.DataReader.SourceReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.AirlineCompanyCSVFileReader;import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import CS3343.AirlineTicketOrdering.DataReader.SourceReader;
import CS3343.AirlineTicketOrdering.DataWriter.SourceWriter;
import CS3343.AirlineTicketOrdering.Discount.Impl.AirlineDiscount;
import CS3343.AirlineTicketOrdering.Parser.Parser;
import CS3343.AirlineTicketOrdering.Parser.Impl.AirlineDiscountParser;
import CS3343.AirlineTicketOrdering.DataReader.Impl.AirlineDiscountCSVFileReader;


public class AirlineDiscountQueryTest {
	
	@Test
	public void findAllAirlineDiscountsTest() throws ParseException, IOException{
		SourceReader<AirlineDiscount> airlineDiscountReader = mock(AirlineDiscountCSVFileReader.class);
		
		AirlineDiscount ad1 = new AirlineDiscount();
		ad1.setAirline("Cathay Pacific Airways");
		ad1.setCreditCardType("VISA");
		ad1.setDiscount(0.8);
		
		AirlineDiscount ad2 = new AirlineDiscount();
		ad2.setAirline("Cathay Pacific Airways");
		ad2.setCreditCardType("Master CArd");
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
		
		List<AirlineDiscount> airlineDiscounts = new ArrayList<AirlineDiscount>();
		airlineDiscounts.add(ad1);
		airlineDiscounts.add(ad2);
		airlineDiscounts.add(ad3);
		airlineDiscounts.add(ad4);
		airlineDiscounts.add(ad5);
		airlineDiscounts.add(ad6);
		airlineDiscounts.add(ad7);
		airlineDiscounts.add(ad8);
		airlineDiscounts.add(ad9);
		
		when(airlineDiscountReader.read((Parser<AirlineDiscount>) any())).thenReturn(airlineDiscounts);
		
		AirlineDiscountQuery airlineDiscountQuery = new AirlineDiscountQuery(airlineDiscountReader);
		List<AirlineDiscount> resultDiscounts = airlineDiscountQuery.findAllAirlineDiscounts();
		
		assertThat(9, is(resultDiscounts.size()));
		
	}
}

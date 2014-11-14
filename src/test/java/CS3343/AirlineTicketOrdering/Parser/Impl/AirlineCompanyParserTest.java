package CS3343.AirlineTicketOrdering.Parser.Impl;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

import CS3343.AirlineTicketOrdering.Model.AirlineCompany;
import CS3343.AirlineTicketOrdering.Parser.Parser;
import static org.hamcrest.CoreMatchers.is;

public class AirlineCompanyParserTest {

	@Test
	public void testParseString() throws ParseException {
		String airline = "Cathay Pacific Airways";
		
		Parser<AirlineCompany> parser = new AirlineCompanyParser();
		AirlineCompany airlineCompany = parser.parseString(airline);
		
		assertThat(airlineCompany.getAirline(), is(airline));
	}

	@Test
	public void testParseObject() {
		AirlineCompany airlineCompany = new AirlineCompany();
		airlineCompany.setAirline("Cathay Pacific Airways");
		
		Parser<AirlineCompany> parser = new AirlineCompanyParser();
		String line = parser.parseObject(airlineCompany);
		
		String airlineString = "Cathay Pacific Airways";
		
		assertThat(airlineString, is(line));
	}

}

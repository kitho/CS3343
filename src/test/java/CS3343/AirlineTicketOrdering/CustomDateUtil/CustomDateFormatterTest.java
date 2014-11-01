package CS3343.AirlineTicketOrdering.CustomDateUtil;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

public class CustomDateFormatterTest {

	@Test
	public void parseStringToDateFailureTest() {
		String date = "22:30:15 01/12/2012";
		CustomDateFormatter formatter = new CustomDateFormatter();
		
		try{
			formatter.parse(date);
			fail("Format incorrect");
		} catch (ParseException e) {
			assertThat(e.getMessage().toString(), is(not(nullValue())));
		}
	}
	
	@Test
	public void parseStringToDateTest() throws ParseException {
		String date = "2012-12-01 22:30:15";
		CustomDateFormatter formatter = new CustomDateFormatter();
		
		assertThat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date), 
				is(formatter.parse(date)));
	}

	@Test
	public void formatDateToStringTest(){
		
	}
}

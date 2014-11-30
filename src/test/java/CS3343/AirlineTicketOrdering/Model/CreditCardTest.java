package CS3343.AirlineTicketOrdering.Model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreditCardTest {
	
	@Test
	public void testCreditCard(){
		CreditCard card = new CreditCard();
		card.setBank("HSBC");
		card.setCreditCardType("VISA");
		card.setCreditCardNumber("4890-8500-0000-8888");
		
		assertThat("HSBC",is(card.getBank()));
		assertThat("VISA",is(card.getCreditCardType()));
		assertThat("4890-8500-0000-8888",is(card.getCreditCardNumber()));
	}
}

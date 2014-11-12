package CS3343.AirlineTicketOrdering.CreditCard;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import CS3343.AirlineTicketOrdering.CreditCardTools.CodeChecker;
import CS3343.AirlineTicketOrdering.CreditCardTools.Impl.CreditCardCodeChecker;
import CS3343.AirlineTicketOrdering.Model.CreditCard;


public class CreditCardTest {

	@Test
	public void testVISA() throws Exception {
		CreditCard card = new CreditCard();
		card.setBank("HSBC");
		card.setCreditCardType("VISA");
		card.setCreditCardNumber("4890-8500-0000-8888");
		CodeChecker ccCodeChecker = new CreditCardCodeChecker("VISA",card);
		assertThat(true, is(ccCodeChecker.check()));
	}
	
	@Test
	public void testMasterCard() throws Exception {
		CreditCard card = new CreditCard();
		card.setBank("HSBC");
		card.setCreditCardType("Master Card");
		card.setCreditCardNumber("5120-4300-8888-8888");
		CodeChecker ccCodeChecker = new CreditCardCodeChecker("Master Card",card);
		assertThat(true, is(ccCodeChecker.check()));
	}
	
	@Test
	public void testAmericanExpress() throws Exception {
		CreditCard card = new CreditCard();
		card.setBank("HSBC");
		card.setCreditCardType("American Express");
		card.setCreditCardNumber("3759-876543-21001");
		CodeChecker ccCodeChecker = new CreditCardCodeChecker("American Express",card);
		assertThat(true, is(ccCodeChecker.check()));
	}
	
	@Test
	public void testWrongTypeOfCreditCard() throws Exception {
		CreditCard card = new CreditCard();
		card.setBank("HSBC");
		card.setCreditCardType("VISA");
		card.setCreditCardNumber("4890-8500-0000-8888");
		CodeChecker ccCodeChecker = new CreditCardCodeChecker("Master Card",card);
		assertThat(false, is(ccCodeChecker.check()));
	}
	
	@Test
	public void testNonExistCreditCard() throws Exception {
		CreditCard card = new CreditCard();
		card.setBank("HSBC");
		card.setCreditCardType("Citizen");
		card.setCreditCardNumber("1290-8500-0000-8888");
		CodeChecker ccCodeChecker = new CreditCardCodeChecker("Citizen",card);
		assertThat(false, is(ccCodeChecker.check()));
	}
	

}

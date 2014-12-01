package CS3343.AirlineTicketOrdering.Model;

public class CreditCard {

	private String bank;
	private String creditCardType;
	private String creditCardNumber;
	
	/**
	 * get bank
	 * @return String bank
	 */
	public String getBank() {
		return bank;
	}
	
	/**
	 * set bank
	 * @param bank
	 * @return 
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}
	
	/**
	 * get credit card type
	 * @return String creditCardType
	 */
	public String getCreditCardType() {
		return creditCardType;
	}
	
	/**
	 * set credit card type
	 * @param creditCardType
	 * @return 
	 */
	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}
	
	/**
	 * get credit card number
	 * @return creditCardNumber 
	 */
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	
	/**
	 * set credit card number
	 * @param creditCardNumber
	 * @return 
	 */
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	
	
}

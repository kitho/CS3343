package CS3343.AirlineTicketOrdering.CSVFile;

/**
 * The Enum CSVFile.
 */
public enum CSVFile {
	
	/** The airlinecompanycsv. */
	AIRLINECOMPANYCSV("/datasource/airlineCompany.csv")
	, 
	 /** The flightcsv. */
	 FLIGHTCSV("/datasource/flight.csv")
		, 
	 /** The baggageplancsv. */
	 BAGGAGEPLANCSV("/datasource/BaggagePlan.csv")
		, 
	 /** The ordercsv. */
	 ORDERCSV("/datasource/order.csv")
		, 
	 /** The airlinediscountcsv. */
	 AIRLINEDISCOUNTCSV("/datasource/airlineDiscount.csv")
		, 
	 /** The routecsv. */
	 ROUTECSV("/datasource/route.csv");

	/** The value. */
	private String value;
	
	/**
	 * Instantiates a new CSV file.
	 *
	 * @param value the value
	 */
	private CSVFile(String value){
		this.value = value;
	}
	
	/**
	 * Value of the csv path
	 *
	 * @return the string
	 */
	public String value(){
		return value;
	}
}

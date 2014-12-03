package CS3343.AirlineTicketOrdering.CSVFile;

public enum CSVFile {
	AIRLINECOMPANYCSV("/datasource/airlineCompany.csv")
	, FLIGHTCSV("/datasource/flight.csv")
	, BAGGAGEPLANCSV("/datasource/BaggagePlan.csv")
	, ORDERCSV("/datasource/order.csv")
	, AIRLINEDISCOUNTCSV("/datasource/airlineDiscount.csv")
	, ROUTECSV("/datasource/route.csv");

	private String value;
	
	private CSVFile(String value){
		this.value = value;
	}
	
	public String value(){
		return value;
	}
}

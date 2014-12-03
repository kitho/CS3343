package TestingTool.DataWriter;

public enum CSVFileTest {
	AIRLINECOMPANYCSV("/datasourcetest/airlineCompany.csv")
	, FLIGHTCSV("/datasourcetest/flight.csv")
	, BAGGAGEPLANCSV("/datasourcetest/BaggagePlan.csv")
	, ORDERCSV("/datasourcetest/order.csv")
	, AIRLINEDISCOUNTCSV("/datasourcetest/airlineDiscount.csv")
	, ROUTECSV("/datasourcetest/route.csv");

	
	private String value;
	
	private CSVFileTest(String value){
		this.value = value;
	}
	
	public String value(){
		return value;
	}
}

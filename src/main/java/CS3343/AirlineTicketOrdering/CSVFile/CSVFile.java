package CS3343.AirlineTicketOrdering.CSVFile;

public enum CSVFile {
	AIRLINECOMPANYCSV("/datasource/airlineCompany.csv"), FLIGHTCSV("/datasource/flight.csv"), MODELCSV("/datasource/model.csv");
	
	private String value;
	
	private CSVFile(String value){
		this.value = value;
	}
	
	public String value(){
		return value;
	}
}

package CS3343.AirlineTicketOrdering.DataReader;

public enum CSVFile {
	AIRLINECOMPANYCSV("/datasource/airlineCompany.csv"), FLIGHTCSV("/datasource/flight.csv"), MODELCSV("/datasource/model.csv"),
	FOODCSV("/datasource/Food.csv"), MEALCSV("/datasource/Meal.csv");
	
	private String value;
	
	private CSVFile(String value){
		this.value = value;
	}
	
	public String value(){
		return value;
	}
}

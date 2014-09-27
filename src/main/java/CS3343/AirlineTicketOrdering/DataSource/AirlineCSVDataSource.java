package CS3343.AirlineTicketOrdering.DataSource;

import java.util.List;

import CS3343.AirlineTicketOrdering.Model.AirlineCompany;

public class AirlineCSVDataSource implements AirlineDataSource {

	public final static String AIRLINECSV = "/datasource/airline.csv";
	public final static String FLIGHTCSV = "/datasource/flight.csv";
	private List<AirlineCompany> airlineCompanies;
	
	public AirlineCSVDataSource(){

	}
	
}

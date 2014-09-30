package CS3343.AirlineTicketOrdering.DataQuery;

import java.util.List;

import CS3343.AirlineTicketOrdering.Model.AirlineCompany;

public class AirlineQuery {

	public final static String AIRLINECSV = "/datasource/airlinecompany.csv";
	public final static String FLIGHTCSV = "/datasource/flight.csv";
	private List<AirlineCompany> airlineCompanies;
	
	public AirlineQuery(){
		
	}
	
}

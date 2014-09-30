package CS3343.AirlineTicketOrdering.DataQuery;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import CS3343.AirlineTicketOrdering.FileReader.SourceReader;
import CS3343.AirlineTicketOrdering.Model.AirlineCompany;

public class AirlineQuery {

	public final static String AIRLINECSV = "/datasource/airlinecompany.csv";
	public final static String FLIGHTCSV = "/datasource/flight.csv";
	private List<AirlineCompany> airlineCompanies;
	
	public AirlineQuery(SourceReader<AirlineCompany> airlineCompanyReader) throws IOException, ParseException{
		airlineCompanies = airlineCompanyReader.read();
	}
	
	
	
}

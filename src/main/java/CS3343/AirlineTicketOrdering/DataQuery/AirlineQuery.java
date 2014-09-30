package CS3343.AirlineTicketOrdering.DataQuery;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import CS3343.AirlineTicketOrdering.DataReader.SourceReader;
import CS3343.AirlineTicketOrdering.Model.AirlineCompany;

public class AirlineQuery {

	private List<AirlineCompany> airlineCompanies;
	
	public AirlineQuery(SourceReader<AirlineCompany> airlineCompanyReader) throws IOException, ParseException{
		airlineCompanies = airlineCompanyReader.read();
	}
	
	
	
}

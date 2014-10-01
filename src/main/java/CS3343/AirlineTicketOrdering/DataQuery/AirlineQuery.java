package CS3343.AirlineTicketOrdering.DataQuery;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

import CS3343.AirlineTicketOrdering.DataReader.SourceReader;
import CS3343.AirlineTicketOrdering.Model.AirlineCompany;
import CS3343.AirlineTicketOrdering.Model.Flight;

public class AirlineQuery {

	private List<AirlineCompany> airlineCompanies;
	
	public AirlineQuery(SourceReader<AirlineCompany> airlineCompanyReader) throws IOException, ParseException{
		airlineCompanies = airlineCompanyReader.read();
	}

	public List<Flight> findFlightsByDepatureAndDestinationAndDate(String depature,
			String destination, Date depatureDate) {
		List<Flight> flights = new ArrayList<Flight>();
		
		for (AirlineCompany airlineCompany : airlineCompanies) {
			for (Flight flight : airlineCompany.getFlights()) {
				if(flight.getDepature().toUpperCase().equals(depature.toUpperCase()) &&
						flight.getDestination().toUpperCase().equals(destination.toUpperCase()) &&
						DateUtils.isSameDay(depatureDate, flight.getDepatureDateTime()) && flight.getAvailable() > 0)
					flights.add(flight);
			}
		}
		
		return flights;
	}
	
	
	
}

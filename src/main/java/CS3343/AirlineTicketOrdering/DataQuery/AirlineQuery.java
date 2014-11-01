package CS3343.AirlineTicketOrdering.DataQuery;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

import CS3343.AirlineTicketOrdering.DataReader.SourceReader;
import CS3343.AirlineTicketOrdering.DataWriter.SourceWriter;
import CS3343.AirlineTicketOrdering.Model.AirlineCompany;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Parser.Impl.AirlineCompanyParser;
import CS3343.AirlineTicketOrdering.Parser.Impl.FlightParser;

public class AirlineQuery {

	private SourceWriter<List<Flight>> flightWriter;
	private List<AirlineCompany> airlineCompanies;
	private List<Flight> flights;
	
	public AirlineQuery(SourceReader<AirlineCompany> airlineCompanyReader, SourceReader<Flight> flightReader, SourceWriter<List<Flight>> flightWriter) throws IOException, ParseException{
		airlineCompanies = airlineCompanyReader.read(new AirlineCompanyParser());
		flights = flightReader.read(new FlightParser());
		this.flightWriter = flightWriter;
		
	}

	public List<Flight> findFlightsByDepatureAndDestinationAndDate(String depature,
			String destination, Date depatureDate) {
		List<Flight> flightResultList = new ArrayList<Flight>();
		
		for (AirlineCompany airlineCompany : airlineCompanies) {
			for (Flight flight : flights) {
				if(flight.getDepature().toUpperCase().equals(depature.toUpperCase()) &&
						flight.getDestination().toUpperCase().equals(destination.toUpperCase()) &&
						DateUtils.isSameDay(depatureDate, flight.getDepatureDateTime()) && flight.getAvailable() > 0)
					flightResultList.add(flight);
			}
		}
		
		return flightResultList;
	}

	public void updateFlightAvailableByFlightAndReducingNumber(Flight targetFlight, int reducingNumber) throws IOException {
		List<Flight> flights = new ArrayList<Flight>();
		
		for (AirlineCompany airlineCompany : airlineCompanies) {
			for (Flight flight : airlineCompany.getFlights()) {
				if(flight.equals(targetFlight) && flight.getAvailable() - reducingNumber >= 0)
					flight.setAvailable(flight.getAvailable() - reducingNumber);
				flights.add(flight);
			}
		}
		
		flightWriter.write(flights);
	}
		
}

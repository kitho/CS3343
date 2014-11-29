package CS3343.AirlineTicketOrdering.DataQuery;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

import CS3343.AirlineTicketOrdering.DataReader.SourceReader;
import CS3343.AirlineTicketOrdering.DataWriter.SourceWriter;
import CS3343.AirlineTicketOrdering.Model.AirlineCompany;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Parser.Impl.AirlineCompanyParser;
import CS3343.AirlineTicketOrdering.Parser.Impl.FlightParser;

public class FlightQuery {
	private SourceWriter<List<Flight>> flightWriter;
	private List<AirlineCompany> airlineCompanies;
	private List<Flight> flights;
	
	public FlightQuery(SourceReader<AirlineCompany> airlineCompanyReader, SourceReader<Flight> flightReader, SourceWriter<List<Flight>> flightWriter) throws IOException, ParseException{
		airlineCompanies = airlineCompanyReader.read(new AirlineCompanyParser());
		flights = flightReader.read(new FlightParser());
		this.flightWriter = flightWriter;
		for(int i = 0; i < airlineCompanies.size(); i++){
			for(int j = 0; j < flights.size(); j ++)
				airlineCompanies.get(i).addFlight(flights.get(j));
		}
	}

	
	/**
	 * Returns an List object that meet the departure, destination and date
	 * 
	 * @param  deaprture  Departure that user want to search
	 * @param  destination Destination that user want to search
	 * @return ArrayList of Flight
	 */
	public List<Flight> findFlightsByDepatureAndDestinationAndDate(String depature,
			String destination, Date depatureDate) {
		List<Flight> flightResultList = new ArrayList<Flight>();
		
		
		for (Flight flight : flights) {
			if(flight.getDepature().toUpperCase().equals(depature.toUpperCase()) &&
					flight.getDestination().toUpperCase().equals(destination.toUpperCase()) &&
					DateUtils.isSameDay(depatureDate, flight.getDepatureDateTime()) && flight.getAvailable() > 0)
					flightResultList.add(flight);
		}
		
		return flightResultList;
	}
	
	/**
	 * Returns an List object of all flights
	 * 
	 * @return ArrayList of all flight
	 */
	public List<Flight> getFlights(){
		return flights;
	}

	/**
	 * Update the FlightAvailable
	 * 
	 *  @param  targetFlight  the flight that have to reduce available seat
	 *  @param  reducingNumber number of seat to reduce 
	 */
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

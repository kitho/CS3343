package CS3343.AirlineTicketOrdering.DataSource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import CS3343.AirlineTicketOrdering.Model.AirlineCompany;
import CS3343.AirlineTicketOrdering.Model.Flight;

public class AirlineCompanyCSVFileReader extends CSVFileReader<AirlineCompany> {

	private CSVFileReader<Flight> fileReader;
	
	public AirlineCompanyCSVFileReader(String path, CSVFileReader<Flight> fileReader) throws FileNotFoundException {
		super(path);
		this.fileReader = fileReader;
	}

	@Override
	public List<AirlineCompany> read() throws IOException, ParseException {
		List<Flight> flights = fileReader.read();
		fileReader.close();
		
		List<AirlineCompany> airlineCompanies = new ArrayList<AirlineCompany>();
		
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			AirlineCompany airlineCompany = new AirlineCompany(line);
			airlineCompanies.add(airlineCompany);
			for (Flight flight : flights) {
				if (airlineCompany.getAirline().equals(flight.getAirline())) {
					airlineCompany.addFlight(flight);
				}
			}
		}
		
		return airlineCompanies;
	}

}

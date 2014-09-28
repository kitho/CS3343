package CS3343.AirlineTicketOrdering.DataSource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import CS3343.AirlineTicketOrdering.Model.AirlineCompany;

public class AirlineCompanyCSVFileReader extends CSVFileReader<AirlineCompany> {

	public AirlineCompanyCSVFileReader(String path) throws FileNotFoundException {
		super(path);
	}

	@Override
	public List<AirlineCompany> read() throws IOException {
		List<AirlineCompany> airlineCompanies = new ArrayList<AirlineCompany>();
		
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			AirlineCompany airlineCompany = new AirlineCompany(line);
			airlineCompanies.add(airlineCompany);
		}
		
		return airlineCompanies;
	}

}

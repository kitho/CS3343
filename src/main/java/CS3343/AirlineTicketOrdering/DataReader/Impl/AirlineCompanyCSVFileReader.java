package CS3343.AirlineTicketOrdering.DataReader.Impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import CS3343.AirlineTicketOrdering.DataReader.FileReader;
import CS3343.AirlineTicketOrdering.Model.AirlineCompany;
import CS3343.AirlineTicketOrdering.Parser.Parser;

public class AirlineCompanyCSVFileReader extends FileReader<AirlineCompany> {

	public AirlineCompanyCSVFileReader(String path) throws FileNotFoundException {
		super(path);
	}

	@Override
	public List<AirlineCompany> read(Parser<AirlineCompany> parser) throws IOException, ParseException {

		List<AirlineCompany> airlineCompanies = new ArrayList<AirlineCompany>();

		String line;
		while ((line = bufferedReader.readLine()) != null) {
			AirlineCompany airlineCompany = parser.parseString(line);
			airlineCompanies.add(airlineCompany);
		}

		return airlineCompanies;
	}

}

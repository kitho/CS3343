package CS3343.AirlineTicketOrdering.DataReader.Impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import CS3343.AirlineTicketOrdering.DataReader.CSVFileReader;
import CS3343.AirlineTicketOrdering.Model.AirlineCompany;
import CS3343.AirlineTicketOrdering.Parser.Parser;

/**
 * The Class AirlineCompanyCSVFileReader.
 */
public class AirlineCompanyCSVFileReader extends CSVFileReader<AirlineCompany> {

	/**
	 * Instantiates a new airline company csv file reader.
	 *
	 * @param path the path
	 * @throws FileNotFoundException the file not found exception
	 */
	public AirlineCompanyCSVFileReader(String path) throws FileNotFoundException {
		super(path);
	}

	/**
	 * Read the airline company information from CSV file
	 * and return the all the airline companies
	 * 
	 * @param parser
	 * @return list of airline company
	 */
	@Override
	public List<AirlineCompany> read(Parser<AirlineCompany> parser) throws IOException, ParseException {

		List<AirlineCompany> airlineCompanies = new ArrayList<AirlineCompany>();

		String line;
		while ((line = bufferedReader.readLine()) != null) {
			AirlineCompany airlineCompany = parser.parseString(line);
			airlineCompanies.add(airlineCompany);
		}
		bufferedReader.close();
		fileReader.close();
		return airlineCompanies;
	}

}

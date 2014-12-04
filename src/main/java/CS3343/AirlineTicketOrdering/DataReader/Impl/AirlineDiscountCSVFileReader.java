package CS3343.AirlineTicketOrdering.DataReader.Impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import CS3343.AirlineTicketOrdering.DataReader.CSVFileReader;
import CS3343.AirlineTicketOrdering.Discount.Impl.AirlineDiscount;
import CS3343.AirlineTicketOrdering.Parser.Parser;

/**
 * The Class AirlineDiscountCSVFileReader.
 */
public class AirlineDiscountCSVFileReader extends CSVFileReader<AirlineDiscount> {
	
	/**
	 * Instantiates a new airline discount csv file reader.
	 *
	 * @param path the path
	 * @throws FileNotFoundException the file not found exception
	 */
	public AirlineDiscountCSVFileReader(String path) throws FileNotFoundException{
		super(path);
	}

	/**
	 * Read the airline discount from CSV file
	 * and return the all the airline discount
	 * 
	 * @param parser
	 * @return list of airline discounts
	 */
	@Override
	public List<AirlineDiscount> read(Parser<AirlineDiscount> parser)
			throws IOException, ParseException {

		List<AirlineDiscount> airlineDiscounts = new ArrayList<AirlineDiscount>();
		
		String line;
		while ((line = bufferedReader.readLine()) != null){
			AirlineDiscount airlineDiscount = parser.parseString(line);
			airlineDiscounts.add(airlineDiscount);
		}
		bufferedReader.close();
		fileReader.close();
		return airlineDiscounts;
	}
}

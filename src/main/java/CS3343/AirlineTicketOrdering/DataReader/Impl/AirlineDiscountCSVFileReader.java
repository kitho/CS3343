package CS3343.AirlineTicketOrdering.DataReader.Impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import CS3343.AirlineTicketOrdering.DataReader.CSVFileReader;
import CS3343.AirlineTicketOrdering.Discount.Impl.AirlineDiscount;
import CS3343.AirlineTicketOrdering.Model.AirlineCompany;
import CS3343.AirlineTicketOrdering.Parser.Parser;


public class AirlineDiscountCSVFileReader extends CSVFileReader<AirlineDiscount> {
	public AirlineDiscountCSVFileReader(String path) throws FileNotFoundException{
		super(path);
	}

	@Override
	public List<AirlineDiscount> read(Parser<AirlineDiscount> parser)
			throws IOException, ParseException {
		// TODO Auto-generated method stub
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

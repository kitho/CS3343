package CS3343.AirlineTicketOrdering.DataQuery;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import CS3343.AirlineTicketOrdering.DataReader.SourceReader;
import CS3343.AirlineTicketOrdering.Discount.Impl.AirlineDiscount;
import CS3343.AirlineTicketOrdering.Parser.Impl.AirlineDiscountParser;

public class AirlineDiscountQuery {
	private List<AirlineDiscount> airlineDiscounts;
	
	public AirlineDiscountQuery(SourceReader<AirlineDiscount> airlineDiscountReader) throws IOException, ParseException{
		airlineDiscounts = airlineDiscountReader.read(new AirlineDiscountParser());
	}
	
	public List<AirlineDiscount> findAllAirlineDiscounts(){
		return this.airlineDiscounts;
	}
}
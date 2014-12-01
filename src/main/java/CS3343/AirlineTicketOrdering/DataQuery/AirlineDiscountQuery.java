package CS3343.AirlineTicketOrdering.DataQuery;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import CS3343.AirlineTicketOrdering.DataReader.SourceReader;
import CS3343.AirlineTicketOrdering.Discount.Impl.AirlineDiscount;
import CS3343.AirlineTicketOrdering.Parser.Impl.AirlineDiscountParser;

public class AirlineDiscountQuery {
	private List<AirlineDiscount> airlineDiscounts;
	
	/**
	 * Constructs a airline discount query with required parameters
	 * @param airlineDiscountReader
	 * @return 
	 */
	public AirlineDiscountQuery(SourceReader<AirlineDiscount> airlineDiscountReader) throws IOException, ParseException{
		airlineDiscounts = airlineDiscountReader.read(new AirlineDiscountParser());
	}
	
	/**
	 * find all airline discounts
	 * @return  List<AirlineDiscount> airlineDiscounts
	 */
	public List<AirlineDiscount> findAllAirlineDiscounts(){
		return this.airlineDiscounts;
	}
}
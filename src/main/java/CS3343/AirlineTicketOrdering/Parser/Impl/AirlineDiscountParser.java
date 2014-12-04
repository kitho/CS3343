package CS3343.AirlineTicketOrdering.Parser.Impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import CS3343.AirlineTicketOrdering.Discount.Impl.AirlineDiscount;
import CS3343.AirlineTicketOrdering.Parser.Parser;


/**
 * The Class AirlineDiscountParser.
 */
public class AirlineDiscountParser implements Parser<AirlineDiscount> {

	/**
	 * Parse the String into the AirlineDiscount object
	 * 
	 * @param line
	 * 
	 * @return AirlineDiscount
	 */
	public AirlineDiscount parseString(String line) throws ParseException {
		String[] dataStr = line.split(",");
		AirlineDiscount airlineDiscount = new AirlineDiscount();
		airlineDiscount.setAirline(dataStr[0]);
		airlineDiscount.setCreditCardType(dataStr[1]);
		airlineDiscount.setDiscount(Double.parseDouble(dataStr[2]));
		
		return airlineDiscount;
	}

	/**
	 * Parse the AirlineDiscount Object into the string
	 * 
	 * @param AirlineDiscount
	 * 
	 * @return line
	 * 
	 */
	public String parseObject(AirlineDiscount airlineDiscount) {
		List<String> dataList = new ArrayList<String>();
		dataList.add(airlineDiscount.getAirline());
		dataList.add(airlineDiscount.getCreditCardType());
		dataList.add(String.valueOf(airlineDiscount.getDiscount()));
		
		String line = StringUtils.join(dataList, ",");
		
		return line;
	}

}


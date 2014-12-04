package CS3343.AirlineTicketOrdering.Parser.Impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import CS3343.AirlineTicketOrdering.Model.AirlineCompany;
import CS3343.AirlineTicketOrdering.Parser.Parser;

/**
 * The Class AirlineCompanyParser.
 */
public class AirlineCompanyParser implements Parser<AirlineCompany> {

	/**
	 * Parse the String into the AirlineCompany object
	 * 
	 * @param line
	 * 
	 * @return AirlineCompany
	 */
	public AirlineCompany parseString(String line) throws ParseException {
		AirlineCompany airlineCompany = new AirlineCompany();
		airlineCompany.setAirline(line);
		
		return airlineCompany;
	}

	/**
	 * Parse the AirlineCompany Object into the string
	 * 
	 * @param AirlineCompany
	 * 
	 * @return line
	 * 
	 */
	public String parseObject(AirlineCompany airlineCompany) {

		List<String> dataList = new ArrayList<String>();
		dataList.add(airlineCompany.getAirline());
		
		String line = StringUtils.join(dataList, ",");
		
		return line;
	}

}

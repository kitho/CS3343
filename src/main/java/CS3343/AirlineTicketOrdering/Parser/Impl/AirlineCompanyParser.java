package CS3343.AirlineTicketOrdering.Parser.Impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import CS3343.AirlineTicketOrdering.Model.AirlineCompany;
import CS3343.AirlineTicketOrdering.Parser.Parser;

public class AirlineCompanyParser implements Parser<AirlineCompany> {

	public AirlineCompany parseString(String line) throws ParseException {
		AirlineCompany airlineCompany = new AirlineCompany();
		airlineCompany.setAirline(line);
		
		return airlineCompany;
	}

	public String parseObject(AirlineCompany airlineCompany) {

		List<String> dataList = new ArrayList<String>();
		dataList.add(airlineCompany.getAirline());
		
		String line = StringUtils.join(dataList, ",");
		
		return line;
	}

}

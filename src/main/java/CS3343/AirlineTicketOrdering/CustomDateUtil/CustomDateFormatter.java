package CS3343.AirlineTicketOrdering.CustomDateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateFormatter {
	
	private SimpleDateFormat formatter;
	
	public CustomDateFormatter() {
		formatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
	}
	
	public Date parse(String date) throws ParseException {
		return formatter.parse(date);
	}
	
	public String format(Date date){
		return formatter.format(date);
	}
}

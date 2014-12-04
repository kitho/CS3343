package CS3343.AirlineTicketOrdering.CustomDateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Class CustomDateFormatter.
 */
public class CustomDateFormatter {
	
	/** The formatter. */
	private SimpleDateFormat formatter;
	
	/**
	 * Instantiates a new custom date formatter with format in yyyy-MM-dd HH:mm:ss
	 */
	public CustomDateFormatter() {
		formatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * Parses yyyy-MM-dd HH:mm:ss format string to date object
	 *
	 * @param date string
	 * @return the date
	 * @throws ParseException the parse exception
	 */
	public Date parse(String date) throws ParseException {
		return formatter.parse(date);
	}
	
	/**
	 * Format the date object to string format yyyy-MM-dd HH:mm:ss
	 *
	 * @param date the date
	 * @return the string
	 */
	public String format(Date date){
		return formatter.format(date);
	}
}

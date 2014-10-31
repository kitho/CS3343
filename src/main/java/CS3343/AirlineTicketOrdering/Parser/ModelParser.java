package CS3343.AirlineTicketOrdering.Parser;

import java.text.ParseException;

public interface ModelParser<T> {

	public T parse(String line) throws ParseException;
	
}

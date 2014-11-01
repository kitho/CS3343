package CS3343.AirlineTicketOrdering.Parser;

import java.text.ParseException;

public interface ModelParser<T> {

	public T parseString(String line) throws ParseException;
	
	public String parseObject(T t);
}

package CS3343.AirlineTicketOrdering.Parser;

import java.text.ParseException;

/**
 * The Interface Parser.
 *
 * @param <T> the generic type
 */
public interface Parser<T> {

	/**
	 * Parses the string.
	 *
	 * @param line the line
	 * @return the t
	 * @throws ParseException the parse exception
	 */
	public T parseString(String line) throws ParseException;
	
	/**
	 * Parses the object.
	 *
	 * @param t the t
	 * @return the string
	 */
	public String parseObject(T t);
}

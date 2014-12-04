package CS3343.AirlineTicketOrdering.DataReader;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import CS3343.AirlineTicketOrdering.Parser.Parser;

/**
 * The Interface SourceReader.
 *
 * @param <T> the generic type
 */
public interface SourceReader<T> {

	/**
	 * Read.
	 *
	 * @param modelParser the model parser
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 */
	public List<T> read(Parser<T> modelParser) throws IOException, ParseException;
	
	/**
	 * Close.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void close() throws IOException;
}

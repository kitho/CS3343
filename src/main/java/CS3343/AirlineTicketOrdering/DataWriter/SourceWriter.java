package CS3343.AirlineTicketOrdering.DataWriter;

import java.io.IOException;

/**
 * The Interface SourceWriter.
 *
 * @param <T> the generic type
 */
public interface SourceWriter<T> {
	
	/**
	 * Write the T into the source
	 *
	 * @param t the t
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void write(T t) throws IOException;
	
	/**
	 * Close.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void close() throws IOException;
	
}

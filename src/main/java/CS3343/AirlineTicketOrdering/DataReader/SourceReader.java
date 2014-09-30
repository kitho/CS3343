package CS3343.AirlineTicketOrdering.DataReader;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface SourceReader<T> {

	public List<T> read() throws IOException, ParseException;
	
	public void close() throws IOException;
}

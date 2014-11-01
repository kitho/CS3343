package CS3343.AirlineTicketOrdering.DataReader;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import CS3343.AirlineTicketOrdering.Parser.Parser;

public interface SourceReader<T> {

	public List<T> read(Parser<T> modelParser) throws IOException, ParseException;
	
	public void close() throws IOException;
}

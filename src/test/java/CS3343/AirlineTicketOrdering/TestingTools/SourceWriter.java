package CS3343.AirlineTicketOrdering.TestingTools;

import java.io.IOException;

public interface SourceWriter<T> {
	
	public void write(T t) throws IOException;
	
	public void close() throws IOException;
	
}

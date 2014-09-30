package CS3343.AirlineTicketOrdering.DataReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public abstract class FileReader<T> implements SourceReader<T> {

	protected BufferedReader bufferedReader;

	public FileReader(String path) throws FileNotFoundException {
		bufferedReader = new BufferedReader(new java.io.FileReader(path));
	}

	abstract public List<T> read() throws IOException, ParseException;

	public void close() throws IOException {
		bufferedReader.close();
	}

}

package CS3343.AirlineTicketOrdering.DataReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import CS3343.AirlineTicketOrdering.Parser.Parser;

public abstract class CSVFileReader<T> implements SourceReader<T> {

	protected BufferedReader bufferedReader;
	protected FileReader fileReader;

	public CSVFileReader(String path) throws FileNotFoundException {
		fileReader = new java.io.FileReader(path);
		bufferedReader = new BufferedReader(fileReader);
	}

	abstract public List<T> read(Parser<T> modelParser) throws IOException, ParseException;

	public void close() throws IOException {
		bufferedReader.close();
	}

}

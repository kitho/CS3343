package CS3343.AirlineTicketOrdering.DataReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import CS3343.AirlineTicketOrdering.Parser.Parser;

/**
 * The Class CSVFileReader is used to read CSV File
 *
 * @param <T> the generic type
 */
public abstract class CSVFileReader<T> implements SourceReader<T> {

	/** The buffered reader. */
	protected BufferedReader bufferedReader;
	
	/** The file reader. */
	protected FileReader fileReader;

	/**
	 * Instantiates a new CSV file reader.
	 *
	 * @param path the path
	 * @throws FileNotFoundException the file not found exception
	 */
	public CSVFileReader(String path) throws FileNotFoundException {
		fileReader = new java.io.FileReader(path);
		bufferedReader = new BufferedReader(fileReader);
	}

	/* (non-Javadoc)
	 * @see CS3343.AirlineTicketOrdering.DataReader.SourceReader#read(CS3343.AirlineTicketOrdering.Parser.Parser)
	 */
	abstract public List<T> read(Parser<T> modelParser) throws IOException, ParseException;

	/* (non-Javadoc)
	 * @see CS3343.AirlineTicketOrdering.DataReader.SourceReader#close()
	 */
	public void close() throws IOException {
		bufferedReader.close();
	}

}

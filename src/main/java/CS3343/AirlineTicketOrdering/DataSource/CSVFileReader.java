package CS3343.AirlineTicketOrdering.DataSource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public abstract class CSVFileReader<T> {
	
	protected BufferedReader bufferedReader;
	
	public CSVFileReader(String path) throws FileNotFoundException{
		bufferedReader = new BufferedReader(new FileReader(path)); 
	}
	
	abstract public List<T> read() throws IOException, ParseException;

	public void close() throws IOException{
		bufferedReader.close();
	}
	
}

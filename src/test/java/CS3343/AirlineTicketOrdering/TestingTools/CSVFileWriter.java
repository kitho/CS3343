package CS3343.AirlineTicketOrdering.TestingTools;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public abstract class CSVFileWriter<T> {
	
	protected BufferedWriter bufferedWriter;
	
	public CSVFileWriter(String path) throws IOException{
		bufferedWriter = new BufferedWriter(new FileWriter(path));
	}
	
	abstract public void write(T t) throws IOException;
	
	public void close() throws IOException {
		bufferedWriter.close();
	}

}


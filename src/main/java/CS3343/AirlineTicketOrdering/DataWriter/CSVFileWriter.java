package CS3343.AirlineTicketOrdering.DataWriter;


import java.io.BufferedWriter;
import java.io.IOException;

public abstract class CSVFileWriter<T> implements SourceWriter<T> {
	
	protected BufferedWriter bufferedWriter;
	
	public CSVFileWriter(String path) throws IOException{
		bufferedWriter = new BufferedWriter(new java.io.FileWriter(path, true));
	}
	
	abstract public void write(T t) throws IOException;
	
	public void close() throws IOException {
		bufferedWriter.close();
	}

}


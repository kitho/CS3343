package CS3343.AirlineTicketOrdering.DataWriter;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public abstract class CSVFileWriter<T> implements SourceWriter<T> {
	
	protected BufferedWriter bufferedWriter;
	protected FileWriter fileWriter;
	
	public CSVFileWriter(String path) throws IOException{
		fileWriter = new java.io.FileWriter(path, true);
		bufferedWriter = new BufferedWriter(fileWriter);
	}
	
	abstract public void write(T t) throws IOException;
	
	public void close() throws IOException {
		bufferedWriter.close();
	}

}


package CS3343.AirlineTicketOrdering.TestingTools;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class CSVFileWriter<T> {
	
	protected BufferedWriter bufferedWriter;
	
	public CSVFileWriter(String path) throws IOException{
		Files.deleteIfExists(Paths.get(path));
		bufferedWriter = new BufferedWriter(new FileWriter(path, true));
	}
	
	abstract public void write(T t) throws IOException;
	
	public void close() throws IOException {
		bufferedWriter.close();
	}

}


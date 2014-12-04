package CS3343.AirlineTicketOrdering.DataWriter;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Class CSVFileWriter.
 *
 * @param <T> the generic type
 */
public abstract class CSVFileWriter<T> implements SourceWriter<T> {
	
	/** The buffered writer. */
	protected BufferedWriter bufferedWriter;
	
	/** The file writer. */
	protected FileWriter fileWriter;
	
	/**
	 * Instantiates a new CSV file writer.
	 *
	 * @param path the path
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public CSVFileWriter(String path) throws IOException{
		fileWriter = new java.io.FileWriter(path, true);
		bufferedWriter = new BufferedWriter(fileWriter);
	}
	
	/* (non-Javadoc)
	 * @see CS3343.AirlineTicketOrdering.DataWriter.SourceWriter#write(java.lang.Object)
	 */
	abstract public void write(T t) throws IOException;
	
	/* (non-Javadoc)
	 * @see CS3343.AirlineTicketOrdering.DataWriter.SourceWriter#close()
	 */
	public void close() throws IOException {
		bufferedWriter.close();
	}

}


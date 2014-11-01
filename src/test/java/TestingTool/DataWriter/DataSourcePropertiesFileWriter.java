package TestingTool.DataWriter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import CS3343.AirlineTicketOrdering.DataWriter.SourceWriter;


public class DataSourcePropertiesFileWriter implements SourceWriter<Map<String, String>> {

	private BufferedWriter bufferedWriter;
	private Properties properties;
	
	public DataSourcePropertiesFileWriter(String path) throws IOException {
		bufferedWriter = new BufferedWriter(new java.io.FileWriter(path, true));
		properties = new Properties();
	}

	public void write(Map<String, String> pairs) throws IOException {
		properties.putAll(pairs);
		properties.store(bufferedWriter, "Data Source Properties");
	}

	public void close() throws IOException {
		bufferedWriter.close();
		properties.clear();
	}

}

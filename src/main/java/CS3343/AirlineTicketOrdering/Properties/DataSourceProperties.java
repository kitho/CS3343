package CS3343.AirlineTicketOrdering.Properties;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DataSourceProperties extends SystemProperties {

	public final static String DATA_SOURCE_PATH = "\\properties\\datasource.properties";
	
	public DataSourceProperties() throws FileNotFoundException, IOException{
		super();
		load(DATA_SOURCE_PATH);
	}

}

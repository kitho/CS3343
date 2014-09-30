package CS3343.AirlineTicketOrdering.Properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SystemProperties {
	
	protected Properties properties;
	
	protected SystemProperties() {
		properties = new Properties();
	}
	
	protected void load(String path) throws FileNotFoundException, IOException {
		properties.load(new FileInputStream(new File(".").getCanonicalFile() + path));
	}
	
	public String getPath(String attribute){
		properties.clear();
		return properties.getProperty(attribute);
	}

}

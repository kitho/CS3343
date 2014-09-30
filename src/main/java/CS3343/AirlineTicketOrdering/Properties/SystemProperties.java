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
		FileInputStream fileInputStream = new FileInputStream(new File(".").getCanonicalFile() + path);
		properties.load(fileInputStream);
		fileInputStream.close();
	}
	
	public String getPath(String attribute){
		return properties.getProperty(attribute);
	}

}

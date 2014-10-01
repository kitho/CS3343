package CS3343.AirlineTicketOrdering.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import CS3343.AirlineTicketOrdering.DataReader.CSVFile;
import CS3343.AirlineTicketOrdering.DataWriter.DataSourcePropertiesFileWriter;
import CS3343.AirlineTicketOrdering.DataWriter.SourceWriter;

public class DataSourcePropertiesTest {
	
	private File projectPath;
	
	@Before
	public void setUp() throws IOException{
		projectPath = new File(".").getCanonicalFile(); 
	}

	@Test
	public void dataSourcePropertiesFileNotExistedTest() throws IOException{
		Files.deleteIfExists(Paths.get(projectPath + DataSourceProperties.DATA_SOURCE_PATH));
		
		try {
			SystemProperties dataSourceProperties = new DataSourceProperties();
			fail("Data Source Properties file is not existed");
		} catch (FileNotFoundException e) {
			assertThat(e.getMessage().toString(), is(not(nullValue())));
		} catch (IOException e) {
			assertThat(e.getMessage().toString(), is(nullValue()));
		}
		
	}
	
	@Test
	public void dataSourcePropertiesReadNonExistedPath() throws IOException{
		Files.deleteIfExists(Paths.get(projectPath + DataSourceProperties.DATA_SOURCE_PATH));
		
		SourceWriter<Map<String, String>> dataSourceFilePropertiesWriter = 
				new DataSourcePropertiesFileWriter(projectPath + DataSourceProperties.DATA_SOURCE_PATH);
		
		Map<String, String> propertyPairs = new HashMap<String, String>();
		
		dataSourceFilePropertiesWriter.write(propertyPairs);
		dataSourceFilePropertiesWriter.close();
		
		SystemProperties dataSourceProperties = new DataSourceProperties();
		
		assertThat(null, is(dataSourceProperties.getPath("airlinecompany")));
		assertThat(null, is(dataSourceProperties.getPath("flight")));
		assertThat(null, is(dataSourceProperties.getPath("food")));
		assertThat(null, is(dataSourceProperties.getPath("meal")));
	}
	
	@Test
	public void dataSourcePropertiesReadAirlineCompanyCVFilePath() throws IOException{
		Files.deleteIfExists(Paths.get(projectPath + DataSourceProperties.DATA_SOURCE_PATH));
		
		SourceWriter<Map<String, String>> dataSourceFilePropertiesWriter = 
				new DataSourcePropertiesFileWriter(projectPath + DataSourceProperties.DATA_SOURCE_PATH);
		
		Map<String, String> propertyPairs = new HashMap<String, String>();
		propertyPairs.put("airlinecompany", CSVFile.AIRLINECOMPANYCSV.value());
		
		dataSourceFilePropertiesWriter.write(propertyPairs);
		dataSourceFilePropertiesWriter.close();
		
		SystemProperties dataSourceProperties = new DataSourceProperties();
		
		assertThat(CSVFile.AIRLINECOMPANYCSV.value(), is(dataSourceProperties.getPath("airlinecompany")));
	}

	@Test
	public void dataSourcePropertiesReadFlightCSVFilePath() throws IOException{
		Files.deleteIfExists(Paths.get(projectPath + DataSourceProperties.DATA_SOURCE_PATH));
		
		SourceWriter<Map<String, String>> dataSourceFilePropertiesWriter = 
				new DataSourcePropertiesFileWriter(projectPath + DataSourceProperties.DATA_SOURCE_PATH);
		
		Map<String, String> propertyPairs = new HashMap<String, String>();
		propertyPairs.put("flight", CSVFile.FLIGHTCSV.value());
		
		dataSourceFilePropertiesWriter.write(propertyPairs);
		dataSourceFilePropertiesWriter.close();
		
		SystemProperties dataSourceProperties = new DataSourceProperties();
		
		assertThat(CSVFile.FLIGHTCSV.value(), is(dataSourceProperties.getPath("flight")));
	}
	
	@Test
	public void dataSourcePropertiesReadFoodCSVFilePath() throws IOException{
		Files.deleteIfExists(Paths.get(projectPath + DataSourceProperties.DATA_SOURCE_PATH));
		
		SourceWriter<Map<String, String>> dataSourceFilePropertiesWriter = 
				new DataSourcePropertiesFileWriter(projectPath + DataSourceProperties.DATA_SOURCE_PATH);
		
		Map<String, String> propertyPairs = new HashMap<String, String>();
		propertyPairs.put("food", CSVFile.FOODCSV.value());
		
		dataSourceFilePropertiesWriter.write(propertyPairs);
		dataSourceFilePropertiesWriter.close();
		
		SystemProperties dataSourceProperties = new DataSourceProperties();
		
		assertThat(CSVFile.FOODCSV.value(), is(dataSourceProperties.getPath("food")));
	}
	
	@Test
	public void dataSourcePropertiesReadMealCSVFilePath() throws IOException{
		Files.deleteIfExists(Paths.get(projectPath + DataSourceProperties.DATA_SOURCE_PATH));
		
		SourceWriter<Map<String, String>> dataSourceFilePropertiesWriter = 
				new DataSourcePropertiesFileWriter(projectPath + DataSourceProperties.DATA_SOURCE_PATH);
		
		Map<String, String> propertyPairs = new HashMap<String, String>();
		propertyPairs.put("meal", CSVFile.MEALCSV.value());
		
		dataSourceFilePropertiesWriter.write(propertyPairs);
		dataSourceFilePropertiesWriter.close();
		
		SystemProperties dataSourceProperties = new DataSourceProperties();
		
		assertThat(CSVFile.MEALCSV.value(), is(dataSourceProperties.getPath("meal")));
	}
	
	@Test
	public void dataSourcePropertiesReadAllCSVFilePath() throws IOException{
		Files.deleteIfExists(Paths.get(projectPath + DataSourceProperties.DATA_SOURCE_PATH));
		
		SourceWriter<Map<String, String>> dataSourceFilePropertiesWriter = 
				new DataSourcePropertiesFileWriter(projectPath + DataSourceProperties.DATA_SOURCE_PATH);
		
		Map<String, String> propertyPairs = new HashMap<String, String>();
		propertyPairs.put("airlinecompany", CSVFile.AIRLINECOMPANYCSV.value());
		propertyPairs.put("flight", CSVFile.FLIGHTCSV.value());
		propertyPairs.put("food", CSVFile.FOODCSV.value());
		propertyPairs.put("meal", CSVFile.MEALCSV.value());
		
		dataSourceFilePropertiesWriter.write(propertyPairs);
		dataSourceFilePropertiesWriter.close();
		
		SystemProperties dataSourceProperties = new DataSourceProperties();
		
		assertThat(CSVFile.AIRLINECOMPANYCSV.value(), is(dataSourceProperties.getPath("airlinecompany")));
		assertThat(CSVFile.FLIGHTCSV.value(), is(dataSourceProperties.getPath("flight")));
		assertThat(CSVFile.FOODCSV.value(), is(dataSourceProperties.getPath("food")));
		assertThat(CSVFile.MEALCSV.value(), is(dataSourceProperties.getPath("meal")));
	}
	
	

}

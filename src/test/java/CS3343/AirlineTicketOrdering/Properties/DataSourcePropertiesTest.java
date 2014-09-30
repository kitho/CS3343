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

import org.junit.Before;
import org.junit.Test;

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

}

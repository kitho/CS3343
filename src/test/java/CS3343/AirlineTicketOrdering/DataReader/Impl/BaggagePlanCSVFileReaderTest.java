package CS3343.AirlineTicketOrdering.DataReader.Impl;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import TestingTool.DataWriter.CSVFileTest;
import CS3343.AirlineTicketOrdering.DataReader.CSVFileReader;
import CS3343.AirlineTicketOrdering.Model.BaggagePlan;
import CS3343.AirlineTicketOrdering.Parser.Parser;

public class BaggagePlanCSVFileReaderTest {
	
	private File projectPath;
	
	@Before
	public void initEnv() throws IOException{
		projectPath = new File(".").getCanonicalFile(); 
	}
	
	@Test
	public void testReadWithReturningObject() throws IOException, ParseException {
		//Parser stub - used to reduce dependence on Parser
		class parser_stub implements Parser<BaggagePlan> {
			@Override
			public BaggagePlan parseString(String line) throws ParseException {
				return new BaggagePlan();
			}

			@Override
			public String parseObject(BaggagePlan t) {
				return null;
			}
		}
		CSVFileReader reader = null;
		try{
			reader = new BaggagePlanCSVFileReader(projectPath + CSVFileTest.BAGGAGEPLANCSV.value());
		}catch(Exception e){
			fail("CSV not exist");
		}
		List<BaggagePlan> baggagePlans = reader.read(new parser_stub());
		assertEquals(true,(baggagePlans != null));
		assertEquals(true,(baggagePlans.size() > 0));
	}
	
	
	@Test
	public void testReadWithReturningNull() throws IOException, ParseException {
		//Parser stub - used to reduce dependence on Parser
		class parser_stub implements Parser<BaggagePlan> {
			@Override
			public BaggagePlan parseString(String line) throws ParseException {
				return null;
			}

			@Override
			public String parseObject(BaggagePlan t) {
				return null;
			}
		}
		
		CSVFileReader reader = new BaggagePlanCSVFileReader(projectPath + CSVFileTest.BAGGAGEPLANCSV.value());
		
		List<BaggagePlan> baggagePlans = reader.read(new parser_stub());
		assertEquals(true,(baggagePlans != null));
		assertEquals(true,(baggagePlans.size() == 0));
	}

}

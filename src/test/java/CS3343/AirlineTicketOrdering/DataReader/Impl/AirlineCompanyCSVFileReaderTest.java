package CS3343.AirlineTicketOrdering.DataReader.Impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import TestingTool.DataWriter.AirlineCompanyCSVFileWriter;
import TestingTool.DataWriter.CSVFileTest;
import CS3343.AirlineTicketOrdering.DataReader.SourceReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.AirlineCompanyCSVFileReader;
import CS3343.AirlineTicketOrdering.DataWriter.SourceWriter;
import CS3343.AirlineTicketOrdering.Model.AirlineCompany;
import CS3343.AirlineTicketOrdering.Parser.Impl.AirlineCompanyParser;

public class AirlineCompanyCSVFileReaderTest {

	private File projectPath;
	
	@Before
	public void setUp() throws IOException{
		projectPath = new File(".").getCanonicalFile(); 
	}
	
	@Test
	public void readAirlineCompanyCSVFileWhenFileNotExisted() throws IOException{
		Files.deleteIfExists(Paths.get(projectPath + CSVFileTest.AIRLINECOMPANYCSV.value()));
		
		SourceReader<AirlineCompany> airlineCompanyCsvReader;
		try {
			airlineCompanyCsvReader = new AirlineCompanyCSVFileReader(projectPath + CSVFileTest.AIRLINECOMPANYCSV.value());
			fail("File not existed");
		} catch (FileNotFoundException e) {
			assertThat(e.getMessage().toString(), is(not(nullValue())));
		}

	}

	@Test
	public void readAirlineCompanyCSVFileWithOneRecordTest() throws IOException, ParseException{
		Files.deleteIfExists(Paths.get(projectPath + CSVFileTest.AIRLINECOMPANYCSV.value()));
		
		AirlineCompany airlineCompany = new AirlineCompany();
		airlineCompany.setAirline("Cathay Pacific Airways");
		
		ArrayList<AirlineCompany> airlineCompanies = new ArrayList<AirlineCompany>();
		airlineCompanies.add(airlineCompany);
		
		SourceWriter<List<AirlineCompany>> airlineCompanyCSVFileWriter = new AirlineCompanyCSVFileWriter(projectPath + CSVFileTest.AIRLINECOMPANYCSV.value());
		airlineCompanyCSVFileWriter.write(airlineCompanies);
		airlineCompanyCSVFileWriter.close();
		
		SourceReader<AirlineCompany> airlineCompanyCSVFileReader = new AirlineCompanyCSVFileReader(projectPath + CSVFileTest.AIRLINECOMPANYCSV.value());
		List<AirlineCompany> airlineCompanyResultList = airlineCompanyCSVFileReader.read(new AirlineCompanyParser());
		airlineCompanyCSVFileReader.close();
		
		assertThat(airlineCompany.getAirline(), is(airlineCompanyResultList.get(0).getAirline()));
		
		assertThat(airlineCompany.getFlights().size(), is(airlineCompanyResultList.get(0).getFlights().size()));
		
	}
	
	
	@Test
	public void readAirlineCompanyCSVFileWithThreeRecordTest() throws IOException, ParseException{		
		Files.deleteIfExists(Paths.get(projectPath + CSVFileTest.AIRLINECOMPANYCSV.value()));
		
		AirlineCompany airlineCompanyCP = new AirlineCompany();
		airlineCompanyCP.setAirline("Cathay Pacific Airways");
		
		AirlineCompany airlineCompanyCA = new AirlineCompany();
		airlineCompanyCA.setAirline("China Airlines");

		AirlineCompany airlineCompanyHKA = new AirlineCompany();
		airlineCompanyHKA.setAirline("Hong Kong Airlines");
		
		ArrayList<AirlineCompany> airlineCompanies = new ArrayList<AirlineCompany>();
		airlineCompanies.add(airlineCompanyCP);
		airlineCompanies.add(airlineCompanyCA);
		airlineCompanies.add(airlineCompanyHKA);

		SourceWriter<List<AirlineCompany>> airlineCompanyCSVFileWriter = new AirlineCompanyCSVFileWriter(projectPath + CSVFileTest.AIRLINECOMPANYCSV.value());
		airlineCompanyCSVFileWriter.write(airlineCompanies);
		airlineCompanyCSVFileWriter.close();
		
		SourceReader<AirlineCompany> airlineCompanyCSVFileReader = new AirlineCompanyCSVFileReader(projectPath + CSVFileTest.AIRLINECOMPANYCSV.value());
		List<AirlineCompany> airlineCompanyResultList = airlineCompanyCSVFileReader.read(new AirlineCompanyParser());
		airlineCompanyCSVFileReader.close();
		
		assertThat(airlineCompanies.size(), is(airlineCompanyResultList.size()));
		
		for (int i = 0; i < airlineCompanies.size() ; i++) {
			assertThat(airlineCompanies.get(i).getAirline(), is(airlineCompanyResultList.get(i).getAirline()));
		}
		
		assertThat(airlineCompanyCP.getFlights().size(), is(airlineCompanyResultList.get(0).getFlights().size()));
		
	}
	
	
}

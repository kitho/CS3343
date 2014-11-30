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
import TestingTool.DataWriter.AirlineDiscountCSVFileWriter;
import TestingTool.DataWriter.CSVFileTest;
import CS3343.AirlineTicketOrdering.DataReader.SourceReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.AirlineDiscountCSVFileReader;
import CS3343.AirlineTicketOrdering.DataWriter.SourceWriter;
import CS3343.AirlineTicketOrdering.Discount.Impl.AirlineDiscount;
import CS3343.AirlineTicketOrdering.Parser.Impl.AirlineDiscountParser;

public class AirlineDiscountCSVFileReaderTest {
	private File projectPath;
	
	@Before
	public void setUp() throws IOException{
		projectPath = new File(".").getCanonicalFile();
	}
	
	@Test
	public void readAirlineDiscountCSVFileWhenFileNotExisted() throws IOException{
		Files.deleteIfExists(Paths.get(projectPath + CSVFileTest.AIRLINEDISCOUNTCSV.value()));
		
		SourceReader<AirlineDiscount> airlineDiscountCSVReader;
		try{
			airlineDiscountCSVReader = new AirlineDiscountCSVFileReader(projectPath + CSVFileTest.AIRLINEDISCOUNTCSV.value());
			fail("File not existed");
		}catch (FileNotFoundException e){
			assertThat(e.getMessage().toString(), is(not(nullValue())));
		}
	}
	
	@Test
	public void readAirlineDiscountCSVFileWithOneRecordTest() throws IOException, ParseException{
		Files.deleteIfExists(Paths.get(projectPath + CSVFileTest.AIRLINEDISCOUNTCSV.value()));
		
		AirlineDiscount airlineDiscount = new AirlineDiscount();
		airlineDiscount.setAirline("Cathay Pacific Airways");
		airlineDiscount.setCreditCardType("VISA");
		airlineDiscount.setDiscount(0.8);
		
		ArrayList<AirlineDiscount> airlineDiscounts = new ArrayList<AirlineDiscount>();
		airlineDiscounts.add(airlineDiscount);
		
		SourceWriter<List<AirlineDiscount>> airlineDiscountCSVFileWriter = new AirlineDiscountCSVFileWriter(projectPath + CSVFileTest.AIRLINEDISCOUNTCSV.value());
		airlineDiscountCSVFileWriter.write(airlineDiscounts);
		airlineDiscountCSVFileWriter.close();
		
		SourceReader<AirlineDiscount> airlineDiscountCSVFileReader = new AirlineDiscountCSVFileReader(projectPath + CSVFileTest.AIRLINEDISCOUNTCSV.value());
		List<AirlineDiscount> airlineDiscountResultList = airlineDiscountCSVFileReader.read(new AirlineDiscountParser());
		airlineDiscountCSVFileReader.close();
		
		assertThat(airlineDiscount.getAirline(),is(airlineDiscountResultList.get(0).getAirline()));
		assertThat(airlineDiscount.getCreditCardType(),is(airlineDiscountResultList.get(0).getCreditCardType()));
		assertThat(airlineDiscount.getDiscount(),is(airlineDiscountResultList.get(0).getDiscount()));
		
	}
	
	@Test
	public void readAirlineDiscountCSVFileWithThreeRecordsTest() throws IOException, ParseException{
		Files.deleteIfExists(Paths.get(projectPath + CSVFileTest.AIRLINEDISCOUNTCSV.value()));
		
		AirlineDiscount airlineDiscount1 = new AirlineDiscount();
		airlineDiscount1.setAirline("Cathay Pacific Airways");
		airlineDiscount1.setCreditCardType("VISA");
		airlineDiscount1.setDiscount(0.8);
		
		AirlineDiscount airlineDiscount2 = new AirlineDiscount();
		airlineDiscount2.setAirline("China Airlines");
		airlineDiscount2.setCreditCardType("Master Card");
		airlineDiscount2.setDiscount(0.9);
	
		AirlineDiscount airlineDiscount3 = new AirlineDiscount();
		airlineDiscount3.setAirline("Hong Kong Airlines");
		airlineDiscount3.setCreditCardType("American Express");
		airlineDiscount3.setDiscount(0.7);
		
		ArrayList<AirlineDiscount> airlineDiscounts = new ArrayList<AirlineDiscount>();
		airlineDiscounts.add(airlineDiscount1);
		airlineDiscounts.add(airlineDiscount2);
		airlineDiscounts.add(airlineDiscount3);
		
		SourceWriter<List<AirlineDiscount>> airlineDiscountCSVFileWriter = new AirlineDiscountCSVFileWriter(projectPath + CSVFileTest.AIRLINEDISCOUNTCSV.value());
		airlineDiscountCSVFileWriter.write(airlineDiscounts);
		airlineDiscountCSVFileWriter.close();
		
		SourceReader<AirlineDiscount> airlineDiscountCSVFileReader = new AirlineDiscountCSVFileReader(projectPath + CSVFileTest.AIRLINEDISCOUNTCSV.value());
		List<AirlineDiscount> airlineDiscountResultList = airlineDiscountCSVFileReader.read(new AirlineDiscountParser());
		airlineDiscountCSVFileReader.close();
		
		assertThat(airlineDiscounts.size(),is(airlineDiscountResultList.size()));
		
		for(int i = 0; i < airlineDiscounts.size(); i++){
			assertThat(airlineDiscounts.get(i).getAirline(),is(airlineDiscountResultList.get(i).getAirline()));
			assertThat(airlineDiscounts.get(i).getCreditCardType(),is(airlineDiscountResultList.get(i).getCreditCardType()));
			assertThat(airlineDiscounts.get(i).getDiscount(),is(airlineDiscountResultList.get(i).getDiscount()));
		}
		
		
	}
}

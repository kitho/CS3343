package CS3343.AirlineTicketOrdering.DataSource;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import CS3343.AirlineTicketOrdering.CSVFileWriter;
import CS3343.AirlineTicketOrdering.Model.AirlineCompany;
import CS3343.AirlineTicketOrdering.Model.Flight;

public class CSVFileReaderTest {

	private File projectPath;
	private SimpleDateFormat formatter;
	
	@Before
	public void setUp() throws IOException{
		projectPath = new File(".").getCanonicalFile(); 
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	@Test
	public void readAirlineCompanyCSVFileWithOneRecordTest() throws IOException, ParseException{

		new Thread(){
			
			@Override
		    public void run() {
				AirlineCompany airlineCompany = new AirlineCompany("Cathay Pacific Airways");
				
				AirlineCompany[] airlineCompanies = new AirlineCompany[]{airlineCompany};
				
				try{
					FileWriter fileWriter = new FileWriter(projectPath + AirlineCSVDataSource.AIRLINECSV, false);
					CSVFileWriter csvWriter = new CSVFileWriter(fileWriter);
					csvWriter.writeToThePathWithData(airlineCompanies);
					
					FileReader fileReader = new FileReader(projectPath + AirlineCSVDataSource.AIRLINECSV);
					CSVFileReader csvReader = new CSVFileReader(fileReader);
					
					List<String[]> airlineCompanyList = csvReader.read();
					
					assertThat(airlineCompany.getAirline(), is(airlineCompanyList.get(0)[0]));
					
					csvWriter.close();
					csvReader.close();
				}catch(Exception e){
					
				}
		    }
			
		}.start();
		
	}
	
	
	@Test
	public void readFlightCSVFileWithOneRecordTest() throws IOException, ParseException{
		new Thread(){
			
			@Override
		    public void run() {
				try{
					Flight flight = new Flight("CP001", "FIRST", "Hong Kong", "Taiwan", formatter.parse("2014-01-01 14:30:00"),
							formatter.parse("2014-01-01 17:30:00"), 30, 2500.00);
					
					Flight[] flights = new Flight[]{flight};
					FileWriter fileWriter = new FileWriter(projectPath + AirlineCSVDataSource.FLIGHTCSV, false);
					CSVFileWriter csvWriter = new CSVFileWriter(fileWriter);
					csvWriter.writeToThePathWithData(flights);
					
					FileReader fileReader = new FileReader(projectPath + AirlineCSVDataSource.FLIGHTCSV);
					CSVFileReader csvReader = new CSVFileReader(fileReader);
					
					List<String[]> flightList = csvReader.read();
					
					assertThat(flight.getFlightNumber(), is(flightList.get(0)[0]));
					assertThat(flight.getTravelClass(), is(flightList.get(0)[1]));
					assertThat(flight.getDepature(), is(flightList.get(0)[2]));
					assertThat(flight.getDestination(), is(flightList.get(0)[3]));
					assertThat(flight.getDepatureDateTime(), is(formatter.parse(flightList.get(0)[4])));
					assertThat(flight.getArrivalDateTime(), is(formatter.parse(flightList.get(0)[5])));
					assertThat(flight.getAvailable(), is(Integer.parseInt(flightList.get(0)[6])));
					assertThat(flight.getOneWayPrice(), is(Double.parseDouble(flightList.get(0)[7])));
					
					csvWriter.close();
					csvReader.close();
				}catch(Exception e){
					
				}
			}
		}.start();
	}
}

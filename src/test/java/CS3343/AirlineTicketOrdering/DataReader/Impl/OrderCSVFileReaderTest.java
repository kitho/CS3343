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
import CS3343.AirlineTicketOrdering.CSVFile.CSVFile;
import CS3343.AirlineTicketOrdering.CustomDateUtil.CustomDateFormatter;
import CS3343.AirlineTicketOrdering.DataReader.SourceReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.AirlineCompanyCSVFileReader;
import CS3343.AirlineTicketOrdering.DataWriter.SourceWriter;
import CS3343.AirlineTicketOrdering.DataWriter.Impl.OrderCSVFileWriter;
import CS3343.AirlineTicketOrdering.Model.AirlineCompany;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Model.Order;
import CS3343.AirlineTicketOrdering.Parser.Impl.AirlineCompanyParser;
import CS3343.AirlineTicketOrdering.Parser.Impl.OrderParser;

public class OrderCSVFileReaderTest {

	private File projectPath;
	private CustomDateFormatter formatter;
	
	@Before
	public void setUp() throws IOException{
		projectPath = new File(".").getCanonicalFile(); 
		formatter = new CustomDateFormatter();

	}
	
	@Test
	public void readOrderCSVFileWhenFileNotExisted() throws IOException{
		Files.deleteIfExists(Paths.get(projectPath + CSVFile.ORDERCSV.value()));
		
		SourceReader<Order> orderCsvReader;
		try {
			orderCsvReader = new OrderCSVFileReader(projectPath + CSVFile.ORDERCSV.value());
			fail("File not existed");
		} catch (FileNotFoundException e) {
			assertThat(e.getMessage().toString(), is(not(nullValue())));
		}

	}

	@Test
	public void readOrderCSVFileWithOneRecordTest() throws IOException, ParseException{
		Files.deleteIfExists(Paths.get(projectPath + CSVFile.ORDERCSV.value()));
		
		Flight flight = new Flight();
		flight.setAirline("Cathay Pacific Airways");
		flight.setFlightNumber("CP001");
		flight.setTravelClass("FIRST");
		flight.setDepature("Hong Kong");
		flight.setDestination("Taiwan");
		flight.setDepatureDateTime(formatter.parse("2014-01-01 14:30:00"));
		flight.setArrivalDateTime(formatter.parse("2014-01-01 17:30:00"));
		flight.setAvailable(30);
		flight.setOneWayPrice(2500.00);

		Order order = new Order();
		order.setId(0);
		order.setFlight(flight);
		order.setNumberOfTicket(1);
		
		ArrayList<Order> orders = new ArrayList<Order>();
		orders.add(order);
		
		SourceWriter<List<Order>> orderSourceWriter = new OrderCSVFileWriter(projectPath + CSVFile.ORDERCSV.value());
		orderSourceWriter.write(orders);
		orderSourceWriter.close();
		
		SourceReader<Order> orderReader = new OrderCSVFileReader(projectPath + CSVFile.ORDERCSV.value());
		List<Order> orderResult = orderReader.read(new OrderParser());
		orderReader.close();
		
		assertThat(order.getId(), is(orderResult.get(0).getId()));
		assertThat(order.getFlight(), is(orderResult.get(0).getFlight()));
	}
	
	
	@Test
	public void readOrderCSVFileWithThreeRecordTest() throws IOException, ParseException{		
		Files.deleteIfExists(Paths.get(projectPath + CSVFile.ORDERCSV.value()));
		
		Flight flight = new Flight();
		flight.setAirline("Cathay Pacific Airways");
		flight.setFlightNumber("CP001");
		flight.setTravelClass("FIRST");
		flight.setDepature("Hong Kong");
		flight.setDestination("Taiwan");
		flight.setDepatureDateTime(formatter.parse("2014-01-01 14:30:00"));
		flight.setArrivalDateTime(formatter.parse("2014-01-01 17:30:00"));
		flight.setAvailable(30);
		flight.setOneWayPrice(2500.00);
		
		Order order1 = new Order();
		order1.setId(0);
		order1.setFlight(flight);
		order1.setNumberOfTicket(1);
		
		Order order2 = new Order();
		order2.setId(1);
		order2.setFlight(flight);
		order2.setNumberOfTicket(1);
		
		Order order3 = new Order();
		order3.setId(2);
		order3.setFlight(flight);
		order3.setNumberOfTicket(1);

		
		ArrayList<Order> orders = new ArrayList<Order>();
		orders.add(order1);
		orders.add(order2);
		orders.add(order3);

		SourceWriter<List<Order>> orderSourceWriter = new OrderCSVFileWriter(projectPath + CSVFile.ORDERCSV.value());
		orderSourceWriter.write(orders);
		orderSourceWriter.close();
		
		SourceReader<Order> orderReader = new OrderCSVFileReader(projectPath + CSVFile.ORDERCSV.value());
		List<Order> orderResult = orderReader.read(new OrderParser());
		orderReader.close();
		
		assertThat(orders.size(), is(orderResult.size()));
		
		for (int i = 0; i < orders.size() ; i++) {
			assertThat(orders.get(i).getFlight(), is(orderResult.get(i).getFlight()));
		}			
	}
	
	
}

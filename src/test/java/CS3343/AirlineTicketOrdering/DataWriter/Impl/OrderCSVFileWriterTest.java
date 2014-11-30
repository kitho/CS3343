package CS3343.AirlineTicketOrdering.DataWriter.Impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import TestingTool.DataWriter.CSVFileTest;
import CS3343.AirlineTicketOrdering.CustomDateUtil.CustomDateFormatter;
import CS3343.AirlineTicketOrdering.DataReader.SourceReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.FlightCSVFileReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.OrderCSVFileReader;
import CS3343.AirlineTicketOrdering.DataWriter.SourceWriter;
import CS3343.AirlineTicketOrdering.DataWriter.Impl.FlightCSVFileWriter;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Model.Order;
import CS3343.AirlineTicketOrdering.Parser.Impl.FlightParser;
import CS3343.AirlineTicketOrdering.Parser.Impl.OrderParser;

public class OrderCSVFileWriterTest {
	
	private File projectPath;
	private CustomDateFormatter formatter;
	
	@Before
	public void setUp() throws IOException {
		projectPath = new File(".").getCanonicalFile(); 
		formatter = new CustomDateFormatter();
	}
	
	@Test
	public void writeFlightCSVFileOnce() throws IOException, ParseException {
		Files.deleteIfExists(Paths.get(projectPath + CSVFileTest.ORDERCSV.value()));
		
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
		order.setNumberOfTicket(4);
		
		List<Order> orders = new ArrayList<Order>();
		orders.add(order);


		SourceWriter<List<Order>> orderCSVFileWriter = new OrderCSVFileWriter(projectPath + CSVFileTest.ORDERCSV.value());
		orderCSVFileWriter.write(orders);
		orderCSVFileWriter.close();
		
		SourceReader<Order> orderCSVFileReader = new OrderCSVFileReader(projectPath + CSVFileTest.ORDERCSV.value());
		List<Order> orderResultList = (ArrayList<Order>) orderCSVFileReader.read(new OrderParser());

		assertThat(true, is(Files.exists(Paths.get(projectPath + CSVFileTest.ORDERCSV.value()))));
		assertThat(1, is(orderResultList.size()));
		
		for (int i = 0; i < orders.size() ; i++) {
			assertThat(orders.get(i).getId(), is(orderResultList.get(i).getId()));
			assertThat(orders.get(i).getFlight(), is(orderResultList.get(i).getFlight()));
			assertThat(orders.get(i).getNumberOfTicket(), is(orderResultList.get(i).getNumberOfTicket()));
		}		
	}
	
	@Test
	public void writeFlightCSVFileThreeTimes() throws IOException, ParseException {
		Files.deleteIfExists(Paths.get(projectPath + CSVFileTest.ORDERCSV.value()));
		
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
		order1.setId(100);
		order1.setFlight(flight);
		order1.setNumberOfTicket(5);
		
		Order order2 = new Order();
		order2.setId(102);
		order2.setFlight(flight);
		order2.setNumberOfTicket(6);
		
		Order order3 = new Order();
		order3.setId(101);
		order3.setFlight(flight);
		order3.setNumberOfTicket(7);
		
		List<Order> orders = new ArrayList<Order>();
		orders.add(order1);
		orders.add(order2);
		orders.add(order3);
		
		SourceWriter<List<Order>> orderCSVFileWriter = new OrderCSVFileWriter(projectPath + CSVFileTest.ORDERCSV.value());
		orderCSVFileWriter.write(orders);
		orderCSVFileWriter.close();
		
		SourceReader<Order> orderCSVFileReader = new OrderCSVFileReader(projectPath + CSVFileTest.ORDERCSV.value());
		List<Order> orderResultList = (ArrayList<Order>) orderCSVFileReader.read(new OrderParser());

		assertThat(true, is(Files.exists(Paths.get(projectPath + CSVFileTest.ORDERCSV.value()))));
		assertThat(3, is(orderResultList.size()));
		for (int i = 0; i < orders.size() ; i++) {
			assertThat(orders.get(i).getId(), is(orderResultList.get(i).getId()));
			assertThat(orders.get(i).getFlight(), is(orderResultList.get(i).getFlight()));
			assertThat(orders.get(i).getNumberOfTicket(), is(orderResultList.get(i).getNumberOfTicket()));
		}		
	}
	
}

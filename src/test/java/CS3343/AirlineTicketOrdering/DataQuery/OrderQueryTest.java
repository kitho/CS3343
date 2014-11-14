package CS3343.AirlineTicketOrdering.DataQuery;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import CS3343.AirlineTicketOrdering.DataReader.SourceReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.OrderCSVFileReader;
import CS3343.AirlineTicketOrdering.DataWriter.SourceWriter;
import CS3343.AirlineTicketOrdering.DataWriter.Impl.FlightCSVFileWriter;
import CS3343.AirlineTicketOrdering.DataWriter.Impl.OrderCSVFileWriter;
import CS3343.AirlineTicketOrdering.Model.AirlineCompany;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Model.Order;
import CS3343.AirlineTicketOrdering.Parser.Parser;

public class OrderQueryTest {
	
	private SimpleDateFormat formatter;

	@Before
	public void setUp() throws IOException{
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	
	@Test
	public void TestWithOutOrder() throws ParseException, IOException{
		SourceReader<Order> orderReader = mock(OrderCSVFileReader.class);
		ArrayList<Order> orders = new ArrayList<>();		
		when(orderReader.read((Parser<Order>) any())).thenReturn(orders);
		
		OrderQuery orderQuery = new OrderQuery(orderReader, (SourceWriter<List<Order>>)mock(OrderCSVFileWriter.class));
		int result = orderQuery.getMaxOrderId();

		assertThat(result,is(0));
		
	}
	
	@Test
	public void TestWithOneOrder() throws ParseException, IOException{
		SourceReader<Order> orderReader = mock(OrderCSVFileReader.class);

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
		order.setId(1);
		order.setFlight(flight);
		order.setNumberOfTicket(1);
		
		ArrayList<Order> orders = new ArrayList<>();
		orders.add(order);
		
		when(orderReader.read((Parser<Order>) any())).thenReturn(orders);
		
		OrderQuery orderQuery = new OrderQuery(orderReader, (SourceWriter<List<Order>>)mock(OrderCSVFileWriter.class));
		int result = orderQuery.getMaxOrderId();

		assertThat(result,is(1));
		
	}

	@Test
	public void TestWithThreeOrder() throws ParseException, IOException{
		SourceReader<Order> orderReader = mock(OrderCSVFileReader.class);

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
		order1.setId(1);
		order1.setFlight(flight);
		order1.setNumberOfTicket(1);
		
		Order order2 = new Order();
		order2.setId(3);
		order2.setFlight(flight);
		order2.setNumberOfTicket(1);
		
		Order order3 = new Order();
		order3.setId(2);
		order3.setFlight(flight);
		order3.setNumberOfTicket(1);
		
		ArrayList<Order> orders = new ArrayList<>();
		orders.add(order1);
		orders.add(order2);
		orders.add(order3);
		
		when(orderReader.read((Parser<Order>) any())).thenReturn(orders);
		
		OrderQuery orderQuery = new OrderQuery(orderReader, (SourceWriter<List<Order>>)mock(OrderCSVFileWriter.class));
		int result = orderQuery.getMaxOrderId();

		assertThat(result,is(3));
		
	}
	

}

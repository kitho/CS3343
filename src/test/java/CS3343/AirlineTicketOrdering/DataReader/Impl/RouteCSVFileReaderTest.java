package CS3343.AirlineTicketOrdering.DataReader.Impl;

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

import TestingTool.DataWriter.CSVFileTest;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import CS3343.AirlineTicketOrdering.CustomDateUtil.CustomDateFormatter;
import CS3343.AirlineTicketOrdering.DataReader.SourceReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.FlightCSVFileReader;
import CS3343.AirlineTicketOrdering.DataWriter.SourceWriter;
import CS3343.AirlineTicketOrdering.DataWriter.Impl.FlightCSVFileWriter;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Model.Route;
import CS3343.AirlineTicketOrdering.Parser.Impl.FlightParser;
import CS3343.AirlineTicketOrdering.Parser.Impl.RouteParser;

public class RouteCSVFileReaderTest {

	private File projectPath;
	private CustomDateFormatter formatter;
	
	@Before
	public void setUp() throws IOException{
		projectPath = new File(".").getCanonicalFile(); 
		formatter = new CustomDateFormatter();
	}

	
	@Test
	public void readFlightCSVFileWithOneRecordTest() throws IOException, ParseException{		
		Files.deleteIfExists(Paths.get(projectPath + CSVFileTest.FLIGHTCSV.value()));
		
		Route route = new Route();
		route.setDeparture("Hong Kong");
		route.setDestination("Taiwan");
		route.setDistance(1000);

		
		ArrayList<Route> routes = new ArrayList<Route>();
		routes.add(route);
		
		
		SourceReader<Route> routeCsvFileReader = new RouteCSVFileReader(projectPath + CSVFileTest.ROUTECSV.value());
		ArrayList<Route> resultList = (ArrayList<Route>) routeCsvFileReader.read(new RouteParser());
		routeCsvFileReader.close();
		
		assertThat(route.getDeparture(), is(resultList.get(0).getDeparture()));
		assertThat(route.getDestination(), is(resultList.get(0).getDestination()));
		assertThat(route.getDistance(), is(resultList.get(0).getDistance()));


	}
	
	
	
	
}

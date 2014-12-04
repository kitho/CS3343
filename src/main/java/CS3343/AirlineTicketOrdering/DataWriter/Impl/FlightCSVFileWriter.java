package CS3343.AirlineTicketOrdering.DataWriter.Impl;

import java.io.IOException;
import java.util.List;

import CS3343.AirlineTicketOrdering.DataWriter.CSVFileWriter;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Parser.Parser;
import CS3343.AirlineTicketOrdering.Parser.Impl.FlightParser;

/**
 * The Class FlightCSVFileWriter.
 */
public class FlightCSVFileWriter extends CSVFileWriter<List<Flight>> {

	/**
	 * Instantiates a new flight csv file writer.
	 *
	 * @param path the path
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public FlightCSVFileWriter(String path) throws IOException {
		super(path);
	}

	/**
	 * Save all the flights into the CSV file
	 * 
	 * @param List of flights
	 */
	@Override
	public void write(List<Flight> flights) throws IOException {
		Parser<Flight> flightParser = new FlightParser();
		
		for (Flight flight : flights) {
			String dataString = flightParser.parseObject(flight); 
			bufferedWriter.write(dataString);
			bufferedWriter.newLine();
		}
		bufferedWriter.close();
		fileWriter.close();
	}

}

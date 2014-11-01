package CS3343.AirlineTicketOrdering.DataWriter.Impl;

import java.io.IOException;
import java.util.List;

import CS3343.AirlineTicketOrdering.DataWriter.FileWriter;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Parser.ModelParser;
import CS3343.AirlineTicketOrdering.Parser.Impl.FlightParser;

public class FlightCSVFileWriter extends FileWriter<List<Flight>> {

	public FlightCSVFileWriter(String path) throws IOException {
		super(path);
	}

	@Override
	public void write(List<Flight> flights) throws IOException {
		//ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.SIMPLE_STYLE);
		ModelParser<Flight> flightParser = new FlightParser();
		
		for (Flight flight : flights) {
			//String dataString = ReflectionToStringBuilder.toString(flight); 
			String dataString = flightParser.parseObject(flight); 
			bufferedWriter.write(dataString);
			bufferedWriter.newLine();
		}
	}

}

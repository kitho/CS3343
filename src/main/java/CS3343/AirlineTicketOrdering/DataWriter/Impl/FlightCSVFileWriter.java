package CS3343.AirlineTicketOrdering.DataWriter.Impl;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import CS3343.AirlineTicketOrdering.DataWriter.FileWriter;
import CS3343.AirlineTicketOrdering.Model.Flight;

public class FlightCSVFileWriter extends FileWriter<List<Flight>> {

	public FlightCSVFileWriter(String path) throws IOException {
		super(path);
	}

	@Override
	public void write(List<Flight> flights) throws IOException {
		ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.SIMPLE_STYLE);
		
		for (Flight flight : flights) {
			String dataString = ReflectionToStringBuilder.toString(flight); 
			bufferedWriter.write(dataString);
			bufferedWriter.newLine();
		}
	}

}

package CS3343.AirlineTicketOrdering.TestingTools;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import CS3343.AirlineTicketOrdering.Model.AirlineCompany;

public class AirlineCompanyCSVFileWriter extends FileWriter<List<AirlineCompany>> {

	public AirlineCompanyCSVFileWriter(String path) throws IOException {
		super(path);
	}

	@Override
	public void write(List<AirlineCompany> airlineCompanies) throws IOException {
		ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.SIMPLE_STYLE);
		for (AirlineCompany airlineCompany : airlineCompanies) {
			String dataString = ReflectionToStringBuilder.toStringExclude(airlineCompany, "flights"); 
			bufferedWriter.write(dataString);
			bufferedWriter.newLine();
		}
	}
	
}

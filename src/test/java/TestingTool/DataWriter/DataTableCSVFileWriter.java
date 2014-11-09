package TestingTool.DataWriter;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import CS3343.AirlineTicketOrdering.DataWriter.CSVFileWriter;
import cucumber.api.DataTable;

public class DataTableCSVFileWriter extends CSVFileWriter<DataTable> {
	
	public DataTableCSVFileWriter(String path) throws IOException {
		super(path);
	}

	@Override
	public void write(DataTable dataTable) throws IOException {
		List<List<String>> dataTableList = dataTable.raw();
	    
		for (int i = 1 ; i < dataTableList.size() ; i++) {
			String dataString = StringUtils.join(dataTableList.get(i), ",");
			bufferedWriter.write(dataString);
			bufferedWriter.newLine();
		}
	    
	}

}

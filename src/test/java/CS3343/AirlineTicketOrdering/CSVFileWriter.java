package CS3343.AirlineTicketOrdering;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import CS3343.AirlineTicketOrdering.Model.AirlineCompany;
import CS3343.AirlineTicketOrdering.Model.Flight;
import au.com.bytecode.opencsv.CSVWriter;
import cucumber.api.DataTable;

public class CSVFileWriter {
	
	private CSVWriter csvWriter;
	
	public CSVFileWriter(FileWriter fileWriter){
		csvWriter = new CSVWriter(fileWriter);
	}
	
	public void writeToThePathWithData(AirlineCompany[] airlineCompanies){
        csvWriter.writeAll(convertAirLineCompanyList(Arrays.asList(airlineCompanies)));
	}
	
	public void writeToThePathWithData(Flight[] flights){
		csvWriter.writeAll(convertFlightList(Arrays.asList(flights)));
	}
		
	public void writeToThePathWithData(DataTable dataTable){
        csvWriter.writeAll(convertDataTable(dataTable));
	}
	
	public void close() throws IOException{
        csvWriter.close();
	}

	private List<String[]> convertFlightList(List<Flight> list){
		List<String[]> resultList = new ArrayList<String[]>();
	    for(int i = 0 ; i < list.size() ; i++){
	    	
	    }
	    return resultList;
	}
	
	private List<String[]> convertAirLineCompanyList(List<AirlineCompany> list){
	    List<String[]> resultList = new ArrayList<String[]>();
	    for(AirlineCompany airlineCompany : list){
	    	resultList.add(new String[]{airlineCompany.getAirline()});
	    }
	    return resultList;
	}
	
	private List<String[]> convertDataTable(DataTable dataTable){
		List<List<String>> dataTableList = dataTable.raw();
	    List<String[]> resultList = new ArrayList<String[]>();
	    for(int i = 1 ; i < dataTableList.size() ; i++){
	    	resultList.add(dataTableList.get(i).toArray(new String[dataTableList.get(i).size()]));
	    }
	    return resultList;
	}

}

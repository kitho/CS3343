package CS3343.AirlineTicketOrdering.DataSource;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class CSVFileReader {
	
	private CSVReader reader;
	
	public CSVFileReader(FileReader fileReader){
		reader = new CSVReader(fileReader, ',');
	}
	
	public List<String[]> read() throws IOException{
		List<String[]> resultList = new ArrayList<String[]>();
		
		String[] record = null;
		while((record = reader.readNext()) != null){
			resultList.add(record);
		}
		
		return resultList;
	}

	public void close() throws IOException{
		reader.close();
	}
	
}

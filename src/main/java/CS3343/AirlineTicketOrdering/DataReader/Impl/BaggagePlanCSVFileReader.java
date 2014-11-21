package CS3343.AirlineTicketOrdering.DataReader.Impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import CS3343.AirlineTicketOrdering.DataReader.CSVFileReader;
import CS3343.AirlineTicketOrdering.Model.BaggagePlan;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Parser.Parser;

public class BaggagePlanCSVFileReader extends CSVFileReader<BaggagePlan> {

	public BaggagePlanCSVFileReader(String path) throws FileNotFoundException {
		super(path);
	}

	@Override
	public List<BaggagePlan> read(Parser<BaggagePlan> parser) throws IOException, ParseException {

		List<BaggagePlan> baggagePlanList = new ArrayList<BaggagePlan>();

		String line;
		bufferedReader.readLine(); //pass first line
		while ((line = bufferedReader.readLine()) != null) {
			BaggagePlan bp = parser.parseString(line);
			if(bp != null){
				baggagePlanList.add(bp);
			}
		}
		bufferedReader.close();
		fileReader.close();
		return baggagePlanList;
	}

}

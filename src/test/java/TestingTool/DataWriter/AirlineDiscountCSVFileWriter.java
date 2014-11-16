package TestingTool.DataWriter;

import java.io.IOException;
import java.util.List;

import CS3343.AirlineTicketOrdering.DataWriter.CSVFileWriter;
import CS3343.AirlineTicketOrdering.Discount.Impl.AirlineDiscount;
import CS3343.AirlineTicketOrdering.Parser.Parser;
import CS3343.AirlineTicketOrdering.Parser.Impl.AirlineDiscountParser;

public class AirlineDiscountCSVFileWriter extends CSVFileWriter<List<AirlineDiscount>> {
	public AirlineDiscountCSVFileWriter(String path) throws IOException{
		super(path);
	}
	
	@Override
	public void write(List<AirlineDiscount> airlineDiscounts) throws IOException{
		Parser<AirlineDiscount> airlineDiscountParser = new AirlineDiscountParser();
		
		for(AirlineDiscount ad: airlineDiscounts){
			String dataString = airlineDiscountParser.parseObject(ad);
			bufferedWriter.write(dataString);
			bufferedWriter.newLine();
		}
	}
}

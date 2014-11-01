package CS3343.AirlineTicketOrdering.DataReader.Impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import CS3343.AirlineTicketOrdering.DataReader.FileReader;
import CS3343.AirlineTicketOrdering.Model.Food;
import CS3343.AirlineTicketOrdering.Parser.Parser;

public class FoodCSVFileReader extends FileReader<Food> {
	
	public FoodCSVFileReader(String path) throws FileNotFoundException{
		super(path);
	}

	@Override
	public List<Food> read(Parser<Food> parser) throws IOException, ParseException {
		List<Food> foods = new ArrayList<Food>();
		
		String line;
		while ((line = bufferedReader.readLine()) != null){
			foods.add(parser.parseString(line));
		}
		
		return foods;
	}
	
	
}

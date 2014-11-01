package CS3343.AirlineTicketOrdering.DataReader.Impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import CS3343.AirlineTicketOrdering.DataReader.FileReader;
import CS3343.AirlineTicketOrdering.Model.Meal;
import CS3343.AirlineTicketOrdering.Parser.Parser;

public class MealCSVFileReader extends FileReader<Meal> {
	

	public MealCSVFileReader(String path) throws FileNotFoundException{
		super(path);
	}

	@Override
	public List<Meal> read(Parser<Meal> parser) throws IOException, ParseException {
		List<Meal> meals = new ArrayList<Meal>();
		
		String line;
		while ((line = bufferedReader.readLine()) != null){
			meals.add(parser.parseString(line));
		}
		
		return meals;
	}
	
	
}

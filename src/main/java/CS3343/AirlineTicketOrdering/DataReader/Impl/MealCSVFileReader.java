package CS3343.AirlineTicketOrdering.DataReader.Impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import CS3343.AirlineTicketOrdering.DataReader.FileReader;
import CS3343.AirlineTicketOrdering.Model.Food;
import CS3343.AirlineTicketOrdering.Model.Meal;

public class MealCSVFileReader extends FileReader<Meal> {
	

	public MealCSVFileReader(String path) throws FileNotFoundException{
		super(path);
	}

	@Override
	public List<Meal> read() throws IOException, ParseException {
		
		List<Meal> meals = new ArrayList<Meal>();
		
		String line;
		while ((line = bufferedReader.readLine()) != null){
			Meal meal = new Meal();
			String[] dataStr = line.split(",");
			
			meal.setMealId(dataStr[0]);
			meal.setFoodIds(dataStr[1]);
			meals.add(meal);
		}
		
		return meals;
	}
	
	
}

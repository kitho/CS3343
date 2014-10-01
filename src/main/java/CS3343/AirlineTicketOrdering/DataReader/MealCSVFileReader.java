package CS3343.AirlineTicketOrdering.DataReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import CS3343.AirlineTicketOrdering.Model.Food;
import CS3343.AirlineTicketOrdering.Model.Meal;

public class MealCSVFileReader extends FileReader<Meal> {
	
	private SourceReader<Food> fileReader;
	
	public MealCSVFileReader(String path, SourceReader<Food> fileReader) throws FileNotFoundException{
		super(path);
		this.fileReader = fileReader;
	}

	@Override
	public List<Meal> read() throws IOException, ParseException {
		List<Food> foods = fileReader.read();
		fileReader.close();
		
		List<Meal> meals = new ArrayList<Meal>();
		
		String line;
		while ((line = bufferedReader.readLine()) != null){
			Meal meal = new Meal();
			String[] dataStr = line.split(",");
			
			meal.setMealId(dataStr[0]);
			meals.add(meal);
			
			String[] tempFoodList = dataStr[1].split(",");
			for(String foodA: tempFoodList){
				for(Food foodB: foods){
					if(foodA.equals(String.valueOf(foodB.getId())) ){
						meal.addFood(foodB);
						break;
					}
				}
			}
		}
		
		return meals;
	}
	
	
}

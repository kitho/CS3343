package CS3343.AirlineTicketOrdering.DataReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import CS3343.AirlineTicketOrdering.Model.Food;
import CS3343.AirlineTicketOrdering.Model.Meal;

public class FoodCSVFileReader extends FileReader<Food> {
	
	public FoodCSVFileReader(String path) throws FileNotFoundException{
		super(path);
	}

	@Override
	public List<Food> read() throws IOException, ParseException {
		List<Food> foods = new ArrayList<Food>();
		
		String line;
		while ((line = bufferedReader.readLine()) != null){
			String[] dataStr = line.split(",");
			Food food = new Food();
			food.setId(Integer.parseInt(dataStr[0]));
			food.setName(dataStr[1]);
			food.setCategory(dataStr[2]);
			food.setUnitPrice(Integer.parseInt(dataStr[3]));
			foods.add(food);
		}
		
		return foods;
	}
	
	
}

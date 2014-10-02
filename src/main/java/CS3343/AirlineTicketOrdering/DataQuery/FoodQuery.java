package CS3343.AirlineTicketOrdering.DataQuery;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import CS3343.AirlineTicketOrdering.DataReader.SourceReader;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Model.Food;
import CS3343.AirlineTicketOrdering.Model.Meal;

public class FoodQuery {
	private List<Flight> flights;
	private List<Meal> meals;
	private List<Food> foods;
	
	
	public FoodQuery(SourceReader<Flight> flightReader,SourceReader<Meal> mealReader, SourceReader<Food> foodReader) throws IOException, ParseException{
		flights = flightReader.read();
		meals = mealReader.read();
		foods = foodReader.read();
	}
	
	public List<Food> findAllFoods(){
		return this.foods;
	}
	
	public Food findFoodByFoodId(int id){
		for(Food food: this.foods){
			if(id == food.getId()){
				return food;
			}
		}
		return null;
	}
	
	public List<Food> findFoodsByCategory(String category){
		List<Food> fList = new ArrayList<Food>();
		
		for(Food food: this.foods){
			if(category.equals(food.getCategory())){
				fList.add(food);
			}
		}
		
		return (fList.size() >= 1)?fList:null;
	}
	
	public List<Food> findFoodsByOneMeal(String mealId){
		List<Food> fList = null;
		Meal m = null;
		
		for(Meal meal: this.meals){
			if(meal.getMealId().equals(mealId)){
				m = meal;
			}
		}
		if(m == null){
			return null;
		}else{
			fList = new ArrayList<Food>();
			String[] temp = m.getFoodIds().split("-");
			for(int i = 0; i < temp.length; i++){
				for(Food f : this.foods){
					if(temp[i].equals(String.valueOf(f.getId()))){
						fList.add(f);
					}
				}
			}
			return fList;
		}
		
	}
}

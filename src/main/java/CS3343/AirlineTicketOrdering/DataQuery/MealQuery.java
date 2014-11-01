package CS3343.AirlineTicketOrdering.DataQuery;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import CS3343.AirlineTicketOrdering.DataReader.SourceReader;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Model.Meal;
import CS3343.AirlineTicketOrdering.Model.Food;
import CS3343.AirlineTicketOrdering.Parser.Impl.FlightParser;
import CS3343.AirlineTicketOrdering.Parser.Impl.FoodParser;
import CS3343.AirlineTicketOrdering.Parser.Impl.MealParser;

public class MealQuery {
	private List<Flight> flights;
	private List<Food> foods;
	private List<Meal> meals;
	
	public MealQuery(SourceReader<Flight> flightReader,SourceReader<Meal> mealReader, SourceReader<Food> foodReader) throws IOException, ParseException{
		flights = flightReader.read(new FlightParser());
		meals = mealReader.read(new MealParser());
		foods = foodReader.read(new FoodParser());
	}
	
	public List<Meal> findAllMeals(){
		return this.meals;
	}
	
	public Meal findMealByMealId(String mealId){
		for(Meal meal: this.meals){
			if(meal.getMealId().equals(mealId)){
				return meal;
			}
		}
		return null;
	}
	
	public List<Flight> findFlightsByOneMeal(String mealId){
		List<Flight> fList = new ArrayList<Flight>();
		
		for(Flight flight: this.flights){
			if(flight.getMealIds().contains(mealId)){
				fList.add(flight);
			}
		}
		
		return (fList.size() >= 1)?fList:null;
	}
	
	public List<Meal> findMealsByOneFlight(String flightNumber){
		List<Meal> mList = null;
		String mIds = "";
		
		for(Flight flight: this.flights){
			if(flight.getFlightNumber().equals(flightNumber)){
				mIds = flight.getMealIds();
			}
		}
		
		if(mIds.equals("")){
			return mList;
		}else{
			String[] m;
			mList = new ArrayList<Meal>();
			
			if(mIds.contains("-")){
				m = mIds.split("-");
			}else{
				m = new String[1];
				m[0] = mIds;
			}
			for(int i = 0; i < m.length; i ++){
				for(Meal meal:this.meals){
					if(meal.getMealId().equals(m[i])){
						mList.add(meal);
					}
				}
			}
		}
		return mList;
	}
	
	public List<Meal> findMealsByOneFood(int id){
		List<Meal> mList = new ArrayList<Meal>();
		
		for(Meal meal: this.meals){
			if(meal.getFoodIds().contains(String.valueOf(id))){
				mList.add(meal);
			}
		}
		
		return (mList.size() >= 1)? mList:null;
	}
	
}

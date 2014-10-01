package CS3343.AirlineTicketOrdering.Model;

import java.util.ArrayList;
import java.util.List;

public class Meal {
	private String mealId;
	private List<Food>foodList;
	
	public Meal(){
		foodList = new ArrayList<Food>();
	}
	
	public String getMealId() {
		return mealId;
	}
	
	public void setMealId(String mealId) {
		this.mealId = mealId;
	}
	public List<Food> getFoodList() {
		return foodList;
	}
	public void addFood(Food food) {
		this.foodList.add(food);
	}
	
	
}

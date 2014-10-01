package CS3343.AirlineTicketOrdering.Model;

import java.util.ArrayList;

public class Meal {
	private String mealId;
	private ArrayList<Food>foodList;
	
	public Meal(){
		foodList = new ArrayList<Food>();
	}
	
	public String getMealId() {
		return mealId;
	}
	
	public void setMealId(String mealId) {
		this.mealId = mealId;
	}
	public ArrayList<Food> getFoodList() {
		return foodList;
	}
	public void setFoodList(ArrayList<Food> foodList) {
		this.foodList = foodList;
	}
	
	
}

package CS3343.AirlineTicketOrdering.Parser.Impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import CS3343.AirlineTicketOrdering.Model.Meal;
import CS3343.AirlineTicketOrdering.Parser.Parser;

public class MealParser implements Parser<Meal> {

	public Meal parseString(String line) throws ParseException {

		Meal meal = new Meal();
		String[] dataStr = line.split(",");
		
		meal.setMealId(dataStr[0]);
		meal.setFoodIds(dataStr[1]);
		
		return meal;
	}

	public String parseObject(Meal meal) {
		List<String> dataList = new ArrayList<String>();
		dataList.add(meal.getMealId());
		dataList.add(meal.getFoodIds());

		String line = StringUtils.join(dataList, ",");
		
		return line;
	}

}

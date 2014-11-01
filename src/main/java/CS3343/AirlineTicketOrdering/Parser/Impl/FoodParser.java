package CS3343.AirlineTicketOrdering.Parser.Impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import CS3343.AirlineTicketOrdering.Model.Food;
import CS3343.AirlineTicketOrdering.Parser.Parser;

public class FoodParser implements Parser<Food> {

	public Food parseString(String line) throws ParseException {
		
		String[] dataStr = line.split(",");
		Food food = new Food();
		food.setId(Integer.parseInt(dataStr[0]));
		food.setName(dataStr[1]);
		food.setCategory(dataStr[2]);
		food.setUnitPrice(Integer.parseInt(dataStr[3]));
		
		return food;
	}

	public String parseObject(Food food) {
		
		List<String> dataList = new ArrayList<String>();
		dataList.add(String.valueOf(food.getId()));
		dataList.add(food.getName());
		dataList.add(food.getCategory());
		dataList.add(String.valueOf(food.getUnitPrice()));
		
		String line = StringUtils.join(dataList, ",");
		
		return line;
	}

}

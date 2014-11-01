package CS3343.AirlineTicketOrdering.DataQuery;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import CS3343.AirlineTicketOrdering.DataReader.CSVFile;
import CS3343.AirlineTicketOrdering.DataReader.SourceReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.FlightCSVFileReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.FoodCSVFileReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.MealCSVFileReader;
import CS3343.AirlineTicketOrdering.Model.Meal;
import CS3343.AirlineTicketOrdering.Model.Food;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Parser.Impl.FoodParser;

public class FoodQueryTest {
	private File projectPath;
	private SourceReader<Flight> flightReader;
	private SourceReader<Meal> mealReader;
	private SourceReader<Food> foodReader;
	
	@Before
	public void setUp() throws IOException{
		projectPath = new File(".").getCanonicalFile(); 
		flightReader = new FlightCSVFileReader(projectPath + CSVFile.FLIGHTCSV.value());
		mealReader = new MealCSVFileReader(projectPath + CSVFile.MEALCSV.value());
		foodReader = new FoodCSVFileReader(projectPath + CSVFile.FOODCSV.value());
	}
	
	@Test
	public void findAllFoodsTest() throws IOException, ParseException{
		List<Food> foods = new ArrayList<Food>();
		foods = foodReader.read(new FoodParser());
		
		foodReader.close();
		foodReader = new FoodCSVFileReader(projectPath + CSVFile.FOODCSV.value()); 
		
		FoodQuery foodQuery = new FoodQuery(flightReader,mealReader,foodReader);
		List<Food> resultList = foodQuery.findAllFoods();
		
		assertThat(foods.size(), is(resultList.size()));
		
		for(int i = 0; i < foods.size(); i++){
			assertThat(foods.get(i).getId(),is(resultList.get(i).getId()));
			assertThat(foods.get(i).getName(),is(resultList.get(i).getName()));
			assertThat(foods.get(i).getCategory(),is(resultList.get(i).getCategory()));
			assertThat(foods.get(i).getUnitPrice(),is(resultList.get(i).getUnitPrice()));
		}
	
	}
	
	@Test
	public void findFoodByFoodIdTest() throws IOException, ParseException{
		List<Food> foods = new ArrayList<Food>();
		foods = foodReader.read(new FoodParser());
		
		Food food = foods.get(0);
		
		foodReader.close();
		foodReader = new FoodCSVFileReader(projectPath + CSVFile.FOODCSV.value()); 
		
		FoodQuery foodQuery = new FoodQuery(flightReader,mealReader,foodReader);
		Food result = foodQuery.findFoodByFoodId(1);
		
		assertThat(food.getId(),is(result.getId()));
		assertThat(food.getName(),is(result.getName()));
		assertThat(food.getCategory(),is(result.getCategory()));
		assertThat(food.getUnitPrice(),is(result.getUnitPrice()));
	
	}
	
	@Test
	public void findFoodByFoodIdNoResultTest() throws IOException, ParseException{
		FoodQuery foodQuery = new FoodQuery(flightReader,mealReader,foodReader);
		Food result = foodQuery.findFoodByFoodId(99);
		
		assertThat(result,is(nullValue()));
	
	}
	
	@Test
	public void findFoodsByCategoryTest() throws IOException, ParseException{
		List<Food> foods = new ArrayList<Food>();
		foods = foodReader.read(new FoodParser());
		
		List<Food> tempF = new ArrayList<Food>();
		tempF.add(foods.get(2));
		tempF.add(foods.get(6));
		tempF.add(foods.get(12));
		
		foodReader.close();
		foodReader = new FoodCSVFileReader(projectPath + CSVFile.FOODCSV.value()); 
		
		FoodQuery foodQuery = new FoodQuery(flightReader,mealReader,foodReader);
		List<Food> resultList = foodQuery.findFoodsByCategory("Main");
		
		assertThat(tempF.size(), is(resultList.size()));
		
		for(int i = 0; i < tempF.size(); i++){
			assertThat(tempF.get(i).getId(),is(resultList.get(i).getId()));
			assertThat(tempF.get(i).getName(),is(resultList.get(i).getName()));
			assertThat(tempF.get(i).getCategory(),is(resultList.get(i).getCategory()));
			assertThat(tempF.get(i).getUnitPrice(),is(resultList.get(i).getUnitPrice()));
		}
	
	}
	
	@Test
	public void findFoodsByCategoryNoResultTest() throws IOException, ParseException{ 
		
		FoodQuery foodQuery = new FoodQuery(flightReader,mealReader,foodReader);
		List<Food> resultList = foodQuery.findFoodsByCategory("Mainnnnn");
		
		assertThat(resultList, is(nullValue()));
		
	}
	
	@Test
	public void findFoodsByOneMealTest() throws IOException, ParseException{
		List<Food> foods = new ArrayList<Food>();
		foods = foodReader.read(new FoodParser());
		
		List<Food> tempF = new ArrayList<Food>();
		tempF.add(foods.get(3));
		tempF.add(foods.get(2));
		tempF.add(foods.get(5));
		tempF.add(foods.get(10));
		
		foodReader.close();
		foodReader = new FoodCSVFileReader(projectPath + CSVFile.FOODCSV.value()); 
		
		FoodQuery foodQuery = new FoodQuery(flightReader,mealReader,foodReader);
		List<Food> resultList = foodQuery.findFoodsByOneMeal("M1");
		
		assertThat(tempF.size(), is(resultList.size()));
		
		for(int i = 0; i < tempF.size(); i++){
			assertThat(tempF.get(i).getId(),is(resultList.get(i).getId()));
			assertThat(tempF.get(i).getName(),is(resultList.get(i).getName()));
			assertThat(tempF.get(i).getCategory(),is(resultList.get(i).getCategory()));
			assertThat(tempF.get(i).getUnitPrice(),is(resultList.get(i).getUnitPrice()));
		}
	
	}
	
	@Test
	public void findFoodsByOneMealNoResultTest() throws IOException, ParseException{ 
		
		FoodQuery foodQuery = new FoodQuery(flightReader,mealReader,foodReader);
		List<Food> resultList = foodQuery.findFoodsByOneMeal("M99");
		
		assertThat(resultList, is(nullValue()));
		
	}
	
}

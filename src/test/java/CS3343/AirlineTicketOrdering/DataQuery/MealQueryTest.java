package CS3343.AirlineTicketOrdering.DataQuery;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
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

public class MealQueryTest {
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
	public void findAllMealsTest() throws ParseException, IOException{
		List<Meal> meals = new ArrayList<Meal>();
		meals = mealReader.read();
		
		mealReader.close();
		mealReader = new MealCSVFileReader(projectPath + CSVFile.MEALCSV.value());
		
		MealQuery mealQuery = new MealQuery(flightReader,mealReader,foodReader);
		List<Meal> resultList = mealQuery.findAllMeals();
		
		assertThat(meals.size(),is(resultList.size()));
		
		for(int i = 0; i < meals.size(); i++){
			assertThat(meals.get(i).getMealId(),is(resultList.get(i).getMealId()));
			assertThat(meals.get(i).getFoodIds(),is(resultList.get(i).getFoodIds()));
		}
	}
	
	@Test
	public void findMealByMealIdTest() throws ParseException, IOException{
		MealQuery mealQuery = new MealQuery(flightReader,mealReader,foodReader);
		
		Meal m = mealQuery.findMealByMealId("M1");
		
		assertThat(m, not(nullValue()));
		
		assertThat(m.getMealId(),is("M1"));
		assertThat(m.getFoodIds(),is("4-3-6-11"));
	}
	
	@Test
	public void findMealByMealIdNoResultTest() throws ParseException, IOException{
		MealQuery mealQuery = new MealQuery(flightReader,mealReader,foodReader);
		
		Meal m = mealQuery.findMealByMealId("M4");
		
		assertThat(m, nullValue());
	}
	
	@Test
	public void findFlightsByOneMealTest() throws ParseException, IOException{
		List<Flight> flights = new ArrayList<Flight>();
		flights = flightReader.read();
		
		Flight flight1 = flights.get(0);
		Flight flight2 = flights.get(1);
		
		List<Flight> tempF = new ArrayList<Flight>();
		tempF.add(flight1);
		tempF.add(flight2);
		
		flightReader.close();
		flightReader = new FlightCSVFileReader(projectPath + CSVFile.FLIGHTCSV.value());
		
		MealQuery mealQuery = new MealQuery(flightReader,mealReader,foodReader);
		List<Flight> resultList = mealQuery.findFlightsByOneMeal("M1");
		
		assertThat(tempF.size(),is(resultList.size()));
		
		for(int i = 0; i < tempF.size(); i++){
			assertThat(tempF.get(i).getAirline(), is(resultList.get(i).getAirline()));
			assertThat(tempF.get(i).getFlightNumber(), is(resultList.get(i).getFlightNumber()));
			assertThat(tempF.get(i).getTravelClass(), is(resultList.get(i).getTravelClass()));
			assertThat(tempF.get(i).getDepature(), is(resultList.get(i).getDepature()));
			assertThat(tempF.get(i).getDestination(), is(resultList.get(i).getDestination()));
			assertThat(tempF.get(i).getDepatureDateTime(), is(resultList.get(i).getDepatureDateTime()));
			assertThat(tempF.get(i).getArrivalDateTime(), is(resultList.get(i).getArrivalDateTime()));
			assertThat(tempF.get(i).getAvailable(), is(resultList.get(i).getAvailable()));
			assertThat(tempF.get(i).getOneWayPrice(), is(resultList.get(i).getOneWayPrice()));
			assertThat(tempF.get(i).getModel(), is(resultList.get(i).getModel()));
			assertThat(tempF.get(i).getMealIds(), is(resultList.get(i).getMealIds()));
			assertThat(tempF.get(i).getFoodIds(), is(resultList.get(i).getFoodIds()));
		}
		
	}
	
	@Test
	public void findFlightsByOneMealNoResultTest() throws ParseException, IOException{
		MealQuery mealQuery = new MealQuery(flightReader,mealReader,foodReader);
		List<Flight> resultList = mealQuery.findFlightsByOneMeal("M4");
		
		assertThat(resultList,is(nullValue()));
	}
	
	@Test
	public void findMealsByOneFlightTest() throws ParseException, IOException{
		List<Meal> meals = new ArrayList<Meal>();
		meals = mealReader.read();
		
		Meal m1 = meals.get(0);
		Meal m2 = meals.get(1);
		Meal m3 = meals.get(2);
		
		List<Meal> tempM = new ArrayList<Meal>();
		tempM.add(m1);
		tempM.add(m2);
		tempM.add(m3);
		
		mealReader.close();
		mealReader = new MealCSVFileReader(projectPath + CSVFile.MEALCSV.value());
		
		MealQuery mealQuery = new MealQuery(flightReader,mealReader,foodReader);
		List<Meal>resultList = mealQuery.findMealsByOneFlight("CA001");
		
		assertThat(tempM.size(),is(resultList.size()));
		
		for(int i = 0; i < tempM.size(); i++){
			assertThat(tempM.get(i).getMealId(),is(resultList.get(i).getMealId()));
			assertThat(tempM.get(i).getFoodIds(),is(resultList.get(i).getFoodIds()));
		}
	}
	
	@Test
	public void findMealsByOneFlightNoResultTest() throws ParseException, IOException{
		MealQuery mealQuery = new MealQuery(flightReader,mealReader,foodReader);
		List<Meal>resultList = mealQuery.findMealsByOneFlight("CS001");
		
		assertThat(resultList,is(nullValue()));
	}
	
	@Test
	public void findMealsByOneFoodTest() throws ParseException, IOException{
		List<Meal> meals = new ArrayList<Meal>();
		meals = mealReader.read();
		
		mealReader.close();
		mealReader = new MealCSVFileReader(projectPath + CSVFile.MEALCSV.value());
		
		MealQuery mealQuery = new MealQuery(flightReader,mealReader,foodReader);
		List<Meal> resultList = mealQuery.findMealsByOneFood(4);
		
		assertThat(meals.size(),is(resultList.size()));
		
		for(int i = 0; i < meals.size(); i++){
			assertThat(meals.get(i).getMealId(), is(resultList.get(i).getMealId()));
			assertThat(meals.get(i).getFoodIds(), is(resultList.get(i).getFoodIds()));
		}
	}
	
	@Test
	public void findMealsByOneFoodNoResultTest() throws ParseException, IOException{
		MealQuery mealQuery = new MealQuery(flightReader,mealReader,foodReader);
		List<Meal> resultList = mealQuery.findMealsByOneFood(99);
		
		assertThat(resultList,is(nullValue()));
	}
}

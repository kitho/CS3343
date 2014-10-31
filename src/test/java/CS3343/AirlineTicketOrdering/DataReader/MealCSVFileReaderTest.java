package CS3343.AirlineTicketOrdering.DataReader;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import TestingTool.DataWriter.FoodCSVFileWriter;
import TestingTool.DataWriter.MealCSVFileWriter;
import CS3343.AirlineTicketOrdering.DataReader.CSVFile;
import CS3343.AirlineTicketOrdering.DataReader.SourceReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.AirlineCompanyCSVFileReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.FlightCSVFileReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.MealCSVFileReader;
import CS3343.AirlineTicketOrdering.DataWriter.SourceWriter;
import CS3343.AirlineTicketOrdering.Model.AirlineCompany;
import CS3343.AirlineTicketOrdering.Model.Food;
import CS3343.AirlineTicketOrdering.Model.Meal;

public class MealCSVFileReaderTest {
	private File projectPath;
	
	@Before
	public void setUp() throws IOException{
		projectPath = new File(".").getCanonicalFile();

	}
	
	@Test
	public void readMealCSVFileWhenFileNotExisted() throws IOException{
		Files.deleteIfExists(Paths.get(projectPath + CSVFile.MEALCSV.value()));
		
		SourceReader<AirlineCompany> mealCsvReader;
		try {
			mealCsvReader = new AirlineCompanyCSVFileReader(projectPath + CSVFile.MEALCSV.value(), 
					new FlightCSVFileReader(projectPath + CSVFile.MEALCSV.value()));
			fail("File not existed");
		} catch (FileNotFoundException e) {
			assertThat(e.getMessage().toString(), is(not(nullValue())));
		}
	}
	
	@Test
	public void readMealCSVFileWithOneRecordTest() throws IOException, ParseException{
		Files.deleteIfExists(Paths.get(projectPath + CSVFile.MEALCSV.value()));

		Meal meal = new Meal();
		
		meal.setMealId("M1");
		meal.setFoodIds("4-3-6-11");
		
		List<Meal> meals = new ArrayList<Meal>();
		meals.add(meal);
		
		SourceWriter<List<Meal>> mealCsvFileWriter = new MealCSVFileWriter(projectPath + CSVFile.MEALCSV.value());
		mealCsvFileWriter.write(meals);
		mealCsvFileWriter.close();
		
		SourceReader<Meal> mealCsvFileReader = new MealCSVFileReader(projectPath + CSVFile.MEALCSV.value());
		List<Meal> resultList = mealCsvFileReader.read();
		mealCsvFileReader.close();

		assertThat(meal.getMealId(), is(resultList.get(0).getMealId()));
		assertThat(meal.getFoodIds(), is(resultList.get(0).getFoodIds()));
		
	}
	
	@Test
	public void readMealCSVFileWithThreeRecordsTest() throws IOException, ParseException{
		Files.deleteIfExists(Paths.get(projectPath + CSVFile.MEALCSV.value()));
		
		Meal meal1 = new Meal();
		Meal meal2 = new Meal();
		Meal meal3 = new Meal();
		
		meal1.setMealId("M1");
		meal1.setFoodIds("4-3-6-11");
		meal2.setMealId("M2");
		meal2.setFoodIds("4-7-2-12");
		meal3.setMealId("M3");
		meal3.setFoodIds("4-13-2-9-5");
		
		List<Meal> meals = new ArrayList<Meal>();
		meals.add(meal1);
		meals.add(meal2);
		meals.add(meal3);
		
		SourceWriter<List<Meal>> mealCsvFileWriter = new MealCSVFileWriter(projectPath + CSVFile.MEALCSV.value());
		mealCsvFileWriter.write(meals);
		mealCsvFileWriter.close();
		
		SourceReader<Meal> mealCsvFileReader = new MealCSVFileReader(projectPath + CSVFile.MEALCSV.value());
		List<Meal> resultList = mealCsvFileReader.read();
		mealCsvFileReader.close();
		
		assertThat(meals.size(), is(resultList.size()));
		
		for(int i = 0; i < meals.size(); i ++){
			assertThat(meals.get(i).getMealId(), is(resultList.get(i).getMealId()));
			assertThat(meals.get(i).getFoodIds(), is(resultList.get(i).getFoodIds()));
		}
		
	}
	
}

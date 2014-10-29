package CS3343.AirlineTicketOrdering.DataReader;

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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import CS3343.AirlineTicketOrdering.DataReader.CSVFile;
import CS3343.AirlineTicketOrdering.DataReader.SourceReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.FlightCSVFileReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.FoodCSVFileReader;
import CS3343.AirlineTicketOrdering.DataWriter.FoodCSVFileWriter;
import CS3343.AirlineTicketOrdering.DataWriter.SourceWriter;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Model.Food;

public class FoodCSVFileReaderTest {
	private File projectPath;
	
	@Before
	public void setUp() throws IOException{
		projectPath = new File(".").getCanonicalFile();
	}
	

	@Test
	public void readFoodCSVFileWhenFileNotExisted() throws IOException{
		Files.deleteIfExists(Paths.get(projectPath + CSVFile.FOODCSV.value()));
		SourceReader<Food> foodCsvReader;
		try {
			foodCsvReader = new FoodCSVFileReader(projectPath + CSVFile.FOODCSV.value());
			fail("File not existed");
		} catch (FileNotFoundException e) {
			assertThat(e.getMessage().toString(), is(not(nullValue())));
		}
	}
	
	@Test
	public void readFoodCSVFileWithOneRecordTest() throws IOException, ParseException{
		Files.deleteIfExists(Paths.get(projectPath + CSVFile.FOODCSV.value()));
		
		Food food = new Food();
		
		food.setId(1);
		food.setName("Banana");
		food.setCategory("Fruit");
		food.setUnitPrice(5);
		
		List<Food> foods = new ArrayList<Food>();
		foods.add(food);
		
		SourceWriter<List<Food>> foodCsvFileWriter = new FoodCSVFileWriter(projectPath + CSVFile.FOODCSV.value());
		foodCsvFileWriter.write(foods);
		foodCsvFileWriter.close();
		
		SourceReader<Food> foodCsvFileReader = new FoodCSVFileReader(projectPath + CSVFile.FOODCSV.value());
		List<Food> resultList = foodCsvFileReader.read();
		foodCsvFileReader.close();
		
		assertThat(food.getId(),is(resultList.get(0).getId()));
		assertThat(food.getName(),is(resultList.get(0).getName()));
		assertThat(food.getCategory(),is(resultList.get(0).getCategory()));
		assertThat(food.getUnitPrice(),is(resultList.get(0).getUnitPrice()));
	}
	
	@Test
	public void readFoodCSVFileWithFourRecordsTest() throws IOException,ParseException{
		Files.deleteIfExists(Paths.get(projectPath + CSVFile.FOODCSV.value()));
		
		Food food1 = new Food();
		
		food1.setId(3);
		food1.setName("Tuna Sandwich");
		food1.setCategory("Main");
		food1.setUnitPrice(30);
		
		Food food2 = new Food();
		
		food2.setId(4);
		food2.setName("Caesar Salad");
		food2.setCategory("Appetizer");
		food2.setUnitPrice(20);
		
		Food food3 = new Food();
		
		food3.setId(6);
		food3.setName("Orange");
		food3.setCategory("Fruit");
		food3.setUnitPrice(5);
		
		Food food4 = new Food();
		
		food4.setId(11);
		food4.setName("Orange Juice");
		food4.setCategory("Drink");
		food4.setUnitPrice(13);
		
		List<Food> foods = new ArrayList<Food>();
		foods.add(food1);
		foods.add(food2);
		foods.add(food3);
		foods.add(food4);
		
		SourceWriter<List<Food>> foodCsvFileWriter = new FoodCSVFileWriter(projectPath + CSVFile.FOODCSV.value());
		foodCsvFileWriter.write(foods);
		foodCsvFileWriter.close();
		
		SourceReader<Food> foodCsvFileReader = new FoodCSVFileReader(projectPath + CSVFile.FOODCSV.value());
		List<Food> resultList = foodCsvFileReader.read();
		foodCsvFileReader.close();
		
		assertThat(foods.size(), is(resultList.size()));
		
		for(int i = 0; i < foods.size(); i++){
			assertThat(foods.get(i).getId(),is(resultList.get(i).getId()));
			assertThat(foods.get(i).getName(),is(resultList.get(i).getName()));
			assertThat(foods.get(i).getCategory(),is(resultList.get(i).getCategory()));
			assertThat(foods.get(i).getUnitPrice(),is(resultList.get(i).getUnitPrice()));
		}
	}
	
}

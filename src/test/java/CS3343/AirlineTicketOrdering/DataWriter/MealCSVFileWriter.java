package CS3343.AirlineTicketOrdering.DataWriter;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import CS3343.AirlineTicketOrdering.Model.Meal;

public class MealCSVFileWriter extends FileWriter<List<Meal>> {

	public MealCSVFileWriter(String path) throws IOException {
		super(path);
	}

	@Override
	public void write(List<Meal> Meals) throws IOException {
		ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.SIMPLE_STYLE);
		for (Meal Meal : Meals) {
			String dataString = ReflectionToStringBuilder.toString(Meal); 
			bufferedWriter.write(dataString);
			bufferedWriter.newLine();
		}
	}
	
	
}

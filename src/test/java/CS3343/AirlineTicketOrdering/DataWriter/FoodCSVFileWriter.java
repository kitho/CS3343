package CS3343.AirlineTicketOrdering.DataWriter;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import CS3343.AirlineTicketOrdering.Model.Food;

public class FoodCSVFileWriter extends FileWriter<List<Food>> {

	public FoodCSVFileWriter(String path) throws IOException {		
		super(path);
	}

	@Override
	public void write(List<Food> Foods) throws IOException {
		ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.SIMPLE_STYLE);
		for (Food Food : Foods) {
			String dataString = ReflectionToStringBuilder.toString(Food); 
			bufferedWriter.write(dataString);
			bufferedWriter.newLine();
		}
	}
	
	
}

package CS3343.AirlineTicketOrdering.DataWriter;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import CS3343.AirlineTicketOrdering.Model.Model;

public class ModelCSVFileWriter extends FileWriter<List<Model>> {

	public ModelCSVFileWriter(String path) throws IOException {
		super(path);
	}

	@Override
	public void write(List<Model> models) throws IOException {
		ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.SIMPLE_STYLE);
		for (Model model : models) {
			String dataString = ReflectionToStringBuilder.toString(model); 
			bufferedWriter.write(dataString);
			bufferedWriter.newLine();
		}
	}

}

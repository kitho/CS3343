package CS3343.AirlineTicketOrdering.DataReader.Impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import CS3343.AirlineTicketOrdering.DataReader.FileReader;
import CS3343.AirlineTicketOrdering.Model.Model;

public class ModelCSVFileReader extends FileReader<Model> {
	public ModelCSVFileReader(String path) throws FileNotFoundException {
		super(path);
	}

	@Override
	public List<Model> read() throws IOException, ParseException {

		List<Model> models = new ArrayList<Model>();

		String line;
		while ((line = bufferedReader.readLine()) != null) {
			String[] dataStr = line.split(",");
			Model model = new Model();
			model.setModel(dataStr[0]);
			model.setSeat(Integer.parseInt(dataStr[1]));
			model.setMaxBaggageKg(Integer.parseInt(dataStr[2]));
			models.add(model);
		}

		return models;
	}

}

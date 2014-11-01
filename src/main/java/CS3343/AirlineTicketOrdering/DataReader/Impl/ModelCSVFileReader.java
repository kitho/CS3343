package CS3343.AirlineTicketOrdering.DataReader.Impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import CS3343.AirlineTicketOrdering.DataReader.FileReader;
import CS3343.AirlineTicketOrdering.Model.Model;
import CS3343.AirlineTicketOrdering.Parser.Parser;

public class ModelCSVFileReader extends FileReader<Model> {
	public ModelCSVFileReader(String path) throws FileNotFoundException {
		super(path);
	}

	@Override
	public List<Model> read(Parser<Model> parser) throws IOException, ParseException {

		List<Model> models = new ArrayList<Model>();

		String line;
		while ((line = bufferedReader.readLine()) != null) {
			models.add(parser.parseString(line));
		}

		return models;
	}

}

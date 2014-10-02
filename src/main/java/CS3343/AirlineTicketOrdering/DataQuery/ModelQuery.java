package CS3343.AirlineTicketOrdering.DataQuery;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import CS3343.AirlineTicketOrdering.DataReader.SourceReader;
import CS3343.AirlineTicketOrdering.Model.Model;

public class ModelQuery {
	private List<Model> models;
	
	public ModelQuery(SourceReader<Model> modelReader) throws IOException, ParseException{
		models = modelReader.read();
	}

	public Model getModelByName(String name){
		for(Model model: models){
			if(model.getModel().equals(name)){
				return model;
			}
		}
		return null;
	}
	
	

}

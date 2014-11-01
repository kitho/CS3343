package CS3343.AirlineTicketOrdering.DataQuery;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import CS3343.AirlineTicketOrdering.DataReader.SourceReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.ModelCSVFileReader;
import CS3343.AirlineTicketOrdering.Model.Model;
import CS3343.AirlineTicketOrdering.Parser.Impl.ModelParser;

public class ModelQueryTest {
	
	@Before
	public void setUp() throws IOException{
	}
	
	@Test
	public void findNotExistModel() throws IOException, ParseException{
		SourceReader<Model> modelReader = mock(ModelCSVFileReader.class);
		List<Model> models = new ArrayList<Model>();
		when(modelReader.read(new ModelParser())).thenReturn(models);
		ModelQuery modelQuery = new ModelQuery(modelReader);
		Model model = modelQuery.getModelByName("12312412");
		assertThat(null, is(model));
	}
	
	@Test
	public void findModelByName()throws IOException, ParseException{
		SourceReader<Model> modelReader = mock(ModelCSVFileReader.class);
		List<Model> models = new ArrayList<Model>();
		Model model = new Model();
		model.setModel("A320");
		model.setSeat(180);
		model.setMaxBaggageKg(50000);	
		models.add(model);
		when(modelReader.read(new ModelParser())).thenReturn(models);
		
		ModelQuery modelQuery = new ModelQuery(modelReader);
		Model modelResult = modelQuery.getModelByName("A320");
		
		assertThat(50000, is(modelResult.getMaxBaggageKg()));
		assertThat(180, is(modelResult.getSeat()));
	}
}

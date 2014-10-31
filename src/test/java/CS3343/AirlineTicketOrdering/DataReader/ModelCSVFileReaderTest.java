package CS3343.AirlineTicketOrdering.DataReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import TestingTool.DataWriter.ModelCSVFileWriter;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import CS3343.AirlineTicketOrdering.DataReader.CSVFile;
import CS3343.AirlineTicketOrdering.DataReader.SourceReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.ModelCSVFileReader;
import CS3343.AirlineTicketOrdering.DataWriter.SourceWriter;
import CS3343.AirlineTicketOrdering.Model.Model;

public class ModelCSVFileReaderTest {
	private File projectPath;
	
	@Before
	public void setUp() throws IOException{
		projectPath = new File(".").getCanonicalFile();
	}
	

	@Test
	public void readModelCSVFileWhenFileNotExisted() throws IOException{
		Files.deleteIfExists(Paths.get(projectPath + CSVFile.MODELCSV.value()));
		SourceReader<Model> modelCSVReader;
		try {
			modelCSVReader = new ModelCSVFileReader(projectPath + CSVFile.MODELCSV.value());
			fail("File not existed");
		} catch (FileNotFoundException e) {
			assertThat(e.getMessage().toString(), is(not(nullValue())));
		}
	}
	
	@Test
	public void readModelCSVFileWithOneRecordTest() throws IOException, ParseException{
		Files.deleteIfExists(Paths.get(projectPath + CSVFile.MODELCSV.value()));
		
		Model model = new Model();
		
		model.setModel("A320");
		model.setSeat(180);
		model.setMaxBaggageKg(5000);
		
		List<Model> models = new ArrayList<Model>();
		models.add(model);
		
		SourceWriter<List<Model>> modelCsvFileWriter = new ModelCSVFileWriter(projectPath + CSVFile.MODELCSV.value());
		modelCsvFileWriter.write(models);
		modelCsvFileWriter.close();
		
		SourceReader<Model> modelCsvFileReader = new ModelCSVFileReader(projectPath + CSVFile.MODELCSV.value());
		List<Model> resultList = modelCsvFileReader.read();
		modelCsvFileReader.close();
		
		assertThat(model.getMaxBaggageKg(),is(resultList.get(0).getMaxBaggageKg()));
		assertThat(model.getModel(),is(resultList.get(0).getModel()));
		assertThat(model.getSeat(),is(resultList.get(0).getSeat()));
	}
	
	@Test
	public void readModelCSVFileWithMoreThanOneRecordsTest() throws IOException,ParseException{
		Files.deleteIfExists(Paths.get(projectPath + CSVFile.MODELCSV.value()));
		
		Model model1 = new Model();
		model1.setModel("A320");
		model1.setSeat(180);
		model1.setMaxBaggageKg(5000);
		
		Model model2 = new Model();
		model2.setModel("737-900");
		model2.setSeat(215);
		model2.setMaxBaggageKg(5925);

		List<Model> models = new ArrayList<Model>();
		models.add(model1);
		models.add(model2);
		
		SourceWriter<List<Model>> modelCSVFileWriter = new ModelCSVFileWriter(projectPath + CSVFile.MODELCSV.value());
		modelCSVFileWriter.write(models);
		modelCSVFileWriter.close();
		
		SourceReader<Model> modelCSVFileReader = new ModelCSVFileReader(projectPath + CSVFile.MODELCSV.value());
		List<Model> resultList = modelCSVFileReader.read();
		modelCSVFileReader.close();
		
		assertThat(models.size(), is(resultList.size()));
		
		for(int i = 0; i < models.size(); i++){
			assertThat(models.get(i).getMaxBaggageKg(),is(resultList.get(i).getMaxBaggageKg()));
			assertThat(models.get(i).getModel(),is(resultList.get(i).getModel()));
			assertThat(models.get(i).getSeat(),is(resultList.get(i).getSeat()));
		}
	}
	
}

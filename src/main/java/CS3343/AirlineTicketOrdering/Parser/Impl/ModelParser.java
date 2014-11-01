package CS3343.AirlineTicketOrdering.Parser.Impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import CS3343.AirlineTicketOrdering.Model.Model;
import CS3343.AirlineTicketOrdering.Parser.Parser;

public class ModelParser implements Parser<Model> {

	public Model parseString(String line) throws ParseException {
		String[] dataStr = line.split(",");
		Model model = new Model();
		model.setModel(dataStr[0]);
		model.setSeat(Integer.parseInt(dataStr[1]));
		model.setMaxBaggageKg(Integer.parseInt(dataStr[2]));
		
		return model;
	}

	public String parseObject(Model model) {
		List<String> dataList = new ArrayList<String>();
		dataList.add(model.getModel());
		dataList.add(String.valueOf(model.getSeat()));
		dataList.add(String.valueOf(model.getMaxBaggageKg()));

		String line = StringUtils.join(dataList, ",");
		
		return line;
	}

}

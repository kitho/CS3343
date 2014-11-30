package CS3343.AirlineTicketOrdering.DataQuery;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

import CS3343.AirlineTicketOrdering.DataReader.SourceReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.BaggagePlanCSVFileReader;
import CS3343.AirlineTicketOrdering.DataWriter.SourceWriter;
import CS3343.AirlineTicketOrdering.Model.AirlineCompany;
import CS3343.AirlineTicketOrdering.Model.BaggagePlan;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Model.Route;
import CS3343.AirlineTicketOrdering.Parser.Parser;
import CS3343.AirlineTicketOrdering.Parser.Impl.AirlineCompanyParser;
import CS3343.AirlineTicketOrdering.Parser.Impl.BaggagePlanParser;
import CS3343.AirlineTicketOrdering.Parser.Impl.FlightParser;

public class BaggageQuery {
	private List<BaggagePlan> plans = null;

	/**
	 * Baggage Query constructor
	 * @param baggagePlanCSVFileReader
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public BaggageQuery(BaggagePlanCSVFileReader baggagePlanCSVFileReader) throws IOException, ParseException {
		//Read Baggage Plan
		Parser<BaggagePlan> parser = new BaggagePlanParser();
		plans = baggagePlanCSVFileReader.read(parser);

	}
	

	/**
	 * Find all plans
	 * @return list of baggagePlan
	 */
	public List<BaggagePlan> findAllPlans(){
		return plans;
	}
	
	
	
	/**
	 * Find a plan by location from and to
	 * @param from
	 * @param to
	 * @return BaggagePlan
	 */
	public BaggagePlan findPlanByLocation(String from, String to){
		BaggagePlan plan;
		for(int i = 0; i < plans.size(); i++){
			plan = plans.get(i);
			List<String> placeFroms = plan.getPlaceFroms();
			List<String> placeTos = plan.getPlaceTos();

			for(int y = 0; y < placeFroms.size(); y++){
				if(placeFroms.get(y).equals(from) && placeTos.get(y).equals(to)){
					return plan;
				}
			}
		}
		return null;
	}

	/**
	 * Get a list of plans
	 * @return list of BaggagePlan
	 */
	public List<BaggagePlan> getPlans() {
		return plans;
	}

	/**
	 * Set a set of plans
	 * @param plans
	 */
	public void setPlans(List<BaggagePlan> plans) {
		this.plans = plans;
	}
	
	
}

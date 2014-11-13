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

public class RouteQuery {
	private List<Route> routes;

	public RouteQuery(BaggagePlanCSVFileReader baggagePlanCSVFileReader) {
		//Read Baggage Plan
		Parser<BaggagePlan> parser = new BaggagePlanParser();
		List<BaggagePlan> plans = null;
		try {
			plans = baggagePlanCSVFileReader.read(parser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//***TESTING ONLY. SHOULD BE READ ROUTES FROM CSV***//
		int baggagePlanID = 0;  //SHOULD READ THIS ID FROM ROUTE'S CSV
		routes = new ArrayList<Route>();
		Route route1 = new Route();
		route1.setBaggagePlan(plans.get(baggagePlanID));
		routes.add(route1);
	}
	
	public List<Route> findAllRoutes(){
		return routes;
	}
}

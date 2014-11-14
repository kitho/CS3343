package CS3343.AirlineTicketOrdering.DataQuery;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import CS3343.AirlineTicketOrdering.DataReader.SourceReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.AirlineCompanyCSVFileReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.BaggagePlanCSVFileReader;
import CS3343.AirlineTicketOrdering.Model.AirlineCompany;
import CS3343.AirlineTicketOrdering.Model.BaggagePlan;

public class BaggageQueryTest {

	private BaggageQuery baggageQuery;
	private BaggagePlan plan_1, plan_2;
	private List<BaggagePlan> plans;
	
	@Before
	public void initialEnvironment(){
		plans = new ArrayList<BaggagePlan>();
		
		
		//Set plan's supporting location
		plan_1 = new BaggagePlan();
		List<String> placeFroms = new ArrayList<String>();
		placeFroms.add("Hong Kong");
		List<String> placeTos = new ArrayList<String>();
		placeTos.add("Taiwan");
		plan_1.setPlaceFroms(placeFroms);
		plan_1.setPlaceTos(placeTos);
		
		plan_2 = new BaggagePlan();
		placeFroms = new ArrayList<String>();
		placeFroms.add("Hong Kong");
		placeTos = new ArrayList<String>();
		placeTos.add("Japan");
		plan_2.setPlaceFroms(placeFroms);
		plan_2.setPlaceTos(placeTos);
		
		plans.add(plan_1);
		plans.add(plan_2);
		
		baggageQuery = new BaggageQuery(mock(BaggagePlanCSVFileReader.class));
		baggageQuery.setPlans(plans);
	}
	
	@Test
	public void findPlanByLocationWithCorrectData() {
		BaggagePlan plan = baggageQuery.findPlanByLocation("Hong Kong", "Japan");
		assertEquals(plan_2, plan);
	}
	
	@Test
	public void findPlanByLocationWithIncorrectData() {
		BaggagePlan plan = baggageQuery.findPlanByLocation("ABC", "EFG");
		assertEquals(null, plan);
	}
	
	@Test
	public void findAllPlans() {
		List<BaggagePlan> returnedPlans = baggageQuery.findAllPlans();
		assertEquals(plans, returnedPlans);
	}

}

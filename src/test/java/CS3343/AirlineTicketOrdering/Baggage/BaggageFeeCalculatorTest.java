package CS3343.AirlineTicketOrdering.Baggage;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import CS3343.AirlineTicketOrdering.Baggage.Temp.BaggagePlan;
import CS3343.AirlineTicketOrdering.Baggage.Temp.FlightClass;
import CS3343.AirlineTicketOrdering.Baggage.Temp.Route;

public class BaggageFeeCalculatorTest {
	private BaggagePlan baggagePlan;
	private FlightClass flightClass;
	private Route route;
	private BaggageFeeCalculator calculator;
	
	@Before
	public void initialEnvironment(){
		//***1. Initial flight class...
		flightClass = new FlightClass("EconomyClass");
		
		//***2. Initial baggage plan...
		baggagePlan = new BaggagePlan();
		
		//2.1 Initial supported unit
		ArrayList<String> unit = new ArrayList<String>();
		unit.add("KG");
		unit.add("Piece");
		unit.add("Inch");
		
		//2.2 Initial free units 
		Map<FlightClass, Map<String, Float>> freeUnits = new HashMap<FlightClass, Map<String, Float>>();
		Map<String, Float> freeUnitDetails = new HashMap<String, Float>();
		freeUnitDetails.put(unit.get(0), 20f);
		freeUnits.put(flightClass, freeUnitDetails);
		
		//2.3 Initial free units for sporting equipments
		Map<String, Map<String, Float>> extraFreeUnitForSportingEquipments = new HashMap<String, Map<String, Float>>();
		Map<String, Float> freeSEUnitDetails = new HashMap<String, Float>();
		freeSEUnitDetails.put(unit.get(0), 10f);
		extraFreeUnitForSportingEquipments.put("Bicycles", freeSEUnitDetails);
		extraFreeUnitForSportingEquipments.put("Golf equipment", freeSEUnitDetails);
		
		//2.4 Initial extra fee per unit
		Map<String, Float> extraFeePerUnit = new HashMap<String, Float>();
		extraFeePerUnit.put(unit.get(0), 100f);
		
		//2.5 Initial pet fee
		Map<String, Float> petFee = new HashMap<String, Float>();
		petFee.put(unit.get(0), 200f);
		
		//2.6 Set value for baggage plan
		baggagePlan.setUnit(unit);
		baggagePlan.setFreeUnit(freeUnits);
		baggagePlan.setExtraFreeUnitForSportingEquipments(extraFreeUnitForSportingEquipments);
		baggagePlan.setExtraFeePerUnit(extraFeePerUnit);
		baggagePlan.setPetFee(petFee);
		
		//***3. Initial route...
		route = new Route();
		route.setBaggagePlan(baggagePlan);
		
		//***4. Get instance of calculator...
		calculator = BaggageFeeCalculator.getInstance();
	}
	
	@Test
	public void calculateBaggageFeeWithoutEquipmentAndPet(){
		//1. Initial passenger data...
		ArrayList<String> units = baggagePlan.getUnit();
				
		//1.1 Passenger take n KG baggage
		Map<String, Float> unitNumForBaggage = new HashMap<String, Float>();
		unitNumForBaggage.put(units.get(0),30f);
		
		//1.2 NO sporting  equipment
		ArrayList<String> sportingEquipments = new ArrayList<String>();
		
		//1.3 NO pet
		Map<String, Float> unitNumForPet = new HashMap<String, Float>();

		float fee = calculator.calBaggageFeeForOnePassenger(
				route, 
				flightClass, 
				unitNumForBaggage, 
				sportingEquipments, 
				unitNumForPet);

		assertEquals(1000f, fee, 0);
	}
	
	@Test
	public void calculateBaggageFeeWithEquipment(){
		//1. Initial passenger data...
		ArrayList<String> units = baggagePlan.getUnit();
				
		//1.1 Passenger take 50KG baggage
		Map<String, Float> unitNumForBaggage = new HashMap<String, Float>();
		unitNumForBaggage.put(units.get(0),50f);
		
		//1.2 The baggage include bicycles
		ArrayList<String> sportingEquipments = new ArrayList<String>();
		sportingEquipments.add("Bicycles");
		
		//1.3 NO pet
		Map<String, Float> unitNumForPet = new HashMap<String, Float>();

		float fee = calculator.calBaggageFeeForOnePassenger(
				route, 
				flightClass, 
				unitNumForBaggage, 
				sportingEquipments, 
				unitNumForPet);

		assertEquals(2000f, fee, 0);
	}
	
	
	@Test
	public void calculateBaggageFeeWithPet(){
		//1. Initial passenger data...
		ArrayList<String> units = baggagePlan.getUnit();
				
		//1.1 Passenger take 30KG baggage
		Map<String, Float> unitNumForBaggage = new HashMap<String, Float>();
		unitNumForBaggage.put(units.get(0),30f);
		
		//1.2 NO Equipment
		ArrayList<String> sportingEquipments = new ArrayList<String>();
		
		//1.3 The passenger take 10KG pets
		Map<String, Float> unitNumForPet = new HashMap<String, Float>();
		unitNumForPet.put(units.get(0), 10f);

		float fee = calculator.calBaggageFeeForOnePassenger(
				route, 
				flightClass, 
				unitNumForBaggage, 
				sportingEquipments, 
				unitNumForPet);

		assertEquals(3000f, fee, 0);
	}
	
	@Test
	public void calculateBaggageFeeWithEquipmentAndPet() {
		//1. Initial passenger data...
		ArrayList<String> units = baggagePlan.getUnit();
		
		//1.1 Passenger take 40KG baggage
		Map<String, Float> unitNumForBaggage = new HashMap<String, Float>();
		unitNumForBaggage.put(units.get(0),40f);
		
		//1.2 The baggage include bicycles
		ArrayList<String> sportingEquipments = new ArrayList<String>();
		sportingEquipments.add("Bicycles");
		
		//1.3 The passenger take 10KG pets
		Map<String, Float> unitNumForPet = new HashMap<String, Float>();
		unitNumForPet.put(units.get(0), 10f);
		
		float fee = calculator.calBaggageFeeForOnePassenger(
				route, 
				flightClass, 
				unitNumForBaggage, 
				sportingEquipments, 
				unitNumForPet);

		assertEquals(3000f, fee, 0);
	}

}
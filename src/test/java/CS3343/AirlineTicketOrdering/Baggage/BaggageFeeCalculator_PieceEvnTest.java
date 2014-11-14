package CS3343.AirlineTicketOrdering.Baggage;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import CS3343.AirlineTicketOrdering.Model.BaggagePlan;
import CS3343.AirlineTicketOrdering.Model.Route;

public class BaggageFeeCalculator_PieceEvnTest {
	private BaggagePlan baggagePlan;
	private String flightClass;
	private Route route;
	private BaggageFeeCalculator calculator;
	
	@Before
	public void initialEnvironment(){
		//***1. Initial flight class...
		flightClass = "EconomyClass";
		
		//***2. Initial baggage plan...
		baggagePlan = new BaggagePlan();
		
		//2.1 Initial supported unit
		ArrayList<String> unit = new ArrayList<String>();
		unit.add("KG");
		unit.add("Piece");
		unit.add("Inch");
		
		//2.2 Initial free units 
		Map<String, Map<String, Float>> freeUnits = new HashMap<String, Map<String, Float>>();
		Map<String, Float> freeUnitDetails = new HashMap<String, Float>();
		freeUnitDetails.put(unit.get(1), 1f);
		freeUnits.put(flightClass, freeUnitDetails);
			
		//2.3 Initial free units for sporting equipments
		Map<String, Map<String, Float>> extraFreeUnitForSportingEquipments = new HashMap<String, Map<String, Float>>();
		Map<String, Float> freeSEUnitDetails = new HashMap<String, Float>();
		freeSEUnitDetails.put(unit.get(1), 1f);
		extraFreeUnitForSportingEquipments.put("Bicycles", freeSEUnitDetails);
		extraFreeUnitForSportingEquipments.put("Golf equipment", freeSEUnitDetails);
		
		//2.4 Initial extra fee per unit
		Map<String, Float> extraFeePerUnit = new HashMap<String, Float>();
		extraFeePerUnit.put(unit.get(1), 1000f);
		
		//2.5 Initial extra extra fee for so many / very large baggage
		//*Normally for Piece Unit
		Map<String, Map<String, Float>> extraExtraFeeForLevels = new HashMap<String, Map<String, Float>>();
		//Define 3 levels' extra extra fee for the flight class
		Map<String, Float> extraExtraFees = new HashMap<String, Float>();
		extraExtraFees.put("1", 100f);
		extraExtraFees.put("2", 100f);
		extraExtraFees.put("3", 400f);
		extraExtraFees.put("4", 400f);
		extraExtraFeeForLevels.put(flightClass, extraExtraFees);
		//Define condition ranges for each level
		//Level 1. 25-30KG
		Map<String, Map<String, ArrayList<Float>>> extraExtraFeeCondtions = new HashMap<String, Map<String, ArrayList<Float>>>();
		ArrayList<Float> unitRangeNum = new ArrayList<Float>();
		unitRangeNum.add(25f);
		unitRangeNum.add(30f);
		Map<String, ArrayList<Float>> unitRange = new HashMap<String, ArrayList<Float>>();
		unitRange.put(unit.get(0), unitRangeNum);
		extraExtraFeeCondtions.put("1", unitRange);
		//Level 2. 40-50 Inch
		unitRangeNum = new ArrayList<Float>();
		unitRangeNum.add(40f);
		unitRangeNum.add(50f);
		unitRange = new HashMap<String, ArrayList<Float>>();
		unitRange.put(unit.get(2), unitRangeNum);
		extraExtraFeeCondtions.put("2", unitRange);
		//Level 3. > 31 KG
		unitRangeNum = new ArrayList<Float>();
		unitRangeNum.add(31f);
		unitRangeNum.add(9999f);
		unitRange = new HashMap<String, ArrayList<Float>>();
		unitRange.put(unit.get(0), unitRangeNum);
		extraExtraFeeCondtions.put("3", unitRange);
		//Level 4. > 51 Inch
		unitRangeNum = new ArrayList<Float>();
		unitRangeNum.add(51f);
		unitRangeNum.add(9999f);
		unitRange = new HashMap<String, ArrayList<Float>>();
		unitRange.put(unit.get(2), unitRangeNum);
		extraExtraFeeCondtions.put("4", unitRange);
		
		//2.6 Initial pet fee
		Map<String, Float> petFee = new HashMap<String, Float>();
		petFee.put(unit.get(1), 1500f);
		
		//2.7 Initial extra extra PET fee for so many / very large pet
		Map<String, Map<String, Float>> extraPetFeeForLevels = new HashMap<String, Map<String, Float>>();
		//Define 3 levels' extra PET fee for the flight class
		Map<String, Float> extraPetFees = new HashMap<String, Float>();
		extraPetFees.put("1", 100f);
		extraPetFees.put("2", 100f);
		extraPetFees.put("3", 400f);
		extraPetFees.put("4", 400f);
		extraPetFeeForLevels.put(flightClass, extraPetFees);
		//Define condition ranges for each level
		//Level 1. 25-30KG
		Map<String, Map<String, ArrayList<Float>>> extraPetFeeCondtions = new HashMap<String, Map<String, ArrayList<Float>>>();
		unitRangeNum = new ArrayList<Float>();
		unitRangeNum.add(25f);
		unitRangeNum.add(30f);
		unitRange = new HashMap<String, ArrayList<Float>>();
		unitRange.put(unit.get(0), unitRangeNum);
		extraPetFeeCondtions.put("1", unitRange);
		//Level 2. 40-50 Inch
		unitRangeNum = new ArrayList<Float>();
		unitRangeNum.add(40f);
		unitRangeNum.add(50f);
		unitRange = new HashMap<String, ArrayList<Float>>();
		unitRange.put(unit.get(2), unitRangeNum);
		extraPetFeeCondtions.put("2", unitRange);
		//Level 3. > 31 KG
		unitRangeNum = new ArrayList<Float>();
		unitRangeNum.add(31f);
		unitRangeNum.add(9999f);
		unitRange = new HashMap<String, ArrayList<Float>>();
		unitRange.put(unit.get(0), unitRangeNum);
		extraPetFeeCondtions.put("3", unitRange);
		//Level 4. > 51 Inch
		unitRangeNum = new ArrayList<Float>();
		unitRangeNum.add(51f);
		unitRangeNum.add(9999f);
		unitRange = new HashMap<String, ArrayList<Float>>();
		unitRange.put(unit.get(2), unitRangeNum);
		extraPetFeeCondtions.put("4", unitRange);
		
		//2.8 Set value for baggage plan
		baggagePlan.setUnit(unit);
		baggagePlan.setFreeUnit(freeUnits);
		baggagePlan.setExtraFreeUnitForSportingEquipments(extraFreeUnitForSportingEquipments);
		baggagePlan.setExtraFeePerUnit(extraFeePerUnit);
		baggagePlan.setPetFee(petFee);
		baggagePlan.setExtraExtraFeeForLevel(extraExtraFeeForLevels);
		baggagePlan.setExtraExtraFeeCondtion(extraExtraFeeCondtions);
		baggagePlan.setExtraExtraPetFeeForLevel(extraPetFeeForLevels);
		baggagePlan.setExtraExtraPetFeeCondtion(extraPetFeeCondtions);
		
		
		//***4. Get instance of calculator...
		calculator = new BaggageFeeCalculator();
	}
	
	@Test
	public void calculateBaggageFeeWithoutEquipmentAndPet(){
		//1. Initial passenger data...
		ArrayList<String> units = baggagePlan.getUnit();
				
		//1.1 Passenger take n KG baggage
		Map<String, Float> unitNumForBaggage = new HashMap<String, Float>();
		unitNumForBaggage.put(units.get(0),30f);
		unitNumForBaggage.put(units.get(1),1f);
		unitNumForBaggage.put(units.get(2),40f);
		
		//1.2 NO sporting  equipment
		ArrayList<String> sportingEquipments = new ArrayList<String>();
		
		//1.3 NO pet
		Map<String, Float> unitNumForPet = new HashMap<String, Float>();

		float fee = calculator.calBaggageFee(
				baggagePlan, 
				flightClass, 
				unitNumForBaggage, 
				sportingEquipments, 
				unitNumForPet,
				1);

		assertEquals(100f, fee, 0);
	}
	
	@Test
	public void calculateBaggageFeeWithEquipment(){
		//1. Initial passenger data...
		ArrayList<String> units = baggagePlan.getUnit();
				
		//1.1 Passenger take 30KG baggage
		Map<String, Float> unitNumForBaggage = new HashMap<String, Float>();
		unitNumForBaggage.put(units.get(0),30f);
		unitNumForBaggage.put(units.get(1),3f);
		unitNumForBaggage.put(units.get(2),40f);
		
		//1.2 The baggage include bicycles
		ArrayList<String> sportingEquipments = new ArrayList<String>();
		sportingEquipments.add("Bicycles");
		
		//1.3 NO pet
		Map<String, Float> unitNumForPet = new HashMap<String, Float>();

		float fee = calculator.calBaggageFee(
				baggagePlan, 
				flightClass, 
				unitNumForBaggage, 
				sportingEquipments, 
				unitNumForPet,
				1);

		assertEquals(1000f, fee, 0);
	}
	
	
	@Test
	public void calculateBaggageFeeWithPet(){
		//1. Initial passenger data...
		ArrayList<String> units = baggagePlan.getUnit();
				
		//1.1 Passenger take n KG baggage
		Map<String, Float> unitNumForBaggage = new HashMap<String, Float>();
		unitNumForBaggage.put(units.get(0),20f);
		unitNumForBaggage.put(units.get(1),1f);
		unitNumForBaggage.put(units.get(2),20f);
		
		//1.2 NO Equipment
		ArrayList<String> sportingEquipments = new ArrayList<String>();
		
		//1.3 The passenger take n pets
		Map<String, Float> unitNumForPet = new HashMap<String, Float>();
		unitNumForPet.put(units.get(0), 30f);
		unitNumForPet.put(units.get(1), 2f);
		unitNumForPet.put(units.get(2), 40f);

		float fee = calculator.calBaggageFee(
				baggagePlan, 
					flightClass, 
					unitNumForBaggage, 
					sportingEquipments, 
					unitNumForPet,
					1);

		assertEquals(3000f, fee, 0);
	}
	
	@Test
	public void calculateBaggageFeeWithEquipmentAndPet() {
		//1. Initial passenger data...
		ArrayList<String> units = baggagePlan.getUnit();
		
		//1.1 Passenger take baggage
		Map<String, Float> unitNumForBaggage = new HashMap<String, Float>();
		unitNumForBaggage.put(units.get(0),40f);
		unitNumForBaggage.put(units.get(1),2f);
		unitNumForBaggage.put(units.get(2),40f);
		
		//1.2 The baggage include bicycles
		ArrayList<String> sportingEquipments = new ArrayList<String>();
		sportingEquipments.add("Bicycles");
		
		//1.3 The passenger take a pets
		Map<String, Float> unitNumForPet = new HashMap<String, Float>();
		unitNumForPet.put(units.get(0), 30f);
		unitNumForPet.put(units.get(1), 1f);
		unitNumForPet.put(units.get(2), 40f);
		
		float fee = calculator.calBaggageFee(
				baggagePlan, 
				flightClass, 
				unitNumForBaggage, 
				sportingEquipments, 
				unitNumForPet,
				1);

		assertEquals(1600f, fee, 0);
	}

}

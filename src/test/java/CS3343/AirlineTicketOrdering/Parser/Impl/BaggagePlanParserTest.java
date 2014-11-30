package CS3343.AirlineTicketOrdering.Parser.Impl;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import CS3343.AirlineTicketOrdering.Model.BaggagePlan;
import CS3343.AirlineTicketOrdering.Parser.Parser;

public class BaggagePlanParserTest {

	private BaggagePlan baggagePlan = null;
	
	@Before
	public void initEnv(){
		//===Ready a baggage plan===//
		//***1. Initial flight class...
		String flightClass = "Economy Class";
		
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
		freeUnitDetails.put(unit.get(0), 20f);
		freeUnits.put(flightClass, freeUnitDetails);
		
		//2.3 Initial free units for sporting equipments
		Map<String, Map<String, Float>> extraFreeUnitForSportingEquipments = new HashMap<String, Map<String, Float>>();
		Map<String, Float> freeSEUnitDetails = new HashMap<String, Float>();
		freeSEUnitDetails.put(unit.get(0), 10f);
		extraFreeUnitForSportingEquipments.put("Bicycles equipment", freeSEUnitDetails);
		extraFreeUnitForSportingEquipments.put("Golf equipment", freeSEUnitDetails);
		
		//2.4 Initial extra fee per unit
		Map<String, Float> extraFeePerUnit = new HashMap<String, Float>();
		extraFeePerUnit.put(unit.get(0), 100f);
		
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
		petFee.put(unit.get(0), 30f);
		
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
		
		List<String> placeFroms = new ArrayList<String>();
		placeFroms.add("Hong Kong");
		List<String> placeTos = new ArrayList<String>();
		placeTos.add("Taiwan");
		
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
		baggagePlan.setPlaceFroms(placeFroms);
		baggagePlan.setPlaceTos(placeTos);
		//===End of ready baggage plan object===//
	}
	
	@Test
	public void parserBaggageCSVFormatString() throws ParseException {
		//===Ready a string for a baggage plan===//
		List<String> baggageCSVFormatStrings = new ArrayList<String>();
		baggageCSVFormatStrings.add("S,1,Economy Class,KG,20,Bicycles equipment,KG,10,KG,100,Economy Class,1,100,KG,25,30,KG,30,Economy Class,1,100,KG,25,30,Hong Kong,Taiwan");
		baggageCSVFormatStrings.add(",,,,,Golf equipment,KG,10,,,,2,100,Inch,40,50,,,,2,100,Inch,40,50,,");
		baggageCSVFormatStrings.add(",,,,,,,,,,,3,400,KG,31,9999,,,,3,400,KG,31,9999,,");
		baggageCSVFormatStrings.add("E,,,,,,,,,,,4,400,Inch,51,9999,,,,4,400,Inch,51,9999,,");
		
		//Parse the string
		Parser<BaggagePlan> parser = new BaggagePlanParser();
		BaggagePlan plan = null;
		for(int i = 0; i < baggageCSVFormatStrings.size(); i++){
			plan = parser.parseString(baggageCSVFormatStrings.get(i));
		}
		
		
		//Compare the object and parsered string
		assertEquals(baggagePlan.getAvailSportingEquipments(), plan.getAvailSportingEquipments());
		assertEquals(baggagePlan.getExtraExtraFeeCondtion(), plan.getExtraExtraFeeCondtion());
		assertEquals(baggagePlan.getExtraExtraFeeForLevel(), plan.getExtraExtraFeeForLevel());
		assertEquals(baggagePlan.getExtraExtraPetFeeCondtion(), plan.getExtraExtraPetFeeCondtion());
		assertEquals(baggagePlan.getExtraExtraPetFeeForLevel(), plan.getExtraExtraPetFeeForLevel());
		assertEquals(baggagePlan.getExtraFeePerUnit(), plan.getExtraFeePerUnit());
		assertEquals(baggagePlan.getExtraFreeUnitForSportingEquipments(), plan.getExtraFreeUnitForSportingEquipments());
		assertEquals(baggagePlan.getFreeUnit(), plan.getFreeUnit());
		assertEquals(baggagePlan.getPetFee(), plan.getPetFee());
		assertEquals(baggagePlan.getPlaceFroms(), plan.getPlaceFroms());
		assertEquals(baggagePlan.getPlaceTos(), plan.getPlaceTos());
		assertEquals(baggagePlan.getUnit(), plan.getUnit());
	}
	
	
	@Test
	public void parserBaggageEmptyString() throws ParseException {
		//===Ready a string for a baggage plan===//
		List<String> baggageCSVFormatStrings = new ArrayList<String>();
		baggageCSVFormatStrings.add("S,,,,,,,,,,,,,,,,,,,,,,,,,");
		baggageCSVFormatStrings.add(",,,,,,,,,,,,,,,,,,,,,,,,,");
		baggageCSVFormatStrings.add(",,,,,,,,,,,,,,,,,,,,,,,,,");
		baggageCSVFormatStrings.add("E,,,,,,,,,,,,,,,,,,,,,,,,,");
		
		//Parse the string
		Parser<BaggagePlan> parser = new BaggagePlanParser();
		BaggagePlan plan = null;
		for(int i = 0; i < baggageCSVFormatStrings.size(); i++){
			plan = parser.parseString(baggageCSVFormatStrings.get(i));
		}
		
		//Compare the object and parsered string
		assertEquals(new ArrayList<String>(), plan.getAvailSportingEquipments());
		assertEquals(new HashMap<String, Map<String, ArrayList<Float>>>(), plan.getExtraExtraFeeCondtion());
		assertEquals(new HashMap<String, Map<String, Float>>(), plan.getExtraExtraFeeForLevel());
		assertEquals(new HashMap<String, Map<String, ArrayList<Float>>>(), plan.getExtraExtraPetFeeCondtion());
		assertEquals(new HashMap<String, Map<String, Float>>(), plan.getExtraExtraPetFeeForLevel());
		assertEquals(new HashMap<String, Float>(), plan.getExtraFeePerUnit());
		assertEquals(new HashMap<String, Map<String, Float>>(), plan.getExtraFreeUnitForSportingEquipments());
		assertEquals(new HashMap<String, Map<String, Float>>(), plan.getFreeUnit());
		assertEquals(new HashMap<String, Float>(), plan.getPetFee());
		assertEquals(new ArrayList<String>(), plan.getPlaceFroms());
		assertEquals(new ArrayList<String>(), plan.getPlaceTos());
		assertEquals(baggagePlan.getUnit(), plan.getUnit());
	}
	
	@Test
	public void parserBaggagePartOfEmptyString() throws ParseException {
		//===Ready a string for a baggage plan===//
		List<String> baggageCSVFormatStrings = new ArrayList<String>();
		baggageCSVFormatStrings.add("S,1,,,20,,,10,KG,,,1,100,KG,,30,,30,,1,,,25,,,Taiwan");
		baggageCSVFormatStrings.add(",,,,,,KG,10,,,,2,100,,,50,20,,,2,,,40,50,,");
		baggageCSVFormatStrings.add(",,,,,,,,,,,3,400,,31,,,,,3,400,,,9999,,");
		baggageCSVFormatStrings.add("E,,,,,,,,,,,4,,,,9999,,,,4,,,,,,T");
			
		//Parse the string
		Parser<BaggagePlan> parser = new BaggagePlanParser();
		BaggagePlan plan = null;
		for(int i = 0; i < baggageCSVFormatStrings.size(); i++){
			plan = parser.parseString(baggageCSVFormatStrings.get(i));
		}
		
		//Compare the object and parsered string
		assertEquals(new ArrayList<String>(), plan.getAvailSportingEquipments());
		assertEquals(new HashMap<String, Map<String, ArrayList<Float>>>(), plan.getExtraExtraFeeCondtion());
		assertEquals(new HashMap<String, Map<String, Float>>(), plan.getExtraExtraFeeForLevel());
		assertEquals(new HashMap<String, Map<String, ArrayList<Float>>>(), plan.getExtraExtraPetFeeCondtion());
		assertEquals(new HashMap<String, Map<String, Float>>(), plan.getExtraExtraPetFeeForLevel());
		assertEquals(new HashMap<String, Float>(), plan.getExtraFeePerUnit());
		assertEquals(new HashMap<String, Map<String, Float>>(), plan.getExtraFreeUnitForSportingEquipments());
		assertEquals(new HashMap<String, Map<String, Float>>(), plan.getFreeUnit());
		assertEquals(new HashMap<String, Float>(), plan.getPetFee());
		assertEquals(new ArrayList<String>(), plan.getPlaceFroms());
		assertEquals(new ArrayList<String>(), plan.getPlaceTos());
		assertEquals(baggagePlan.getUnit(), plan.getUnit());
	}

}

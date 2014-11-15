package CS3343.AirlineTicketOrdering.View.Impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import CS3343.AirlineTicketOrdering.Model.BaggagePlan;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

public class InputBaggageDataViewTest {

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
	public void testViewWithNoContentSession() throws IOException {
		BufferedReader bufferedReader = org.mockito.Mockito.mock(BufferedReader.class);
		Mockito.when(bufferedReader.readLine()).thenReturn("20")
		.thenReturn("1").thenReturn("25").thenReturn("2").thenReturn("0")
		.thenReturn("0").thenReturn("0");
		
		View view = new InputBaggageDataView(bufferedReader);
		Session session = Session.getInstance();
		session.removeAttribute("baggagePlan");
		session.removeAttribute("flightClass");
		session.setAttribute("numberOfPassengers",1);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		view.display(session);
		assertEquals("No suitable baggage plan.\r\n", outContent.toString());
		outContent.reset();
		
	}
	
	@Test
	public void testViewWithSession() throws IOException {
		BufferedReader bufferedReader = org.mockito.Mockito.mock(BufferedReader.class);
		Mockito.when(bufferedReader.readLine()).thenReturn("20 1 25")
			.thenReturn("2").thenReturn("0 0 0");

		View view = new InputBaggageDataView(bufferedReader);
		Session session = Session.getInstance();
		session.setAttribute("baggagePlan", baggagePlan);
		session.setAttribute("flightClass", "Economy Class");
		session.setAttribute("numberOfPassengers",1);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		view.display(session);
		assertEquals("\n=====Baggage Plan For Economy Class=====\n1. Each passenger can enjoy free 20.0 KG(s) (Can be shared with other tickets purcahsed at the same time.)\n2. Basic fee per KG $100.0\n3. Extra fee if average exceed following items:\n\t25.0 KG(s)\t-\t30.0 KG(s)\t$100.0\n\t40.0 Inch(s)\t-\t50.0 Inch(s)\t$100.0\n\t>=31.0 KG(s)\t\t\t\t$400.0\n\t>=51.0 Inch(s)\t\t\t\t$400.0\n\n4. Basic pet fee per KG $30.0\n5. Extra pet if average exceed following items:\n\t25.0 KG(s)\t-\t30.0 KG(s)\t$100.0\n\t40.0 Inch(s)\t-\t50.0 Inch(s)\t$100.0\n\t>=31.0 KG(s)\t\t\t\t$400.0\n\t>=51.0 Inch(s)\t\t\t\t$400.0\n\r\n\nInput your baggage total kg, total piece and total size for #1 passengers (Format: 99.9 99 99.9): \nEnjoy Free Sporting Equipments Shipping:\n1. Bicycles equipment\t\t- 10.0 KG(s)\r\n2. Golf equipment\t\t- 10.0 KG(s)\r\n3. No Sporting Equipment\r\nPlease select one sporting equipments to enjoy free unit for #1 passagers: \nInput your pet total kg, total piece and total size for #1 passengers (Format: 99.9 99 99.9): \nCalculated Baggage Fee Info:\r\nYou can enjoy       \t{KG=20.0}\r\nYour remaining unit \t{KG=10.0}\r\nBasic Baggage Fee   \t$0.0\r\nExtra Baggage Fee   \t$0.0\r\nBasic Pet Fee       \t$0.0\r\nExtra Pet Fee       \t$0.0\r\nTotal Baggage Fee   \t$0.0\r\n",
				outContent.toString());
		outContent.reset();
	}
}

	
	
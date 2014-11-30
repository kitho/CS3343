package CS3343.AirlineTicketOrdering.View.Impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import CS3343.AirlineTicketOrdering.Util.LineSeparatorUtil;
import CS3343.AirlineTicketOrdering.View.View;

public class InputBaggageDataViewTest {

private BaggagePlan baggagePlan = null;
private String separator = LineSeparatorUtil.newLine();
	
	@Before
	public void initEnv(){
		//===Ready a baggage plan===//
		//***1. Initial flight class...
		String flightClass = "Economy Class";
		
		//***2. Initial baggage plan...
		baggagePlan = new BaggagePlan_stub();
		
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
		BufferedReader bufferedReader = mock(BufferedReader.class);
		Mockito.when(bufferedReader.readLine()).thenReturn("20")
		.thenReturn("1").thenReturn("25").thenReturn("2").thenReturn("0")
		.thenReturn("0").thenReturn("0");
		
		View view = new InputBaggageDataView(bufferedReader);
		Session session = mock(Session.class);
		when(session.getAttribute("baggagePlan")).thenReturn(null);
		when(session.getAttribute("flightClass")).thenReturn("Economy Class");
		when(session.getAttribute("numberOfTicket")).thenReturn(1);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		view.display(session);
		assertEquals("No suitable baggage plan." + separator, outContent.toString());
		outContent.reset();
		
	}
	
	@Test
	public void testViewWithSession() throws IOException {
		BufferedReader bufferedReader = org.mockito.Mockito.mock(BufferedReader.class);
		Mockito.when(bufferedReader.readLine()).thenReturn("20 1 25")
			.thenReturn("2").thenReturn("0 0 0");

		View view = new InputBaggageDataView(bufferedReader);
		Session session = mock(Session.class);
		when(session.getAttribute("baggagePlan")).thenReturn(baggagePlan);
		when(session.getAttribute("flightClass")).thenReturn("Economy Class");
		when(session.getAttribute("numberOfTicket")).thenReturn(1);
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		view.display(session);
		assertEquals(""+separator+"=====Baggage Plan For Economy Class====="+separator+"1. Each passenger can enjoy free 20.0 KG(s) (Can be shared with other tickets purcahsed at the same time.)"+separator+"2. Basic fee per KG $100.0"+separator+"3. Extra fee if average exceed following items:"+separator+"\t25.0 KG(s)\t-\t30.0 KG(s)\t$100.0"+separator+"\t40.0 Inch(s)\t-\t50.0 Inch(s)\t$100.0"+separator+"\t>=31.0 KG(s)\t\t\t\t$400.0"+separator+"\t>=51.0 Inch(s)\t\t\t\t$400.0"+separator+""+separator+"4. Basic pet fee per KG $30.0"+separator+"5. Extra pet if average exceed following items:"+separator+"\t25.0 KG(s)\t-\t30.0 KG(s)\t$100.0"+separator+"\t40.0 Inch(s)\t-\t50.0 Inch(s)\t$100.0"+separator+"\t>=31.0 KG(s)\t\t\t\t$400.0"+separator+"\t>=51.0 Inch(s)\t\t\t\t$400.0"+separator+""+separator+""+separator+"Input your baggage total kg, total piece and total size for #1 passengers (Format: 99.9 99 99.9): "+separator+"Enjoy Free Sporting Equipments Shipping:"+separator+"1. Bicycles equipment\t\t- 10.0 KG(s)"+separator+"2. Golf equipment\t\t- 10.0 KG(s)"+separator+"3. No Sporting Equipment"+separator+"Please select one sporting equipments to enjoy free unit for #1 passagers: "+separator+"Input your pet total kg, total piece and total size for #1 passengers (Format: 99.9 99 99.9): ",
				is(outContent.toString()));
		outContent.reset();
	}
	
	@Test
	public void testViewWithIncorrectEnglishLetterInput() throws IOException {
		BufferedReader bufferedReader = org.mockito.Mockito.mock(BufferedReader.class);
		Mockito.when(bufferedReader.readLine()).thenReturn("w w w").thenReturn("20 1 25")
			.thenReturn("999").thenReturn("2").thenReturn("w w w").thenReturn("0 0 0");

		View view = new InputBaggageDataView(bufferedReader);
		Session session = mock(Session.class);
		when(session.getAttribute("baggagePlan")).thenReturn(baggagePlan);
		when(session.getAttribute("flightClass")).thenReturn("Economy Class");
		when(session.getAttribute("numberOfTicket")).thenReturn(1);

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		view.display(session);
		assertEquals(""+separator+"=====Baggage Plan For Economy Class====="+separator+"1. Each passenger can enjoy free 20.0 KG(s) (Can be shared with other tickets purcahsed at the same time.)"+separator+"2. Basic fee per KG $100.0"+separator+"3. Extra fee if average exceed following items:"+separator+"\t25.0 KG(s)\t-\t30.0 KG(s)\t$100.0"+separator+"\t40.0 Inch(s)\t-\t50.0 Inch(s)\t$100.0"+separator+"\t>=31.0 KG(s)\t\t\t\t$400.0"+separator+"\t>=51.0 Inch(s)\t\t\t\t$400.0"+separator+""+separator+"4. Basic pet fee per KG $30.0"+separator+"5. Extra pet if average exceed following items:"+separator+"\t25.0 KG(s)\t-\t30.0 KG(s)\t$100.0"+separator+"\t40.0 Inch(s)\t-\t50.0 Inch(s)\t$100.0"+separator+"\t>=31.0 KG(s)\t\t\t\t$400.0"+separator+"\t>=51.0 Inch(s)\t\t\t\t$400.0"+separator+""+separator+""+separator+"Input your baggage total kg, total piece and total size for #1 passengers (Format: 99.9 99 99.9): Please input positive numerics with format (99.9 99 99.9)."+separator+""+separator+"Input your baggage total kg, total piece and total size for #1 passengers (Format: 99.9 99 99.9): "+separator+"Enjoy Free Sporting Equipments Shipping:"+separator+"1. Bicycles equipment\t\t- 10.0 KG(s)"+separator+"2. Golf equipment\t\t- 10.0 KG(s)"+separator+"3. No Sporting Equipment"+separator+"Please select one sporting equipments to enjoy free unit for #1 passagers: Please input a numeric options from 1 to 3"+separator+"Please select one sporting equipments to enjoy free unit for #1 passagers: "+separator+"Input your pet total kg, total piece and total size for #1 passengers (Format: 99.9 99 99.9): Please input positive numerics with format (99.9 99 99.9)."+separator+""+separator+"Input your pet total kg, total piece and total size for #1 passengers (Format: 99.9 99 99.9): ",
				outContent.toString());
		outContent.reset();
	}
	
	@Test
	public void testViewWithIncorrectNegativeInput() throws IOException {
		BufferedReader bufferedReader = org.mockito.Mockito.mock(BufferedReader.class);
		Mockito.when(bufferedReader.readLine()).thenReturn("-2 -2 -2").thenReturn("20 1 25")
			.thenReturn("999").thenReturn("2").thenReturn("-2 -2 -2").thenReturn("0 0 0");

		View view = new InputBaggageDataView(bufferedReader);
		Session session = mock(Session.class);
		when(session.getAttribute("baggagePlan")).thenReturn(baggagePlan);
		when(session.getAttribute("flightClass")).thenReturn("Economy Class");
		when(session.getAttribute("numberOfTicket")).thenReturn(1);
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		view.display(session);
		assertEquals(""+LineSeparatorUtil.newLine()+"=====Baggage Plan For Economy Class====="+LineSeparatorUtil.newLine()+"1. Each passenger can enjoy free 20.0 KG(s) (Can be shared with other tickets purcahsed at the same time.)"+LineSeparatorUtil.newLine()+"2. Basic fee per KG $100.0"+LineSeparatorUtil.newLine()+"3. Extra fee if average exceed following items:"+LineSeparatorUtil.newLine()+"\t25.0 KG(s)\t-\t30.0 KG(s)\t$100.0"+LineSeparatorUtil.newLine()+"\t40.0 Inch(s)\t-\t50.0 Inch(s)\t$100.0"+LineSeparatorUtil.newLine()+"\t>=31.0 KG(s)\t\t\t\t$400.0"+LineSeparatorUtil.newLine()+"\t>=51.0 Inch(s)\t\t\t\t$400.0"+LineSeparatorUtil.newLine()+""+LineSeparatorUtil.newLine()+"4. Basic pet fee per KG $30.0"+LineSeparatorUtil.newLine()+"5. Extra pet if average exceed following items:"+LineSeparatorUtil.newLine()+"\t25.0 KG(s)\t-\t30.0 KG(s)\t$100.0"+LineSeparatorUtil.newLine()+"\t40.0 Inch(s)\t-\t50.0 Inch(s)\t$100.0"+LineSeparatorUtil.newLine()+"\t>=31.0 KG(s)\t\t\t\t$400.0"+LineSeparatorUtil.newLine()+"\t>=51.0 Inch(s)\t\t\t\t$400.0"+LineSeparatorUtil.newLine()+""+LineSeparatorUtil.newLine()+""+LineSeparatorUtil.newLine()+"Input your baggage total kg, total piece and total size for #1 passengers (Format: 99.9 99 99.9): Please input positive numerics with format (99.9 99 99.9)."+LineSeparatorUtil.newLine()+""+LineSeparatorUtil.newLine()+"Input your baggage total kg, total piece and total size for #1 passengers (Format: 99.9 99 99.9): "+LineSeparatorUtil.newLine()+"Enjoy Free Sporting Equipments Shipping:"+LineSeparatorUtil.newLine()+"1. Bicycles equipment\t\t- 10.0 KG(s)"+LineSeparatorUtil.newLine()+"2. Golf equipment\t\t- 10.0 KG(s)"+LineSeparatorUtil.newLine()+"3. No Sporting Equipment"+LineSeparatorUtil.newLine()+"Please select one sporting equipments to enjoy free unit for #1 passagers: Please input a numeric options from 1 to 3"+LineSeparatorUtil.newLine()+"Please select one sporting equipments to enjoy free unit for #1 passagers: "+LineSeparatorUtil.newLine()+"Input your pet total kg, total piece and total size for #1 passengers (Format: 99.9 99 99.9): Please input positive numerics with format (99.9 99 99.9)."+LineSeparatorUtil.newLine()+""+LineSeparatorUtil.newLine()+"Input your pet total kg, total piece and total size for #1 passengers (Format: 99.9 99 99.9): ",
				outContent.toString());
		outContent.reset();
	}
	
	
	class BaggagePlan_stub extends BaggagePlan{
		//Unit
		private ArrayList<String> unit;												//KG, Baggage, Size, etc.
		
		//For Passenger's Baggage
		private Map<String, Map<String, Float>> freeUnit;
		private Map<String, Map<String, Float>> extraFreeUnitForSportingEquipments;	//<NameOfEquipments, <Unit, Num>>
		private Map<String, Float> extraFeePerUnit;									//<Unit, Fee>
		
		//Extra extra fee
		private Map<String, Map<String, Float>> extraExtraFeeForLevel;			//<FlightClass, <Level, Fee>>
		private Map<String, Map<String, ArrayList<Float>>> extraExtraFeeCondtion;	//<Level, <Unit, Num Range>>
		
		//For Pet Placed in Baggage
		private Map<String, Float> petFee;											//<Unit,Pat Fee>
		
		//For Pet If Exceed Normal Fee's Weight or Size
		private Map<String, Map<String, Float>> extraExtraPetFeeForLevel;		//<FlightClass, <Level, Fee>>
		private Map<String, Map<String, ArrayList<Float>>> extraExtraPetFeeCondtion;//<Level, <Unit, Num Range>>
		
		//For place
		private List<String> placeFroms = new ArrayList<String>();
		private List<String> placeTos = new ArrayList<String>();
		
		public BaggagePlan_stub(){
			super();
		}

		public void setUnit(ArrayList<String> unit) {
			this.unit = unit;
		}


		public ArrayList<String> getUnit() {
			return unit;
		}
		
		public Map<String, Map<String, Float>> getFreeUnit() {
			return freeUnit;
		}

		public void setFreeUnit(Map<String, Map<String, Float>> freeUnit) {
			this.freeUnit = freeUnit;
		}

		public Map<String, Map<String, Float>> getExtraFreeUnitForSportingEquipments() {
			return extraFreeUnitForSportingEquipments;
		}

		public void setExtraFreeUnitForSportingEquipments(
				Map<String, Map<String, Float>> extraFreeUnitForSportingEquipments) {
			this.extraFreeUnitForSportingEquipments = extraFreeUnitForSportingEquipments;
		}

		public Map<String, Float> getExtraFeePerUnit() {
			return extraFeePerUnit;
		}

		public void setExtraFeePerUnit(Map<String, Float> extraFeePerUnit) {
			this.extraFeePerUnit = extraFeePerUnit;
		}

		public Map<String, Map<String, Float>> getExtraExtraFeeForLevel() {
			return extraExtraFeeForLevel;
		}

		public void setExtraExtraFeeForLevel(
				Map<String, Map<String, Float>> extraExtraFeeForLevel) {
			this.extraExtraFeeForLevel = extraExtraFeeForLevel;
		}

		public Map<String, Map<String, ArrayList<Float>>> getExtraExtraFeeCondtion() {
			return extraExtraFeeCondtion;
		}

		public void setExtraExtraFeeCondtion(
				Map<String, Map<String, ArrayList<Float>>> extraExtraFeeCondtion) {
			this.extraExtraFeeCondtion = extraExtraFeeCondtion;
		}

		public Map<String, Float> getPetFee() {
			return petFee;
		}

		public void setPetFee(Map<String, Float> petFee) {
			this.petFee = petFee;
		}

		public Map<String, Map<String, Float>> getExtraExtraPetFeeForLevel() {
			return extraExtraPetFeeForLevel;
		}

		public void setExtraExtraPetFeeForLevel(
				Map<String, Map<String, Float>> extraExtraPetFeeForLevel) {
			this.extraExtraPetFeeForLevel = extraExtraPetFeeForLevel;
		}

		public Map<String, Map<String, ArrayList<Float>>> getExtraExtraPetFeeCondtion() {
			return extraExtraPetFeeCondtion;
		}

		public void setExtraExtraPetFeeCondtion(
				Map<String, Map<String, ArrayList<Float>>> extraExtraPetFeeCondtion) {
			this.extraExtraPetFeeCondtion = extraExtraPetFeeCondtion;
		}
		

		public void setPlaceFroms(List<String> placeFroms) {
			this.placeFroms = placeFroms;
		}

		public void setPlaceTos(List<String> placeTos) {
			this.placeTos = placeTos;
		}

		public ArrayList<String> getAvailSportingEquipments(){
			ArrayList<String> availSportingEquipments = new ArrayList<String>();
			for(String nameKey : extraFreeUnitForSportingEquipments.keySet()){
				availSportingEquipments.add(nameKey);
			}
			return availSportingEquipments;
		}
		
		
	}

}

	
	

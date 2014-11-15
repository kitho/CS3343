package CS3343.AirlineTicketOrdering.Controller.Impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import CS3343.AirlineTicketOrdering.Controller.AirlineTicketOrderingController;
import CS3343.AirlineTicketOrdering.Model.BaggagePlan;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

public class BaggageCalculationControllerTest {
	private BaggagePlan baggagePlan;
	private Map<String, Float> unitNumForBaggage = new HashMap<String, Float>();
	private String flightClass;

	@Before
	public void initEnv(){
	
		//***1. Initial flight class...
		flightClass = "Economy Class";
		
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
		extraFreeUnitForSportingEquipments.put("Bicycles", freeSEUnitDetails);
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

		unitNumForBaggage.put("KG", 20f);
		unitNumForBaggage.put("Piece", 20f);
		unitNumForBaggage.put("Inch", 20f);
	}
	
	@Test
	public void testExecute() throws Exception {
		Session session = mock(Session.class);
		when(session.getAttribute("baggagePlan")).thenReturn(baggagePlan);
		when(session.getAttribute("flightClass")).thenReturn("Economy Class");
		when(session.getAttribute("numberOfTicket")).thenReturn(1);
		when(session.getAttribute("unitNumForBaggage")).thenReturn(unitNumForBaggage);
		when(session.getAttribute("unitNumForPet")).thenReturn(unitNumForBaggage);
		when(session.getAttribute("sportingEquipments")).thenReturn(new ArrayList<String>());
		
		View view = mock(View.class);
		
		AirlineTicketOrderingController next = mock(AirlineTicketOrderingController.class); 
		AirlineTicketOrderingController baggageCalculationController = new BaggageCalculationController(session, view);
		
		baggageCalculationController.setNext(next);
		baggageCalculationController.execute();
		
		Map<String, Float> expectedOrgFreeUnit = new HashMap<String, Float>();
		expectedOrgFreeUnit.put("KG", 20f);
		
		Map<String, Float> expectedRemainingFreeUnit = new HashMap<String, Float>();
		expectedRemainingFreeUnit.put("KG", 0f);
		
		verify(session, times(1)).setAttribute("orgFreeUnit", expectedOrgFreeUnit);
		verify(session, times(1)).setAttribute("remainingFreeUnit", expectedRemainingFreeUnit);
		verify(session, times(1)).setAttribute("basicBaggageFee", 0f);
		verify(session, times(1)).setAttribute("extraBaggageFee", 0f);
		verify(session, times(1)).setAttribute("petFee", -600f);
		verify(session, times(1)).setAttribute("extraPetFee", 0f);
		verify(session, times(1)).setAttribute("totalFee", -600f);
		verify(view, times(1)).display(session);
		verify(next,times(1)).execute();
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
	
		
		
	}

}

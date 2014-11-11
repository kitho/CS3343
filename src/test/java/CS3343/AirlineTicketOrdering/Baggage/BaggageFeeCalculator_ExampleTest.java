package CS3343.AirlineTicketOrdering.Baggage;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import CS3343.AirlineTicketOrdering.Model.BaggagePlan;
import CS3343.AirlineTicketOrdering.Model.FlightClass;
import CS3343.AirlineTicketOrdering.Model.Route;

public class BaggageFeeCalculator_ExampleTest {
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
		
		//2.5 Initial extra extra fee (NO Extra Extra Fee for KG Unit)
		Map<FlightClass, Map<String, Float>> extraExtraFeeForLevels = new HashMap<FlightClass, Map<String, Float>>();
		Map<String, Map<String, ArrayList<Float>>> extraExtraFeeCondtions = new HashMap<String, Map<String, ArrayList<Float>>>();
		
		//2.6 Initial pet fee
		Map<String, Float> petFee = new HashMap<String, Float>();
		petFee.put(unit.get(0), 30f);
		
		//2.7 Initial extra extra PET fee (NO Extra Extra Pet Fee for KG Unit)
		Map<FlightClass, Map<String, Float>> extraPetFeeForLevels = new HashMap<FlightClass, Map<String, Float>>();
		Map<String, Map<String, ArrayList<Float>>> extraPetFeeCondtions = new HashMap<String, Map<String, ArrayList<Float>>>();
		
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
		
		//***3. Initial route...
		route = new Route();
		route.setBaggagePlan(baggagePlan);
		
		//***4. Get instance of calculator...
		calculator = new BaggageFeeCalculator();
	}
	
	
	@Test
	public void exampleTest() {
		//1. Initial passenger data...
		ArrayList<String> units = baggagePlan.getUnit();
		
		//System input ready
		Scanner in = new Scanner(System.in);
		
		//Number of Passengers
		System.out.println("Input number of passengers:");
		int numOfPassengers = in.nextInt();
		
		//1.1 Passenger take n baggage
		ArrayList<Float> kgList = new ArrayList<Float>();
		ArrayList<Integer> pieceList = new ArrayList<Integer>();
		ArrayList<Float> sizeList = new ArrayList<Float>();
		for(int i = 0; i < numOfPassengers; i++){
			System.out.println("Input your baggage total kg, total piece and total size for #" + (i+1) + " passengers");
			kgList.add(in.nextFloat());
			pieceList.add(in.nextInt());
			sizeList.add(in.nextFloat());
		}
		
		//Sum all value of baggage
		float sumKG = 0, sumPiece = 0, sumSize = 0;
		for (Float num : kgList) 
			sumKG += num;
		for (Integer num : pieceList) 
			sumPiece += num;
		for (Float num : sizeList) 
			sumSize += num;
		Map<String, Float> unitNumForBaggage = new HashMap<String, Float>();
		unitNumForBaggage.put(units.get(0),sumKG);
		unitNumForBaggage.put(units.get(1),sumPiece);
		unitNumForBaggage.put(units.get(2),sumSize);
		
		//1.2 The baggage include bicycles
		ArrayList<String> sportingEquipments = new ArrayList<String>();
		//sportingEquipments.add("Bicycles");
		
		//1.3 The passenger take n pets
		ArrayList<Float> petKgList = new ArrayList<Float>();
		ArrayList<Integer> petPieceList = new ArrayList<Integer>();
		ArrayList<Float> petSizeList = new ArrayList<Float>();
		for(int i = 0; i < numOfPassengers; i++){
			System.out.println("Input your pet total kg, total piece and total size for #" + (i+1) + " passengers");
			petKgList.add(in.nextFloat());
			petPieceList.add(in.nextInt());
			petSizeList.add(in.nextFloat());
		}
	
		//Sum all value of pet
		float sumPetKG = 0, sumPetPiece = 0, sumPetSize = 0;
		for (Float num : petKgList) 
			sumPetKG += num;
		for (Integer num : petPieceList) 
			sumPetPiece += num;
		for (Float num : petSizeList) 
			sumPetSize += num;
		Map<String, Float> unitNumForPet = new HashMap<String, Float>();
		unitNumForPet.put(units.get(0), sumPetKG);
		unitNumForPet.put(units.get(1), sumPetPiece);
		unitNumForPet.put(units.get(2), sumPetSize);
		
		float fee = calculator.calBaggageFee(
				route, 
				flightClass, 
				unitNumForBaggage, 
				sportingEquipments, 
				unitNumForPet,
				numOfPassengers);

		System.out.println("You can enjoy " + calculator.getOrgFreeUnit());
		System.out.println("Your remaining unit " + calculator.getRemainingFreeUnit());
		System.out.println("Basic Baggage Fee " + calculator.getExtraBaggageFee());
		System.out.println("Extra Baggage Fee " + calculator.getExtraExtraBaggageFee());
		System.out.println("Basic Pet Fee " + calculator.getPetFee());
		System.out.println("Extra Pet Fee " + calculator.getExtraPatFee());
		System.out.println("Total Fee " + calculator.getResultFee());
		
		assertEquals(1900f, fee, 0);
	}

}

package CS3343.AirlineTicketOrdering.Baggage;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import CS3343.AirlineTicketOrdering.DataReader.CSVFileReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.BaggagePlanCSVFileReader;
import CS3343.AirlineTicketOrdering.Model.BaggagePlan;
import CS3343.AirlineTicketOrdering.Model.FlightClass;
import CS3343.AirlineTicketOrdering.Model.Route;
import CS3343.AirlineTicketOrdering.Parser.Parser;
import CS3343.AirlineTicketOrdering.Parser.Impl.BaggagePlanParser;

public class BaggageFeeCalculator_ExampleTest {
	private BaggagePlan baggagePlan;
	private String flightClass;
	private Route route;
	private BaggageFeeCalculator calculator;
	private BaggageRulePrinter rulePrinter;
	
	@Before
	public void initialEnvironment(){
		//Init baggage plan into route
		List<BaggagePlan> planList = null;
		try {
			CSVFileReader<BaggagePlan> reader = new BaggagePlanCSVFileReader("datasource/BaggagePlan.csv");
			Parser<BaggagePlan> parser = new BaggagePlanParser();
			planList = reader.read(parser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Get the first of the list of baggage plans
		baggagePlan = planList.get(1);
		
		flightClass = FlightClass.ECONOMY_CLASS;
		
		//***4. Get instance of calculator...
		calculator = new BaggageFeeCalculator();
		rulePrinter = new BaggageRulePrinter();
	}
	
	
	@Test
	public void exampleTest() {
		//System input ready
		Scanner in = new Scanner(System.in);
		
		//Print rule
		String rule = rulePrinter.printRule(baggagePlan, flightClass);
		System.out.println(rule);
		
		//Input number of Passengers
		System.out.println("Input number of passengers:");
		int numOfPassengers = in.nextInt();
		
		//1.1 Input baggage data for N passengers
		ArrayList<Float> kgList = new ArrayList<Float>();
		ArrayList<Integer> pieceList = new ArrayList<Integer>();
		ArrayList<Float> sizeList = new ArrayList<Float>();
		for(int i = 0; i < numOfPassengers; i++){
			System.out.println("Input your baggage total kg, total piece and total size for #" + (i+1) + " passengers");
			kgList.add(in.nextFloat());
			pieceList.add(in.nextInt());
			sizeList.add(in.nextFloat());
		}
		
		//1.2 Sum all value of baggage for sharing free unit
		ArrayList<String> units = baggagePlan.getUnit();
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
		
		//2.1 Input sporting equipments for N passengers
		//2.2 Get the list of sporting equipments from the plan
		Map<String, Map<String, Float>> sportingEquipmentFreeUnits = baggagePlan.getExtraFreeUnitForSportingEquipments();
		ArrayList<String> sportingEquipmentList = baggagePlan.getAvailSportingEquipments();
		
		//2.3 Select sporting equipments to enjoy free unit
		ArrayList<String> sportingEquipments = new ArrayList<String>();
		for(int y = 0; y < numOfPassengers; y++){
			System.out.println("Please select sporting equipments to enjoy free unit for #" + (y+1) + " passagers.");
			for(int i = 0; i < sportingEquipmentList.size(); i++){
				Map<String, Float> SEFreeUnit = sportingEquipmentFreeUnits.get(sportingEquipmentList.get(i));
				String freeUnitStr = "";
				for(String unitKey : SEFreeUnit.keySet())
					freeUnitStr += SEFreeUnit.get(unitKey) + " " + unitKey + "(s)";
				System.out.println((i+1) + ". " + sportingEquipmentList.get(i) + "\t\t- " + freeUnitStr);
			}
			System.out.println((sportingEquipmentList.size()+1) + ". No Sporting Equipment");
			int indexOfSelectSE = in.nextInt();
			if(indexOfSelectSE != sportingEquipmentList.size()+1)
				sportingEquipments.add(sportingEquipmentList.get(indexOfSelectSE-1));
		}
		
		//3.1 Input pet info for N passengers
		ArrayList<Float> petKgList = new ArrayList<Float>();
		ArrayList<Integer> petPieceList = new ArrayList<Integer>();
		ArrayList<Float> petSizeList = new ArrayList<Float>();
		for(int i = 0; i < numOfPassengers; i++){
			System.out.println("Input your pet total kg, total piece and total size for #" + (i+1) + " passengers");
			petKgList.add(in.nextFloat());
			petPieceList.add(in.nextInt());
			petSizeList.add(in.nextFloat());
		}
	
		//3.2 Sum all value of pet for sharing free unit
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
		
		//4. Calculate free
		float totalFee = calculator.calBaggageFee(
				baggagePlan, 
				flightClass, 
				unitNumForBaggage, 
				sportingEquipments, 
				unitNumForPet,
				numOfPassengers);

		//5. Get parts of calculated fee
		System.out.println("You can enjoy       \t" + calculator.getOrgFreeUnit());
		System.out.println("Your remaining unit \t" + calculator.getRemainingFreeUnit());
		System.out.println("Basic Baggage Fee   \t$" + calculator.getExtraBaggageFee());
		System.out.println("Extra Baggage Fee   \t$" + calculator.getExtraExtraBaggageFee());
		System.out.println("Basic Pet Fee       \t$" + calculator.getPetFee());
		System.out.println("Extra Pet Fee       \t$" + calculator.getExtraPetFee());
		System.out.println("Total Fee           \t$" + calculator.getResultFee());
		
		assertEquals(1900f, totalFee, 0);
	}

}

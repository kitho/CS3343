package CS3343.AirlineTicketOrdering.View.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import CS3343.AirlineTicketOrdering.Baggage.BaggageFeeCalculator;
import CS3343.AirlineTicketOrdering.Baggage.BaggageRulePrinter;
import CS3343.AirlineTicketOrdering.DataReader.CSVFileReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.BaggagePlanCSVFileReader;
import CS3343.AirlineTicketOrdering.Model.BaggagePlan;
import CS3343.AirlineTicketOrdering.Model.Route;
import CS3343.AirlineTicketOrdering.Parser.Parser;
import CS3343.AirlineTicketOrdering.Parser.Impl.BaggagePlanParser;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

public class InputBaggageDataView implements View {
	private BaggagePlan baggagePlan;
	private String flightClass;
	private Route route;
	private BaggageFeeCalculator calculator;
	private BaggageRulePrinter rulePrinter;
	
	public void display(Session response) {
		//Get needed data from session
		baggagePlan = (BaggagePlan)response.getAttribute("baggagePlan");
		if(baggagePlan == null){
			System.out.println("No suitable baggage plan.");
			return;
		}
		flightClass = (String)response.getAttribute("flightClass");
		
		//***4. Get instance of calculator...
		calculator = new BaggageFeeCalculator();
		rulePrinter = new BaggageRulePrinter();
		
		//System input ready
		Scanner in = new Scanner(System.in);
		
		//Print rule
		String rule = rulePrinter.printRule(baggagePlan, flightClass);
		System.out.println("\n" + rule);
		
		//Input number of Passengers
		int numOfPassengers = 0;
		while(true){
			System.out.print("\nInput number of passengers: ");
			String input = in.next();
			try{
				numOfPassengers = Integer.parseInt(input);
				if(numOfPassengers <= 0)
					System.out.println("Please input a positive numeric.");
				else
					break;
			}catch(Exception e){
				System.out.println("Please input a positive numeric.");
			}
		}
		
		//1.1 Input baggage data for N passengers
		ArrayList<Float> kgList = new ArrayList<Float>();
		ArrayList<Integer> pieceList = new ArrayList<Integer>();
		ArrayList<Float> sizeList = new ArrayList<Float>();
		for(int i = 0; i < numOfPassengers; i++){
			float kg = 0, size = 0;
			int piece = 0;
			while(true){
				System.out.print("\nInput your baggage total kg, total piece and total size for #" + (i+1) + " passengers (e.g. 20 1 50): ");
				String inputKG = in.next();
				String inputPiece = in.next();
				String inputSize = in.next();
				try{
					kg = Float.parseFloat(inputKG);
					piece = Integer.parseInt(inputPiece);
					size = Float.parseFloat(inputSize);
					if(kg < 0 || piece < 0 || size < 0)
						System.out.println("Please input positive numerics.");
					else{
						kgList.add(kg);
						pieceList.add(piece);
						sizeList.add(size);
						break;
					}
				}catch(Exception e){
					System.out.println("Please input positive numerics.");
				}
			}
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
		System.out.print("\nEnjoy Free Sporting Equipments Shipping:\n");
		ArrayList<String> sportingEquipments = new ArrayList<String>();
		for(int y = 0; y < numOfPassengers; y++){
			for(int i = 0; i < sportingEquipmentList.size(); i++){
				Map<String, Float> SEFreeUnit = sportingEquipmentFreeUnits.get(sportingEquipmentList.get(i));
				String freeUnitStr = "";
				for(String unitKey : SEFreeUnit.keySet())
					freeUnitStr += SEFreeUnit.get(unitKey) + " " + unitKey + "(s)";
				System.out.println((i+1) + ". " + sportingEquipmentList.get(i) + "\t\t- " + freeUnitStr);
			}
			System.out.println((sportingEquipmentList.size()+1) + ". No Sporting Equipment");
			
			int indexOfSelectSE = 0;
			while(true){
				System.out.print("Please select one sporting equipments to enjoy free unit for #" + (y+1) + " passagers: ");
				String input = in.next();
				try{
					indexOfSelectSE = Integer.parseInt(input);
					if(indexOfSelectSE <= 0 || indexOfSelectSE > sportingEquipmentList.size() + 1)
						System.out.println("Please input a numeric options from 1 to " + (sportingEquipmentList.size() + 1));
					else
						break;
				}catch(Exception e){
					System.out.println("Please input a numeric options from 1 to " + (sportingEquipmentList.size() + 1));
				}
			}
			
			
			if(indexOfSelectSE != sportingEquipmentList.size()+1)
				sportingEquipments.add(sportingEquipmentList.get(indexOfSelectSE-1));
		}
		
		//3.1 Input pet info for N passengers
		ArrayList<Float> petKgList = new ArrayList<Float>();
		ArrayList<Integer> petPieceList = new ArrayList<Integer>();
		ArrayList<Float> petSizeList = new ArrayList<Float>();
		for(int i = 0; i < numOfPassengers; i++){
			float kg = 0, size = 0;
			int piece = 0;
			while(true){
				System.out.print("\nInput your pet total kg, total piece and total size for #" + (i+1) + " passengers (e.g. 12 1 50): ");
				String inputKG = in.next();
				String inputPiece = in.next();
				String inputSize = in.next();
				try{
					kg = Float.parseFloat(inputKG);
					piece = Integer.parseInt(inputPiece);
					size = Float.parseFloat(inputSize);
					if(kg < 0 || piece < 0 || size < 0)
						System.out.println("Please input positive numerics.");
					else{
						petKgList.add(kg);
						petPieceList.add(piece);
						petSizeList.add(size);
						break;
					}
				}catch(Exception e){
					System.out.println("Please input positive numerics.");
				}
			}
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
		
		//5. Set fee into session
		response.setAttribute("baggageFee", calculator.getResultFee());

		//6. Get parts of calculated fee
		System.out.println("\nCalculated Baggage Fee Info:");
		System.out.println("You can enjoy       \t" + calculator.getOrgFreeUnit());
		System.out.println("Your remaining unit \t" + calculator.getRemainingFreeUnit());
		System.out.println("Basic Baggage Fee   \t$" + calculator.getExtraBaggageFee());
		System.out.println("Extra Baggage Fee   \t$" + calculator.getExtraExtraBaggageFee());
		System.out.println("Basic Pet Fee       \t$" + calculator.getPetFee());
		System.out.println("Extra Pet Fee       \t$" + calculator.getExtraPetFee());
		System.out.println("Total Baggage Fee   \t$" + calculator.getResultFee());
	}

}

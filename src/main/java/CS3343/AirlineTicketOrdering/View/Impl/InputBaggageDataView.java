package CS3343.AirlineTicketOrdering.View.Impl;

import java.io.BufferedReader;
import java.io.IOException;
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
	private BufferedReader bufferedReader;
	
	public InputBaggageDataView(BufferedReader bufferedReader){
		this.bufferedReader = bufferedReader;
	}
	
	public void display(Session response) throws IOException {
		//Get needed data from session
		baggagePlan = (BaggagePlan)response.getAttribute("baggagePlan");
		if(baggagePlan == null){
			System.out.println("No suitable baggage plan.");
			return;
		}
		
		String baggagePlanRule = (String) response.getAttribute("baggagePlanRule");
		int numOfPassengers = (int)response.getAttribute("amountOfPassenger");
		
		System.out.println("\n" + baggagePlanRule);
		
		//1.1 Input baggage data for N passengers
		ArrayList<Float> kgList = new ArrayList<Float>();
		ArrayList<Integer> pieceList = new ArrayList<Integer>();
		ArrayList<Float> sizeList = new ArrayList<Float>();
		for(int i = 0; i < numOfPassengers; i++){
			float kg = 0, size = 0;
			int piece = 0;
			while(true){
				System.out.print("\nInput your baggage total kg, total piece and total size for #" + (i+1) + " passengers (Format: 99.9 99 99.9): ");
				try{
					String input = bufferedReader.readLine();
					String[] inputs = input.split(" ");
					String inputKG = inputs[0];
					String inputPiece = inputs[1];
					String inputSize = inputs[2];
					kg = Float.parseFloat(inputKG);
					piece = Integer.parseInt(inputPiece);
					size = Float.parseFloat(inputSize);
					if(kg < 0 || piece < 0 || size < 0)
						System.out.println("Please input positive numerics with format (99.9 99 99.9).");
					else{
						kgList.add(kg);
						pieceList.add(piece);
						sizeList.add(size);
						break;
					}
				}catch(Exception e){
					System.out.println("Please input positive numerics with format (99.9 99 99.9).");
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
				String input = bufferedReader.readLine();
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
				System.out.print("\nInput your pet total kg, total piece and total size for #" + (i+1) + " passengers (Format: 99.9 99 99.9): ");
				try{
					String input = bufferedReader.readLine();
					String[] inputs = input.split(" ");
					String inputKG = inputs[0];
					String inputPiece = inputs[1];
					String inputSize = inputs[2];
					kg = Float.parseFloat(inputKG);
					piece = Integer.parseInt(inputPiece);
					size = Float.parseFloat(inputSize);
					if(kg < 0 || piece < 0 || size < 0)
						System.out.println("Please input positive numerics with format (99.9 99 99.9).");
					else{
						petKgList.add(kg);
						petPieceList.add(piece);
						petSizeList.add(size);
						break;
					}
				}catch(Exception e){
					System.out.println("Please input positive numerics with format (99.9 99 99.9).");
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
		
		
		//4. Save into session
		response.setAttribute("unitNumForBaggage", unitNumForBaggage);
		response.setAttribute("unitNumForPet", unitNumForPet);
		response.setAttribute("sportingEquipments", sportingEquipments);
	}

}

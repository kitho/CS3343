package CS3343.AirlineTicketOrdering.View.Impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import CS3343.AirlineTicketOrdering.Model.BaggagePlan;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

public class InputBaggageDataView implements View {
	private BufferedReader bufferedReader;
	
	public InputBaggageDataView(BufferedReader bufferedReader){
		this.bufferedReader = bufferedReader;
	}
	
	public void display(Session response) throws IOException {
		//Get needed data from session
		BaggagePlan baggagePlan = (BaggagePlan)response.getAttribute("baggagePlan");
		String flightClass = (String)response.getAttribute("flightClass");
		if(baggagePlan == null){
			System.out.println("No suitable baggage plan.");
			return;
		}
		
		int numOfPassengers = (int)response.getAttribute("amountOfPassenger");
		
		System.out.println("\n" + this.genBaggagePlanRule(baggagePlan, flightClass));
		
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
	
	private String genBaggagePlanRule(BaggagePlan plan, String flightClass){
		String rule = "";
		
		//Get free unit rule string
		String feeUnitStr = "";
		Map<String, Map<String, Float>> freeUnits = plan.getFreeUnit();
		for(String classKey : freeUnits.keySet()){
			if(classKey.equals(flightClass)){
				Map<String, Float> freeUnit = freeUnits.get(classKey);
					for(String unitKey : freeUnit.keySet()){
						feeUnitStr += freeUnit.get(unitKey) + " " + unitKey + "(s)";
					}
			}
		}
		
		//Get basic fee rule string
		String basicFeeStr = "";
		Map<String, Float> extraFeePerUnits = plan.getExtraFeePerUnit();
		for(String unitKey : extraFeePerUnits.keySet()){
			basicFeeStr += unitKey + " $" + extraFeePerUnits.get(unitKey);
		}
		
		//Get extra fee rule string
		String extraFeeStr = "";
		Map<String, Map<String, Float>> extraExtraFeeForLevels = plan.getExtraExtraFeeForLevel();
		Map<String, Map<String, ArrayList<Float>>> extraExtraFeeCondtions = plan.getExtraExtraFeeCondtion();
		int i = 0;
		for(String keyLevel : extraExtraFeeCondtions.keySet()){
			i++;
			Map<String, ArrayList<Float>> conditions = extraExtraFeeCondtions.get(keyLevel);
			for(String keyUnit : conditions.keySet()){
				ArrayList<Float> conditionUnitNums = conditions.get(keyUnit);
				Float conditionUnitNumFrom = conditionUnitNums.get(0);
				Float conditionUnitNumTo = conditionUnitNums.get(1);
				
				Map<String, Float> extraExtraFees = extraExtraFeeForLevels.get(flightClass);
				
				if(conditionUnitNumTo < 9999)
					extraFeeStr += "\t" + conditionUnitNumFrom + " " + keyUnit + "(s)\t-\t" + 
									conditionUnitNumTo + " " + keyUnit + "(s)" +
									"\t$" + extraExtraFees.get(""+i) + "\n";
				else
					extraFeeStr += "\t>=" + conditionUnitNumFrom + " " + keyUnit + "(s)" + 
							"\t\t\t\t$" + extraExtraFees.get(""+i) + "\n";
			}
		}
		
		
		//Get basic pet rule string
		String basicPetFeeStr = "";
		Map<String, Float> petFeePerUnits = plan.getPetFee();
		for(String unitKey : petFeePerUnits.keySet()){
			basicPetFeeStr += unitKey + " $" + petFeePerUnits.get(unitKey);
		}
		
		//Get extra pet rule string
		String extraPetFeeStr = "";
		Map<String, Map<String, Float>> extraPetFeeForLevels = plan.getExtraExtraPetFeeForLevel();
		Map<String, Map<String, ArrayList<Float>>> extraPetFeeCondtions = plan.getExtraExtraPetFeeCondtion();
		i = 0;
		for(String keyLevel : extraPetFeeCondtions.keySet()){
			i++;
			Map<String, ArrayList<Float>> conditions = extraPetFeeCondtions.get(keyLevel);
			for(String keyUnit : conditions.keySet()){
				ArrayList<Float> conditionUnitNums = conditions.get(keyUnit);
				Float conditionUnitNumFrom = conditionUnitNums.get(0);
				Float conditionUnitNumTo = conditionUnitNums.get(1);
				
				Map<String, Float> extraPetFees = extraPetFeeForLevels.get(flightClass);
				
				if(conditionUnitNumTo < 9999)
					extraPetFeeStr += "\t" + conditionUnitNumFrom + " " + keyUnit + "(s)\t-\t" + 
									conditionUnitNumTo + " " + keyUnit + "(s)" +
									"\t$" + extraPetFees.get(""+i) + "\n";
				else
					extraPetFeeStr += "\t>=" + conditionUnitNumFrom + " " + keyUnit + "(s)" +
							"\t\t\t\t$" + extraPetFees.get(""+i) + "\n";
			}
		}
		
		rule += "=====Baggage Plan For " + flightClass + "=====";
		rule += "\n1. Each passenger can enjoy free " + feeUnitStr + " (Can be shared with other tickets purcahsed at the same time.)";
		rule += "\n2. Basic fee per " + basicFeeStr;
		rule += "\n3. Extra fee if average exceed following items:\n" + extraFeeStr;
		rule += "\n4. Basic pet fee per " + basicPetFeeStr;
		rule += "\n5. Extra pet if average exceed following items:\n" + extraPetFeeStr;
		
		return rule;
	}

}

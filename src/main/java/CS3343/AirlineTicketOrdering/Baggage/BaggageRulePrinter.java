package CS3343.AirlineTicketOrdering.Baggage;

import java.util.ArrayList;
import java.util.Map;

import CS3343.AirlineTicketOrdering.Model.BaggagePlan;

public class BaggageRulePrinter {
	public BaggageRulePrinter(){
		
	}
	
	public String printRule(BaggagePlan plan, String flightClass){
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

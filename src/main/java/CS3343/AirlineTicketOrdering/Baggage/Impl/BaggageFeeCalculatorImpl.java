package CS3343.AirlineTicketOrdering.Baggage.Impl;

import java.util.ArrayList;
import java.util.Map;

import CS3343.AirlineTicketOrdering.Baggage.BaggageFeeCalculator;

public class BaggageFeeCalculatorImpl implements BaggageFeeCalculator{
	
	@Override
	public float calBasicFee(Map<String, Float> currentUnit, Map<String, Float> basicFeePerUnits){
		float fee = 0;
		for(String key : currentUnit.keySet()){
			float unitNum = currentUnit.get(key);
			float feePerUnit = 0;
			if(currentUnit.size() > 0 && basicFeePerUnits.containsKey(key)) 
				feePerUnit = basicFeePerUnits.get(key);
			fee += unitNum * feePerUnit;
		}
		return (fee < 0) ? fee : 0;
	}
	
	@Override
	public float calExtraFee(
			Map<String, Float> currentUnitNum,
			String flightClass, int amountOfPassenger,
			Map<String, Map<String, Float>> extraFeeLevels,
			Map<String, Map<String, ArrayList<Float>>> extraFeeConditions){
		
		float totalFee = 0;
		
		//Find last passed level
		String lastPassedLevel = null;
		Float numberOfBaggage = 0f;
		for(String keyLevel : extraFeeConditions.keySet()){
			Map<String, ArrayList<Float>> conditions = extraFeeConditions.get(keyLevel);
			boolean isPass = false;
			for(String keyUnit : conditions.keySet()){
				ArrayList<Float> conditionUnitNums = conditions.get(keyUnit);
				Float conditionUnitNumFrom = conditionUnitNums.get(0);
				Float conditionUnitNumTo = conditionUnitNums.get(1);
				
				//Does it fulfill condition?
				//Pre-process: calculate avg unit for a baggage
				Float unitNumForPassenger = currentUnitNum.get(keyUnit) / amountOfPassenger;
				numberOfBaggage = currentUnitNum.get(UNIT_PIECE);
				Float avgUnitNumForPassenger = unitNumForPassenger / (numberOfBaggage / amountOfPassenger);
				
				if(conditionUnitNumTo < 9999){
					if(avgUnitNumForPassenger >= conditionUnitNumFrom &&
							 avgUnitNumForPassenger < conditionUnitNumTo + 1)
						isPass = true;
				}else{
					if(avgUnitNumForPassenger >= conditionUnitNumFrom)
						isPass = true;
				}
			}
			if(isPass)
				lastPassedLevel = keyLevel;
		}
		
		//Plus the level's fee
		if(lastPassedLevel != null){
			Map<String, Float> extraExtraFees = extraFeeLevels.get(flightClass);
			totalFee += extraExtraFees.get(lastPassedLevel) * numberOfBaggage;
		}
		return -totalFee;
	}
}

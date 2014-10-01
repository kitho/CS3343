/*
 * A baggage fee calculator to calculate baggage fee
 * by passing multiple routes and baggage information.
 */

package CS3343.AirlineTicketOrdering.Baggage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import CS3343.AirlineTicketOrdering.Baggage.Temp.*;

public class BaggageFeeCalculator {
	private static BaggageFeeCalculator calculator;
	
	//Singleton Pattern
	private BaggageFeeCalculator(){}
	
	public static BaggageFeeCalculator getInstance(){
		if (calculator == null)
			calculator = new BaggageFeeCalculator();
		return calculator;
	}
	
	//Calculate baggage fee for ONE passenger
	public float calBaggageFeeForOnePassenger(Route route, FlightClass flightClass, 
			Map<String, Float> unitNumForBaggage, ArrayList<String> sportingEquipments, 
			Map<String, Float> unitNumForPet){
		
		float resultFee = 0;
		BaggagePlan plan = route.getBaggagePlan();
		
		//Initial remaining unit
		float baggageFee = 0;
		Map<FlightClass, Map<String, Float>> freeUnit = plan.getFreeUnit();
		Map<String, Float> remainingFreeUnit = freeUnit.get(flightClass);
		
		//Calculate remaining unit
		remainingFreeUnit = this.subBaggageUnitNum(remainingFreeUnit, unitNumForBaggage);
		
		//Plus free sporting equipments unit
		remainingFreeUnit = this.plusSportingEquipments(remainingFreeUnit, 
														sportingEquipments, 
														plan, 
														flightClass);
		//Calculate extra fee
		baggageFee += this.calExtraFreeForRemainingUnit(remainingFreeUnit, plan);
		
		//Extra extra fee...
		Map<FlightClass, Map<String, Float>> extraExtraFeeForLevels = plan.getExtraExtraFeeForLevel();
		Map<String, Map<String, ArrayList<Float>>> extraExtraFeeCondtions = plan.getExtraExtraFeeCondtion();
		//Find last passed level
		String lastPassedLevel = null;
		for(String keyLevel : extraExtraFeeCondtions.keySet()){
			Map<String, ArrayList<Float>> conditions = extraExtraFeeCondtions.get(keyLevel);
			boolean isPass = true;
			for(String keyUnit : conditions.keySet()){
				ArrayList<Float> conditionUnitNums = conditions.get(keyUnit);
				Float conditionUnitNumFrom = conditionUnitNums.get(0);
				Float conditionUnitNumTo = conditionUnitNums.get(1);
				
				//Does it fulfill condition?
				Float unitNumForPassenger = unitNumForBaggage.get(keyUnit);
				if(conditionUnitNumFrom > unitNumForPassenger ||
						conditionUnitNumTo < unitNumForPassenger)
					isPass = false;
			}
			if(isPass)
				lastPassedLevel = keyLevel;
		}
		//Plus the level's fee
		if(lastPassedLevel != null){
			Map<String, Float> extraExtraFees = extraExtraFeeForLevels.get(flightClass);
			baggageFee += extraExtraFees.get(lastPassedLevel);
		}
			
		
		//Pet fee
		float petFee = this.calPetFee(plan, unitNumForPet);
		
		//Pet extra fee
		//...
		
		resultFee = baggageFee + petFee;
		return resultFee;
	}
	
	//Calculate baggage fee for GROUP
	public float calBaggageFeeForGroup(){
		return 0;
	}
	
	//Calculate pet fee
	private float calPetFee(BaggagePlan plan, Map<String, Float> unitNumForPet){
		
		float petFee = 0;
		Map<String, Float> planPetFee = plan.getPetFee();
		for(String key : planPetFee.keySet()){
			float feePerUnit = planPetFee.get(key);
			float petUnitNum = 0;
			if(unitNumForPet.size() > 0)
				petUnitNum = unitNumForPet.get(key);
			petFee += feePerUnit * petUnitNum;
		}
		return petFee;
		
	}
	
	//Calculate extra free for remaining unit
	private float calExtraFreeForRemainingUnit(Map<String, Float> remainingFreeUnit,
			BaggagePlan plan){
		float fee = 0;
		Map<String, Float> extraFeePerUnits = plan.getExtraFeePerUnit();
		for(String key : remainingFreeUnit.keySet()){
			float unitNum = Math.abs(remainingFreeUnit.get(key));
			float feePerUnit = extraFeePerUnits.get(key);
			fee += unitNum * feePerUnit;
		}
		return fee;
	}
	
	
	//Subtract baggage unit for remaining free unit
	private Map<String, Float> subBaggageUnitNum(Map<String, Float> remainingFreeUnit,
			Map<String, Float> unitNumForBaggage){
		
		for(String key : remainingFreeUnit.keySet()){
			Float oldValue = remainingFreeUnit.get(key);
			Float newValue = oldValue - unitNumForBaggage.get(key);
			remainingFreeUnit.replace(key, oldValue, newValue);
		}
		return remainingFreeUnit;
		
	}
	
	//Plus extra enjoy sporting equipment unit for remaining unit
	private Map<String, Float> plusSportingEquipments(Map<String, Float> remainingFreeUnit,
			ArrayList<String> sportingEquipments, BaggagePlan plan, FlightClass flightClass){
		
		ArrayList<String> units = this.getUnits(plan, flightClass);
		Map<String, Float> maxUnitNumsForSE = new HashMap<String, Float>();
		Iterator<String> itr = units.iterator();
		while(itr.hasNext()){
			String unit = itr.next();
			float maxUnitNum = this.findMaxSportingEquipmentsUnitNum(
					sportingEquipments,
					plan.getExtraFreeUnitForSportingEquipments(),
					unit);
			maxUnitNumsForSE.put(unit, maxUnitNum);
		}
		for(String key : remainingFreeUnit.keySet()){
			Float oldValue = remainingFreeUnit.get(key);
			Float newValue = oldValue + maxUnitNumsForSE.get(key);
			remainingFreeUnit.replace(key, oldValue, newValue);
		}
		return remainingFreeUnit;
		
	}
	
	
	//Get the supported units of the plan for free baggage weight
	private ArrayList<String> getUnits(BaggagePlan plan, FlightClass flightClass){
		
		ArrayList<String> units = new ArrayList<String>();
		Map<FlightClass, Map<String, Float>> freeUnit = plan.getFreeUnit();
		Map<String, Float> remainingFreeUnit = freeUnit.get(flightClass);
		for(String key : remainingFreeUnit.keySet())
			units.add(key);
		return units;
		
	}
	
	
	//Find max sporting equipments unit number
	private float findMaxSportingEquipmentsUnitNum(ArrayList<String> sportingEquipments, 
			Map<String, Map<String, Float>> extraFreeUnitForSportingEquipments,
			String unit){
		
		float maxUnitNum = 0;
		Iterator<String> itr = sportingEquipments.iterator();
		while(itr.hasNext()){
			String equipmentName = itr.next();
			Map<String, Float> extraFreeUnit = extraFreeUnitForSportingEquipments.get(equipmentName);
			float unitNum = extraFreeUnit.get(unit);
			if(maxUnitNum < unitNum)
				maxUnitNum = unitNum;
		}
		return maxUnitNum;
		
	}
}

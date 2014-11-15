/*
 * A baggage fee calculator to calculate baggage fee
 * by passing multiple routes and baggage information.
 */

package CS3343.AirlineTicketOrdering.Baggage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import CS3343.AirlineTicketOrdering.Model.BaggagePlan;
import CS3343.AirlineTicketOrdering.Model.Route;

public class BaggageFeeCalculator {
	private float resultFee = 0;
	private float extraBaggageFee = 0;
	private float extraExtraBaggageFee = 0;
	private float petFee = 0;
	private float extraPetFee = 0;
	private Map<String, Float> orgFreeUnit = new HashMap<String, Float>();
	private Map<String, Float> remainingFreeUnit = new HashMap<String, Float>();
	
	public BaggageFeeCalculator(){}
	
	//Calculate baggage fee for N passengers
	public float calBaggageFee(BaggagePlan plan, String flightClass, 
			Map<String, Float> unitNumForBaggage, ArrayList<String> sportingEquipments, 
			Map<String, Float> unitNumForPet, int amountOfPassenger){
		
		//Initial remaining unit
		Map<String, Map<String, Float>> freeUnit = plan.getFreeUnit();
		orgFreeUnit.putAll(freeUnit.get(flightClass));			//Clone into new list
		remainingFreeUnit.putAll(freeUnit.get(flightClass));	//Clone into new list
		
		//Increase free unit based on amount of passenger
		orgFreeUnit = this.increaseFreeUnit(orgFreeUnit, amountOfPassenger);
		remainingFreeUnit = this.increaseFreeUnit(remainingFreeUnit, amountOfPassenger);

		//Calculate remaining unit
		remainingFreeUnit = this.subBaggageUnitNum(remainingFreeUnit, unitNumForBaggage);

		//Plus free sporting equipments unit
		remainingFreeUnit = this.plusSportingEquipments(remainingFreeUnit, 
														sportingEquipments, 
														plan, 
														flightClass);
		//Calculate extra fee
		extraBaggageFee += this.calExtraFreeForRemainingUnit(remainingFreeUnit, plan);
		
		//Extra extra fee...
		extraExtraBaggageFee += this.calExtraExtraFee(plan, unitNumForBaggage, flightClass, amountOfPassenger);
			
		//Pet fee
		petFee += this.calPetFee(plan, unitNumForPet);
		
		//Pet extra fee
		extraPetFee += this.calExtraPetFee(plan, unitNumForPet, flightClass, amountOfPassenger);
		
		//Final result fees
		resultFee = extraBaggageFee + extraExtraBaggageFee + petFee + extraPetFee;
		
		return resultFee;
	}
	
	//Increase free unit based on amount of passenger
	private Map<String, Float> increaseFreeUnit(Map<String, Float> freeUnit, int amountOfPassenger){
		for(String keyUnit : freeUnit.keySet()){
			Float unitNumber = freeUnit.get(keyUnit);
			unitNumber = unitNumber * amountOfPassenger;
			freeUnit.remove(keyUnit);
			freeUnit.put(keyUnit, unitNumber);
		}
		return freeUnit;
	}
	
	//Calculate extra extra fee
	private float calExtraExtraFee(BaggagePlan plan, Map<String, Float> unitNumForBaggage,
			String flightClass, int amountOfPassenger){
		float resultExtraExtraFee = 0;
		Map<String, Map<String, Float>> extraExtraFeeForLevels = plan.getExtraExtraFeeForLevel();
		Map<String, Map<String, ArrayList<Float>>> extraExtraFeeCondtions = plan.getExtraExtraFeeCondtion();
		//Find last passed level
		String lastPassedLevel = null;
		Float numberOfBaggage = 0f;
		for(String keyLevel : extraExtraFeeCondtions.keySet()){
			Map<String, ArrayList<Float>> conditions = extraExtraFeeCondtions.get(keyLevel);
			boolean isPass = true;
			for(String keyUnit : conditions.keySet()){
				ArrayList<Float> conditionUnitNums = conditions.get(keyUnit);
				Float conditionUnitNumFrom = conditionUnitNums.get(0);
				Float conditionUnitNumTo = conditionUnitNums.get(1);
				
				//Does it fulfill condition?
				//Pre-process: calculate avg unit for a baggage
				Float unitNumForPassenger = unitNumForBaggage.get(keyUnit) / amountOfPassenger;
				numberOfBaggage = unitNumForBaggage.get(plan.getUnit().get(1));
				Float avgUnitNumForPassenger = unitNumForPassenger / (numberOfBaggage / amountOfPassenger);
				//Float avgUnitNumForPassenger = unitNumForPassenger;

				if(conditionUnitNumTo < 9999){
					if(conditionUnitNumFrom > avgUnitNumForPassenger ||
							conditionUnitNumTo < avgUnitNumForPassenger)
						isPass = false;
				}else{
					if(conditionUnitNumFrom > avgUnitNumForPassenger)
						isPass = false;
				}
			}
			if(isPass)
				lastPassedLevel = keyLevel;
		}
		//Plus the level's fee
		if(lastPassedLevel != null){
			Map<String, Float> extraExtraFees = extraExtraFeeForLevels.get(flightClass);
			resultExtraExtraFee += extraExtraFees.get(lastPassedLevel) * numberOfBaggage;
		}
		return -resultExtraExtraFee;
	}
	
	//Calculate extra pet fee
	private float calExtraPetFee(BaggagePlan plan, Map<String, Float> unitNumForPet,
			String flightClass, int amountOfPassenger){
		if(unitNumForPet.size() == 0)
			return 0;
		float resultExtraPetFee = 0;
		Map<String, Map<String, Float>> extraExtraPetFeeForLevels = plan.getExtraExtraPetFeeForLevel();
		Map<String, Map<String, ArrayList<Float>>> extraExtraPetFeeCondtions = plan.getExtraExtraPetFeeCondtion();
		//Find last passed level
		String lastPassedLevel = null;
		Float numberOfPet = 0f;
		for(String keyLevel : extraExtraPetFeeCondtions.keySet()){
			Map<String, ArrayList<Float>> conditions = extraExtraPetFeeCondtions.get(keyLevel);
			boolean isPass = true;
			for(String keyUnit : conditions.keySet()){
				ArrayList<Float> conditionUnitNums = conditions.get(keyUnit);
				Float conditionUnitNumFrom = conditionUnitNums.get(0);
				Float conditionUnitNumTo = conditionUnitNums.get(1);
				
				//Does it fulfill condition?
				Float unitNum = unitNumForPet.get(keyUnit) / amountOfPassenger;
				
				//Pre-process: calculate avg
				numberOfPet = unitNumForPet.get(plan.getUnit().get(1));
				Float avgUnitNum = unitNum / (numberOfPet / amountOfPassenger);
				
				if(conditionUnitNumTo < 9999){
					if(conditionUnitNumFrom > avgUnitNum ||
							conditionUnitNumTo < avgUnitNum)
						isPass = false;
				}else{
					if(conditionUnitNumFrom > avgUnitNum)
						isPass = false;
				}
			}
			if(isPass)
				lastPassedLevel = keyLevel;
		}
		//Plus the level's fee
		if(lastPassedLevel != null){
			Map<String, Float> extraPetFees = extraExtraPetFeeForLevels.get(flightClass);
			resultExtraPetFee += extraPetFees.get(lastPassedLevel) * numberOfPet;
		}
		return -resultExtraPetFee;
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
		return -petFee;
		
	}
	
	//Calculate extra free for remaining unit
	private float calExtraFreeForRemainingUnit(Map<String, Float> remainingFreeUnit,
			BaggagePlan plan){
		float fee = 0;
		Map<String, Float> extraFeePerUnits = plan.getExtraFeePerUnit();
		for(String key : remainingFreeUnit.keySet()){
			float unitNum = remainingFreeUnit.get(key);
			float feePerUnit = extraFeePerUnits.get(key);
			fee += unitNum * feePerUnit;
		}
		return (fee < 0) ? fee : 0;
	}
	
	
	//Subtract baggage unit for remaining free unit
	private Map<String, Float> subBaggageUnitNum(Map<String, Float> remainingFreeUnit,
			Map<String, Float> unitNumForBaggage){
		
		for(String key : remainingFreeUnit.keySet()){
			Float oldValue = remainingFreeUnit.get(key);
			Float newValue = oldValue - unitNumForBaggage.get(key);
			remainingFreeUnit.remove(key);
			remainingFreeUnit.put(key, newValue);
		}
		return remainingFreeUnit;
		
	}
	
	//Plus extra enjoy sporting equipment unit for remaining unit
	private Map<String, Float> plusSportingEquipments(Map<String, Float> remainingFreeUnit,
			ArrayList<String> sportingEquipments, BaggagePlan plan, String flightClass){
			
		Map<String, Map<String, Float>> extraFreeUnitForSE = plan.getExtraFreeUnitForSportingEquipments();
		
		for(int i = 0; i < sportingEquipments.size(); i++){
			Map<String, Float> extraFreeUnitForOneSE = extraFreeUnitForSE.get(sportingEquipments.get(i));
			
			for(String key : remainingFreeUnit.keySet()){
				Float oldValue = remainingFreeUnit.get(key);
				Float newValue = oldValue + extraFreeUnitForOneSE.get(key);
				remainingFreeUnit.remove(key);
				remainingFreeUnit.put(key, newValue);
			}
		}
		return remainingFreeUnit;
		
	}
	
	
	//Get the supported units of the plan for free baggage weight
	private ArrayList<String> getUnits(BaggagePlan plan, String flightClass){
		
		ArrayList<String> units = new ArrayList<String>();
		Map<String, Map<String, Float>> freeUnit = plan.getFreeUnit();
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

	public float getResultFee() {
		return resultFee;
	}

	public void setResultFee(float resultFee) {
		this.resultFee = resultFee;
	}

	public float getExtraBaggageFee() {
		return extraBaggageFee;
	}

	public void setExtraBaggageFee(float extraBaggageFee) {
		this.extraBaggageFee = extraBaggageFee;
	}

	public float getExtraExtraBaggageFee() {
		return extraExtraBaggageFee;
	}

	public void setExtraExtraBaggageFee(float extraExtraBaggageFee) {
		this.extraExtraBaggageFee = extraExtraBaggageFee;
	}

	public float getPetFee() {
		return petFee;
	}

	public void setPetFee(float petFee) {
		this.petFee = petFee;
	}

	public float getExtraPetFee() {
		return extraPetFee;
	}

	public void setExtraPetFee(float extraPetFee) {
		this.extraPetFee = extraPetFee;
	}

	public Map<String, Float> getOrgFreeUnit() {
		return orgFreeUnit;
	}

	public void setOrgFreeUnit(Map<String, Float> orgFreeUnit) {
		this.orgFreeUnit = orgFreeUnit;
	}

	public Map<String, Float> getRemainingFreeUnit() {
		return remainingFreeUnit;
	}

	public void setRemainingFreeUnit(Map<String, Float> remainingFreeUnit) {
		this.remainingFreeUnit = remainingFreeUnit;
	}
	
	
}

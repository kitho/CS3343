package CS3343.AirlineTicketOrdering.Baggage.Data;

import java.util.ArrayList;
import java.util.Map;

public class BaggageDataPreparer {
	/**
	 * Increase free unit based on amount of passenger
	 * @param freeUnit
	 * @param amountOfPassenger
	 * @return
	 */
	public Map<String, Float> increaseFreeUnit(Map<String, Float> freeUnit, int amountOfPassenger){
		for(String keyUnit : freeUnit.keySet()){
			Float unitNumber = freeUnit.get(keyUnit);
			unitNumber = unitNumber * amountOfPassenger;
			freeUnit.remove(keyUnit);
			freeUnit.put(keyUnit, unitNumber);
		}
		return freeUnit;
	}
	
	
	/** 
	 * Subtract unit for remaining free unit
	 * @param remainingFreeUnit
	 * @param unitNumForBaggage
	 * @return
	 */
	public Map<String, Float> subBaggageUnitNum(Map<String, Float> remainingFreeUnit,
			Map<String, Float> unitNumForBaggage){
		
		for(String key : remainingFreeUnit.keySet()){
			Float oldValue = remainingFreeUnit.get(key);
			Float newValue = 0f;
			if(unitNumForBaggage.containsKey(key))
				newValue = oldValue - unitNumForBaggage.get(key);
			remainingFreeUnit.remove(key);
			remainingFreeUnit.put(key, newValue);
		}
		return remainingFreeUnit;
	}


	/**
	 * Plus extra enjoy sporting equipment unit for remaining unit
	 * @param remainingFreeUnit
	 * @param sportingEquipments
	 * @param extraFreeUnitForSE
	 * @return
	 */
	public Map<String, Float> plusSportingEquipments(Map<String, Float> remainingFreeUnit,
			ArrayList<String> sportingEquipments, Map<String, Map<String, Float>> extraFreeUnitForSE){
		
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
}

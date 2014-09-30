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
		
		float resultFee;
		BaggagePlan plan = route.getBaggagePlan();
		
		//Calculate remaining unit
		Map<FlightClass, Map<String, Float>> freeUnit = plan.getFreeUnit();
		Map<String, Float> remainingFreeUnit = freeUnit.get(flightClass);
		for(String key : remainingFreeUnit.keySet()){
			Float oldValue = remainingFreeUnit.get(key);
			Float newValue = oldValue - unitNumForBaggage.get(key);
			remainingFreeUnit.replace(key, oldValue, newValue);
		}
		
		
		//Plus free sporting equipments unit
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
		
		//Cal extra fee
		
		
		return 0;
	}
	
	//Calculate baggage fee for GROUP
	public float calBaggageFeeForGroup(){
		return 0;
	}
	
	private ArrayList<String> getUnits(BaggagePlan plan, FlightClass flightClass){
		ArrayList<String> units = new ArrayList<String>();
		Map<FlightClass, Map<String, Float>> freeUnit = plan.getFreeUnit();
		Map<String, Float> remainingFreeUnit = freeUnit.get(flightClass);
		for(String key : remainingFreeUnit.keySet())
			units.add(key);
		return units;
	}
	
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

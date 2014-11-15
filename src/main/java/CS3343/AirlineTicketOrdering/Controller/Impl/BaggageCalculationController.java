package CS3343.AirlineTicketOrdering.Controller.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import CS3343.AirlineTicketOrdering.Baggage.BaggageFeeCalculator;
import CS3343.AirlineTicketOrdering.Baggage.BaggageFeeCalculatorImpl;
import CS3343.AirlineTicketOrdering.Controller.AirlineTicketOrderingController;
import CS3343.AirlineTicketOrdering.Model.BaggagePlan;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

public class BaggageCalculationController extends AirlineTicketOrderingController {

	public BaggageCalculationController(Session session, View view) {
		super(session, view);
	}

	@Override
	public void execute() throws Exception {
		//Get needed session data
		BaggagePlan plan = (BaggagePlan) session.getAttribute("baggagePlan");
		String flightClass = (String) session.getAttribute("flightClass");
		int amountOfPassenger = (int) session.getAttribute("amountOfPassenger");
		Map<String, Float> unitNumForBaggage = (Map<String, Float>) session.getAttribute("unitNumForBaggage");
		Map<String, Float> unitNumForPet = (Map<String, Float>) session.getAttribute("unitNumForPet");
		ArrayList<String> sportingEquipments = (ArrayList<String>) session.getAttribute("sportingEquipments");
		
		//Instance calculator
		BaggageFeeCalculator calculator = new BaggageFeeCalculatorImpl();
		
		//Define needed variable
	    Map<String, Float> orgFreeUnit = new HashMap<String, Float>();
		Map<String, Float> remainingFreeUnit = new HashMap<String, Float>();
		
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
														plan.getExtraFreeUnitForSportingEquipments(), 
														flightClass);
		
		//Calculate baggage fee
		Float totalFee = 0f, basicBaggageFee = 0f, extraBaggageFee = 0f
				,petFee = 0f, extraPetFee = 0f;
		basicBaggageFee += calculator.calBasicFee(remainingFreeUnit, plan.getExtraFeePerUnit());		
		extraBaggageFee += calculator.calExtraFee(
									unitNumForBaggage, 
									flightClass, 
									amountOfPassenger, 
									plan.getExtraExtraFeeForLevel(),
									plan.getExtraExtraPetFeeCondtion());
		petFee += calculator.calBasicFee(unitNumForPet, plan.getPetFee());
		extraPetFee += calculator.calExtraFee(
									unitNumForPet, 
									flightClass, 
									amountOfPassenger, 
									plan.getExtraExtraPetFeeForLevel(),
									plan.getExtraExtraPetFeeCondtion());
		
		totalFee = basicBaggageFee + extraBaggageFee + petFee + extraPetFee;
		
		session.setAttribute("orgFreeUnit", orgFreeUnit);
		session.setAttribute("remainingFreeUnit", remainingFreeUnit);
		session.setAttribute("basicBaggageFee", basicBaggageFee);
		session.setAttribute("extraBaggageFee", extraBaggageFee);
		session.setAttribute("petFee", petFee);
		session.setAttribute("extraPetFee", extraPetFee);
		session.setAttribute("totalFee", totalFee);
		
		view.display(session);
	}
	
	
	/**
	 * Increase free unit based on amount of passenger
	 * @param freeUnit
	 * @param amountOfPassenger
	 * @return
	 */
	private Map<String, Float> increaseFreeUnit(Map<String, Float> freeUnit, int amountOfPassenger){
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


	/**
	 * Plus extra enjoy sporting equipment unit for remaining unit
	 * @param remainingFreeUnit
	 * @param sportingEquipments
	 * @param extraFreeUnitForSE
	 * @param flightClass
	 * @return
	 */
	private Map<String, Float> plusSportingEquipments(Map<String, Float> remainingFreeUnit,
			ArrayList<String> sportingEquipments, Map<String, Map<String, Float>> extraFreeUnitForSE, String flightClass){
		
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

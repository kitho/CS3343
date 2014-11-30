package CS3343.AirlineTicketOrdering.Controller.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import CS3343.AirlineTicketOrdering.Baggage.BaggageFeeCalculator;
import CS3343.AirlineTicketOrdering.Baggage.Data.BaggageDataPreparer;
import CS3343.AirlineTicketOrdering.Baggage.Impl.BaggageFeeCalculatorImpl;
import CS3343.AirlineTicketOrdering.Controller.AirlineTicketOrderingController;
import CS3343.AirlineTicketOrdering.Model.BaggagePlan;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

public class BaggageCalculationController extends AirlineTicketOrderingController {

	/**
	 * Baggage calculation controller's constructor
	 * @param session
	 * @param view
	 */
	public BaggageCalculationController(Session session, View view) {
		super(session, view);
	}

	@Override
	public void execute() throws Exception {
		//Get needed session data
		BaggagePlan plan = (BaggagePlan) session.getAttribute("baggagePlan");
		String flightClass = (String) session.getAttribute("flightClass");
		int amountOfPassenger = (int) session.getAttribute("numberOfTicket");
		Map<String, Float> unitNumForBaggage = (Map<String, Float>) session.getAttribute("unitNumForBaggage");
		Map<String, Float> unitNumForPet = (Map<String, Float>) session.getAttribute("unitNumForPet");
		ArrayList<String> sportingEquipments = (ArrayList<String>) session.getAttribute("sportingEquipments");
		
		//Instance data preparer and calculator
		BaggageFeeCalculator calculator = new BaggageFeeCalculatorImpl();
		BaggageDataPreparer preparer = new BaggageDataPreparer();
		
		//Define needed variable
	    Map<String, Float> orgFreeUnit = new HashMap<String, Float>();
		Map<String, Float> remainingFreeUnit = new HashMap<String, Float>();
		
		//Initial remaining unit
		Map<String, Map<String, Float>> freeUnit = plan.getFreeUnit();
		orgFreeUnit.putAll(freeUnit.get(flightClass));			//Clone into new list
		remainingFreeUnit.putAll(freeUnit.get(flightClass));	//Clone into new list
		
		//Increase free unit based on amount of passenger
		orgFreeUnit = preparer.increaseFreeUnit(orgFreeUnit, amountOfPassenger);
		remainingFreeUnit = preparer.increaseFreeUnit(remainingFreeUnit, amountOfPassenger);
		
		//Calculate remaining unit
		remainingFreeUnit = preparer.subBaggageUnitNum(remainingFreeUnit, unitNumForBaggage);
		
		//Plus free sporting equipments unit
		remainingFreeUnit = preparer.plusSportingEquipments(remainingFreeUnit, 
														sportingEquipments, 
														plan.getExtraFreeUnitForSportingEquipments());
		
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
		basicBaggageFee = (basicBaggageFee < 0) ? basicBaggageFee : 0;
		petFee = (petFee <= 0) ? petFee : -petFee;
		//petFee = (petFee < 0) ? petFee : 0;
		totalFee = basicBaggageFee + extraBaggageFee + petFee + extraPetFee;
		
		session.setAttribute("orgFreeUnit", orgFreeUnit);
		session.setAttribute("remainingFreeUnit", remainingFreeUnit);
		session.setAttribute("basicBaggageFee", basicBaggageFee);
		session.setAttribute("extraBaggageFee", extraBaggageFee);
		session.setAttribute("petFee", petFee);
		session.setAttribute("extraPetFee", extraPetFee);
		session.setAttribute("totalFee", totalFee);
		
		view.display(session);
		
		next();
	}
	
	
	
	
	

}

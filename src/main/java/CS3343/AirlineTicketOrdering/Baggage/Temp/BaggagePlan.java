package CS3343.AirlineTicketOrdering.Baggage.Temp;

import java.util.ArrayList;
import java.util.Map;

public class BaggagePlan {
	//Unit
	private ArrayList<String> unit;									//KG, Baggage, Size, etc.
	
	//For Passenger's Baggage
	private Map<FlightClass, Float> freeWeight;
	private Map<String, Float> extraFreeWeightForSportingEquipments;//<NameOfEquipments, FreeWeight>
	private Map<String, Float> extraFeePerUnit;						//<Unit, Fee>
	private Map<FlightClass, Map<String, Float>> extraExtraFee;		//<Flight, <Unit, Fee>>
	
	//For Pet Placed in Baggage
	private Map<String, Float> petFee;								//<Unit,Fee>
	private Map<String, Float> normalPetFeeMaxUnit;					//<Unit,Num>
	
	//For Pet If Exceed Normal Fee's Weight or Size
	private Map<String, Float> extraPetFeeMaxUnit;					//<Unit,Num>
	private Map<String, Float> extraPetFeeForLevel;		//<Level, Fee>
														//Level 1: Less than extraFeeMaxWeight & normalFeeMaxSize
														//		2: Less than extraFeeMaxSize & normalFeeMaxWeight
														//		3: Less than extraFeeMaxWeight & extraFeeMaxSize
														//		4: More than extraFeeMaxWeight & LextraFeeMaxSize
	public ArrayList<String> getUnit() {
		return unit;
	}
	public void setUnit(ArrayList<String> unit) {
		this.unit = unit;
	}
	public Map<FlightClass, Float> getFreeWeight() {
		return freeWeight;
	}
	public void setFreeWeight(Map<FlightClass, Float> freeWeight) {
		this.freeWeight = freeWeight;
	}
	public Map<String, Float> getExtraFreeWeightForSportingEquipments() {
		return extraFreeWeightForSportingEquipments;
	}
	public void setExtraFreeWeightForSportingEquipments(
			Map<String, Float> extraFreeWeightForSportingEquipments) {
		this.extraFreeWeightForSportingEquipments = extraFreeWeightForSportingEquipments;
	}
	public Map<String, Float> getExtraFeePerUnit() {
		return extraFeePerUnit;
	}
	public void setExtraFeePerUnit(Map<String, Float> extraFeePerUnit) {
		this.extraFeePerUnit = extraFeePerUnit;
	}
	public Map<FlightClass, Map<String, Float>> getExtraExtraFee() {
		return extraExtraFee;
	}
	public void setExtraExtraFee(Map<FlightClass, Map<String, Float>> extraExtraFee) {
		this.extraExtraFee = extraExtraFee;
	}
	public Map<String, Float> getPetFee() {
		return petFee;
	}
	public void setPetFee(Map<String, Float> petFee) {
		this.petFee = petFee;
	}
	public Map<String, Float> getNormalPetFeeMaxUnit() {
		return normalPetFeeMaxUnit;
	}
	public void setNormalPetFeeMaxUnit(Map<String, Float> normalPetFeeMaxUnit) {
		this.normalPetFeeMaxUnit = normalPetFeeMaxUnit;
	}
	public Map<String, Float> getExtraPetFeeMaxUnit() {
		return extraPetFeeMaxUnit;
	}
	public void setExtraPetFeeMaxUnit(Map<String, Float> extraPetFeeMaxUnit) {
		this.extraPetFeeMaxUnit = extraPetFeeMaxUnit;
	}
	public Map<String, Float> getExtraPetFeeForLevel() {
		return extraPetFeeForLevel;
	}
	public void setExtraPetFeeForLevel(Map<String, Float> extraPetFeeForLevel) {
		this.extraPetFeeForLevel = extraPetFeeForLevel;
	}
	
	
}

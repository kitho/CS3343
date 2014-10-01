package CS3343.AirlineTicketOrdering.Baggage.Temp;

import java.util.ArrayList;
import java.util.Map;

public class BaggagePlan {
	//Unit
	private ArrayList<String> unit;										//KG, Baggage, Size, etc.
	
	//For Passenger's Baggage
	private Map<FlightClass, Map<String, Float>> freeUnit;
	private Map<String, Map<String, Float>> extraFreeUnitForSportingEquipments;	//<NameOfEquipments, <Unit, Num>>
	private Map<String, Float> extraFeePerUnit;							//<Unit, Fee>
	
	//Extra extra fee
	private Map<FlightClass, Map<String, Float>> extraExtraFeeForLevel;	//<FlightClass, <Level, Fee>>
	private Map<String, Map<String, Float>> extraExtraFeeCondtion;		//<Level, <Unit, Num>>
	
	//For Pet Placed in Baggage
	private Map<String, Float> petFee;									//<Unit,Pat Fee>
	
	//For Pet If Exceed Normal Fee's Weight or Size
	private Map<String, Float> normalPetFeeMaxUnit;						//<Unit,Num>
	private Map<String, Float> extraPetFeeMaxUnit;					//<Unit,Num>
	private Map<String, Float> extraPetFeeForLevel;		//<Level, Pat Fee>
														//Level 1: Less than extraFeeMaxWeight & normalFeeMaxSize
														//		2: Less than extraFeeMaxSize & normalFeeMaxWeight
														//		3: Less than extraFeeMaxWeight & extraFeeMaxSize
														//		4: More than extraFeeMaxWeight & LextraFeeMaxSize
	
	public BaggagePlan(){
		super();
	}
	
	public BaggagePlan(
			ArrayList<String> unit,
			Map<FlightClass, Map<String, Float>> freeUnit,
			Map<String, Map<String, Float>> extraFreeUnitForSportingEquipments,
			Map<String, Float> extraFeePerUnit,
			Map<FlightClass, Map<String, Float>> extraExtraFeeForLevel,
			Map<String, Map<String, Float>> extraExtraFeeCondtion,
			Map<String, Float> petFee,
			Map<String, Float> normalPetFeeMaxUnit,
			Map<String, Float> extraPetFeeMaxUnit,
			Map<String, Float> extraPetFeeForLevel) {
		super();
		this.unit = unit;
		this.freeUnit = freeUnit;
		this.extraFreeUnitForSportingEquipments = extraFreeUnitForSportingEquipments;
		this.extraFeePerUnit = extraFeePerUnit;
		this.extraExtraFeeForLevel = extraExtraFeeForLevel;
		this.extraExtraFeeCondtion = extraExtraFeeCondtion;
		this.petFee = petFee;
		this.normalPetFeeMaxUnit = normalPetFeeMaxUnit;
		this.extraPetFeeMaxUnit = extraPetFeeMaxUnit;
		this.extraPetFeeForLevel = extraPetFeeForLevel;
	}
	
	public ArrayList<String> getUnit() {
		return unit;
	}
	public void setUnit(ArrayList<String> unit) {
		this.unit = unit;
	}

	public Map<String, Map<String, Float>> getExtraFreeUnitForSportingEquipments() {
		return extraFreeUnitForSportingEquipments;
	}
	public void setExtraFreeUnitForSportingEquipments(
			Map<String, Map<String, Float>> extraFreeUnitForSportingEquipments) {
		this.extraFreeUnitForSportingEquipments = extraFreeUnitForSportingEquipments;
	}
	public Map<String, Float> getExtraFeePerUnit() {
		return extraFeePerUnit;
	}
	public void setExtraFeePerUnit(Map<String, Float> extraFeePerUnit) {
		this.extraFeePerUnit = extraFeePerUnit;
	}
	public Map<FlightClass, Map<String, Float>> getExtraExtraFeeForLevel() {
		return extraExtraFeeForLevel;
	}
	public void setExtraExtraFeeForLevel(
			Map<FlightClass, Map<String, Float>> extraExtraFeeForLevel) {
		this.extraExtraFeeForLevel = extraExtraFeeForLevel;
	}
	public Map<String, Map<String, Float>> getExtraExtraFeeCondtion() {
		return extraExtraFeeCondtion;
	}
	public void setExtraExtraFeeCondtion(
			Map<String, Map<String, Float>> extraExtraFeeCondtion) {
		this.extraExtraFeeCondtion = extraExtraFeeCondtion;
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
	public Map<FlightClass, Map<String, Float>> getFreeUnit() {
		return freeUnit;
	}
	public void setFreeUnit(Map<FlightClass, Map<String, Float>> freeUnit) {
		this.freeUnit = freeUnit;
	}
	
	
	
}

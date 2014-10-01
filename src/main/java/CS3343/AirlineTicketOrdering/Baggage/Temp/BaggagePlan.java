package CS3343.AirlineTicketOrdering.Baggage.Temp;

import java.util.ArrayList;
import java.util.Map;

public class BaggagePlan {
	//Unit
	private ArrayList<String> unit;												//KG, Baggage, Size, etc.
	
	//For Passenger's Baggage
	private Map<FlightClass, Map<String, Float>> freeUnit;
	private Map<String, Map<String, Float>> extraFreeUnitForSportingEquipments;	//<NameOfEquipments, <Unit, Num>>
	private Map<String, Float> extraFeePerUnit;									//<Unit, Fee>
	
	//Extra extra fee
	private Map<FlightClass, Map<String, Float>> extraExtraFeeForLevel;			//<FlightClass, <Level, Fee>>
	private Map<String, Map<String, ArrayList<Float>>> extraExtraFeeCondtion;	//<Level, <Unit, Num Range>>
	
	//For Pet Placed in Baggage
	private Map<String, Float> petFee;											//<Unit,Pat Fee>
	
	//For Pet If Exceed Normal Fee's Weight or Size
	private Map<FlightClass, Map<String, Float>> extraExtraPetFeeForLevel;		//<FlightClass, <Level, Fee>>
	private Map<String, Map<String, ArrayList<Float>>> extraExtraPetFeeCondtion;//<Level, <Unit, Num Range>>
	
	public BaggagePlan(){
		super();
	}

	public BaggagePlan(ArrayList<String> unit,
			Map<FlightClass, Map<String, Float>> freeUnit,
			Map<String, Map<String, Float>> extraFreeUnitForSportingEquipments,
			Map<String, Float> extraFeePerUnit,
			Map<FlightClass, Map<String, Float>> extraExtraFeeForLevel,
			Map<String, Map<String, ArrayList<Float>>> extraExtraFeeCondtion,
			Map<String, Float> petFee,
			Map<FlightClass, Map<String, Float>> extraExtraPetFeeForLevel,
			Map<String, Map<String, ArrayList<Float>>> extraExtraPetFeeCondtion) {
		super();
		this.unit = unit;
		this.freeUnit = freeUnit;
		this.extraFreeUnitForSportingEquipments = extraFreeUnitForSportingEquipments;
		this.extraFeePerUnit = extraFeePerUnit;
		this.extraExtraFeeForLevel = extraExtraFeeForLevel;
		this.extraExtraFeeCondtion = extraExtraFeeCondtion;
		this.petFee = petFee;
		this.extraExtraPetFeeForLevel = extraExtraPetFeeForLevel;
		this.extraExtraPetFeeCondtion = extraExtraPetFeeCondtion;
	}

	public ArrayList<String> getUnit() {
		return unit;
	}

	public void setUnit(ArrayList<String> unit) {
		this.unit = unit;
	}

	public Map<FlightClass, Map<String, Float>> getFreeUnit() {
		return freeUnit;
	}

	public void setFreeUnit(Map<FlightClass, Map<String, Float>> freeUnit) {
		this.freeUnit = freeUnit;
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

	public Map<String, Map<String, ArrayList<Float>>> getExtraExtraFeeCondtion() {
		return extraExtraFeeCondtion;
	}

	public void setExtraExtraFeeCondtion(
			Map<String, Map<String, ArrayList<Float>>> extraExtraFeeCondtion) {
		this.extraExtraFeeCondtion = extraExtraFeeCondtion;
	}

	public Map<String, Float> getPetFee() {
		return petFee;
	}

	public void setPetFee(Map<String, Float> petFee) {
		this.petFee = petFee;
	}

	public Map<FlightClass, Map<String, Float>> getExtraExtraPetFeeForLevel() {
		return extraExtraPetFeeForLevel;
	}

	public void setExtraExtraPetFeeForLevel(
			Map<FlightClass, Map<String, Float>> extraExtraPetFeeForLevel) {
		this.extraExtraPetFeeForLevel = extraExtraPetFeeForLevel;
	}

	public Map<String, Map<String, ArrayList<Float>>> getExtraExtraPetFeeCondtion() {
		return extraExtraPetFeeCondtion;
	}

	public void setExtraExtraPetFeeCondtion(
			Map<String, Map<String, ArrayList<Float>>> extraExtraPetFeeCondtion) {
		this.extraExtraPetFeeCondtion = extraExtraPetFeeCondtion;
	}
	
	
	
	
}

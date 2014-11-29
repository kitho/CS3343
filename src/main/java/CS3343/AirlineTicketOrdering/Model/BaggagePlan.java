package CS3343.AirlineTicketOrdering.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaggagePlan {
	//Unit
	private ArrayList<String> unit;												//KG, Baggage, Size, etc.
	
	//For Passenger's Baggage
	private Map<String, Map<String, Float>> freeUnit;
	private Map<String, Map<String, Float>> extraFreeUnitForSportingEquipments;	//<NameOfEquipments, <Unit, Num>>
	private Map<String, Float> extraFeePerUnit;									//<Unit, Fee>
	
	//Extra extra fee
	private Map<String, Map<String, Float>> extraExtraFeeForLevel;			//<FlightClass, <Level, Fee>>
	private Map<String, Map<String, ArrayList<Float>>> extraExtraFeeCondtion;	//<Level, <Unit, Num Range>>
	
	//For Pet Placed in Baggage
	private Map<String, Float> petFee;											//<Unit,Pat Fee>
	
	//For Pet If Exceed Normal Fee's Weight or Size
	private Map<String, Map<String, Float>> extraExtraPetFeeForLevel;		//<FlightClass, <Level, Fee>>
	private Map<String, Map<String, ArrayList<Float>>> extraExtraPetFeeCondtion;//<Level, <Unit, Num Range>>
	
	//For place
	private List<String> placeFroms = new ArrayList<String>();
	private List<String> placeTos = new ArrayList<String>();
	
	public BaggagePlan(){
		super();
	}

	/**
	 * Constructor
	 * @param unit
	 * @param freeUnit
	 * @param extraFreeUnitForSportingEquipments
	 * @param extraFeePerUnit
	 * @param extraExtraFeeForLevel
	 * @param extraExtraFeeCondtion
	 * @param petFee
	 * @param extraExtraPetFeeForLevel
	 * @param extraExtraPetFeeCondtion
	 */
	public BaggagePlan(ArrayList<String> unit,
			Map<String, Map<String, Float>> freeUnit,
			Map<String, Map<String, Float>> extraFreeUnitForSportingEquipments,
			Map<String, Float> extraFeePerUnit,
			Map<String, Map<String, Float>> extraExtraFeeForLevel,
			Map<String, Map<String, ArrayList<Float>>> extraExtraFeeCondtion,
			Map<String, Float> petFee,
			Map<String, Map<String, Float>> extraExtraPetFeeForLevel,
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

	public Map<String, Map<String, Float>> getFreeUnit() {
		return freeUnit;
	}

	public void setFreeUnit(Map<String, Map<String, Float>> freeUnit) {
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

	public Map<String, Map<String, Float>> getExtraExtraFeeForLevel() {
		return extraExtraFeeForLevel;
	}

	public void setExtraExtraFeeForLevel(
			Map<String, Map<String, Float>> extraExtraFeeForLevel) {
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

	public Map<String, Map<String, Float>> getExtraExtraPetFeeForLevel() {
		return extraExtraPetFeeForLevel;
	}

	public void setExtraExtraPetFeeForLevel(
			Map<String, Map<String, Float>> extraExtraPetFeeForLevel) {
		this.extraExtraPetFeeForLevel = extraExtraPetFeeForLevel;
	}

	public Map<String, Map<String, ArrayList<Float>>> getExtraExtraPetFeeCondtion() {
		return extraExtraPetFeeCondtion;
	}

	public void setExtraExtraPetFeeCondtion(
			Map<String, Map<String, ArrayList<Float>>> extraExtraPetFeeCondtion) {
		this.extraExtraPetFeeCondtion = extraExtraPetFeeCondtion;
	}
	
	
	
	public List<String> getPlaceFroms() {
		return placeFroms;
	}

	public void setPlaceFroms(List<String> placeFroms) {
		this.placeFroms = placeFroms;
	}

	public List<String> getPlaceTos() {
		return placeTos;
	}

	public void setPlaceTos(List<String> placeTos) {
		this.placeTos = placeTos;
	}

	public ArrayList<String> getAvailSportingEquipments(){
		ArrayList<String> availSportingEquipments = new ArrayList<String>();
		for(String nameKey : extraFreeUnitForSportingEquipments.keySet()){
			availSportingEquipments.add(nameKey);
		}
		return availSportingEquipments;
	}
	
	
}

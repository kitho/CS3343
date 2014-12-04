package CS3343.AirlineTicketOrdering.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Class BaggagePlan.
 */
public class BaggagePlan {
	//Unit
	/** The unit. */
	private ArrayList<String> unit;												//KG, Baggage, Size, etc.
	
	//For Passenger's Baggage
	/** The free unit. */
	private Map<String, Map<String, Float>> freeUnit;
	
	/** The extra free unit for sporting equipments. */
	private Map<String, Map<String, Float>> extraFreeUnitForSportingEquipments;	//<NameOfEquipments, <Unit, Num>>
	
	/** The extra fee per unit. */
	private Map<String, Float> extraFeePerUnit;									//<Unit, Fee>
	
	//Extra extra fee
	/** The extra extra fee for level. */
	private Map<String, Map<String, Float>> extraExtraFeeForLevel;			//<FlightClass, <Level, Fee>>
	
	/** The extra extra fee condtion. */
	private Map<String, Map<String, ArrayList<Float>>> extraExtraFeeCondtion;	//<Level, <Unit, Num Range>>
	
	//For Pet Placed in Baggage
	/** The pet fee. */
	private Map<String, Float> petFee;											//<Unit,Pat Fee>
	
	//For Pet If Exceed Normal Fee's Weight or Size
	/** The extra extra pet fee for level. */
	private Map<String, Map<String, Float>> extraExtraPetFeeForLevel;		//<FlightClass, <Level, Fee>>
	
	/** The extra extra pet fee condtion. */
	private Map<String, Map<String, ArrayList<Float>>> extraExtraPetFeeCondtion;//<Level, <Unit, Num Range>>
	
	//For place
	/** The place froms. */
	private List<String> placeFroms = new ArrayList<String>();
	
	/** The place tos. */
	private List<String> placeTos = new ArrayList<String>();
	
	/**
	 * Instantiates a new baggage plan.
	 */
	public BaggagePlan(){
		super();
	}

	/**
	 * Gets the unit.
	 *
	 * @return the unit
	 */
	public ArrayList<String> getUnit() {
		return unit;
	}

	/**
	 * Sets the unit.
	 *
	 * @param unit the new unit
	 */
	public void setUnit(ArrayList<String> unit) {
		this.unit = unit;
	}

	/**
	 * Gets the free unit.
	 *
	 * @return the free unit
	 */
	public Map<String, Map<String, Float>> getFreeUnit() {
		return freeUnit;
	}

	/**
	 * Sets the free unit.
	 *
	 * @param freeUnit the free unit
	 */
	public void setFreeUnit(Map<String, Map<String, Float>> freeUnit) {
		this.freeUnit = freeUnit;
	}

	/**
	 * Gets the extra free unit for sporting equipments.
	 *
	 * @return the extra free unit for sporting equipments
	 */
	public Map<String, Map<String, Float>> getExtraFreeUnitForSportingEquipments() {
		return extraFreeUnitForSportingEquipments;
	}

	/**
	 * Sets the extra free unit for sporting equipments.
	 *
	 * @param extraFreeUnitForSportingEquipments the extra free unit for sporting equipments
	 */
	public void setExtraFreeUnitForSportingEquipments(
			Map<String, Map<String, Float>> extraFreeUnitForSportingEquipments) {
		this.extraFreeUnitForSportingEquipments = extraFreeUnitForSportingEquipments;
	}

	/**
	 * Gets the extra fee per unit.
	 *
	 * @return the extra fee per unit
	 */
	public Map<String, Float> getExtraFeePerUnit() {
		return extraFeePerUnit;
	}

	/**
	 * Sets the extra fee per unit.
	 *
	 * @param extraFeePerUnit the extra fee per unit
	 */
	public void setExtraFeePerUnit(Map<String, Float> extraFeePerUnit) {
		this.extraFeePerUnit = extraFeePerUnit;
	}

	/**
	 * Gets the extra extra fee for level.
	 *
	 * @return the extra extra fee for level
	 */
	public Map<String, Map<String, Float>> getExtraExtraFeeForLevel() {
		return extraExtraFeeForLevel;
	}

	/**
	 * Sets the extra extra fee for level.
	 *
	 * @param extraExtraFeeForLevel the extra extra fee for level
	 */
	public void setExtraExtraFeeForLevel(
			Map<String, Map<String, Float>> extraExtraFeeForLevel) {
		this.extraExtraFeeForLevel = extraExtraFeeForLevel;
	}

	/**
	 * Gets the extra extra fee condtion.
	 *
	 * @return the extra extra fee condtion
	 */
	public Map<String, Map<String, ArrayList<Float>>> getExtraExtraFeeCondtion() {
		return extraExtraFeeCondtion;
	}

	/**
	 * Sets the extra extra fee condtion.
	 *
	 * @param extraExtraFeeCondtion the extra extra fee condtion
	 */
	public void setExtraExtraFeeCondtion(
			Map<String, Map<String, ArrayList<Float>>> extraExtraFeeCondtion) {
		this.extraExtraFeeCondtion = extraExtraFeeCondtion;
	}

	/**
	 * Gets the pet fee.
	 *
	 * @return the pet fee
	 */
	public Map<String, Float> getPetFee() {
		return petFee;
	}

	/**
	 * Sets the pet fee.
	 *
	 * @param petFee the pet fee
	 */
	public void setPetFee(Map<String, Float> petFee) {
		this.petFee = petFee;
	}

	/**
	 * Gets the extra extra pet fee for level.
	 *
	 * @return the extra extra pet fee for level
	 */
	public Map<String, Map<String, Float>> getExtraExtraPetFeeForLevel() {
		return extraExtraPetFeeForLevel;
	}

	/**
	 * Sets the extra extra pet fee for level.
	 *
	 * @param extraExtraPetFeeForLevel the extra extra pet fee for level
	 */
	public void setExtraExtraPetFeeForLevel(
			Map<String, Map<String, Float>> extraExtraPetFeeForLevel) {
		this.extraExtraPetFeeForLevel = extraExtraPetFeeForLevel;
	}

	/**
	 * Gets the extra extra pet fee condtion.
	 *
	 * @return the extra extra pet fee condtion
	 */
	public Map<String, Map<String, ArrayList<Float>>> getExtraExtraPetFeeCondtion() {
		return extraExtraPetFeeCondtion;
	}

	/**
	 * Sets the extra extra pet fee condtion.
	 *
	 * @param extraExtraPetFeeCondtion the extra extra pet fee condtion
	 */
	public void setExtraExtraPetFeeCondtion(
			Map<String, Map<String, ArrayList<Float>>> extraExtraPetFeeCondtion) {
		this.extraExtraPetFeeCondtion = extraExtraPetFeeCondtion;
	}
	
	
	
	/**
	 * Gets the place froms.
	 *
	 * @return the place froms
	 */
	public List<String> getPlaceFroms() {
		return placeFroms;
	}

	/**
	 * Sets the place froms.
	 *
	 * @param placeFroms the new place froms
	 */
	public void setPlaceFroms(List<String> placeFroms) {
		this.placeFroms = placeFroms;
	}

	/**
	 * Gets the place tos.
	 *
	 * @return the place tos
	 */
	public List<String> getPlaceTos() {
		return placeTos;
	}

	/**
	 * Sets the place tos.
	 *
	 * @param placeTos the new place tos
	 */
	public void setPlaceTos(List<String> placeTos) {
		this.placeTos = placeTos;
	}

	/**
	 * Gets the avail sporting equipments.
	 *
	 * @return the avail sporting equipments
	 */
	public ArrayList<String> getAvailSportingEquipments(){
		ArrayList<String> availSportingEquipments = new ArrayList<String>();
		for(String nameKey : extraFreeUnitForSportingEquipments.keySet()){
			availSportingEquipments.add(nameKey);
		}
		return availSportingEquipments;
	}
	
	
}

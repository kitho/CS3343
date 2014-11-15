package CS3343.AirlineTicketOrdering.Baggage;

import java.util.ArrayList;
import java.util.Map;

public interface BaggageFeeCalculator {
	public static final String UNIT_KG ="KG";
	public static final String UNIT_INCH ="Inch";
	public static final String UNIT_PIECE ="Piece";
	
	/**
	 * Calculate basic fee
	 * @param currentUnit
	 * @param basicFeePerUnits
	 * @return float fee
	 */
	public abstract float calBasicFee(Map<String, Float> currentUnit, Map<String, Float> basicFeePerUnits);
	
	
	/**
	 * Calculate extra fee
	 * @param currentUnitNum
	 * @param flightClass
	 * @param amountOfPassenger
	 * @param extraFeeLevel
	 * @param extraFeeCondition
	 * @return float fee
	 */
	public abstract float calExtraFee(
			Map<String, Float> currentUnitNum,
			String flightClass, int amountOfPassenger,
			Map<String, Map<String, Float>> extraFeeLevel,
			Map<String, Map<String, ArrayList<Float>>> extraFeeCondition);
}

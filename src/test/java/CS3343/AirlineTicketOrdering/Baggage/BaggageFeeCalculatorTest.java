package CS3343.AirlineTicketOrdering.Baggage;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import CS3343.AirlineTicketOrdering.Baggage.Impl.BaggageFeeCalculatorImpl;

public class BaggageFeeCalculatorTest {
	private Map<String, Float> currentUnit = null;
	private Map<String, Float> basicFeePerUnits = null;
	private String flightClass = null;
	private int amountOfPassenger;
	private Map<String, Map<String, Float>> extraFeeLevel = null;
	private Map<String, Map<String, ArrayList<Float>>> extraFeeCondition = null;
	
	
	@Before
	public void initEnv(){
		extraFeeLevel = new HashMap<String, Map<String, Float>>();
		extraFeeCondition = new HashMap<String, Map<String, ArrayList<Float>>>();
		
		flightClass = "Economy Class";
		amountOfPassenger = 1;
		
		Map<String, Float> levelFee = new HashMap<String, Float>();
		levelFee.put("1", 100f);
		levelFee.put("2", 100f);
		levelFee.put("3", 400f);
		levelFee.put("4", 400f);
		extraFeeLevel.put(flightClass, levelFee);
		
		//Level 1. 25-30KG
		Map<String, Map<String, ArrayList<Float>>> condtions = new HashMap<String, Map<String, ArrayList<Float>>>();
		ArrayList<Float> unitRangeNum = new ArrayList<Float>();
		unitRangeNum = new ArrayList<Float>();
		unitRangeNum.add(25f);
		unitRangeNum.add(30f);
		Map<String, ArrayList<Float>> unitRange = new HashMap<String, ArrayList<Float>>();
		unitRange = new HashMap<String, ArrayList<Float>>();
		unitRange.put("KG", unitRangeNum);
		extraFeeCondition.put("1", unitRange);
		//Level 2. 40-50 Inch
		unitRangeNum = new ArrayList<Float>();
		unitRangeNum.add(40f);
		unitRangeNum.add(50f);
		unitRange = new HashMap<String, ArrayList<Float>>();
		unitRange.put("Inch", unitRangeNum);
		extraFeeCondition.put("2", unitRange);
		//Level 3. > 31 KG
		unitRangeNum = new ArrayList<Float>();
		unitRangeNum.add(31f);
		unitRangeNum.add(9999f);
		unitRange = new HashMap<String, ArrayList<Float>>();
		unitRange.put("KG", unitRangeNum);
		extraFeeCondition.put("3", unitRange);
		//Level 4. > 51 Inch
		unitRangeNum = new ArrayList<Float>();
		unitRangeNum.add(51f);
		unitRangeNum.add(9999f);
		unitRange = new HashMap<String, ArrayList<Float>>();
		unitRange.put("Inch", unitRangeNum);
		extraFeeCondition.put("4", unitRange);
	}
	
	@Test
	public void calBasicFeeWithNegative() {
		currentUnit = new HashMap<String, Float>();
		basicFeePerUnits = new HashMap<String, Float>();
		
		currentUnit.put("KG", -20f);
		basicFeePerUnits.put("KG", 100f);
		
		BaggageFeeCalculator baggageFeeCalculator = new BaggageFeeCalculatorImpl();
		float fee = baggageFeeCalculator.calBasicFee(currentUnit, basicFeePerUnits);
		
		assertEquals(-2000f, fee, 0);
	}
	
	@Test
	public void calBasicFeeWithPositiveUnit() {
		currentUnit = new HashMap<String, Float>();
		basicFeePerUnits = new HashMap<String, Float>();
		
		currentUnit.put("KG", 20f);
		basicFeePerUnits.put("KG", 100f);
		
		BaggageFeeCalculator baggageFeeCalculator = new BaggageFeeCalculatorImpl();
		float fee = baggageFeeCalculator.calBasicFee(currentUnit, basicFeePerUnits);
		
		assertEquals(2000f, fee, 0);
	}
	
	@Test
	public void calBasicFeeWithMoreThanOneUnit() {
		currentUnit = new HashMap<String, Float>();
		basicFeePerUnits = new HashMap<String, Float>();
		
		currentUnit.put("KG", -20f);
		currentUnit.put("KG", -30f);
		basicFeePerUnits.put("KG", 100f);
		
		BaggageFeeCalculator baggageFeeCalculator = new BaggageFeeCalculatorImpl();
		float fee = baggageFeeCalculator.calBasicFee(currentUnit, basicFeePerUnits);
		
		assertEquals(-3000f, fee, 0);
	}
	
	@Test
	public void calBasicFeeWithEmptyList() {
		currentUnit = new HashMap<String, Float>();
		basicFeePerUnits = new HashMap<String, Float>();
		
		BaggageFeeCalculator baggageFeeCalculator = new BaggageFeeCalculatorImpl();
		float fee = baggageFeeCalculator.calBasicFee(currentUnit, basicFeePerUnits);
		
		assertEquals(0f, fee, 0);
	}
	
	@Test
	public void calBasicFeeWithNonContainKey() {
		currentUnit = new HashMap<String, Float>();
		basicFeePerUnits = new HashMap<String, Float>();
		
		currentUnit.put("KG", -20f);
		
		BaggageFeeCalculator baggageFeeCalculator = new BaggageFeeCalculatorImpl();
		float fee = baggageFeeCalculator.calBasicFee(currentUnit, basicFeePerUnits);
		
		assertEquals(0f, fee, 0);
	}
	
	
	@Test
	public void calExtraFeeSatisfyFirstLevel(){
		
		Map<String, Float> currentUnitNum = new HashMap<String, Float>();
		currentUnitNum.put("KG",30f);
		currentUnitNum.put("Piece",1f);
		currentUnitNum.put("Inch",40f);
		
		BaggageFeeCalculator baggageFeeCalculator = new BaggageFeeCalculatorImpl();
		float fee = baggageFeeCalculator.calExtraFee(
											currentUnitNum, 
											flightClass, 
											amountOfPassenger, 
											extraFeeLevel, 
											extraFeeCondition);
		
		
		assertEquals(-100f, fee, 0);
	}
	
	@Test
	public void calExtraFeeSatisfyLastLevel(){
		
		Map<String, Float> currentUnitNum = new HashMap<String, Float>();
		currentUnitNum.put("KG",100f);
		currentUnitNum.put("Piece",1f);
		currentUnitNum.put("Inch",40f);
		
		BaggageFeeCalculator baggageFeeCalculator = new BaggageFeeCalculatorImpl();
		float fee = baggageFeeCalculator.calExtraFee(
											currentUnitNum, 
											flightClass, 
											amountOfPassenger, 
											extraFeeLevel, 
											extraFeeCondition);
		
		
		assertEquals(-400f, fee, 0);
	}
	
	@Test
	public void calExtraFeeWithOutOfConditionalRange(){
		
		Map<String, Float> currentUnitNum = new HashMap<String, Float>();
		currentUnitNum.put("KG",10f);
		currentUnitNum.put("Piece",1f);
		currentUnitNum.put("Inch",10f);
		
		BaggageFeeCalculator baggageFeeCalculator = new BaggageFeeCalculatorImpl();
		float fee = baggageFeeCalculator.calExtraFee(
											currentUnitNum, 
											flightClass, 
											amountOfPassenger, 
											extraFeeLevel, 
											extraFeeCondition);
		
		
		assertEquals(-0f, fee, 0);
	}
	
	@Test
	public void calExtraFeeWithAbnormalKG(){
		Map<String, Float> currentUnitNum = new HashMap<String, Float>();
		currentUnitNum.put("KG",10000f);
		currentUnitNum.put("Piece",1f);
		currentUnitNum.put("Inch",40f);
		
		BaggageFeeCalculator baggageFeeCalculator = new BaggageFeeCalculatorImpl();
		float fee = baggageFeeCalculator.calExtraFee(
											currentUnitNum, 
											flightClass, 
											amountOfPassenger, 
											extraFeeLevel, 
											extraFeeCondition);
		
		
		assertEquals(-400f, fee, 0);
	}

}

package CS3343.AirlineTicketOrdering.Baggage.Data;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class BaggageDataPreparerTest {
	private BaggageDataPreparer preparer;
	
	@Before
	public void initEnv(){
		preparer = new BaggageDataPreparer();
	}
	
	@Test
	public void increaseFreeUnitByNumberOfPassengers() {
		Map<String, Float> freeUnit = new HashMap<String, Float>();
		freeUnit.put("KG", 20f);
		int numberOfPassengers = 2;
		Map<String, Float> resultUnit = preparer.increaseFreeUnit(freeUnit, numberOfPassengers);
		
		Map<String, Float> expectedUnit = new HashMap<String, Float>();
		expectedUnit.put("KG", 40f);
		assertEquals(expectedUnit, resultUnit);
	}
	
	@Test
	public void increaseFreeUnitByZeroPassenger() {
		Map<String, Float> freeUnit = new HashMap<String, Float>();
		freeUnit.put("KG", 20f);
		int numberOfPassengers = 0;
		Map<String, Float> resultUnit = preparer.increaseFreeUnit(freeUnit, numberOfPassengers);
		
		Map<String, Float> expectedUnit = new HashMap<String, Float>();
		expectedUnit.put("KG", 0f);
		assertEquals(expectedUnit, resultUnit);
	}
	
	@Test
	public void increaseFreeUnitByOnePassenger() {
		Map<String, Float> freeUnit = new HashMap<String, Float>();
		freeUnit.put("KG", 20f);
		int numberOfPassengers = 1;
		Map<String, Float> resultUnit = preparer.increaseFreeUnit(freeUnit, numberOfPassengers);
		
		Map<String, Float> expectedUnit = new HashMap<String, Float>();
		expectedUnit.put("KG", 20f);
		assertEquals(expectedUnit, resultUnit);
	}
	
	@Test
	public void subBaggageUnitNumByEqualUnit() {
		Map<String, Float> remainingFreeUnit = new HashMap<String, Float>();
		Map<String, Float> unitNumForBaggage = new HashMap<String, Float>();
		
		remainingFreeUnit.put("KG", 20f);
		unitNumForBaggage.put("KG", 20f);
		Map<String, Float> resultUnit = preparer.subBaggageUnitNum(remainingFreeUnit, unitNumForBaggage);
		
		Map<String, Float> expectedUnit = new HashMap<String, Float>();
		expectedUnit.put("KG", 0f);
		assertEquals(expectedUnit, resultUnit);
	}
	
	@Test
	public void subBaggageUnitNumByMoreUnit() {
		Map<String, Float> remainingFreeUnit = new HashMap<String, Float>();
		Map<String, Float> unitNumForBaggage = new HashMap<String, Float>();
		
		remainingFreeUnit.put("KG", 20f);
		unitNumForBaggage.put("KG", 30f);
		Map<String, Float> resultUnit = preparer.subBaggageUnitNum(remainingFreeUnit, unitNumForBaggage);
		
		Map<String, Float> expectedUnit = new HashMap<String, Float>();
		expectedUnit.put("KG", -10f);
		assertEquals(expectedUnit, resultUnit);
	}
	
	@Test
	public void subBaggageUnitNumByLessUnit() {
		Map<String, Float> remainingFreeUnit = new HashMap<String, Float>();
		Map<String, Float> unitNumForBaggage = new HashMap<String, Float>();
		
		remainingFreeUnit.put("KG", 20f);
		unitNumForBaggage.put("KG", 10f);
		Map<String, Float> resultUnit = preparer.subBaggageUnitNum(remainingFreeUnit, unitNumForBaggage);
		
		Map<String, Float> expectedUnit = new HashMap<String, Float>();
		expectedUnit.put("KG", 10f);
		assertEquals(expectedUnit, resultUnit);
	}
	
	@Test
	public void subBaggageUnitNumWithNotFound() {
		Map<String, Float> remainingFreeUnit = new HashMap<String, Float>();
		Map<String, Float> unitNumForBaggage = new HashMap<String, Float>();
		
		remainingFreeUnit.put("NOT", 20f);
		unitNumForBaggage.put("NOT2", 10f);
		Map<String, Float> resultUnit = preparer.subBaggageUnitNum(remainingFreeUnit, unitNumForBaggage);
		
		Map<String, Float> expectedUnit = new HashMap<String, Float>();
		expectedUnit.put("NOT", 0f);
		assertEquals(expectedUnit, resultUnit);
	}
	
	@Test
	public void plusSportingEquipmentsWithPositiveUnit() {
		Map<String, Float> remainingFreeUnit = new HashMap<String, Float>();
		ArrayList<String> sportingEquipments = new ArrayList<String>();
		Map<String, Map<String, Float>> extraFreeUnitForSE = new HashMap<String, Map<String, Float>>();
		Map<String, Float> freeUnit = new HashMap<String, Float>();

		sportingEquipments.add("Bicycles equipment");
		freeUnit.put("KG", 20f);
		extraFreeUnitForSE.put("Bicycles equipment", freeUnit);
		remainingFreeUnit.put("KG", 20f);
		
		Map<String, Float> resultUnit = preparer.plusSportingEquipments(remainingFreeUnit, sportingEquipments, extraFreeUnitForSE);
		
		Map<String, Float> expectedUnit = new HashMap<String, Float>();
		expectedUnit.put("KG", 40f);
		assertEquals(expectedUnit, resultUnit);
	}
	
	@Test
	public void plusSportingEquipmentsWithNegativeUnit() {
		Map<String, Float> remainingFreeUnit = new HashMap<String, Float>();
		ArrayList<String> sportingEquipments = new ArrayList<String>();
		Map<String, Map<String, Float>> extraFreeUnitForSE = new HashMap<String, Map<String, Float>>();
		Map<String, Float> freeUnit = new HashMap<String, Float>();

		sportingEquipments.add("Bicycles equipment");
		freeUnit.put("KG", 20f);
		extraFreeUnitForSE.put("Bicycles equipment", freeUnit);
		remainingFreeUnit.put("KG", -10f);
		
		Map<String, Float> resultUnit = preparer.plusSportingEquipments(remainingFreeUnit, sportingEquipments, extraFreeUnitForSE);
		
		Map<String, Float> expectedUnit = new HashMap<String, Float>();
		expectedUnit.put("KG", 10f);
		assertEquals(expectedUnit, resultUnit);
	}

}

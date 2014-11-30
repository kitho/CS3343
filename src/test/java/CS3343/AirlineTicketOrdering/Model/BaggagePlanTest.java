package CS3343.AirlineTicketOrdering.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BaggagePlanTest {

	@Test
	public void testBaggagePlan(){
		BaggagePlan bp = new BaggagePlan();
		
		ArrayList<String> unit = new ArrayList<String>();												
		Map<String, Map<String, Float>> freeUnit = new HashMap<String,Map<String,Float>>();
		Map<String, Map<String, Float>> extraFreeUnitForSportingEquipments = new HashMap<String,Map<String,Float>>();
		Map<String, Float> extraFeePerUnit = new HashMap<String,Float>();
		Map<String, Map<String, Float>> extraExtraFeeForLevel =  new HashMap<String,Map<String,Float>>();			
		Map<String, Map<String, ArrayList<Float>>> extraExtraFeeCondtion = new HashMap<String,Map<String,ArrayList<Float>>>();	
		Map<String, Float> petFee = new HashMap<String,Float>();										
		Map<String, Map<String, Float>> extraExtraPetFeeForLevel = new HashMap<String,Map<String,Float>>();	
		Map<String, Map<String, ArrayList<Float>>> extraExtraPetFeeCondtion = new HashMap<String,Map<String,ArrayList<Float>>>();
		List<String> placeFroms = new ArrayList<String>();
		List<String> placeTos = new ArrayList<String>();
		
		bp.setUnit(unit);
		bp.setFreeUnit(freeUnit);
		bp.setExtraFreeUnitForSportingEquipments(extraFreeUnitForSportingEquipments);
		bp.setExtraFeePerUnit(extraFeePerUnit);
		bp.setExtraExtraFeeForLevel(extraExtraFeeForLevel);
		bp.setExtraExtraFeeCondtion(extraExtraFeeCondtion);
		bp.setPetFee(petFee);
		bp.setExtraExtraPetFeeForLevel(extraExtraPetFeeForLevel);
		bp.setExtraExtraPetFeeCondtion(extraExtraPetFeeCondtion);
		bp.setPlaceFroms(placeFroms);
		bp.setPlaceTos(placeTos);
		
		assertThat(unit,is(bp.getUnit()));
		assertThat(freeUnit, is(bp.getFreeUnit()));
		assertThat(extraFreeUnitForSportingEquipments, is(bp.getExtraFreeUnitForSportingEquipments()));
		assertThat(extraFeePerUnit, is(bp.getExtraFeePerUnit()));
		assertThat(extraExtraFeeForLevel, is(bp.getExtraExtraFeeForLevel()));
		assertThat(extraExtraFeeCondtion, is(bp.getExtraExtraFeeCondtion()));
		assertThat(petFee, is(bp.getPetFee()));
		assertThat(extraExtraPetFeeForLevel, is(bp.getExtraExtraPetFeeForLevel()));
		assertThat(extraExtraPetFeeCondtion, is(bp.getExtraExtraPetFeeCondtion()));
		assertThat(placeFroms, is(bp.getPlaceFroms()));
		assertThat(placeTos, is(bp.getPlaceTos()));
		assertThat(new ArrayList<String>() , is(bp.getAvailSportingEquipments()));
		
	}
}

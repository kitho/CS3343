package CS3343.AirlineTicketOrdering.Parser.Impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import CS3343.AirlineTicketOrdering.Model.BaggagePlan;
import CS3343.AirlineTicketOrdering.Parser.Parser;

public class BaggagePlanParser implements Parser<BaggagePlan> {

	//For Passenger's Baggage
	private Map<String, Map<String, Float>> freeUnits;
	private Map<String, Map<String, Float>> extraFreeUnitForSportingEquipments;	//<NameOfEquipments, <Unit, Num>>
	private Map<String, Float> extraFeePerUnit;									//<Unit, Fee>
	
	//Extra extra fee
	private Map<String, Map<String, Float>> extraExtraFeeForLevels;			//<FlightClass, <Level, Fee>>
	private Map<String, Map<String, ArrayList<Float>>> extraExtraFeeCondtions;	//<Level, <Unit, Num Range>>
	private Map<String, Float> extraExtraFees;
	
	//For Pet Placed in Baggage
	private Map<String, Float> petFee;											//<Unit,Pat Fee>
	
	//For Pet If Exceed Normal Fee's Weight or Size
	private Map<String, Map<String, Float>> extraPetFeeForLevels;		//<FlightClass, <Level, Fee>>
	private Map<String, Map<String, ArrayList<Float>>> extraPetFeeCondtions;//<Level, <Unit, Num Range>>
	private Map<String, Float> extraPetFees;
	
	//For place
	private List<String> placeFroms = new ArrayList<String>();
	private List<String> placeTos = new ArrayList<String>();
	
	private String flightClass;
	
	private BaggagePlan baggagePlan;
	
	public BaggagePlanParser(){
		
	}

	@Override
	public BaggagePlan parseString(String line) throws ParseException {
		String[] dataStr = line.split(",");
		baggagePlan = new BaggagePlan();

		//Start
		if(dataStr.length > 0 && dataStr[0].equals("S")){
			baggagePlan = new BaggagePlan();
			freeUnits = new HashMap<String, Map<String, Float>>();
			extraFreeUnitForSportingEquipments = new HashMap<String, Map<String, Float>>();
			extraFeePerUnit = new HashMap<String, Float>();
			extraExtraFeeForLevels = new HashMap<String, Map<String, Float>>();
			extraExtraFeeCondtions = new HashMap<String, Map<String, ArrayList<Float>>>();
			extraExtraFees = new HashMap<String, Float>();
			petFee = new HashMap<String, Float>();
			extraPetFeeForLevels = new HashMap<String, Map<String, Float>>();
			extraPetFeeCondtions = new HashMap<String, Map<String, ArrayList<Float>>>();
			extraPetFees = new HashMap<String, Float>();
			placeFroms = new ArrayList<String>();
			placeTos = new ArrayList<String>();
			flightClass = null;
		}
		
		//Free units
		if(dataStr.length > 4 && !dataStr[2].equals("") && !dataStr[3].equals("") && !dataStr[4].equals("")){
			Map<String, Float> freeUnitDetails = new HashMap<String, Float>();
			freeUnitDetails.put(dataStr[3], Float.parseFloat(dataStr[4]));
			flightClass = dataStr[2];
			freeUnits.put(flightClass, freeUnitDetails);
		}
		
		//Sporting
		if(dataStr.length > 7 && !dataStr[5].equals("") && !dataStr[6].equals("") && !dataStr[7].equals("")){
			Map<String, Float> freeSEUnitDetails = new HashMap<String, Float>();
			freeSEUnitDetails.put(dataStr[6], Float.parseFloat(dataStr[7]));
			extraFreeUnitForSportingEquipments.put(dataStr[5], freeSEUnitDetails);
		}
		
		//Extra fee
		if(dataStr.length > 9 && !dataStr[8].equals("") && !dataStr[9].equals("")){
			extraFeePerUnit.put(dataStr[8], Float.parseFloat(dataStr[9]));
		}
		
		
		//Extra extra fee
		if(dataStr.length > 15 && !dataStr[11].equals("") && !dataStr[12].equals("") && !dataStr[13].equals("")
				 && !dataStr[14].equals("") && !dataStr[15].equals("")){
			if(!dataStr[10].equals("")){
				flightClass = dataStr[10];
				extraExtraFees = new HashMap<String, Float>();
			}
			extraExtraFees.put(dataStr[11], Float.parseFloat(dataStr[12]));
			extraExtraFeeForLevels.put(flightClass, extraExtraFees);
			
			ArrayList<Float> unitRangeNum = new ArrayList<Float>();
			unitRangeNum.add(Float.parseFloat(dataStr[14]));
			unitRangeNum.add(Float.parseFloat(dataStr[15]));
			
			Map<String, ArrayList<Float>> unitRange = new HashMap<String, ArrayList<Float>>();
			unitRange.put(dataStr[13], unitRangeNum);
			
			extraExtraFeeCondtions.put(dataStr[11], unitRange);
		}
		
		
		//Pet fee
		if(dataStr.length > 17 && !dataStr[16].equals("") && !dataStr[17].equals("")){
			petFee.put(dataStr[16], Float.parseFloat(dataStr[17]));
		}
		
		//Extra pet fee
		if(dataStr.length > 23 && !dataStr[19].equals("") && !dataStr[20].equals("") && !dataStr[21].equals("")
				 && !dataStr[22].equals("") && !dataStr[23].equals("")){
			if(!dataStr[18].equals("")){
				flightClass = dataStr[10];
				extraPetFees = new HashMap<String, Float>();
			}
			extraPetFees.put(dataStr[19], Float.parseFloat(dataStr[20]));
			extraPetFeeForLevels.put(flightClass, extraExtraFees);
			
			ArrayList<Float> unitRangeNum = new ArrayList<Float>();
			unitRangeNum.add(Float.parseFloat(dataStr[22]));
			unitRangeNum.add(Float.parseFloat(dataStr[23]));
			
			Map<String, ArrayList<Float>> unitRange = new HashMap<String, ArrayList<Float>>();
			unitRange.put(dataStr[21], unitRangeNum);
			
			extraPetFeeCondtions.put(dataStr[19], unitRange);
		}
		
		
		//Supporting location
		if(dataStr.length >= 25){	//Last column must check these
			if(!dataStr[24].equals("") && !dataStr[25].equals("")){
				placeFroms.add(dataStr[24]);
				placeTos.add(dataStr[25]);
			}
		}
		
		
		//End
		if(dataStr.length > 0 && dataStr[0].equals("E")){
			ArrayList<String> unit = new ArrayList<String>();
			unit.add("KG");
			unit.add("Piece");
			unit.add("Inch");
			baggagePlan.setUnit(unit);
			baggagePlan.setFreeUnit(freeUnits);
			baggagePlan.setExtraFreeUnitForSportingEquipments(extraFreeUnitForSportingEquipments);
			baggagePlan.setExtraFeePerUnit(extraFeePerUnit);
			baggagePlan.setPetFee(petFee);
			baggagePlan.setExtraExtraFeeForLevel(extraExtraFeeForLevels);
			baggagePlan.setExtraExtraFeeCondtion(extraExtraFeeCondtions);
			baggagePlan.setExtraExtraPetFeeForLevel(extraPetFeeForLevels);
			baggagePlan.setExtraExtraPetFeeCondtion(extraPetFeeCondtions);
			baggagePlan.setPlaceFroms(placeFroms);
			baggagePlan.setPlaceTos(placeTos);
			return baggagePlan;
		}
		return null;
	}

	@Override
	public String parseObject(BaggagePlan plan) {
		return null;
	}

	
	
}

package CS3343.AirlineTicketOrdering.Baggage.Temp;

import java.util.ArrayList;

public class BaggagePlan {
	//For Passenger's Baggage
	private float freeFirstClassWeight;
	private float freeBusinessClassWeight;
	private float freePremiumEconomyWeight;
	private float freeEconomyWeight;
	private float freeSportingEquipmentWeight;
	private ArrayList<String> extraAllowanceForSportingEnquipments;
	private float extraFeePreKG;
	private float extraFeePreBaggage;
	private String extraFeeBasedOn;			//Value is 'KG' or 'Baggage'
	
	//For Pet Placed in Baggage
	private float petFeePreKG;
	private float petFeePreBaggage;
	private String petFeeBasedOn;		//Value is 'KG' or 'Baggage'
	private float normalFeeMaxWeight;	//Max Weight for normal fee
	private float normalFeeMaxSize;		//Max Size for normal fee
	
	//For Pet If Exceed Normal Fee's Weight or Size
	private float extraFeeMaxWeight;	//Max Weight of Extra Fee
	private float extraFeeMaxSize;		//Max Size of Extra Fee
	private float extraFeeForLevel1;	//Less than extraFeeMaxWeight & normalFeeMaxSize
	private float extraFeeForLevel2;	//Less than extraFeeMaxSize & normalFeeMaxWeight
	private float extraFeeForLevel3;	//Less than extraFeeMaxWeight & extraFeeMaxSize
	private float extraFeeForLevel4;	//More than extraFeeMaxWeight & LextraFeeMaxSize
	
	public float getFreeFirstClassWeight() {
		return freeFirstClassWeight;
	}
	public void setFreeFirstClassWeight(float freeFirstClassWeight) {
		this.freeFirstClassWeight = freeFirstClassWeight;
	}
	public float getFreeBusinessClassWeight() {
		return freeBusinessClassWeight;
	}
	public void setFreeBusinessClassWeight(float freeBusinessClassWeight) {
		this.freeBusinessClassWeight = freeBusinessClassWeight;
	}
	public float getFreePremiumEconomyWeight() {
		return freePremiumEconomyWeight;
	}
	public void setFreePremiumEconomyWeight(float freePremiumEconomyWeight) {
		this.freePremiumEconomyWeight = freePremiumEconomyWeight;
	}
	public float getFreeEconomyWeight() {
		return freeEconomyWeight;
	}
	public void setFreeEconomyWeight(float freeEconomyWeight) {
		this.freeEconomyWeight = freeEconomyWeight;
	}
	public float getFreeSportingEquipmentWeight() {
		return freeSportingEquipmentWeight;
	}
	public void setFreeSportingEquipmentWeight(float freeSportingEquipmentWeight) {
		this.freeSportingEquipmentWeight = freeSportingEquipmentWeight;
	}
	public ArrayList<String> getExtraAllowanceForSportingEnquipments() {
		return extraAllowanceForSportingEnquipments;
	}
	public void setExtraAllowanceForSportingEnquipments(
			ArrayList<String> extraAllowanceForSportingEnquipments) {
		this.extraAllowanceForSportingEnquipments = extraAllowanceForSportingEnquipments;
	}
	public float getExtraFeePreKG() {
		return extraFeePreKG;
	}
	public void setExtraFeePreKG(float extraFeePreKG) {
		this.extraFeePreKG = extraFeePreKG;
	}
	public float getExtraFeePreBaggage() {
		return extraFeePreBaggage;
	}
	public void setExtraFeePreBaggage(float extraFeePreBaggage) {
		this.extraFeePreBaggage = extraFeePreBaggage;
	}
	public String getExtraFeeBasedOn() {
		return extraFeeBasedOn;
	}
	public void setExtraFeeBasedOn(String extraFeeBasedOn) {
		this.extraFeeBasedOn = extraFeeBasedOn;
	}
	public float getPetFeePreKG() {
		return petFeePreKG;
	}
	public void setPetFeePreKG(float petFeePreKG) {
		this.petFeePreKG = petFeePreKG;
	}
	public float getPetFeePreBaggage() {
		return petFeePreBaggage;
	}
	public void setPetFeePreBaggage(float petFeePreBaggage) {
		this.petFeePreBaggage = petFeePreBaggage;
	}
	public String getPetFeeBasedOn() {
		return petFeeBasedOn;
	}
	public void setPetFeeBasedOn(String petFeeBasedOn) {
		this.petFeeBasedOn = petFeeBasedOn;
	}
	public float getNormalFeeMaxWeight() {
		return normalFeeMaxWeight;
	}
	public void setNormalFeeMaxWeight(float normalFeeMaxWeight) {
		this.normalFeeMaxWeight = normalFeeMaxWeight;
	}
	public float getNormalFeeMaxSize() {
		return normalFeeMaxSize;
	}
	public void setNormalFeeMaxSize(float normalFeeMaxSize) {
		this.normalFeeMaxSize = normalFeeMaxSize;
	}
	public float getExtraFeeMaxWeight() {
		return extraFeeMaxWeight;
	}
	public void setExtraFeeMaxWeight(float extraFeeMaxWeight) {
		this.extraFeeMaxWeight = extraFeeMaxWeight;
	}
	public float getExtraFeeMaxSize() {
		return extraFeeMaxSize;
	}
	public void setExtraFeeMaxSize(float extraFeeMaxSize) {
		this.extraFeeMaxSize = extraFeeMaxSize;
	}
	public float getExtraFeeForLevel1() {
		return extraFeeForLevel1;
	}
	public void setExtraFeeForLevel1(float extraFeeForLevel1) {
		this.extraFeeForLevel1 = extraFeeForLevel1;
	}
	public float getExtraFeeForLevel2() {
		return extraFeeForLevel2;
	}
	public void setExtraFeeForLevel2(float extraFeeForLevel2) {
		this.extraFeeForLevel2 = extraFeeForLevel2;
	}
	public float getExtraFeeForLevel3() {
		return extraFeeForLevel3;
	}
	public void setExtraFeeForLevel3(float extraFeeForLevel3) {
		this.extraFeeForLevel3 = extraFeeForLevel3;
	}
	public float getExtraFeeForLevel4() {
		return extraFeeForLevel4;
	}
	public void setExtraFeeForLevel4(float extraFeeForLevel4) {
		this.extraFeeForLevel4 = extraFeeForLevel4;
	}

}

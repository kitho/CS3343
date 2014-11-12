package CS3343.AirlineTicketOrdering.AirlineCompany;

public enum AirlineCompanyShort {
	CPA("Cathay Pacific Airways"),CRK("Hong Kong Airlines"),HDA("Hong Kong Dragon Airlines");

	private String value;
	
	private AirlineCompanyShort(String value){
		this.value = value;
	}
	
	public String value(){
		return value;
	}
}

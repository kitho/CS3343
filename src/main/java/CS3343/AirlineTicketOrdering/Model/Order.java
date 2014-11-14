package CS3343.AirlineTicketOrdering.Model;

public class Order {
	private int id;
	private Flight flight;
	private int numberOfTicket;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public int getNumberOfTicket() {
		return numberOfTicket;
	}
	public void setNumberOfTicket(int numberOfTicket) {
		this.numberOfTicket = numberOfTicket;
	}
	 

	
}

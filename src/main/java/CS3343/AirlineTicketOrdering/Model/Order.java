package CS3343.AirlineTicketOrdering.Model;

/**
 * The Class Order.
 */
public class Order {
	
	/** The id. */
	private int id;
	
	/** The flight. */
	private Flight flight;
	
	/** The number of ticket. */
	private int numberOfTicket;
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the flight.
	 *
	 * @return the flight
	 */
	public Flight getFlight() {
		return flight;
	}
	
	/**
	 * Sets the flight.
	 *
	 * @param flight the new flight
	 */
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	
	/**
	 * Gets the number of ticket.
	 *
	 * @return the number of ticket
	 */
	public int getNumberOfTicket() {
		return numberOfTicket;
	}
	
	/**
	 * Sets the number of ticket.
	 *
	 * @param numberOfTicket the new number of ticket
	 */
	public void setNumberOfTicket(int numberOfTicket) {
		this.numberOfTicket = numberOfTicket;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((flight == null) ? 0 : flight.hashCode());
		result = prime * result + id;
		result = prime * result + numberOfTicket;
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (flight == null) {
			if (other.flight != null)
				return false;
		} else if (!flight.equals(other.flight))
			return false;
		if (id != other.id)
			return false;
		if (numberOfTicket != other.numberOfTicket)
			return false;
		return true;
	}
	 

	
}

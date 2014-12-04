package CS3343.AirlineTicketOrdering.Model;

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class Flight.
 */
public class Flight {
	
	/** The airline. */
	private String airline;
	
	/** The flight number. */
	private String flightNumber;
	
	/** The travel class. */
	private String travelClass;
	
	/** The depature. */
	private String depature;
	
	/** The destination. */
	private String destination;
	
	/** The depature date time. */
	private Date depatureDateTime;
	
	/** The arrival date time. */
	private Date arrivalDateTime;
	
	/** The available. */
	private int available;
	
	/** The one way price. */
	private double oneWayPrice;
	
	/**
	 * Gets the airline.
	 *
	 * @return the airline
	 */
	public String getAirline() {
		return airline;
	}
	
	/**
	 * Sets the airline.
	 *
	 * @param airline the new airline
	 */
	public void setAirline(String airline) {
		this.airline = airline;
	}
	
	/**
	 * Gets the flight number.
	 *
	 * @return the flight number
	 */
	public String getFlightNumber() {
		return flightNumber;
	}
	
	/**
	 * Sets the flight number.
	 *
	 * @param flightNumber the new flight number
	 */
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	
	/**
	 * Gets the travel class.
	 *
	 * @return the travel class
	 */
	public String getTravelClass() {
		return travelClass;
	}
	
	/**
	 * Sets the travel class.
	 *
	 * @param travelClass the new travel class
	 */
	public void setTravelClass(String travelClass) {
		this.travelClass = travelClass;
	}
	
	/**
	 * Gets the depature.
	 *
	 * @return the depature
	 */
	public String getDepature() {
		return depature;
	}
	
	/**
	 * Sets the depature.
	 *
	 * @param depature the new depature
	 */
	public void setDepature(String depature) {
		this.depature = depature;
	}
	
	/**
	 * Gets the destination.
	 *
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}
	
	/**
	 * Sets the destination.
	 *
	 * @param destination the new destination
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	/**
	 * Gets the depature date time.
	 *
	 * @return the depature date time
	 */
	public Date getDepatureDateTime() {
		return depatureDateTime;
	}
	
	/**
	 * Sets the depature date time.
	 *
	 * @param depatureDateTime the new depature date time
	 */
	public void setDepatureDateTime(Date depatureDateTime) {
		this.depatureDateTime = depatureDateTime;
	}
	
	/**
	 * Gets the arrival date time.
	 *
	 * @return the arrival date time
	 */
	public Date getArrivalDateTime() {
		return arrivalDateTime;
	}
	
	/**
	 * Sets the arrival date time.
	 *
	 * @param arrivalDateTime the new arrival date time
	 */
	public void setArrivalDateTime(Date arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}
	
	/**
	 * Gets the available.
	 *
	 * @return the available
	 */
	public int getAvailable() {
		return available;
	}
	
	/**
	 * Sets the available.
	 *
	 * @param available the new available
	 */
	public void setAvailable(int available) {
		this.available = available;
	}
	
	/**
	 * Gets the one way price.
	 *
	 * @return the one way price
	 */
	public double getOneWayPrice() {
		return oneWayPrice;
	}
	
	/**
	 * Sets the one way price.
	 *
	 * @param oneWayPrice the new one way price
	 */
	public void setOneWayPrice(double oneWayPrice) {
		this.oneWayPrice = oneWayPrice;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((airline == null) ? 0 : airline.hashCode());
		result = prime * result
				+ ((arrivalDateTime == null) ? 0 : arrivalDateTime.hashCode());
		result = prime
				* result
				+ ((depatureDateTime == null) ? 0 : depatureDateTime.hashCode());
		result = prime * result
				+ ((flightNumber == null) ? 0 : flightNumber.hashCode());
		result = prime * result
				+ ((travelClass == null) ? 0 : travelClass.hashCode());
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
		Flight other = (Flight) obj;
		if (airline == null) {
			if (other.airline != null)
				return false;
		} else if (!airline.equals(other.airline))
			return false;
		if (arrivalDateTime == null) {
			if (other.arrivalDateTime != null)
				return false;
		} else if (!arrivalDateTime.equals(other.arrivalDateTime))
			return false;
		if (depatureDateTime == null) {
			if (other.depatureDateTime != null)
				return false;
		} else if (!depatureDateTime.equals(other.depatureDateTime))
			return false;
		if (flightNumber == null) {
			if (other.flightNumber != null)
				return false;
		} else if (!flightNumber.equals(other.flightNumber))
			return false;
		if (travelClass == null) {
			if (other.travelClass != null)
				return false;
		} else if (!travelClass.equals(other.travelClass))
			return false;
		return true;
	}

	
}

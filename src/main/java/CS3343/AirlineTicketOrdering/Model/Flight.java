package CS3343.AirlineTicketOrdering.Model;

import java.util.Date;

public class Flight {
	
	private final String flightNumber;
	private final String travelClass;
	private final String depature;
	private final String destination;
	private final Date depatureDateTime;
	private final Date arrivalDateTime;
	private int available;
	private final double oneWayPrice;
	
	public Flight(String flightNumber, String travelClass, String depature,
			String destination, Date depatureDateTime, Date arrivalDateTime,
			int available, double oneWayPrice) {
		this.flightNumber = flightNumber;
		this.travelClass = travelClass;
		this.depature = depature;
		this.destination = destination;
		this.depatureDateTime = depatureDateTime;
		this.arrivalDateTime = arrivalDateTime;
		this.available = available;
		this.oneWayPrice = oneWayPrice;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public String getTravelClass() {
		return travelClass;
	}

	public String getDepature() {
		return depature;
	}

	public String getDestination() {
		return destination;
	}

	public Date getDepatureDateTime() {
		return depatureDateTime;
	}

	public Date getArrivalDateTime() {
		return arrivalDateTime;
	}

	public int getAvailable() {
		return available;
	}

	public double getOneWayPrice() {
		return oneWayPrice;
	}
	
}

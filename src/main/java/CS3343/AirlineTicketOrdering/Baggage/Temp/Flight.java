//THIS CLASS IS TEMPORARY ONLY

package CS3343.AirlineTicketOrdering.Baggage.Temp;

import java.util.Date;

public class Flight {
	
	private String airline;
	private String flightNumber;
	private FlightClass flightClass;
	private Date depatureDateTime;
	private Date arrivalDateTime;
	private int available;
	private double oneWayPrice;
	private Route route;
	private AirplaneModel airplanModel;
	
	public Route getRoute() {
		return route;
	}
	public void setRoute(Route route) {
		this.route = route;
	}
	public AirplaneModel getAirplanModel() {
		return airplanModel;
	}
	public void setAirplanModel(AirplaneModel airplanModel) {
		this.airplanModel = airplanModel;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	
	public FlightClass getFlightClass() {
		return flightClass;
	}
	public void setFlightClass(FlightClass flightClass) {
		this.flightClass = flightClass;
	}
	public Date getDepatureDateTime() {
		return depatureDateTime;
	}
	public void setDepatureDateTime(Date depatureDateTime) {
		this.depatureDateTime = depatureDateTime;
	}
	public Date getArrivalDateTime() {
		return arrivalDateTime;
	}
	public void setArrivalDateTime(Date arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	public double getOneWayPrice() {
		return oneWayPrice;
	}
	public void setOneWayPrice(double oneWayPrice) {
		this.oneWayPrice = oneWayPrice;
	}

}

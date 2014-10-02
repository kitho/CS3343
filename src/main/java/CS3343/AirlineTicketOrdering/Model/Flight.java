package CS3343.AirlineTicketOrdering.Model;

import java.util.Date;

public class Flight {
	
	private String airline;
	private String flightNumber;
	private String travelClass;
	private String depature;
	private String destination;
	private Date depatureDateTime;
	private Date arrivalDateTime;
	private int available;
	private double oneWayPrice;
	private String model;
	private String mealIds;
	private String foodIds;
	
	public String getMealIds() {
		return mealIds;
	}
	public void setMealIds(String mealIds) {
		this.mealIds = mealIds;
	}
	public String getFoodIds() {
		return foodIds;
	}
	public void setFoodIds(String foodIds) {
		this.foodIds = foodIds;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
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
	public String getTravelClass() {
		return travelClass;
	}
	public void setTravelClass(String travelClass) {
		this.travelClass = travelClass;
	}
	public String getDepature() {
		return depature;
	}
	public void setDepature(String depature) {
		this.depature = depature;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
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

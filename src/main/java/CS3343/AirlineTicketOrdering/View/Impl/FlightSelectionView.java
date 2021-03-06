package CS3343.AirlineTicketOrdering.View.Impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import CS3343.AirlineTicketOrdering.FlightPathFinding.FlightPath;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Model.Route;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

public class FlightSelectionView implements View{
	
	private BufferedReader bufferedReader;
	
	
	/**
	 * Constructor
	 * @param bufferedReader
	 */
	public FlightSelectionView(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}
	
	public void display(Session session) throws IOException {
		
		FlightPath fPath = (FlightPath) session.getAttribute("selectedRoute");
		List<Route> routeList = fPath.getFlightList();
		List<Flight> result = new ArrayList<Flight>();
		

		for (int i = 0; i < routeList.size(); i++)
		{
			Route route = routeList.get(i);
			List<Flight> flightList = route.getFlights();
			List<Flight> resultFlights = new ArrayList<Flight>();
	        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	        System.out.println("Select Airline for " +route.getDeparture() + " --> " +route.getDestination());
			System.out.println("=====================");			
			String format = "%-5s%-30s%-20s%-20s%-20s%-20s%-25s%-25s%-20s%s%n";
			System.out.printf(format, "No.","Airline","FlightNumber","TravelClass","Depature","Destination","DepatureDateTime","ArrivalDateTime","Available","OneWayPrice");
			
			for (int j = 0; j < flightList.size(); j++){
				System.out.printf(format,j+1,flightList.get(j).getAirline(),flightList.get(j).getFlightNumber(),flightList.get(j).getTravelClass(),flightList.get(j).getDepature(),flightList.get(j).getDestination(),dt.format(flightList.get(j).getDepatureDateTime()),dt.format(flightList.get(j).getArrivalDateTime()),flightList.get(j).getAvailable(),flightList.get(j).getOneWayPrice());
			}
			System.out.println();
			System.out.print("Please select airline: ");
			String selectFlight = bufferedReader.readLine();
			result.add(flightList.get(Integer.parseInt(selectFlight)-1));
			System.out.println();

	
	
			session.setAttribute("flights", result);
			System.out.print("Please input number of tickets that you need: ");
			String numberOfTicket = bufferedReader.readLine();
			session.setAttribute("numberOfTicket", Integer.parseInt(numberOfTicket));
		}

	}
}

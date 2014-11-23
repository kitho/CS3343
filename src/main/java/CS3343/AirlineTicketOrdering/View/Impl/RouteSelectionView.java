package CS3343.AirlineTicketOrdering.View.Impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import CS3343.AirlineTicketOrdering.FlightPathFinding.FlightPath;
import CS3343.AirlineTicketOrdering.Model.Flight;
import CS3343.AirlineTicketOrdering.Model.Route;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

public class RouteSelectionView implements View{
	
	private BufferedReader bufferedReader;

	
	public RouteSelectionView(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}
	
	public void display(Session session) throws IOException {
		ArrayList<FlightPath> resultRoutes = (ArrayList<FlightPath>) session.getAttribute("FlightPaths");
		
		if(resultRoutes.size() == 0){
			System.out.println("No Route Found");
			session.setAttribute("routes", null);
		}else{
			System.out.println("Route List:");
			for (int i = 0; i < resultRoutes.size(); i++){
				ArrayList<Route> routes = resultRoutes.get(i).getFlightList();
				for (int j = 0; j < routes.size(); j++)
					System.out.print("route"+ (i+1) +": "+routes.get(j).getDeparture() + " --> " + routes.get(j).getDestination() + ", ");
				System.out.println();
			}
		}
	}
}

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
				int distance = 0;
				ArrayList<Route> routes = resultRoutes.get(i).getFlightList();
				System.out.print("route ("+ (i+1) +"): ");
				for (int j = 0; j < routes.size(); j++){
					distance += routes.get(j).getDistance();
					System.out.print(routes.get(j).getDeparture() + " --> " + routes.get(j).getDestination());
					if (j != routes.size()-1)
						System.out.print(", ");
				}
				System.out.println();
				//System.out.println("Total bouns miles can earn: " +distance);
			}
			System.out.println();
			System.out.print("Please enter backet number to select route: ");
			String selectRoute = bufferedReader.readLine();
			session.setAttribute("selectedRoute", resultRoutes.get(Integer.parseInt(selectRoute)-1));
			FlightPath fPath = (FlightPath) session.getAttribute("selectedRoute");
			for (int i = 0; i < fPath.getFlightList().size(); i++){
				Route route = fPath.getFlightList().get(i);
				System.out.print(route.getDeparture() +" : "+ route.getDestination() + " --> ");
			}
			System.out.println ();

		}
	}
}

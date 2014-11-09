package CS3343.AirlineTicketOrdering.FlightPathFinding;

import java.util.ArrayList;

import sun.org.mozilla.javascript.internal.ast.NewExpression;
import CS3343.AirlineTicketOrdering.Model.Route;

public class PathFinding {
	private String from;
	private String to;
	private RouteTable routeTable;
	
	
	public PathFinding(String f, String t){
		from = f;
		to = t;
		routeTable = new RouteTable();
	}
	
	public ArrayList<FlightPath> getDirectFlight(){
		ArrayList<Route> routeList = routeTable.getRouteList();
		ArrayList<FlightPath> resultRouteList = new ArrayList<FlightPath>();

		Route route = findRoute(from, to, routeList);
		if (route != null){
			FlightPath fp = new FlightPath();
			fp.addFlighPath(route);
			resultRouteList.add(fp);
		}
		return resultRouteList;
	}
	
	public Route findRoute(String departure, String destination, ArrayList<Route> rl){
		for (int i = 0; i < rl.size(); i++){
			Route tempRoute = rl.get(i);
			if (tempRoute.getDeparture() == departure && tempRoute.getDestination() == destination)
				return tempRoute;
		}
		return null;		
	}
	
	public ArrayList<FlightPath> getIndirectFlight(ArrayList<FlightPath> resultRouteList){
		ArrayList<Route> routeList = routeTable.getRouteList();
		
		if (resultRouteList.size() == 0){
			ArrayList<Route> DeptList = findRouteDepart(from, routeList);
			if (DeptList.size() == 0)
				return resultRouteList;
			for (int i = 0; i < DeptList.size(); i++){
				FlightPath fPath = new FlightPath();	
				fPath.addFlighPath(DeptList.get(i));

				if (DeptList.get(i).getDestination().equals(to)){
					System.out.println("insert" + DeptList.get(i).getDeparture() + DeptList.get(i).getDestination());
					resultRouteList.add(fPath);	
				}else{
					ArrayList<FlightPath> subList = new ArrayList<FlightPath>();
					subList.add(fPath);
					
					ArrayList<FlightPath> subResultList = getIndirectFlight(subList);
					for (int j = 0; j < subResultList.size(); j++){
						resultRouteList.add(subResultList.get(j));
					}
				}
				
			}
		}else{
			ArrayList<FlightPath> tempResultRouteList = resultRouteList;
			for (int z = 0; z < tempResultRouteList.size(); z++){
				FlightPath fPath = tempResultRouteList.get(z);
				ArrayList<Route> routeList2 =  fPath.getFlightList();	
				//System.out.println("test " + routeList2.get(routeList2.size()-1).getDestination());
				ArrayList<Route> DeptList = findRouteDepart(routeList2.get(routeList2.size()-1).getDestination(), routeList);
				if (DeptList.size() == 0){
					resultRouteList.remove(z);
				}else{
					for (int i = 0; i < DeptList.size(); i++){
						if (DeptList.get(i).getDestination().equals(to)){
							fPath.addFlighPath(DeptList.get(i));
							resultRouteList.add(fPath);
						}else{
							FlightPath subFPath = new FlightPath();
							ArrayList<Route> tempRoutes = fPath.getFlightList();
							for (int x = 0; x < tempRoutes.size(); x++){
								if (!tempRoutes.get(x).getDestination().equals(to))
									subFPath.addFlighPath(tempRoutes.get(x));
							}
							subFPath.addFlighPath(DeptList.get(i));
							ArrayList<FlightPath> subList = new ArrayList<FlightPath>();
							subList.add(subFPath);
							ArrayList<FlightPath> subResultList = getIndirectFlight(subList);
							for (int j = 0; j < subResultList.size(); j++){
								resultRouteList.add(subResultList.get(j));
							}
						}
						
					}
				}
			}
		}

		return resultRouteList;
	}	
	
	public ArrayList<Route> findRouteDepart(String departure,ArrayList<Route> rl){
		ArrayList<Route> resultRoutes = new ArrayList<Route>();
		for (int i = 0; i < rl.size(); i++){
			Route tempRoute = rl.get(i);
			if (tempRoute.getDeparture() == departure)
				resultRoutes.add(tempRoute);
		}
		return resultRoutes;		
	}
	
	public ArrayList<Route> findRouteDest(String destination,ArrayList<Route> rl){
		ArrayList<Route> resultRoutes = new ArrayList<Route>();
		for (int i = 0; i < rl.size(); i++){
			Route tempRoute = rl.get(i);
			if (tempRoute.getDeparture() == destination)
				resultRoutes.add(tempRoute);
		}
		return resultRoutes;				
	}
	
}

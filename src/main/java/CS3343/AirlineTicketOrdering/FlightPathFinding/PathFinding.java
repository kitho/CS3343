package CS3343.AirlineTicketOrdering.FlightPathFinding;

import java.util.ArrayList;

import CS3343.AirlineTicketOrdering.Model.Route;

public class PathFinding {
	private String from;
	private String to;
	private RouteTable routeTable;
	
	public PathFinding(String from, String to, RouteTable rt){
		this.from = from;
		this.to = to;
		routeTable = rt;
		//ArrayList<Route> routeList = routeTable.getRouteList();
		//System.out.println(from + " : " + to);
		//for (int i = 0; i < routeList.size(); i ++)
			//System.out.println(routeList.get(i).getDeparture() + " : "+ routeList.get(i).getDestination());
		
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
		//System.out.println("enter pathfinding");
		if (resultRouteList.size() == 0){
			ArrayList<Route> DeptList = findRouteDepart(from, routeList);
			if (DeptList.size() == 0)
				return resultRouteList;
			for (int i = 0; i < DeptList.size(); i++){
				FlightPath fPath = new FlightPath();	
				fPath.addFlighPath(DeptList.get(i));

				if (DeptList.get(i).getDestination().equals(to)){
					//System.out.println("insert" + DeptList.get(i).getDeparture() + DeptList.get(i).getDestination());
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
				if (routeList2.get(routeList2.size()-1).getDestination().equals(to)){

				}else if (DeptList.size() == 0){
					ArrayList<Route> fp = fPath.getFlightList();
					//if (!fp.get(fp.size()-1).equals(to))
					resultRouteList.remove(fPath);
				}else{
					for (int i = 0; i < DeptList.size(); i++){
						if (DeptList.get(i).getDestination().equals(to)){

							fPath.addFlighPath(DeptList.get(i));
							//resultRouteList.add(fPath);

						}else{
							
							FlightPath subFPath = new FlightPath();
							ArrayList<Route> tempRoutes = fPath.getFlightList();
							boolean chk = true;
							
							for (int x = 0; x < tempRoutes.size(); x++){
								if (!tempRoutes.get(x).getDestination().equals(to))
									subFPath.addFlighPath(tempRoutes.get(x));
							}
							for (int x = 0; x < tempRoutes.size(); x++){
								Route r = DeptList.get(i);
								if (r.getDestination().equals(tempRoutes.get(x).getDeparture()) ||
									r.getDestination().equals(tempRoutes.get(x).getDestination()))
									chk = false;
							}
							if (chk){
								subFPath.addFlighPath(DeptList.get(i));
								ArrayList<FlightPath> subList = new ArrayList<FlightPath>();
								subList.add(subFPath);
								ArrayList<FlightPath> subResultList = getIndirectFlight(subList);
								
								//resultRouteList.get(z).getFlightList().get(z);
								
								ArrayList<Route> route = fPath.getFlightList();
								if (subResultList.size() == 0 && !route.get(route.size()-1).getDestination().equals(to))
									resultRouteList.remove(fPath);
	
								for (int j = 0; j < subResultList.size(); j++){
									resultRouteList.add(subResultList.get(j));
								}
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
			if (tempRoute.getDeparture().equals(departure))
				resultRoutes.add(tempRoute);
		}
		return resultRoutes;		
	}
	

}

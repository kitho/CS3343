package CS3343.AirlineTicketOrdering.FlightPathFinding;

import java.io.IOException;
import java.util.ArrayList;

import CS3343.AirlineTicketOrdering.Model.Route;

public class PathFinding {
	private String from;
	private String to;
	private RouteTable routeTable;
	
	/**
	 * Instantiates a new Path finding
	 *
	 * @param departure
	 * @param destination
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public PathFinding(String from, String to, RouteTable rt){
		this.from = from;
		this.to = to;
		routeTable = rt;
	}
	
	/**
	 * get direct flight
	 *
	 * @return FlightPath ArrayList
	 * 
	 */
	public ArrayList<FlightPath> getDirectFlight(){
		ArrayList<FlightPath> resultRouteList = new ArrayList<FlightPath>();

		Route route = routeTable.findRoute(from, to);
		if (route != null){
			FlightPath fp = new FlightPath();
			fp.addFlighPath(route);
			resultRouteList.add(fp);
		}
		return resultRouteList;
	}
	

	/**
	 * get all indirect flight path
 	 * @param FlightPath ArrayList
	 * @return FlightPath ArrayList
	 */
	public ArrayList<FlightPath> getIndirectFlight(ArrayList<FlightPath> resultRouteList){
		if (resultRouteList.size() == 0){
			ArrayList<Route> DeptList = routeTable.findRouteDepart(from);
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
				ArrayList<Route> DeptList = routeTable.findRouteDepart(routeList2.get(routeList2.size()-1).getDestination());
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
	
	
	

}

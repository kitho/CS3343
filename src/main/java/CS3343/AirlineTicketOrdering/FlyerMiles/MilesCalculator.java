package CS3343.AirlineTicketOrdering.FlyerMiles;

import java.util.ArrayList;

import CS3343.AirlineTicketOrdering.Model.*;

public class MilesCalculator {
	ArrayList<MilesPoint> mp;
	
	public MilesCalculator(){
		 mp = new ArrayList<MilesPoint>();
		 addBaseMiles(0,600,1000);
		 addBaseMiles(601,1200,1500);
		 addBaseMiles(1201,2500,2000);
		 addBaseMiles(2501,5000,2500);
		 addBaseMiles(5001,7500,4000);
		 addBaseMiles(7501,10000,5500);
		 addBaseMiles(10001,999999,7000);
	}
	
	public void addBaseMiles(int mini, int max, int base){
		MilesPoint m = new MilesPoint(mini,max,base);
		mp.add(m);
	}
	
	public int findBasePoints(int distance){
		for(int i=0; i<mp.size(); i++){
			if(distance>mp.get(i).getMinimun() && distance>mp.get(i).getMaximun()){
				return mp.get(i).getBasepoint();
			}
		}
		return 0;
	}
	
}


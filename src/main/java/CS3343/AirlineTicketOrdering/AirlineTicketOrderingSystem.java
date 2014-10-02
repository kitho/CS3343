package CS3343.AirlineTicketOrdering;

import java.io.FileNotFoundException;
import java.io.IOException;

import CS3343.AirlineTicketOrdering.Properties.DataSourceProperties;
import CS3343.AirlineTicketOrdering.Properties.SystemProperties;

public class AirlineTicketOrderingSystem {
	
	public void invoke(){
		AirlineTicketOrderingSystemInvoker.main(new String[]{});
	}
	
	private static class AirlineTicketOrderingSystemInvoker{
		
		public static void main(String[] args){
			try {
				SystemProperties systemProperties = new DataSourceProperties();
				String airlinePath = systemProperties.getPath("airline");
				String flightPath = systemProperties.getPath("flight");
				
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}

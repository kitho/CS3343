package CS3343.AirlineTicketOrdering;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import CS3343.AirlineTicketOrdering.CSVFile.CSVFile;
import CS3343.AirlineTicketOrdering.Calculator.Impl.AirlineCalculator;
import CS3343.AirlineTicketOrdering.Controller.AirlineTicketOrderingController;
import CS3343.AirlineTicketOrdering.Controller.Impl.BaggageCalculationController;
import CS3343.AirlineTicketOrdering.Controller.Impl.EnquireCreditCardController;
import CS3343.AirlineTicketOrdering.Controller.Impl.FlightSelectionController;
import CS3343.AirlineTicketOrdering.Controller.Impl.InputBaggageDataController;
import CS3343.AirlineTicketOrdering.Controller.Impl.InputDestinationController;
import CS3343.AirlineTicketOrdering.Controller.Impl.OrderCompletionController;
import CS3343.AirlineTicketOrdering.Controller.Impl.OrderConfirmationController;
import CS3343.AirlineTicketOrdering.Controller.Impl.RouteSelectionController;
import CS3343.AirlineTicketOrdering.DataQuery.BaggageQuery;
import CS3343.AirlineTicketOrdering.DataQuery.FlightQuery;
import CS3343.AirlineTicketOrdering.DataQuery.OrderQuery;
import CS3343.AirlineTicketOrdering.DataQuery.AirlineDiscountQuery;
import CS3343.AirlineTicketOrdering.DataQuery.RouteQuery;
import CS3343.AirlineTicketOrdering.DataReader.Impl.AirlineCompanyCSVFileReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.AirlineDiscountCSVFileReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.BaggagePlanCSVFileReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.FlightCSVFileReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.OrderCSVFileReader;
import CS3343.AirlineTicketOrdering.DataWriter.Impl.FlightCSVFileWriter;
import CS3343.AirlineTicketOrdering.DataWriter.Impl.OrderCSVFileWriter;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.Impl.BaggageFeeCalculationView;
import CS3343.AirlineTicketOrdering.View.Impl.EnquireCreditCardView;
import CS3343.AirlineTicketOrdering.View.Impl.FlightSelectionView;
import CS3343.AirlineTicketOrdering.View.Impl.InputBaggageDataView;
import CS3343.AirlineTicketOrdering.View.Impl.InputDestinationView;
import CS3343.AirlineTicketOrdering.View.Impl.OrderCompletionView;
import CS3343.AirlineTicketOrdering.View.Impl.OrderConfirmationView;
import CS3343.AirlineTicketOrdering.View.Impl.RouteSelectionView;

public class AirlineTicketOrderingSystem {
	
	public static void main(String[] args) {
		AirlineTicketOrderingSystemInvoker invoker = new AirlineTicketOrderingSystemInvoker();
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			invoker.invoke(bufferedReader);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("System invokes error");
		}
	}
	
	public static class AirlineTicketOrderingSystemInvoker{
		
		private File projectPath; 
		
		public void invoke(BufferedReader bufferedReader) throws Exception{
			projectPath = new File(".").getCanonicalFile();
						
			Session session = Session.getInstance(); 
			AirlineTicketOrderingController inputDestinationController = new InputDestinationController(session, new InputDestinationView(bufferedReader));
			AirlineTicketOrderingController flightSelectionController = new FlightSelectionController(session, new FlightSelectionView(bufferedReader));
			AirlineTicketOrderingController inputBaggageDataController = new InputBaggageDataController(session, new InputBaggageDataView(bufferedReader), 
					new BaggageQuery(new BaggagePlanCSVFileReader(projectPath + CSVFile.BAGGAGEPLANCSV.value())));
			AirlineTicketOrderingController baggageCalculationController = new BaggageCalculationController(session, new BaggageFeeCalculationView(bufferedReader));
			AirlineTicketOrderingController enquireCreditCardController = new EnquireCreditCardController(session, new EnquireCreditCardView(bufferedReader));
			AirlineTicketOrderingController orderConfirmationController = new OrderConfirmationController(session, new OrderConfirmationView(bufferedReader), new AirlineCalculator(),
					new AirlineDiscountQuery(new AirlineDiscountCSVFileReader(projectPath + CSVFile.AIRLINEDISCOUNTCSV.value())));
			AirlineTicketOrderingController orderCompletionController = new OrderCompletionController(session, 
					new OrderCompletionView(),
					new OrderQuery(new OrderCSVFileReader(projectPath + CSVFile.ORDERCSV.value()),
							new OrderCSVFileWriter(projectPath + CSVFile.ORDERCSV.value())));
			
			AirlineTicketOrderingController routeSelectionController = new RouteSelectionController(session, new RouteSelectionView(bufferedReader), 
					new FlightQuery(new AirlineCompanyCSVFileReader(projectPath + CSVFile.AIRLINECOMPANYCSV.value()), 
							new FlightCSVFileReader(projectPath + CSVFile.FLIGHTCSV.value()), 
							new FlightCSVFileWriter(projectPath + CSVFile.FLIGHTCSV.value())), new RouteQuery());
			
			
			inputDestinationController.setNext(routeSelectionController);
			routeSelectionController.setNext(flightSelectionController);
			flightSelectionController.setNext(inputBaggageDataController);
			inputBaggageDataController.setNext(baggageCalculationController);
			baggageCalculationController.setNext(enquireCreditCardController);
			enquireCreditCardController.setNext(orderConfirmationController);
			orderConfirmationController.setNext(orderCompletionController);
			
			System.out.println("Welcome to Airline Ticket Ordering System");
			inputDestinationController.execute();

		}
	}
}

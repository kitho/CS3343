package CS3343.AirlineTicketOrdering;

import java.io.File;
import java.util.Scanner;

import CS3343.AirlineTicketOrdering.CSVFile.CSVFile;
import CS3343.AirlineTicketOrdering.Calculator.Impl.StubCalculator;
import CS3343.AirlineTicketOrdering.Controller.AirlineTicketOrderingController;
import CS3343.AirlineTicketOrdering.Controller.Impl.EnquireCreditCardController;
import CS3343.AirlineTicketOrdering.Controller.Impl.FlightSelectionController;
import CS3343.AirlineTicketOrdering.Controller.Impl.InputDestinationController;
import CS3343.AirlineTicketOrdering.Controller.Impl.OrderCompletionController;
import CS3343.AirlineTicketOrdering.Controller.Impl.OrderConfirmationController;
import CS3343.AirlineTicketOrdering.DataQuery.FlightQuery;
import CS3343.AirlineTicketOrdering.DataReader.Impl.AirlineCompanyCSVFileReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.FlightCSVFileReader;
import CS3343.AirlineTicketOrdering.DataWriter.Impl.FlightCSVFileWriter;
import CS3343.AirlineTicketOrdering.Discount.Impl.StubDiscount;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.Impl.EnquireCreditCardView;
import CS3343.AirlineTicketOrdering.View.Impl.FlightSelectionView;
import CS3343.AirlineTicketOrdering.View.Impl.InputDestinationView;
import CS3343.AirlineTicketOrdering.View.Impl.OrderCompletionView;
import CS3343.AirlineTicketOrdering.View.Impl.OrderConfirmationView;

public class AirlineTicketOrderingSystem {
	
	public static void main(String[] args) {
		AirlineTicketOrderingSystemInvoker invoker = new AirlineTicketOrderingSystemInvoker();
		try {
			invoker.invoke();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("System invokes error");
		}
	}
	
	private static class AirlineTicketOrderingSystemInvoker{
		
		private File projectPath; 
		
		public void invoke() throws Exception{
			projectPath = new File(".").getCanonicalFile();
			
			Scanner scanner = new Scanner(System.in);
			
			Session session = Session.getInstance(); 
			AirlineTicketOrderingController inputDestinationController = new InputDestinationController(session, new InputDestinationView(scanner));
			AirlineTicketOrderingController flightSelectionController = new FlightSelectionController(session, new FlightSelectionView(scanner), 
					new FlightQuery(new AirlineCompanyCSVFileReader(projectPath + CSVFile.AIRLINECOMPANYCSV.value()), 
							new FlightCSVFileReader(projectPath + CSVFile.FLIGHTCSV.value()), 
							new FlightCSVFileWriter(projectPath + CSVFile.FLIGHTCSV.value())));
			AirlineTicketOrderingController enquireCreditCardController = new EnquireCreditCardController(session, new EnquireCreditCardView(scanner));
			AirlineTicketOrderingController orderConfirmationController = new OrderConfirmationController(session, new OrderConfirmationView(scanner), new StubDiscount(), new StubCalculator());
			AirlineTicketOrderingController orderCompletionController = new OrderCompletionController(session, new OrderCompletionView());
			
			inputDestinationController.setNext(flightSelectionController);
			flightSelectionController.setNext(enquireCreditCardController);
			enquireCreditCardController.setNext(orderConfirmationController);
			orderConfirmationController.setNext(orderCompletionController);
			
			System.out.println("Welcome to Airline Ticket Ordering System");
			inputDestinationController.execute();
			
			scanner.close();
		
		}
	}
}
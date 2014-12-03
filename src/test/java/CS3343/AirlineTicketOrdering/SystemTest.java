package CS3343.AirlineTicketOrdering;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import TestingTool.DataWriter.CSVFileTest;
import CS3343.AirlineTicketOrdering.AirlineTicketOrderingSystem.AirlineTicketOrderingSystemInvoker;
import CS3343.AirlineTicketOrdering.CSVFile.CSVFile;
import CS3343.AirlineTicketOrdering.Util.LineSeparatorUtil;


public class SystemTest {
	private ByteArrayOutputStream outContent;
	private File projectPath;
	
	@Before
	public void setUp() throws IOException {
		projectPath = new File(".").getCanonicalFile(); 
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		Path newfile = Paths.get(projectPath + CSVFile.ORDERCSV.value());
		Files.deleteIfExists(Paths.get(projectPath + CSVFile.ORDERCSV.value()));
		Files.createFile(newfile);
	}
	
	@Test
	public void TicketOrderTest() throws Exception{
		AirlineTicketOrderingSystemInvoker invoker = new AirlineTicketOrderingSystemInvoker();
		BufferedReader bufferedReader = org.mockito.Mockito
				.mock(BufferedReader.class);
		Mockito.when(bufferedReader.readLine()).thenReturn("2014-01-01")
				.thenReturn("Hong Kong").thenReturn("Taiwan").thenReturn("5")
				.thenReturn("1").thenReturn("10").thenReturn("1")
				.thenReturn("10").thenReturn("1").thenReturn("10")
				.thenReturn("99.9 99 99.9").thenReturn("99.9 99 99.9")
				.thenReturn("99.9 99 99.9").thenReturn("99.9 99 99.9")
				.thenReturn("99.9 99 99.9").thenReturn("99.9 99 99.9")
				.thenReturn("99.9 99 99.9").thenReturn("99.9 99 99.9")
				.thenReturn("99.9 99 99.9").thenReturn("99.9 99 99.9")
				.thenReturn("7").thenReturn("7").thenReturn("7")
				.thenReturn("7").thenReturn("7").thenReturn("7")
				.thenReturn("7").thenReturn("7").thenReturn("7")
				.thenReturn("7").thenReturn("99.9 99 99.9")
				.thenReturn("99.9 99 99.9").thenReturn("99.9 99 99.9")
				.thenReturn("99.9 99 99.9").thenReturn("99.9 99 99.9")
				.thenReturn("99.9 99 99.9").thenReturn("99.9 99 99.9")
				.thenReturn("99.9 99 99.9").thenReturn("99.9 99 99.9")
				.thenReturn("99.9 99 99.9").thenReturn("HSBC")
				.thenReturn("VISA").thenReturn("1234-1234-1234-1234")
				.thenReturn("Yes");
		invoker.invoke(bufferedReader);
		assertThat(
				"Welcome to Airline Ticket Ordering System"
						+ LineSeparatorUtil.newLine()
						+ "Please Input your depature date, depature and destination to search"
						+ LineSeparatorUtil.newLine()
						+ "Date (YYYY-MM-DD): Depature: Destination: "
						+ LineSeparatorUtil.newLine()
						+ "======= Route List ======="
						+ LineSeparatorUtil.newLine()
						+ "route (1): Hong Kong --> Taiwan"
						+ LineSeparatorUtil.newLine()
						+ "Basic flyer miles*: 1500 points"
						+ LineSeparatorUtil.newLine()
						+ ""
						+ LineSeparatorUtil.newLine()
						+ "route (2): Hong Kong --> Thailand, Thailand --> Taiwan"
						+ LineSeparatorUtil.newLine()
						+ "Basic flyer miles*: 2500 points"
						+ LineSeparatorUtil.newLine()
						+ ""
						+ LineSeparatorUtil.newLine()
						+ "route (3): Hong Kong --> Tokyo, Tokyo --> Taiwan"
						+ LineSeparatorUtil.newLine()
						+ "Basic flyer miles*: 4000 points"
						+ LineSeparatorUtil.newLine()
						+ ""
						+ LineSeparatorUtil.newLine()
						+ "route (4): Hong Kong --> Tokyo, Tokyo --> Thailand, Thailand --> Taiwan"
						+ LineSeparatorUtil.newLine()
						+ "Basic flyer miles*: 5500 points"
						+ LineSeparatorUtil.newLine()
						+ ""
						+ LineSeparatorUtil.newLine()
						+ "route (5): Hong Kong --> Tokyo, Tokyo --> Singapore, Singapore --> Taiwan"
						+ LineSeparatorUtil.newLine()
						+ "Basic flyer miles*: 7000 points"
						+ LineSeparatorUtil.newLine()
						+ ""
						+ LineSeparatorUtil.newLine()
						+ "route (6): Hong Kong --> Singapore, Singapore --> Taiwan"
						+ LineSeparatorUtil.newLine()
						+ "Basic flyer miles*: 4000 points"
						+ LineSeparatorUtil.newLine()
						+ ""
						+ LineSeparatorUtil.newLine()
						+ ""
						+ LineSeparatorUtil.newLine()
						+ "*without airline and credit card bouns"
						+ LineSeparatorUtil.newLine()
						+ ""
						+ LineSeparatorUtil.newLine()
						+ "Please enter backet number to select route: Route selected: Hong Kong --> Tokyo, Tokyo --> Singapore, Singapore --> Taiwan"
						+ LineSeparatorUtil.newLine()
						+ ""
						+ LineSeparatorUtil.newLine()
						+ "Select Airline for Hong Kong --> Tokyo"
						+ LineSeparatorUtil.newLine()
						+ "====================="
						+ LineSeparatorUtil.newLine()
						+ "No.  Airline                       FlightNumber        TravelClass         Depature            Destination         DepatureDateTime         ArrivalDateTime          Available           OneWayPrice"
						+ LineSeparatorUtil.newLine()
						+ "1    Cathay Pacific Airways        CP001               FIRST               Hong Kong           Tokyo               2014-01-01 02:30:00      2014-01-01 05:30:00      30                  2500.0"
						+ LineSeparatorUtil.newLine()
						+ "2    Dragonair                     CP001               FIRST               Hong Kong           Tokyo               2014-01-01 02:30:00      2014-01-01 05:30:00      30                  1500.0"
						+ LineSeparatorUtil.newLine()
						+ ""
						+ LineSeparatorUtil.newLine()
						+ "Please select airline: "
						+ LineSeparatorUtil.newLine()
						+ "Please input number of tickets that you need: Select Airline for Tokyo --> Singapore"
						+ LineSeparatorUtil.newLine()
						+ "====================="
						+ LineSeparatorUtil.newLine()
						+ "No.  Airline                       FlightNumber        TravelClass         Depature            Destination         DepatureDateTime         ArrivalDateTime          Available           OneWayPrice"
						+ LineSeparatorUtil.newLine()
						+ "1    Cathay Pacific Airways        CP001               FIRST               Tokyo               Singapore           2014-01-01 02:30:00      2014-01-01 05:30:00      30                  2500.0"
						+ LineSeparatorUtil.newLine()
						+ ""
						+ LineSeparatorUtil.newLine()
						+ "Please select airline: "
						+ LineSeparatorUtil.newLine()
						+ "Please input number of tickets that you need: Select Airline for Singapore --> Taiwan"
						+ LineSeparatorUtil.newLine()
						+ "====================="
						+ LineSeparatorUtil.newLine()
						+ "No.  Airline                       FlightNumber        TravelClass         Depature            Destination         DepatureDateTime         ArrivalDateTime          Available           OneWayPrice"
						+ LineSeparatorUtil.newLine()
						+ "1    Cathay Pacific Airways        CP001               FIRST               Singapore           Taiwan              2014-01-01 02:30:00      2014-01-01 05:30:00      30                  2500.0"
						+ LineSeparatorUtil.newLine()
						+ "2    Dragonair                     CP001               FIRST               Singapore           Taiwan              2014-01-01 02:30:00      2014-01-01 05:30:00      30                  2500.0"
						+ LineSeparatorUtil.newLine()
						+ ""
						+ LineSeparatorUtil.newLine()
						+ "Please select airline: "
						+ LineSeparatorUtil.newLine()
						+ "Please input number of tickets that you need: "
						+ LineSeparatorUtil.newLine()
						+ "=====Baggage Plan For First Class====="
						+ LineSeparatorUtil.newLine()
						+ "1. Each passenger can enjoy free 20.0 KG(s) (Can be shared with other tickets purcahsed at the same time.)"
						+ LineSeparatorUtil.newLine()
						+ "2. Basic fee per KG $100.0"
						+ LineSeparatorUtil.newLine()
						+ "3. Extra fee if average exceed following items:"
						+ LineSeparatorUtil.newLine()
						+ "\t25.0 KG(s)\t-\t30.0 KG(s)\t$100.0"
						+ LineSeparatorUtil.newLine()
						+ "\t40.0 Inch(s)\t-\t50.0 Inch(s)\t$100.0"
						+ LineSeparatorUtil.newLine()
						+ "\t>=31.0 KG(s)\t\t\t\t$400.0"
						+ LineSeparatorUtil.newLine()
						+ "\t>=51.0 Inch(s)\t\t\t\t$400.0"
						+ LineSeparatorUtil.newLine()
						+ ""
						+ LineSeparatorUtil.newLine()
						+ "4. Basic pet fee per KG $30.0"
						+ LineSeparatorUtil.newLine()
						+ "5. Extra pet if average exceed following items:"
						+ LineSeparatorUtil.newLine()
						+ "\t25.0 KG(s)\t-\t30.0 KG(s)\t$100.0"
						+ LineSeparatorUtil.newLine()
						+ "\t40.0 Inch(s)\t-\t50.0 Inch(s)\t$100.0"
						+ LineSeparatorUtil.newLine()
						+ "\t>=31.0 KG(s)\t\t\t\t$400.0"
						+ LineSeparatorUtil.newLine()
						+ "\t>=51.0 Inch(s)\t\t\t\t$400.0"
						+ LineSeparatorUtil.newLine()
						+ ""
						+ LineSeparatorUtil.newLine()
						+ ""
						+ LineSeparatorUtil.newLine()
						+ "Input your baggage total kg, total piece and total size for #1 passengers (Format: 99.9 99 99.9): "
						+ LineSeparatorUtil.newLine()
						+ "Input your baggage total kg, total piece and total size for #2 passengers (Format: 99.9 99 99.9): "
						+ LineSeparatorUtil.newLine()
						+ "Input your baggage total kg, total piece and total size for #3 passengers (Format: 99.9 99 99.9): "
						+ LineSeparatorUtil.newLine()
						+ "Input your baggage total kg, total piece and total size for #4 passengers (Format: 99.9 99 99.9): "
						+ LineSeparatorUtil.newLine()
						+ "Input your baggage total kg, total piece and total size for #5 passengers (Format: 99.9 99 99.9): "
						+ LineSeparatorUtil.newLine()
						+ "Input your baggage total kg, total piece and total size for #6 passengers (Format: 99.9 99 99.9): "
						+ LineSeparatorUtil.newLine()
						+ "Input your baggage total kg, total piece and total size for #7 passengers (Format: 99.9 99 99.9): "
						+ LineSeparatorUtil.newLine()
						+ "Input your baggage total kg, total piece and total size for #8 passengers (Format: 99.9 99 99.9): "
						+ LineSeparatorUtil.newLine()
						+ "Input your baggage total kg, total piece and total size for #9 passengers (Format: 99.9 99 99.9): "
						+ LineSeparatorUtil.newLine()
						+ "Input your baggage total kg, total piece and total size for #10 passengers (Format: 99.9 99 99.9): "
						+ LineSeparatorUtil.newLine()
						+ "Enjoy Free Sporting Equipments Shipping:"
						+ LineSeparatorUtil.newLine()
						+ "1. Ski equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "2. Fishing equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "3. Bicycles equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "4. Golf equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "5. Trekking equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "6. Surfboards equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "7. No Sporting Equipment"
						+ LineSeparatorUtil.newLine()
						+ "Please select one sporting equipments to enjoy free unit for #1 passagers: 1. Ski equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "2. Fishing equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "3. Bicycles equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "4. Golf equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "5. Trekking equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "6. Surfboards equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "7. No Sporting Equipment"
						+ LineSeparatorUtil.newLine()
						+ "Please select one sporting equipments to enjoy free unit for #2 passagers: 1. Ski equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "2. Fishing equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "3. Bicycles equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "4. Golf equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "5. Trekking equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "6. Surfboards equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "7. No Sporting Equipment"
						+ LineSeparatorUtil.newLine()
						+ "Please select one sporting equipments to enjoy free unit for #3 passagers: 1. Ski equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "2. Fishing equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "3. Bicycles equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "4. Golf equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "5. Trekking equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "6. Surfboards equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "7. No Sporting Equipment"
						+ LineSeparatorUtil.newLine()
						+ "Please select one sporting equipments to enjoy free unit for #4 passagers: 1. Ski equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "2. Fishing equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "3. Bicycles equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "4. Golf equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "5. Trekking equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "6. Surfboards equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "7. No Sporting Equipment"
						+ LineSeparatorUtil.newLine()
						+ "Please select one sporting equipments to enjoy free unit for #5 passagers: 1. Ski equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "2. Fishing equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "3. Bicycles equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "4. Golf equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "5. Trekking equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "6. Surfboards equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "7. No Sporting Equipment"
						+ LineSeparatorUtil.newLine()
						+ "Please select one sporting equipments to enjoy free unit for #6 passagers: 1. Ski equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "2. Fishing equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "3. Bicycles equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "4. Golf equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "5. Trekking equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "6. Surfboards equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "7. No Sporting Equipment"
						+ LineSeparatorUtil.newLine()
						+ "Please select one sporting equipments to enjoy free unit for #7 passagers: 1. Ski equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "2. Fishing equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "3. Bicycles equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "4. Golf equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "5. Trekking equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "6. Surfboards equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "7. No Sporting Equipment"
						+ LineSeparatorUtil.newLine()
						+ "Please select one sporting equipments to enjoy free unit for #8 passagers: 1. Ski equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "2. Fishing equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "3. Bicycles equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "4. Golf equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "5. Trekking equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "6. Surfboards equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "7. No Sporting Equipment"
						+ LineSeparatorUtil.newLine()
						+ "Please select one sporting equipments to enjoy free unit for #9 passagers: 1. Ski equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "2. Fishing equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "3. Bicycles equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "4. Golf equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "5. Trekking equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "6. Surfboards equipment\t\t- 10.0 KG(s)"
						+ LineSeparatorUtil.newLine()
						+ "7. No Sporting Equipment"
						+ LineSeparatorUtil.newLine()
						+ "Please select one sporting equipments to enjoy free unit for #10 passagers: "
						+ LineSeparatorUtil.newLine()
						+ "Input your pet total kg, total piece and total size for #1 passengers (Format: 99.9 99 99.9): "
						+ LineSeparatorUtil.newLine()
						+ "Input your pet total kg, total piece and total size for #2 passengers (Format: 99.9 99 99.9): "
						+ LineSeparatorUtil.newLine()
						+ "Input your pet total kg, total piece and total size for #3 passengers (Format: 99.9 99 99.9): "
						+ LineSeparatorUtil.newLine()
						+ "Input your pet total kg, total piece and total size for #4 passengers (Format: 99.9 99 99.9): "
						+ LineSeparatorUtil.newLine()
						+ "Input your pet total kg, total piece and total size for #5 passengers (Format: 99.9 99 99.9): "
						+ LineSeparatorUtil.newLine()
						+ "Input your pet total kg, total piece and total size for #6 passengers (Format: 99.9 99 99.9): "
						+ LineSeparatorUtil.newLine()
						+ "Input your pet total kg, total piece and total size for #7 passengers (Format: 99.9 99 99.9): "
						+ LineSeparatorUtil.newLine()
						+ "Input your pet total kg, total piece and total size for #8 passengers (Format: 99.9 99 99.9): "
						+ LineSeparatorUtil.newLine()
						+ "Input your pet total kg, total piece and total size for #9 passengers (Format: 99.9 99 99.9): "
						+ LineSeparatorUtil.newLine()
						+ "Input your pet total kg, total piece and total size for #10 passengers (Format: 99.9 99 99.9): "
						+ LineSeparatorUtil.newLine()
						+ "Calculated Baggage Fee Info:"
						+ LineSeparatorUtil.newLine()
						+ "You can enjoy       \t{KG=200.0}"
						+ LineSeparatorUtil.newLine()
						+ "Your remaining unit \t{KG=-799.0001}"
						+ LineSeparatorUtil.newLine()
						+ "Basic Baggage Fee   \t$-79900.016"
						+ LineSeparatorUtil.newLine()
						+ "Extra Baggage Fee   \t$0.0"
						+ LineSeparatorUtil.newLine()
						+ "Basic Pet Fee       \t$-29970.004"
						+ LineSeparatorUtil.newLine()
						+ "Extra Pet Fee       \t$0.0"
						+ LineSeparatorUtil.newLine()
						+ "Total Baggage Fee   \t$-109870.016"
						+ LineSeparatorUtil.newLine()
						+ "Please input your credit card information"
						+ LineSeparatorUtil.newLine()
						+ "Bank: Type: Number: Here is your order detail:"
						+ LineSeparatorUtil.newLine()
						+ "==========Payment Method=========="
						+ LineSeparatorUtil.newLine()
						+ "Bank: HSBC"
						+ LineSeparatorUtil.newLine()
						+ "Type: VISA"
						+ LineSeparatorUtil.newLine()
						+ "Number: 1234-1234-1234-1234"
						+ LineSeparatorUtil.newLine()
						+ ""
						+ LineSeparatorUtil.newLine()
						+ "==========Ticket Information=========="
						+ LineSeparatorUtil.newLine()
						+ "Airline             FlightNumber             TravelClass         Depature            Destination         DepatureDateTime         ArrivalDateTime          Available           OneWayPrice"
						+ LineSeparatorUtil.newLine()
						+ "Cathay Pacific AirwaysCP001                    FIRST               Hong Kong           Tokyo               2014-01-01 02:30:00      2014-01-01 05:30:00      30                  2500.0"
						+ LineSeparatorUtil.newLine()
						+ "Cathay Pacific AirwaysCP001                    FIRST               Tokyo               Singapore           2014-01-01 02:30:00      2014-01-01 05:30:00      30                  2500.0"
						+ LineSeparatorUtil.newLine()
						+ "Cathay Pacific AirwaysCP001                    FIRST               Singapore           Taiwan              2014-01-01 02:30:00      2014-01-01 05:30:00      30                  2500.0"
						+ LineSeparatorUtil.newLine() + ""
						+ LineSeparatorUtil.newLine()
						+ "======================================"
						+ LineSeparatorUtil.newLine() + "Number of Ticket: 10"
						+ LineSeparatorUtil.newLine() + "Total Price: 60000.0"
						+ LineSeparatorUtil.newLine()
						+ "Total award flyer miles: 12600"
						+ LineSeparatorUtil.newLine()
						+ "======================================"
						+ LineSeparatorUtil.newLine() + ""
						+ LineSeparatorUtil.newLine()
						+ "Confirm to order? (Yes/No)Order Success "
						+ LineSeparatorUtil.newLine() + " Order Id: 1",
				is(outContent.toString()));

	}
}

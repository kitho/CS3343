package CS3343.AirlineTicketOrdering;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import TestingTool.DataWriter.DataTableCSVFileWriter;
import CS3343.AirlineTicketOrdering.CSVFile.CSVFile;
import CS3343.AirlineTicketOrdering.DataWriter.SourceWriter;
import cucumber.api.DataTable;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AirlineTicketOrderingStepDef {
	
	private File projectPath; 
	private AirlineTicketOrderingSystem airlineTicketOrderingSystem;
	
	@Before
	public void setUp() throws IOException{
		projectPath = new File(".").getCanonicalFile();
		Files.deleteIfExists(Paths.get(projectPath + CSVFile.AIRLINECOMPANYCSV.value()));
		Files.deleteIfExists(Paths.get(projectPath + CSVFile.FLIGHTCSV.value()));
		airlineTicketOrderingSystem = new AirlineTicketOrderingSystem();
	}

	@Given("^Airline companies are provided:$")
	public void Airline_companies_are_provided(DataTable dataTable) throws Throwable {
		SourceWriter<DataTable> dataTableCSVFileWriter = new DataTableCSVFileWriter(projectPath + CSVFile.AIRLINECOMPANYCSV.value());
		dataTableCSVFileWriter.write(dataTable);
		dataTableCSVFileWriter.close();
	}

	@And("^Flights are provided for the customers:$")
	public void Flights_are_provided_for_the_customers(DataTable dataTable) throws Throwable {
		SourceWriter<DataTable> dataTableCSVFileWriter = new DataTableCSVFileWriter(projectPath + CSVFile.FLIGHTCSV.value());
		dataTableCSVFileWriter.write(dataTable);
		dataTableCSVFileWriter.close();
	}
	

	@And("^Client comes to the airline ticket ordering view$")
	public void Client_comes_to_the_airline_ticket_ordering_view() throws Throwable {
		airlineTicketOrderingSystem.invoke();
	}

	@When("^Client inputs the depature and destination and date:$")
	public void Client_inputs_the_depature_and_destination_and_date(DataTable dataTable) throws Throwable {
	    
	}

	@And("^System shows up the flights and classes and prices and times$")
	public void System_shows_up_the_flights_and_classes_and_prices_and_times() throws Throwable {
	    
	}

	@When("^Client selects the flight and travel Class and number of ticket:$")
	public void Client_selects_the_flight_and_travel_Class_and_number_of_ticket(DataTable dataTable) throws Throwable {
	    
	}

	@And("^System displays the total prices$")
	public void System_displays_the_total_prices() throws Throwable {

	}

	@When("^Customer confirms the order$")
	public void Customer_confirms_the_order() throws Throwable {

	}

	@And("^System enquires the personal information and credit card number to complete the order$")
	public void System_enquires_the_personal_information_and_credit_card_number_to_complete_the_order() throws Throwable {

	}

	@When("^Client inputs personal information:$")
	public void Client_inputs_personal_information(DataTable dataTable) throws Throwable {
	    
	}

	@And("^System display the order for the confirmation$")
	public void System_display_the_order_for_the_confirmation() throws Throwable {
	    
	}

	@Then("^Client confirms the order$")
	public void Client_confirms_the_order() throws Throwable {
	    
	}

	@Then("^System displays the order Number:$")
	public void System_displays_the_order_Number(DataTable dataTable) throws Throwable {
	    
	}


}

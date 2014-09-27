package CS3343.AirlineTicketOrdering;

import java.util.List;

import CS3343.AirlineTicketOrdering.Model.AirlineCompany;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class AirlineTicketOrderingStepDef {
	
	@Given("^Airline Companies are provided:$")
	public void Airline_Companies_are_provided(List<AirlineCompany> airlineCompanies) throws Throwable {
	    
	}

	@And("^Flights are provided for the customers:$")
	public void Flights_are_provided_for_the_customers(DataTable arg1) throws Throwable {
	    // Express the Regexp above with the code you wish you had
	    // For automatic conversion, change DataTable to List<YourType>
	    throw new PendingException();
	}

	@And("^Client comes to the airline ticket ordering view$")
	public void Client_comes_to_the_airline_ticket_ordering_view() throws Throwable {
	    // Express the Regexp above with the code you wish you had
	    throw new PendingException();
	}

	@When("^Client inputs the depature and destination and date:$")
	public void Client_inputs_the_depature_and_destination_and_date(DataTable arg1) throws Throwable {
	    // Express the Regexp above with the code you wish you had
	    // For automatic conversion, change DataTable to List<YourType>
	    throw new PendingException();
	}

	@And("^System shows up the flights and classes and prices and times$")
	public void System_shows_up_the_flights_and_classes_and_prices_and_times() throws Throwable {
	    // Express the Regexp above with the code you wish you had
	    throw new PendingException();
	}

	@When("^Client selects the flight and travel Class and number of ticket:$")
	public void Client_selects_the_flight_and_travel_Class_and_number_of_ticket(DataTable arg1) throws Throwable {
	    // Express the Regexp above with the code you wish you had
	    // For automatic conversion, change DataTable to List<YourType>
	    throw new PendingException();
	}

	@And("^System displays the total prices$")
	public void System_displays_the_total_prices() throws Throwable {
	    // Express the Regexp above with the code you wish you had
	    throw new PendingException();
	}

	@When("^Customer confirms the order$")
	public void Customer_confirms_the_order() throws Throwable {
	    // Express the Regexp above with the code you wish you had
	    throw new PendingException();
	}

	@And("^System enquires the personal information and credit card number to complete the order$")
	public void System_enquires_the_personal_information_and_credit_card_number_to_complete_the_order() throws Throwable {
	    // Express the Regexp above with the code you wish you had
	    throw new PendingException();
	}

	@When("^Client inputs personal information:$")
	public void Client_inputs_personal_information(DataTable arg1) throws Throwable {
	    // Express the Regexp above with the code you wish you had
	    // For automatic conversion, change DataTable to List<YourType>
	    throw new PendingException();
	}

	@And("^System display the order for the confirmation$")
	public void System_display_the_order_for_the_confirmation() throws Throwable {
	    // Express the Regexp above with the code you wish you had
	    throw new PendingException();
	}

	@Then("^Client confirms the order$")
	public void Client_confirms_the_order() throws Throwable {
	    // Express the Regexp above with the code you wish you had
	    throw new PendingException();
	}

	@Then("^System displays the order Number:$")
	public void System_displays_the_order_Number(DataTable arg1) throws Throwable {
	    // Express the Regexp above with the code you wish you had
	    // For automatic conversion, change DataTable to List<YourType>
	    throw new PendingException();
	}


}

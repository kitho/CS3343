package CS3343.AirlineTicketOrdering.View.Impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import CS3343.AirlineTicketOrdering.Baggage.BaggageFeeCalculator;
import CS3343.AirlineTicketOrdering.Baggage.BaggageRulePrinter;
import CS3343.AirlineTicketOrdering.DataReader.CSVFileReader;
import CS3343.AirlineTicketOrdering.DataReader.Impl.BaggagePlanCSVFileReader;
import CS3343.AirlineTicketOrdering.Model.BaggagePlan;
import CS3343.AirlineTicketOrdering.Model.Route;
import CS3343.AirlineTicketOrdering.Parser.Parser;
import CS3343.AirlineTicketOrdering.Parser.Impl.BaggagePlanParser;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

public class BaggageFeeCalculationView implements View {
	private BaggagePlan baggagePlan;
	private String flightClass;
	private BufferedReader bufferedReader;
	
	public BaggageFeeCalculationView(BufferedReader bufferedReader){
		this.bufferedReader = bufferedReader;
	}
	
	public void display(Session response) throws IOException {
		//Get needed data from session
		Map<String, Float> orgFreeUnit = (Map<String, Float>) response.getAttribute("orgFreeUnit");
		Map<String, Float> remainingFreeUnit = (Map<String, Float>)response.getAttribute("remainingFreeUnit");
		Float basicBaggageFee = (Float) response.getAttribute("basicBaggageFee");
		Float extraBaggageFee = (Float)response.getAttribute("extraBaggageFee");
		Float petFee = (Float)response.getAttribute("petFee");
		Float extraPetFee = (Float) response.getAttribute("extraPetFee");
		Float totalFee = (Float) response.getAttribute("totalFee");
		
		//Display them
		System.out.println("\nCalculated Baggage Fee Info:");
		System.out.println("You can enjoy       \t" + orgFreeUnit);
		System.out.println("Your remaining unit \t" + remainingFreeUnit);
		System.out.println("Basic Baggage Fee   \t$" + basicBaggageFee);
		System.out.println("Extra Baggage Fee   \t$" + extraBaggageFee);
		System.out.println("Basic Pet Fee       \t$" + petFee);
		System.out.println("Extra Pet Fee       \t$" + extraPetFee);
		System.out.println("Total Baggage Fee   \t$" + totalFee);
	}

}

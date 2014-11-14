package CS3343.AirlineTicketOrdering.Baggage;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class BaggageTestCase_Invoker {

	public static void main(String[] args) {
		JUnitCore junit = new JUnitCore();
		Result PieceEvnTestResult = junit.run(BaggageFeeCalculator_PieceEvnTest.class);
		Result KGEvnTestResult = junit.run(BaggageFeeCalculator_KGEvnTest.class);
		Result ExampleTestResult = junit.run(BaggageFeeCalculator_ExampleTest.class);
	}

}

package CS3343.AirlineTicketOrdering.Controller;
import CS3343.AirlineTicketOrdering.View.View;

public class InputDesintationController extends AirlineTicketOrderingController {

	private View view;
	
	public InputDesintationController(View view) {
		super(view);
	}
	
	@Override
	public void execute() throws Exception {
		view.display(session);
		
		next.next();
	}

}

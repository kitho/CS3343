package CS3343.AirlineTicketOrdering.Controller;
import CS3343.AirlineTicketOrdering.View.View;

public class InputDesintationController extends AirlineTicketOrderingController {

	private View view;
	
	public InputDesintationController(View view) {
		this.view = view;
	}
	
	@Override
	public void execute() {
		view.display(session);
		
		session.getAttribute("abc");
	
	}

}

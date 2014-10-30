package CS3343.AirlineTicketOrdering.Controller;

public class ControllerChain {
	
	private Controller next;
	
	public void setNext(Controller next) {
		this.next = next;
	}
	
	public void next() {
		if(next != null)
			next.execute();
	}

}

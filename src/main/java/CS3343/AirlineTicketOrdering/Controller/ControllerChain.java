package CS3343.AirlineTicketOrdering.Controller;

public class ControllerChain {
	
	protected Controller next;
	
	public void setNext(Controller next) {
		this.next = next;
	}
	
	public void next() throws Exception {
		if(next != null)
			next.execute();
	}

}

package CS3343.AirlineTicketOrdering.Controller;

/**
 * The ControllerChain is the chain pattern for the controllers to 
 * link up with each together
 */
public class ControllerChain {
	
	/** The next controller in the chain */
	protected Controller next;
	
	/**
	 * Instantiates a new controller chain.
	 */
	public ControllerChain() {
		next = null;	
	}
	

	/**
	 * Sets the next.
	 *
	 * @param next 
	 * 		  The next controller in this chain
	 */
	public void setNext(Controller next) {
		this.next = next;
	}
	
	/**
	 * Call the next controller to perform the action
	 *
	 * @throws Exception the exception
	 */
	public void next() throws Exception {
		if(next != null)
			next.execute();
	}

}

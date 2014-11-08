package CS3343.AirlineTicketOrdering.Controller;


import org.junit.Test;
import static org.mockito.Mockito.*;

public class ControllerChainTest {
	
	@Test
	public void executeNextChainOnce() throws Exception{
		Controller controller = mock(Controller.class);
		ControllerChain controllerChain = new ControllerChain();
		controllerChain.setNext(controller);
		
		controllerChain.next();
	
		verify(controller,times(1)).execute();
	}

}

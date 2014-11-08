package CS3343.AirlineTicketOrdering.Controller;


import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.mockito.Mockito.*;

public class ControllerChainTest {
	
	@Test
	public void executeWithoutNextChain() throws Exception{
		class StubControllerChain extends ControllerChain{
			private int count = 0;
			public int getCount(){
				return count;
			}
			public void next() throws Exception {
				super.next();
				if(next == null)
					count++;
			}
		}
		StubControllerChain controllerChain = new StubControllerChain();
		controllerChain.setNext(null);
		
		controllerChain.next();

		assertThat(1, is(controllerChain.getCount()));
	}
	
	@Test
	public void executeNextChainOnce() throws Exception{
		Controller controller = mock(Controller.class);
		ControllerChain controllerChain = new ControllerChain();
		controllerChain.setNext(controller);
		
		controllerChain.next();
	
		verify(controller,times(1)).execute();
	}

}

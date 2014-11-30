package CS3343.AirlineTicketOrdering.Model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MilesPointTest {
	@Test
	public void testMilesPoint(){
		MilesPoint mp = new MilesPoint(0,0,0);
		mp.setBasepoint(0);
		mp.setMinimun(0);
		mp.setMaximun(0);
		
		assertThat(0,is(mp.getBasepoint()));
		assertThat(0,is(mp.getMinimun()));
		assertThat(0,is(mp.getMaximun()));
		
	}
}

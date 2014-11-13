package CS3343.AirlineTicketOrdering.View;

import java.io.IOException;

import CS3343.AirlineTicketOrdering.Session.Session;

public interface View {

	public void display(Session response)throws IOException;

}

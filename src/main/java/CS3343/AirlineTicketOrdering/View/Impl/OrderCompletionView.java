package CS3343.AirlineTicketOrdering.View.Impl;

import CS3343.AirlineTicketOrdering.Model.Order;
import CS3343.AirlineTicketOrdering.Session.Session;
import CS3343.AirlineTicketOrdering.View.View;

public class OrderCompletionView implements View {

	public void display(Session response) {
		Order order = (Order) response.getAttribute("orders");
		if(order != null){
			System.out.println("Order Success ");
			System.out.print(" Order Id: " + order.getId());
			
		}else{
			System.out.print("Order Canceled");
		}

	}

}

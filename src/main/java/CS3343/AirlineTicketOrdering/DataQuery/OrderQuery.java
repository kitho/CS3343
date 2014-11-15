package CS3343.AirlineTicketOrdering.DataQuery;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import CS3343.AirlineTicketOrdering.DataReader.SourceReader;
import CS3343.AirlineTicketOrdering.DataWriter.SourceWriter;
import CS3343.AirlineTicketOrdering.Model.Order;
import CS3343.AirlineTicketOrdering.Parser.Impl.OrderParser;


public class OrderQuery {
	
	private SourceWriter<List<Order>> orderWriter;
	private List<Order> orders;
	
	public OrderQuery(SourceReader<Order> orderReader, SourceWriter<List<Order>> OrderWriter) throws IOException, ParseException{
		this.orderWriter = orderWriter;
		orders = orderReader.read(new OrderParser());
	}
	
	public int getMaxOrderId(){
		int max = 0;		
		for(int i = 0; i < orders.size(); i++){
			if(orders.get(i).getId() > max){
				max = orders.get(i).getId();
			}
		}
		return max;
	}

}
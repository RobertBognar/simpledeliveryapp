package deliveryapp.jwd.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import deliveryapp.jwd.model.Bill;
import deliveryapp.jwd.model.Order;
import deliveryapp.jwd.service.BillService;
import deliveryapp.jwd.service.OrderService;
import deliveryapp.jwd.web.dto.OrderDTO;

@Component
public class OrderDtoToOrder implements Converter<OrderDTO, Order> {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private BillToBillDto toBillDto;
	
	@Autowired
	private BillService billService;

	@Override
	public Order convert(OrderDTO orderDto) {
		Long id = orderDto.getId();
		Order order = id == null ? new Order() : orderService.getOne(id).get();
		
		if(order != null) {
			order.setId(id);
			order.setOrderDate(orderDto.getOrderDate());
			order.setOrderDescription(orderDto.getOrderDescription());
			order.setOrderNumber(orderDto.getOrderNumber());
			order.setOrderPrice(orderDto.getOrderPrice());
			order.setBill(toBillDto.convert(order.getBill()));
			
		}
		return order;
		
		
	}
	
	

}

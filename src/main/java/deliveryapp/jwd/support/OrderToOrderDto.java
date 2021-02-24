package deliveryapp.jwd.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import deliveryapp.jwd.model.Order;
import deliveryapp.jwd.web.dto.OrderDTO;

@Component
public class OrderToOrderDto implements Converter<Order, OrderDTO>{
	
	@Autowired
	private BillToBillDto billToBillDto;

	@Override
	public OrderDTO convert(Order order) {
		OrderDTO dto = new OrderDTO();
		dto.setId(order.getId());
		dto.setDeliveryPlace(order.getDeliveryPlace());
		dto.setOrderDate(order.getOrderDate());
		dto.setOrderDescription(order.getOrderDescription());
		dto.setOrderNumber(dto.getOrderNumber());
		dto.setOrderPrice(dto.getOrderPrice());
		return dto;
	}
	
	public List<OrderDTO> convert(List<Order> orders) {
		List<OrderDTO> ordersDto = new ArrayList<>();
		
		for(Order order : orders) {
			ordersDto.add(convert(order));
		}
		return ordersDto;
	}

}

package deliveryapp.jwd.support;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import deliveryapp.jwd.model.Deliverer;
import deliveryapp.jwd.model.Order;
import deliveryapp.jwd.web.dto.DelivererDTO;
import deliveryapp.jwd.web.dto.OrderDTO;

@Component
public class DelivererToDelivererDto implements Converter<Deliverer, DelivererDTO> {
	
	@Autowired
	private OrderToOrderDto orderToOrderDto;

	@Override
	public DelivererDTO convert(Deliverer deliverer) {
		DelivererDTO dto = new DelivererDTO();
		dto.setId(deliverer.getId());
		dto.setFirstLastName(deliverer.getFirstLastName());
		dto.setIdCardNumber(deliverer.getIdCardNumber());
		dto.setJmbg(deliverer.getJmbg());
		List<Order> orders = new ArrayList<>(deliverer.getOrders());
		dto.setOrders(new HashSet<>(orderToOrderDto.convert(orders)));
		return dto;
		
	}
	
	public List<DelivererDTO> convert(List<Deliverer> deliverers) {
		List<DelivererDTO> deliverersDto = new ArrayList<>();
		
		for(Deliverer deliverer : deliverers) {
			deliverersDto.add(convert(deliverer));
		}
		return deliverersDto;
	}

	
	
	

}

package deliveryapp.jwd.support;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import deliveryapp.jwd.model.Deliverer;
import deliveryapp.jwd.model.Order;
import deliveryapp.jwd.service.DelivererService;
import deliveryapp.jwd.service.OrderService;
import deliveryapp.jwd.web.dto.DelivererDTO;

@Component
public class DelivererDtoToDeliverer implements Converter<DelivererDTO, Deliverer> {
	
	@Autowired
	private DelivererService delivererService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderToOrderDto toOrderDto;

	@Override
	public Deliverer convert(DelivererDTO delivererDto) {
		
		Long id = delivererDto.getId();
		Deliverer deliverer = id == null ? new Deliverer() : delivererService.getOne(id).get();
		
		if(deliverer != null) {
			deliverer.setId(id);
			deliverer.setFirstLastName(delivererDto.getFirstLastName());
			deliverer.setIdCardNumber(delivererDto.getIdCardNumber());
			deliverer.setJmbg(delivererDto.getJmbg());
			deliverer.setOrders((List<Order>) toOrderDto.convert((Order) delivererDto.getOrders()));
		}
		return deliverer;
	}
	
	

}

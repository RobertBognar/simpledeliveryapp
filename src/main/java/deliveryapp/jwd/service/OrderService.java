package deliveryapp.jwd.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import deliveryapp.jwd.model.Order;

public interface OrderService {
	
	Optional<Order> getOne(Long id);
	
	Order findOne(Long id);
	
	Order save(Order order);
	
	Order delete(Long id);
	
	Page<Order> all(int page);
	
	Page<Order> search(String orderNumber, String orderDescription, int pageNum);

	Order update(Order order);
}

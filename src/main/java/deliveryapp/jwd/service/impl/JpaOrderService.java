package deliveryapp.jwd.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import deliveryapp.jwd.model.Order;
import deliveryapp.jwd.repository.DelivererRepository;
import deliveryapp.jwd.repository.OrderRepository;
import deliveryapp.jwd.service.OrderService;

@Service
public class JpaOrderService implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private DelivererRepository delivererRepository;

	@Override
	public Optional<Order> getOne(Long id) {
		return orderRepository.findById(id);
	}

	@Override
	public Order save(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public void delete(Long id) {
		orderRepository.deleteById(id);
		
	}

	@Override
	public Page<Order> all(int page) {
		return orderRepository.findAll(PageRequest.of(page, 10));
	}

	@Override
	public Page<Order> search(String orderNumber, String orderDescription, int pageNum) {
		return orderRepository.search(orderNumber, orderDescription, PageRequest.of(pageNum, 10));
	}
	
	

}

package deliveryapp.jwd.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import deliveryapp.jwd.model.Deliverer;
import deliveryapp.jwd.repository.DelivererRepository;
import deliveryapp.jwd.repository.OrderRepository;
import deliveryapp.jwd.service.DelivererService;

@Service
public class JpaDelivererService implements DelivererService {
	
	@Autowired
	private DelivererRepository delivererRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public List<Deliverer> getAll() {
		return delivererRepository.findAll();
	}

	@Override
	public Optional<Deliverer> getOne(Long id) {
		return delivererRepository.findById(id);
	}

	@Override
	public Deliverer save(Deliverer deliverer) {
		return delivererRepository.save(deliverer);
	}

}

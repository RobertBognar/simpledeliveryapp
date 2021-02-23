package deliveryapp.jwd.service;

import java.util.List;
import java.util.Optional;

import deliveryapp.jwd.model.Deliverer;

public interface DelivererService {
	
	List<Deliverer> getAll();
	Optional<Deliverer> getOne(Long id);
	Deliverer save(Deliverer deliverer);

}

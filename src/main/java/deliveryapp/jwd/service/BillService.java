package deliveryapp.jwd.service;

import java.util.List;
import java.util.Optional;

import deliveryapp.jwd.model.Bill;

public interface BillService {
	
	List<Bill> getAll();
	Optional<Bill> getOne(Long id);
	Bill save(Bill bill);

}

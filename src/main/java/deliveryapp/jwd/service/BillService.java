package deliveryapp.jwd.service;

import java.util.List;
import java.util.Optional;

import deliveryapp.jwd.model.Bill;

public interface BillService {
	
	Bill findOne(Long id);
	
	List<Bill> getAll();
	
	Bill save(Bill bill);
	
	Bill update(Bill bill);
	
	Bill deleted(Long id);
	
	Optional<Bill> getOne(Long id);
}

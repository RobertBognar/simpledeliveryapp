package deliveryapp.jwd.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import deliveryapp.jwd.model.Bill;
import deliveryapp.jwd.repository.BillRepository;
import deliveryapp.jwd.service.BillService;

@Service
public class JpaBillService implements BillService {
	
	@Autowired
	private BillRepository billRepository;

	@Override
	public Bill findOne(Long id) {
		return billRepository.findOneById(id);
	}

	@Override
	public List<Bill> getAll() {
		return billRepository.findAll();
	}

	@Override
	public Bill save(Bill bill) {
		return billRepository.save(bill);
	}

	@Override
	public Bill update(Bill bill) {
		return billRepository.save(bill);
	}

	@Override
	public Bill deleted(Long id) {
		Optional<Bill> bill = billRepository.findById(id);
		if(bill.isPresent()) {
			billRepository.deleteById(id);
			return bill.get();
		}
		return null;
	}

	@Override
	public Optional<Bill> getOne(Long id) {
		return billRepository.findById(id);
	}
	
	

}

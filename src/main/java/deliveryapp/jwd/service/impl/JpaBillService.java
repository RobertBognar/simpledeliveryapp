package deliveryapp.jwd.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.filter.OrderedRequestContextFilter;
import org.springframework.stereotype.Service;

import deliveryapp.jwd.model.Bill;
import deliveryapp.jwd.repository.BillRepository;
import deliveryapp.jwd.repository.OrderRepository;
import deliveryapp.jwd.service.BillService;

@Service
public class JpaBillService implements BillService {
	
	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public List<Bill> getAll() {
		return billRepository.findAll();
	}

	@Override
	public Optional<Bill> getOne(Long id) {
		return billRepository.findById(id);
	}

	@Override
	public Bill save(Bill bill) {
		return billRepository.save(bill);
	}
	
	

}

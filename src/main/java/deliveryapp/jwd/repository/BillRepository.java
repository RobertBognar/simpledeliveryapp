package deliveryapp.jwd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import deliveryapp.jwd.model.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
	
	Bill findOneById(Long id);
	
}

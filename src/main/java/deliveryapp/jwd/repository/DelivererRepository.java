package deliveryapp.jwd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import deliveryapp.jwd.model.Deliverer;

@Repository
public interface DelivererRepository extends JpaRepository<Deliverer, Long> {

	List<Deliverer> find(String jmbg, String idCardNumber, String firstLastName);

}

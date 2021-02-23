package deliveryapp.jwd.repository;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import deliveryapp.jwd.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	@Query("SELECT o FROM Order o WHERE" +
			"(:orderNumber = NULL OR o.number LIKE :orderNumber) AND " + 
			"(:orderDescription = NULL OR o.description = :orderDescription)")
	Page<Order> search(@Param("orderNumber") String orderNumber, @Param("orderDescription") String orderDescription, PageRequest pageRequest);

}

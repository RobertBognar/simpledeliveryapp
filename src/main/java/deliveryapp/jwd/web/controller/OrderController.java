package deliveryapp.jwd.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.fabric.Response;

import deliveryapp.jwd.model.Order;
import deliveryapp.jwd.service.BillService;
import deliveryapp.jwd.service.DelivererService;
import deliveryapp.jwd.service.OrderService;
import deliveryapp.jwd.support.OrderDtoToOrder;
import deliveryapp.jwd.support.OrderToOrderDto;
import deliveryapp.jwd.web.dto.OrderDTO;

@RestController
@RequestMapping(value = "/api/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private DelivererService delivererService;
	
	@Autowired
	private OrderDtoToOrder toOrder;
	
	@Autowired
	private OrderToOrderDto toOrderDto;
	
	@Autowired
	private Converter<List<Order>, List<OrderDTO>> toDtoList;
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderDTO> create(@Valid @RequestBody OrderDTO orderDTO) {
		
		Order order = toOrder.convert(orderDTO);
		Order savedOrder = orderService.save(order);
		
		return new ResponseEntity<>(toOrderDto.convert(savedOrder), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderDTO> update(@PathVariable Long id, @Valid @RequestBody OrderDTO orderDTO) {
		
		if(!id.equals(orderDTO.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Order order = toOrder.convert(orderDTO);
		Order savedOrder = orderService.update(order);
		
		return new ResponseEntity<>(toOrderDto.convert(savedOrder), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		Order deletedOrder = orderService.delete(id);
		
		if(deletedOrder != null) { 
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<OrderDTO> getOne(@PathVariable Long id){
		Order order = orderService.findOne(id);
		
		if(order != null) {
			return new ResponseEntity<>(toOrderDto.convert(order), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping
	public ResponseEntity<List<OrderDTO>> get(@RequestParam(defaultValue = "0") int page) {
		Page<Order> ordersPage = orderService.all(page);
		List<Order> orders = ordersPage.getContent();
		List<OrderDTO> body = toDtoList.convert(orders);
		return new ResponseEntity<>(body, HttpStatus.OK);
	}

}

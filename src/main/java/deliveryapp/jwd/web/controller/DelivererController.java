package deliveryapp.jwd.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import deliveryapp.jwd.model.Deliverer;
import deliveryapp.jwd.service.DelivererService;
import deliveryapp.jwd.service.OrderService;
import deliveryapp.jwd.support.DelivererDtoToDeliverer;
import deliveryapp.jwd.support.DelivererToDelivererDto;
import deliveryapp.jwd.support.OrderToOrderDto;
import deliveryapp.jwd.web.dto.DelivererDTO;

@RestController
@RequestMapping(value = "/api/deliverers", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class DelivererController {
	
	@Autowired
	private DelivererService delivererService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private DelivererDtoToDeliverer toDeliverer;
	
	@Autowired
	private DelivererToDelivererDto toDelivererDto;
	
	@Autowired
	private OrderToOrderDto toOrderDto;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DelivererDTO> create(@Valid @RequestBody DelivererDTO delivererDTO) {
		Deliverer deliverer = toDeliverer.convert(delivererDTO);
		Deliverer savedDeliverer = delivererService.save(deliverer);
		
		return new ResponseEntity<>(toDelivererDto.convert(savedDeliverer), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DelivererDTO> update(@PathVariable Long id, @Valid @RequestBody DelivererDTO delivererDTO) {
		
		if(!id.equals(delivererDTO.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Deliverer deliverer = toDeliverer.convert(delivererDTO);
		Deliverer savedDeliverer = delivererService.update(deliverer);
		
		return new ResponseEntity<>(toDelivererDto.convert(savedDeliverer), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		
		Deliverer deletedDeliverer = delivererService.delete(id);
		
		if(deletedDeliverer != null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<DelivererDTO>> getAll(
			@RequestParam(required = false) String jmbg,
			@RequestParam(required = false) String idCardNumber,
			@RequestParam(required = false) String firstLastName) {
		
		List<Deliverer> deliverers = delivererService.find(jmbg, idCardNumber, firstLastName);
		
		return new ResponseEntity<>(toDelivererDto.convert(deliverers), HttpStatus.OK);
	}

}

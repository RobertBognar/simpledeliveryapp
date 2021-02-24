package deliveryapp.jwd.web.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import deliveryapp.jwd.model.Bill;
import deliveryapp.jwd.service.BillService;
import deliveryapp.jwd.support.BillDtoToBill;
import deliveryapp.jwd.support.BillToBillDto;
import deliveryapp.jwd.web.dto.BillDTO;

@RestController
@RequestMapping(value = "/api/bills", produces = MediaType.APPLICATION_JSON_VALUE)
public class BillController {
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private BillDtoToBill toBill;
	
	@Autowired
	private BillToBillDto toBillDto;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BillDTO> create(@Valid @RequestBody BillDTO billDTO) {
			Bill bill = toBill.convert(billDTO);
			
			if(bill.getOrder() == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
			Bill savedBill = billService.save(bill);
			return new ResponseEntity<>(toBillDto.convert(savedBill), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BillDTO> update(@PathVariable Long id, @Valid @RequestBody BillDTO billDTO) {
		if(!id.equals(billDTO.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Bill bill = toBill.convert(billDTO);
		
		if(bill.getOrder() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Bill savedBill = billService.update(bill);
		
		return new ResponseEntity<>(toBillDto.convert(savedBill), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		Bill deletedBill = billService.deleted(id);
		
		if(deletedBill != null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<BillDTO> getOne(@PathVariable Long id) {
		Bill bill = billService.findOne(id);
		
		if(bill != null) {
			return new ResponseEntity<>(toBillDto.convert(bill), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

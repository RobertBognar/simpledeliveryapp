package deliveryapp.jwd.support;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import deliveryapp.jwd.model.Bill;
import deliveryapp.jwd.model.Order;
import deliveryapp.jwd.web.dto.BillDTO;

@Component
public class BillToBillDto implements Converter<Bill, BillDTO> {
	
	@Autowired
	private OrderToOrderDto toOrderDto;

	@Override
	public BillDTO convert(Bill bill) {
		BillDTO dto = new BillDTO();
		dto.setId(bill.getId());
		dto.setBillDate(bill.getBillDate());
		dto.setBillNumber(bill.getBillNumber());
		List<Order> orders = new ArrayList<>();
		dto.setOrders(new HashSet<>(toOrderDto.convert(orders)));
		return dto;
	}
	
	public List<BillDTO> convert(List<Bill> bills) {
		List<BillDTO> billsDto = new ArrayList<>();
		
		for(Bill bill : bills) { 
			billsDto.add(convert(bill));
		}
		return billsDto;
	}
	
	

}

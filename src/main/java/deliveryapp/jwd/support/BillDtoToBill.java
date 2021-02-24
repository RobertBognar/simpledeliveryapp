package deliveryapp.jwd.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import deliveryapp.jwd.model.Bill;
import deliveryapp.jwd.service.BillService;
import deliveryapp.jwd.web.dto.BillDTO;

@Component
public class BillDtoToBill implements Converter<BillDTO, Bill> {
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private OrderToOrderDto toOrderDto;

	@Override
	public Bill convert(BillDTO billDto) {

			Long id = billDto.getId();
			Bill bill = id == null ? new Bill() : billService.getOne(id).get();
			
			if(bill != null) {
				bill.setId(id);
				bill.setBillDate(billDto.getBillDate());
				bill.setBillNumber(billDto.getBillNumber());
				bill.setTotalPrice(billDto.getTotalPrice());
				bill.setOrder(toOrderDto.convert(bill.getOrder()));
			}
			return bill;
	}

}

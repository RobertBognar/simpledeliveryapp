package deliveryapp.jwd.web.dto;

import java.util.HashSet;
import java.util.Set;

public class BillDTO {
	
	private Long id;
	
	private int billNumber;
	
	private String billDate;
	
	private double totalPrice;
	
	private Set<OrderDTO> orders = new HashSet<>();
	
	public BillDTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(int billNumber) {
		this.billNumber = billNumber;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Set<OrderDTO> getOrders() {
		return orders;
	}

	public void setOrders(Set<OrderDTO> orders) {
		this.orders = orders;
	}
	
	
	

}

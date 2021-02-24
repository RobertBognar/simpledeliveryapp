package deliveryapp.jwd.web.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import deliveryapp.jwd.model.Order;

public class DelivererDTO {
	
	private Long id;
	
	private String jmbg;
	
	private String idCardNumber;
	
	private String firstLastName;
	
	private Set<OrderDTO> orders = new HashSet<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getJmbg() {
		return jmbg;
	}
	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}
	public String getIdCardNumber() {
		return idCardNumber;
	}
	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}
	public String getFirstLastName() {
		return firstLastName;
	}
	public void setFirstLastName(String firstLastName) {
		this.firstLastName = firstLastName;
	}
	public Set<OrderDTO> getOrders() {
		return orders;
	}
	public void setOrders(Set<OrderDTO> orders) {
		this.orders = orders;
	}
	
	
	

}

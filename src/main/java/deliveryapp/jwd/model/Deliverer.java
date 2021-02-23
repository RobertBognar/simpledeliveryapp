package deliveryapp.jwd.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Deliverer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String jmbg;
	
	@Column(nullable = false, unique = true)
	private String idCardNumber;
	
	@Column(nullable = false)
	private String firstLastName;
	
	@OneToMany(mappedBy = "deliverer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Order> orders = new ArrayList<>();
	
	public Deliverer() {
		
	}
	
	public Deliverer(String jmbg, String idCardNumber, String firstLastName) {
		this.jmbg = jmbg;
		this.idCardNumber = idCardNumber;
		this.firstLastName = firstLastName;
	}

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

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public void addOrder(Order order) {
		if(order.getDeliverer() != this) {
			order.setDeliverer(this);
		}
		orders.add(order);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstLastName == null) ? 0 : firstLastName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idCardNumber == null) ? 0 : idCardNumber.hashCode());
		result = prime * result + ((jmbg == null) ? 0 : jmbg.hashCode());
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Deliverer other = (Deliverer) obj;
		if (firstLastName == null) {
			if (other.firstLastName != null)
				return false;
		} else if (!firstLastName.equals(other.firstLastName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idCardNumber == null) {
			if (other.idCardNumber != null)
				return false;
		} else if (!idCardNumber.equals(other.idCardNumber))
			return false;
		if (jmbg == null) {
			if (other.jmbg != null)
				return false;
		} else if (!jmbg.equals(other.jmbg))
			return false;
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Deliverer [id=" + id + ", jmbg=" + jmbg + ", idCardNumber=" + idCardNumber + ", firstLastName="
				+ firstLastName + ", orders=" + orders + "]";
	}
	
	
	

}

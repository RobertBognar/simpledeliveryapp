package deliveryapp.jwd.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private int orderNumber;
	
	@Column(nullable = false)
	private String orderDate;
	
	@Column(nullable = false)
	private String deliveryPlace;
	
	@Column
	private double orderPrice;
	
	@Column
	private String orderDescription;
	
	@ManyToOne
	private Deliverer deliverer;
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Bill bill;
	
	public Order() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getDeliveryPlace() {
		return deliveryPlace;
	}

	public void setDeliveryPlace(String deliveryPlace) {
		this.deliveryPlace = deliveryPlace;
	}

	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getOrderDescription() {
		return orderDescription;
	}

	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}

	public Deliverer getDeliverer() {
		return deliverer;
	}

	public void setDeliverer(Deliverer deliverer) {
		this.deliverer = deliverer;
		if(!deliverer.getOrders().contains(this)) {
			deliverer.getOrders().add(this);
		}
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bill == null) ? 0 : bill.hashCode());
		result = prime * result + ((deliverer == null) ? 0 : deliverer.hashCode());
		result = prime * result + ((deliveryPlace == null) ? 0 : deliveryPlace.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + ((orderDescription == null) ? 0 : orderDescription.hashCode());
		result = prime * result + orderNumber;
		long temp;
		temp = Double.doubleToLongBits(orderPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Order other = (Order) obj;
		if (bill == null) {
			if (other.bill != null)
				return false;
		} else if (!bill.equals(other.bill))
			return false;
		if (deliverer == null) {
			if (other.deliverer != null)
				return false;
		} else if (!deliverer.equals(other.deliverer))
			return false;
		if (deliveryPlace == null) {
			if (other.deliveryPlace != null)
				return false;
		} else if (!deliveryPlace.equals(other.deliveryPlace))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (orderDescription == null) {
			if (other.orderDescription != null)
				return false;
		} else if (!orderDescription.equals(other.orderDescription))
			return false;
		if (orderNumber != other.orderNumber)
			return false;
		if (Double.doubleToLongBits(orderPrice) != Double.doubleToLongBits(other.orderPrice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderNumber=" + orderNumber + ", orderDate=" + orderDate + ", deliveryPlace="
				+ deliveryPlace + ", orderPrice=" + orderPrice + ", orderDescription=" + orderDescription
				+ ", deliverer=" + deliverer + ", bill=" + bill + "]";
	}
	
	
	

}

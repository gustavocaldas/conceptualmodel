package com.gcaldas.conceptualmodel.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Embeddable
public class OrderItemPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private PurcharseOrder purcharseOrder;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	public PurcharseOrder getPurcharseOrder() {
		return purcharseOrder;
	}

	public void setPurcharseOrder(PurcharseOrder purcharseOrder) {
		this.purcharseOrder = purcharseOrder;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((purcharseOrder == null) ? 0 : purcharseOrder.hashCode());
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
		OrderItemPK other = (OrderItemPK) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (purcharseOrder == null) {
			if (other.purcharseOrder != null)
				return false;
		} else if (!purcharseOrder.equals(other.purcharseOrder))
			return false;
		return true;
	}
}

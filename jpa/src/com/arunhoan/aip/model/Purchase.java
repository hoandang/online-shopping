
package com.arunhoan.aip.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the purchase database table.
 * 
 */
@Entity
public class Purchase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int purchaseID;

	private String status;

	//bi-directional many-to-one association to Customer
	@ManyToOne (fetch=FetchType.EAGER)
	@JoinColumn(name="CustomerID")

	private Customer customer;

	//bi-directional many-to-one association to Cart
	@ManyToOne (fetch=FetchType.EAGER)
	@JoinColumn(name="CartID")
	private Cart cart;

    public Purchase() {
    }

	public Purchase(Customer customer, Cart cart, String status) {
		this.customer = customer;
		this.cart = cart;
		this.status = status;
	}

	public int getOrderID() {
		return this.purchaseID;
	}

	public void setOrderID(int orderID) {
		this.purchaseID = orderID;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Cart getCart() {
		return this.cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
}
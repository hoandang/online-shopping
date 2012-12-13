package com.arunhoan.aip.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cart database table.
 * 
 */
@Entity
public class Cart implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int cartID;

    @Temporal( TemporalType.TIMESTAMP)
	private Date dateCreated;

	private double totalPrice;

	//bi-directional many-to-one association to CartProduct
	@OneToMany(fetch=FetchType.EAGER, mappedBy="cart")
	private List<CartProduct> cartProducts;

	//bi-directional many-to-one association to Purchase
	@OneToMany(fetch=FetchType.EAGER, mappedBy="cart")
	private List<Purchase> purchases;

	public Cart() {}
	
    public Cart(Date dateCreated, double totalPrice) {
    	this.dateCreated = dateCreated;
    	this.totalPrice = totalPrice;
    }

	public int getCartID() {
		return this.cartID;
	}

	public void setCartID(int cartID) {
		this.cartID = cartID;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<CartProduct> getCartProducts() {
		return this.cartProducts;
	}

	public void setCartProducts(List<CartProduct> cartProducts) {
		this.cartProducts = cartProducts;
	}
	
	public List<Purchase> getPurchases() {
		return this.purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}
	
}
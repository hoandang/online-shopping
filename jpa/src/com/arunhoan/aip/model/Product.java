package com.arunhoan.aip.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
public class Product implements Serializable { 
	private static final long serialVersionUID = 1L;

	@Id
	private int productID;

	private String description;

	private double price;

	//bi-directional many-to-one association to CartProduct
	@OneToMany(mappedBy="product")
	private Set<CartProduct> cartProducts;

	//bi-directional many-to-one association to Category
    @ManyToOne
	@JoinColumn(name="CategoryID")
	private Category category;

    private int quantity;
    
    public Product() {
    }

	public int getProductID() {
		return this.productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Set<CartProduct> getCartProducts() {
		return this.cartProducts;
	}

	public void setCartProducts(Set<CartProduct> cartProducts) {
		this.cartProducts = cartProducts;
	}
	
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}	
}
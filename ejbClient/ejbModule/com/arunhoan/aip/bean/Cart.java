package com.arunhoan.aip.bean;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Remote;

import com.arunhoan.aip.model.Product;

@Remote
public interface Cart {
	
	// Add product to cart
	// @product as item is to add to cart
	// @cart as existed cart is to check whether this is the new cart or not
	public void add(Product product, List<Product> cart);

	// Delete selected product in cart
	public void delete(Product product, List<Product> cart);
	
	// Update cart
	public void update(HashMap<Integer, Integer> newCart, List<Product> cart);
	
	public List<Product> cart();
}

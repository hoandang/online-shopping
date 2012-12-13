package com.arunhoan.aip.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateful;
import com.arunhoan.aip.model.Product;

/**
 * Session Bean implementation class CartBean
 */
@Stateful(name = "cartBean", mappedName = "ejb/cart")
public class CartBean implements Cart, CartBeanLocal {

	private List<Product> cart;

	@Override
	public void add(Product product, List<Product> cart) {		
		if (cart == null || cart.isEmpty()) { // If this is the first time the cart session has been initialized
			this.cart = new ArrayList<Product>(); // Init a new fresh session cart
			init(product); // Add the first product to cart
		} else {			
			// The cart has already had item inside, assign the existing cart to the local bean cart
			setCart(cart);
			if(isDuplicated(product) != null) { // If the item adding to cart is already existed into cart  
				Product p = isDuplicated(product); // Get it
				p.setQuantity(p.getQuantity() + 1); // Then increase product quantity by 1, no need to add it again
			}
			else {
				init(product); // This is a new product in cart, add it
			}
		}
	}
	
	@Override
	public void delete(Product product, List<Product> cart) {	
		// Iterate the products list to remove the request deleted product
		for(Product p : cart) { 
			if(p.getProductID() == product.getProductID()) {
				cart.remove(p);
				break;
			}
		}
		// After removing if there is no product remaining
		if (cart.isEmpty()) 
			cart.clear(); // clear the cart
		
		setCart(cart);
	}
	
	@Override
	public void update(HashMap<Integer, Integer> newCart, List<Product> cart) {
		// Loop through the hashmap to retrieve productId and its quantity
	    for (Map.Entry<Integer, Integer> entry : newCart.entrySet())
	    {
	    	for (Product p: cart) {
	    		if (p.getProductID() == entry.getKey()) {
	    			if (entry.getValue() > 0) // If the new quantity is positive
	    				p.setQuantity(entry.getValue()); // Set new quantity
	    		}
	    	}
	    }
	    setCart(cart);
	}

	@Override
	public List<Product> cart() {
		return cart;
	}
	
	private void setCart(List<Product> cart) {
		this.cart = cart;
	}
	
	// Add product into session cart
	private void init(Product product) {
		int quantity = 1; // The first quantity is 1
		// Set quantity, category name for the first time added into cart
		product.setQuantity(quantity);
		// Add item reversely
		cart.add(0, product);
	}

	// Is the product exist in the session cart
	private Product isDuplicated(Product product) {
		for(Product p : cart) {
			if(p.getProductID() == product.getProductID())
				return p;
		}
		return null;
	}
}

package com.arunhoan.aip.bean;

import java.util.List;
import javax.ejb.Remote;

import com.arunhoan.aip.model.Cart;
import com.arunhoan.aip.model.Customer;
import com.arunhoan.aip.model.Product;
import com.arunhoan.aip.model.Purchase;

@Remote
public interface Order {

	// Add customer to database
	public void add(Customer customer);
	
	// Add the products which are added to cart to database
	public void add(List<Product> cart);
	
	// Get current order id with the format following huydangnnnn
	public String getOrderID();
	
	// Get the request order id with the format following huydangnnnn
	public String getOrderID(Purchase order);
	
	// Save order to database
	public void generateOrder();
	
	// Search order by surname and order number 
	// the order id is a input String like huydang0001 then the string will be processed to number
	public Purchase getOrderBy(String orderNo, String customerSurname);
	
	// Search order by order number and surname
	// This case solves the problem when admin select the order in admin page, that number will be an integer not String like above case
	public Purchase getOrderBy(int orderNo, String customerSurname);
	
	// Get the products which is inside request cart
	public List<Product> getProductsBy(Cart cart);
	
	// Return order status
	public String status(Purchase order);
	
	// Find the order with status is ORDERED and PAID
	public List<Purchase> findOutstandingOrders();
	
	// Return customer with request customer id 
	public Customer customer(int customerID);

	// Update order status
	public void updateOrder(int orderId, String status);

	// Return the list of order with the status is PAID
	public List<Purchase> paidOrders();

	// Return order by order id
	public Purchase getOrderBy(String orderID);

	// Check whether the order is paid or not
	public boolean isPaidOrder(String orderID);
}

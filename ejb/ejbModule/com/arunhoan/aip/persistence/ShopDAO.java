package com.arunhoan.aip.persistence;

import com.arunhoan.aip.model.*;

import java.util.List;

public interface ShopDAO {
	// Add cart
	public void add(Cart cart);
	
	// Add order
	public void add(Purchase order);
	
	// Add customer
	public void add(Customer customer);
	
	// Add cart_product
	public void add(CartProduct cartProduct);
	
	// Update status order
	public void updateOrder(int orderId, String status);
	
	// Find all category
	public List<Category> findAllCategory();
	
	// Find the newest added product
	public List<Product> findNewProducts();
	
	// Find the products by category
	public List<Product> findProductsByCategory(String categoryName);
	
	// Find particular product
	public Product findProduct(int productId);
	
	// Find particular category 
	public Category findCategory(int categoryId);
	
	// Find particular customer
	public Customer findCustomer(int customerId);
	
	// Find recent customer
	public Customer findRecentCustomer();
	
	// Find recent cart
	public Cart findRecentCart();
	
	// Find recent order
	public Purchase findRecentOrder();
	
	// Find all order
	public List<Purchase> findAllOrder();
	
	// Find outstanding orders
	public List<Purchase> findOutstandingOrders();
	
	// Find all product
	public List<Product> findAllProduct();
	
	// Find all customer
	public List<Customer> findAllCustomer();
	
	// Find all cart
	public List<Cart> findAllCart();
	
	// Search products by cart
	public List<Product> searchProductsByCart(int cartId);
	
	// Search order by order number and customer surname
	public Purchase searchOrderBy(int orderID, String customerSurname);
	
	// Search order by order number
	public Purchase searchOrderBy(int orderID);
	
	// Find customer with outstanding purchase status is PAID or ORDERED
	public Customer findOutstandingCustomersBy(int orderID);
	
	// Find cart with outstanding purchase status
	public Cart findOutstandingCartsBy(int orderID);
	
    public List<Product> findProductsByCategorys(String type, int offSet, int noOfRecords);
    
    public Number getRowFound(String categoryName);

	public Cart getLastCart();
	
	public List<Purchase> findPaidOrder();

}

package com.arunhoan.aip.bean;

import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.arunhoan.aip.model.Cart;
import com.arunhoan.aip.model.CartProduct;
import com.arunhoan.aip.model.CartProductPK;
import com.arunhoan.aip.model.Customer;
import com.arunhoan.aip.model.Product;
import com.arunhoan.aip.model.Purchase;
import com.arunhoan.aip.persistence.ShopDAO;
import com.arunhoan.aip.persistence.ShopDAOImpl;

@Stateless(name = "orderBean", mappedName = "ejb/order")
public class OrderBean implements Order, OrderBeanLocal {

	@PersistenceContext
	private EntityManager entityManager;
	
	private static final String STATUS = "ORDERED";
	private ShopDAO dao;
	
	@PostConstruct
    public void init() {
    	dao = new ShopDAOImpl(entityManager);
    }

	@Override
	public void add(Customer customer) {
		dao.add(customer);
	}

	// Add the cart information to db
	@Override
	public void add(List<Product> cart) {
		
		// Add the cart session into db with the price cart and date created
		dao.add(new Cart(new Date(), totalPrice(cart)));
		
		// Get the last cart just added to make it as a main key for the products
		// Eg: cart1 -> product1, cart1 -> product2,..
		Cart lastCart = dao.getLastCart();
		
		// Loop the whole cart to extract each record inside
		for (Product product: cart) {
			// Save the whole cart information to db
			dao.add(cartProduct(cartProductPK(product, lastCart)));
		}
		
	}
	
	// Return cartproductpk which stores the cart and its product and total its product's quantity inside
	// Eg: The whole cart may be:
	// 					cart1 -> prod1(10 items)
	//					cart1 -> prod2(2 items)
	// 					cart1 -> prod3(3 items)
	// This method functions each record of cart 
	// Eg:				cart1 -> prod1(10 items)
	private CartProductPK cartProductPK(Product product, Cart cart) {
		
		int cartID = cart.getCartID();
		int productID = product.getProductID();
		int quantity = product.getQuantity(); // This is the total quantity for each product added to cart
		
		return new CartProductPK(cartID, productID, quantity); 
	}
	
	// This method functions the whole cart
	private CartProduct cartProduct(CartProductPK cartProductPK) {
		return new CartProduct(cartProductPK);
	}

	// Get cart total price for the order
	private double totalPrice(List<Product> cart) {
		double totalPrice = 0.0;
		
		for(Product product : cart) {
			totalPrice += (product.getQuantity() * product.getPrice());
		}
		
		return totalPrice;
	}

	// Get order id for the recent order
	@Override
	public String getOrderID() {
		return orderId(dao.findRecentOrder());
	}
	
	// Get the order id by given order
	@Override
	public String getOrderID(Purchase order) {
		return orderId(order);
	}

	@Override
	public void generateOrder() {
		Customer customer = dao.findRecentCustomer();
		Cart cart = dao.findRecentCart(); 
		dao.add(new Purchase(customer, cart, STATUS));
	}
	
	// Search order by surname and order number 
	// the order id is a input String like huydang0001 then the string will be processed to number
	@Override
	public Purchase getOrderBy(String orderNo, String customerSurname) {
		try {
			int orderID = 0;
			// Substring huydang0001 7 characters, it becomes 0001
			if (orderNo.substring(7).length() != 4)   // If the last digit size not 4 that means order number may be huydang1
				return null; // not found
			else {
				orderID = Integer.parseInt(orderNo.substring(7)); // If everything fine with format 0001 for the last digit, convert to number	
				return dao.searchOrderBy(orderID, customerSurname);
			}
		} catch (Exception e) { // Exception occurs when order number is all string
			return null;
		}
	}
	
	// Search order by order number and surname
	// This case solves the problem when admin select the order in admin page, that number will be an integer not String like above case
	public Purchase getOrderBy(int orderNo, String customerSurname) {
		return dao.searchOrderBy(orderNo, customerSurname);
	}
	
	@Override
	public Purchase getOrderBy(String orderNo) {
		try {
			int orderID = 0;
			// Substring huydang0001 7 characters, it becomes 0001
			if (orderNo.substring(7).length() != 4)   // If the last digit size not 4 that means order number may be huydang1
				return null; // not found
			else {
				orderID = Integer.parseInt(orderNo.substring(7));
				return dao.searchOrderBy(orderID);
			}
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Product> getProductsBy(Cart cart) {	
		List<Product> products = dao.searchProductsByCart(cart.getCartID());
		  
		for (Product p: products) {
			for (CartProduct cp: p.getCartProducts())
				if (cp.getCart().getCartID() == cart.getCartID())
					p.setQuantity((cp.getId().getQuantity()));
		}
		
		return products;
	}	
	
	// Generate suffix id number following the format nnnn
	private String suffix(int id) {
		if (id < 10)
			return "000" + id;
		else if (id < 100)
			return "00" + id;
		else if (id < 1000)
			return "0" + id;
		else 
			return String.valueOf(id);
	}
	
	// Create the order id format huydangnnnn
	private String orderId(Purchase order) {
		String prefix = "huydang";
		// Retrieve the last order id from database then substring it to get the 4 last digit
		// convert it to number then check condition to add the correct 0 number by calling method suffix()
		String suffix = suffix(order.getOrderID());
		return prefix + suffix;
	}

	// Return status order by request order
	@Override
	public String status(Purchase order) {
		return dao.searchOrderBy(order.getOrderID()).getStatus();
	}
	
	// Return orders have the PAID and ORDERED status
	@Override
	public List<Purchase> findOutstandingOrders() {
		// set outstanding customer and cart for each order
		List<Purchase> orders = dao.findOutstandingOrders(); 
		return orders;
	}

	// Return customer by request customer id
	@Override
	public Customer customer(int customerID) {
		return dao.findCustomer(customerID);
	}

	// Update status order
	@Override
	public void updateOrder(int orderId, String status) {
		dao.updateOrder(orderId, status);
	}
	
	// Return orders have the PAID status
	@Override
	public List<Purchase> paidOrders() {
		return dao.findPaidOrder();
	}

	@Override
	public boolean isPaidOrder(String orderID) {
		try {
			int id = Integer.parseInt(orderID.substring(7));
			for (Purchase order: dao.findPaidOrder()) {
				if (id == order.getOrderID())
					return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
}

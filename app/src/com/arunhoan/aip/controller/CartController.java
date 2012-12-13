package com.arunhoan.aip.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.arunhoan.aip.bean.Cart;
import com.arunhoan.aip.bean.Shop;
import com.arunhoan.aip.model.Product;

public class CartController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private Context context;
	
	@EJB (name = "shopBean", mappedName = "ejb/shop")
    private Shop shop;
	
	private Cart cartService;
	
	// The products session
	private List<Product> cart;
        
    public CartController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Init the session bean
		initBean();
		
		// Get the session cart
		HttpSession session = request.getSession();		
		cart = (List<Product>)session.getAttribute("cart");
		
		String action = request.getParameter("action");
		
		// Trigger the add cart action from add cart button
		if (action.equals("add")) {
			addCart(request);
		}
		// Trigger the delete action which deletes the selected item in cart page
		else if (action.equals("delete")) {
			deleteCart(request);
		}
		// Trigger the update action from the update button in cart page
		else if (action.equals("update")) {
			updateCart(request);
		}
		// Clear completely the session cart and empty the current cart
		else if (action.equals("cancel")) {
			destroyCart();
		}
		
		// Assign the session cart
		session.setAttribute("cart", cart);

		response.sendRedirect("shop?action=cart");
	}

	// Add item to cart
	private void addCart(HttpServletRequest request) {
		// Inject cart bean to process the adding action
		cartService.add(product(request), cart);
		cart = cartService.cart();
	}

	// delete product in cart 
	private void deleteCart(HttpServletRequest request) {
		cartService.delete(product(request), cart);
		cart = cartService.cart();
	}
	
	
	// Update cart
	private void updateCart(HttpServletRequest request) {
		cartService.update(newCart(request), cart);
		cart = cartService.cart();
	}
	
	// Empty the cart
	private void destroyCart() {
		cart.clear();
	}
	
	// Looup the the stateful session bea by JDNI lookup
	private void initBean() {
		String mappedName = "ejb/cart";
		String weblogicExtra = "#" + Cart.class.getName();
		String jndiName = mappedName + weblogicExtra;
		
		try {
			context = new InitialContext();
			cartService = (Cart) context.lookup(jndiName);
		} catch (NamingException e) { e.printStackTrace(); }
	}
	
	// Return product information by request product id when user click add cart button
	private Product product(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("productId"));
		return shop.product(id);
	}
	
	// Return a hashmap which consists the product id and its new quantity from request update
	// The hashmap can store both key which are productid and quantity
	private HashMap<Integer, Integer> newCart(HttpServletRequest request) {
		HashMap<Integer, Integer> newCart = new HashMap<Integer, Integer>();
		
		@SuppressWarnings("rawtypes")
		Enumeration parEnumeration = request.getParameterNames();
	    while (parEnumeration.hasMoreElements()) {
	    	
	    	// This is the input name
	        String parameterName = (String) parEnumeration.nextElement();
	        
	        if (!parameterName.equals("action")) { // For some reason the server get one extra value name "action", so I get rid of
	        	int newQuantity = 0;
	        	try {
	        		// This is the input value by its name, get value from html input
	        		newQuantity = Integer.parseInt(request.getParameter(parameterName));
	        	// Catch exception if the input is invalid such as negative number or character
	        	} catch (Exception e) {
	        		// If exception caught, the new quantity will be negative
	        		// This will be sent to the bean and bean will ignore this quantity
	        		// and keep the original quantity 
	        		newQuantity = -1; 
	        	}
	        	int productId = Integer.parseInt(parameterName);
	        	newCart.put(productId, newQuantity);
	        }
	        
	    }
	    return newCart;
	}
}
 
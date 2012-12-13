package com.arunhoan.aip.controller;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.arunhoan.aip.bean.Order;
import com.arunhoan.aip.model.Cart;
import com.arunhoan.aip.model.Customer;
import com.arunhoan.aip.model.Product;
import com.arunhoan.aip.model.Purchase;

public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB (name = "orderBean", mappedName = "ejb/order")
    private Order orderService;
	
    public OrderController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if (action.equals("confirm")) {
			initCustomerSession(request);
			response.sendRedirect("shop?action=confirm");
		} 
		else if (action.equals("proceed")) {
			// Save customer info
			Customer customer = (Customer)request.getSession().getAttribute("customer");
			save(request, customer);
			
			// Save cart info
			@SuppressWarnings("unchecked")
			List<Product> cart = (List<Product>)request.getSession().getAttribute("cart");
			save(request, cart);
			
			// Generate order
			orderService.generateOrder();
			response.sendRedirect("shop?action=success&orderId=" + orderService.getOrderID());
		}	
		else if (action.equals("search")) {
			String orderID = request.getParameter("orderNo");
			String surname = request.getParameter("surname");
			
			Purchase order = orderService.getOrderBy(orderID, surname);
			if (order != null) {
				
				Cart cart = order.getCart();
		 		List<Product> products = orderService.getProductsBy(cart);

				request.getSession().setAttribute("products", products);
				request.getSession().setAttribute("order", order);
			
				response.sendRedirect("shop?action=search&found=true");
			} else 
				response.sendRedirect("shop?action=search&found=false");
		}
	}
	
	// Save customer info into database
	private void save(HttpServletRequest request, Customer customer) {
		orderService.add(customer);
		request.getSession().removeAttribute("customer");
	}
	
	private void save(HttpServletRequest request, List<Product> cart) {
		orderService.add(cart);
		request.getSession().removeAttribute("cart");
	}
	
	// Create a session customer information
	private void initCustomerSession(HttpServletRequest request) {
		
		if (request.getSession().getAttribute("customer") != null)
			request.getSession().removeAttribute("customer");
		
		String title = request.getParameter("title");
		String givenName = request.getParameter("givenName");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		String addr = request.getParameter("address");
		String country = request.getParameter("country");
		String state = request.getParameter("state");
		String postcode = request.getParameter("postcode");
		String creditNo = request.getParameter("creditNo");
		
		Customer customer = new Customer(title, givenName, surname, addr, state, postcode, country, email, creditNo);
		
		request.getSession().setAttribute("customer", customer);
	}
}

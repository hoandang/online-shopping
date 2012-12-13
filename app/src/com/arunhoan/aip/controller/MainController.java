package com.arunhoan.aip.controller;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arunhoan.aip.bean.Order;
import com.arunhoan.aip.bean.Shop;
import com.arunhoan.aip.model.Category;
import com.arunhoan.aip.model.Customer;
import com.arunhoan.aip.model.Product;
import com.arunhoan.aip.model.Purchase;

public class MainController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@EJB (name = "shopBean", mappedName = "ejb/shop")
    private Shop shop;
	
	@EJB (name = "orderBean", mappedName = "ejb/order")
    private Order orderService;
	
    public MainController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if (action == null) 
			action = "home";
		
		String address = "";
		
		// Redirect to home page
		if (action.equals("home")) {
			address = "home.jsp";
		} 
		// Redirect to new product page
		else if (action.equals("new-products")) {
			address = "new_products.jsp";
			browseNewProducts(request);
		}
		// Redirect to the product page with the particular category
		else if (action.equals("products")) {
			address = "products.jsp";
			browseProducts(request);
		} 
		// Redirect to the cart page
		else if (action.equals("cart")) {
			@SuppressWarnings("unchecked")
			List<Product> products = (List<Product>)request.getSession().getAttribute("cart");
			if (products == null || products.isEmpty())  // If there is no cart session
				address = "empty_cart.jsp"; // Forward to empty cart page
			else {
				address = "cart.jsp";
				request.setAttribute("products", products);
			}
		}
		// Redirect to the checkout page
		else if (action.equals("checkout")) {
			@SuppressWarnings("unchecked")
			List<Product> products = (List<Product>)request.getSession().getAttribute("cart");
			if (products == null || products.isEmpty()) // If there is no cart session
				response.sendRedirect("shop?action=404"); // Forward to 404 page
			else 
				address = "checkout.jsp";
		// Redirect to the confirm page
		} else if (action.equals("confirm")) {
			address = "confirm.jsp";
			Customer customer = (Customer)request.getSession().getAttribute("customer");
			
			if (customer == null) // If there is no customer session 
				response.sendRedirect("shop?action=404"); // Forward to 404 page
			else {
				// Validate customer info
				validateCustomerInfo(request, customer);
				
				request.setAttribute("customer", customer);
			}
		} 
		// Redirect to the success page
		else if (action.equals("success")) {
			address = "success.jsp";
			String orderId = request.getParameter("orderId");
			request.setAttribute("orderId", orderId);
		}
		
        // Redirect to the search order page
        else if (action.equals("search")) {
            try {
                if (request.getParameter("found").equals("true")) {
                    address = "search_result.jsp";
                    request.setAttribute("products", request.getSession().getAttribute("products"));
                    
                    // Load order information
                    Purchase order = (Purchase)request.getSession().getAttribute("order");
                    order.setStatus(orderService.status(order)); // Reload status in case admin changes the status
                    String orderID = orderService.getOrderID(order);
                    request.setAttribute("order", order);
                    request.setAttribute("orderID", orderID);
                    
                } else
                    address = "search_not_found.jsp";
            } catch (Exception e) {
                address = "search.jsp";
                request.getSession().removeAttribute("products");
                request.getSession().removeAttribute("order");
            }
        }
		
		else if (action.equals("404")) {
			address ="404.jsp";
		} else {
			response.sendRedirect("shop?action=404");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
	
	// Browse to new product page
	private void browseNewProducts(HttpServletRequest request) {
		List<Category> categories = shop.categories();
		List<Product> products = shop.newProducts();
		request.setAttribute("categories", categories);
		request.setAttribute("products", products);
	}
	
	// Browse product in particular category
	// Initially set max 6 number of the products to display on product.jsp
	private void browseProducts(HttpServletRequest request) {
		int page = 1;
	    int recordPerPages = 6;
	    String category = null;
		category = request.getParameter("category");
		
		//get page number from jsp
		if(request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));
		
		// offset = set the max limit of data to retrieve from database
		// noOfRecords = get total row found in product database
		// noOfPages = set no of pages to display on product.jsp 
		int offSet = (page -1) * recordPerPages;
		int noOfRecords = shop.getRowFound(category);
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordPerPages);
		
		// set max and min limit to access data.
		List<Category> categories = shop.categories();
		List<Product> products = shop.productsBy(category, offSet, recordPerPages);
		
		request.setAttribute("noOfPages", noOfPages);
	    request.setAttribute("currentPage", page);
		request.setAttribute("categoryName", category);
		request.setAttribute("categories", categories);
		request.setAttribute("products", products);
	}
	
	// Validate customer info
	private void validateCustomerInfo(HttpServletRequest request, Customer customer) {
		if (!customer.isValid())
			request.setAttribute("error", "true");
		
		if (!customer.isValidGivenName())
			request.setAttribute("invalidGivenName", "Given Name must not be empty and number");
		if (!customer.isValidSurname())
			request.setAttribute("invalidSurname", "Surname must not be empty and number");
		if (!customer.isValidAddress())
			request.setAttribute("invalidAddress", "Address must not be empty and special character");
		if (!customer.isValidEmail())
			request.setAttribute("invalidEmail", "Email has an invalid email format");
		if (!customer.isValidCountry())
			request.setAttribute("invalidCountry", "Country name must not be empty and number");
		if (!customer.isValidState())
			request.setAttribute("invalidState", "State must not be empty and number");
		if (!customer.isValidPoscode())
			request.setAttribute("invalidPostCode", "Postcode must not be empty and characters");
		if (!customer.isValidCreditNo())
			request.setAttribute("invalidCreditNo", "Credit Number must not be empty and characters");
	}

}

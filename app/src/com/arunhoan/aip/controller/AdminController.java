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
import com.arunhoan.aip.model.Customer;
import com.arunhoan.aip.model.Product;
import com.arunhoan.aip.model.Purchase;


public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB (name = "orderBean", mappedName = "ejb/order")
    private Order orderService;
       
    public AdminController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if (action == null) 
			action = "admin";
		
		String address = "";
		
		// Redirect to admin page
		if (action.equals("admin")) {
			address = "admin.jsp";
			
			// List all information about outstanding order
			request.setAttribute("orders", orderService.findOutstandingOrders());
		} 
		// Redirect to select order page
		else if (action.equals("select")) {
			
			address = "select.jsp";
			
			// get request orderid and customerid
			int orderID = Integer.parseInt(request.getParameter("orderId"));
			int customerId = Integer.parseInt(request.getParameter("customerId"));
			
			// fetch customer info
			Customer customer = orderService.customer(customerId);
			
			// fetch order and products inside the order
			Purchase order = orderService.getOrderBy(orderID, customer.getSurname());
			List<Product> products = orderService.getProductsBy(order.getCart());

			// send respone back to select page
			request.setAttribute("products", products);
			request.setAttribute("orderID", orderService.getOrderID(order));
			request.setAttribute("order", order);
			request.setAttribute("customer", customer);
			
		} 
		// Update the status order then reload page
		else if (action.equals("update")) {
			
			String status = request.getParameter("status").toUpperCase();
			int orderId = Integer.parseInt(request.getParameter("orderId"));
			int customerId = Integer.parseInt(request.getParameter("customerId"));
			
			orderService.updateOrder(orderId, status);
			address = "admin?action=select&orderId="+ orderId +"&customerId=" + customerId;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
}

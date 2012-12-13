package com.arunhoan.aip.service;

import javax.ejb.EJB;
import javax.jws.*;
import java.util.ArrayList;
import java.util.List;
import com.arunhoan.aip.bean.Order;
import com.arunhoan.aip.model.CartProduct;
import com.arunhoan.aip.model.Purchase;
import com.arunhoan.aip.service.pojo.PaidOrder;

@WebService(portName = "SupplierPort", serviceName = "SupplierService", 
targetNamespace = "http://com.arunhoan.aip.service/", endpointInterface = "com.arunhoan.aip.service.Supplier")
public class SupplierImpl implements Supplier {
	
	@EJB (name = "orderBean", mappedName = "ejb/order")
    private Order orderService;  

	@Override
	public List<PaidOrder> getPaidOrders() {

		List<PaidOrder> paidOrders = new ArrayList<PaidOrder>();
		for (Purchase order: orderService.paidOrders()) 
			paidOrders.add(paidOrder(order));

		return paidOrders;

	} 

	// Return PaidOrder bean
	private PaidOrder paidOrder(Purchase order) {
		String orderID = orderService.getOrderID(order);
		String customerSurname = order.getCustomer().getSurname();
		String status = order.getStatus();
		double totalPrice = 0.0;
		int quantity = 0;
		
		for(CartProduct cp : order.getCart().getCartProducts()) {
			totalPrice = cp.getCart().getTotalPrice();
			quantity += cp.getId().getQuantity();
		}
		
		return new PaidOrder(orderID, customerSurname, quantity, status, totalPrice);
	}
	
	@Override
	public String updateOrder(String orderID, String status) {
		

		// If the request order doesn't exist or its status not PAID
		if (orderService.getOrderBy(orderID) == null || !orderService.isPaidOrder(orderID)) {
			return "The order number doesn't exist. Update unsuccessful";
		// If the status request is not SENT
		} else if (!status.equalsIgnoreCase("sent")) {
			return "The status must be SENT. Update unsuccessful";
		// Get the String order id with format huydangnnnn, then substring the huydang to cast the number ID inside
		} else { 
			int orderNo = Integer.parseInt(orderID.substring(7));
			orderService.updateOrder(orderNo, status.toUpperCase());
			return "Updated successful !!!";
		}

	}

	@Override
	public boolean isAuthorised() {
		return true;
	}
}

package com.arunhoan.aip.service;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

import com.arunhoan.aip.service.pojo.PaidOrder;

@WebService(name = "Supplier", targetNamespace = "http://com.arunhoan.aip.service/")
public interface Supplier {

	// Return list of paid orders
	@WebMethod(operationName = "getPaidOrders")
	public List<PaidOrder> getPaidOrders();

	// Update the order to SENT status, available only for orders have status PAID
	@WebMethod(operationName = "updateOrder")
	public String updateOrder(String orderID, String status);
	
	@WebMethod(operationName = "isAuthorised")
	public boolean isAuthorised();
}
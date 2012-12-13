package com.arunhoan.aip.service.pojo;

public class PaidOrder {
	private String orderID;
	private String customerSurname;
	private int quantity;
	private String status;
	private double totalPrice;
	
	public PaidOrder() {}

	public PaidOrder(String orderID, String customerSurname, int quantity, 
			String status, double totalPrice) {
		this.orderID = orderID;
		this.customerSurname = customerSurname;
		this.quantity = quantity;
		this.status = status;
		this.totalPrice = totalPrice;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getCustomerSurname() {
		return customerSurname;
	}

	public void setCustomerSurname(String customerSurname) {
		this.customerSurname = customerSurname;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}

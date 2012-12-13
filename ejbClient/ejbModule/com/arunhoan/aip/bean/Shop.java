package com.arunhoan.aip.bean;
import java.util.List;

import javax.ejb.Remote;

import com.arunhoan.aip.model.Category;
import com.arunhoan.aip.model.Product;

@Remote
public interface Shop {
	// List new product
	public List<Product> newProducts();
	
	// Display menu category
	public List<Category> categories();
	
	// Display product by category
	public List<Product> productsBy(String category);
	
	// Display product by product id
	public Product product(int id);
	
	// Display product by pagination
	public List<Product> productsBy(String category, int offSet, int noOfRecords);
	public int getRowFound(String categoryName);
}

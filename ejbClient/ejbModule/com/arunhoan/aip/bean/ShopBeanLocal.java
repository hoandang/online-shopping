package com.arunhoan.aip.bean;

import java.util.List;
import javax.ejb.Local;
import com.arunhoan.aip.model.Category;
import com.arunhoan.aip.model.Product;

@Local
public interface ShopBeanLocal {
	public List<Product> newProducts();
	public List<Category> categories();
	public List<Product> productsBy(String category);
	public Product product(int id);
	public List<Product> productsBy(String category, int offSet, int noOfRecords);
	public int getRowFound(String categoryName);
}

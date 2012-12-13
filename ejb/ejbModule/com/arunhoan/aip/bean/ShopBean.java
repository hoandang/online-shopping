package com.arunhoan.aip.bean;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.arunhoan.aip.model.Category;
import com.arunhoan.aip.model.Product;
import com.arunhoan.aip.persistence.ShopDAO;
import com.arunhoan.aip.persistence.ShopDAOImpl;

@Stateless(name = "shopBean", mappedName = "ejb/shop")
public class ShopBean implements Shop, ShopBeanLocal {

	@PersistenceContext
	private EntityManager entityManager;
	
	private ShopDAO dao;
    
    @PostConstruct
    public void init() {
    	dao = new ShopDAOImpl(entityManager);
    }
    
	@Override
	public List<Product> newProducts() {
		return dao.findNewProducts();
	}

	@Override
	public List<Category> categories() {
		return dao.findAllCategory();
	}

	@Override
	public List<Product> productsBy(String category) {
		return dao.findProductsByCategory(category);
	}

	@Override
	public Product product(int id) {
		return dao.findProduct(id);
	}

	@Override
	public List<Product> productsBy(String category, int offSet, int noOfRecords) {
		return dao.findProductsByCategorys(category, offSet, noOfRecords);
	}

	@Override
	public int getRowFound(String categoryName) {
		return dao.getRowFound(categoryName).intValue();
	}
}

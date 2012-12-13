package com.arunhoan.aip.persistence;

import java.util.List;

import javax.persistence.*;

import com.arunhoan.aip.model.*;

public class ShopDAOImpl implements ShopDAO {

	@PersistenceContext
	private EntityManager entityMng;

	public ShopDAOImpl(EntityManager entMang) {
		this.entityMng = entMang;
	}

	@Override
	public void add(Purchase order) {
		entityMng.persist(order);
	}

	@Override
	public void add(Cart cart) {
		entityMng.persist(cart);
	}
	
	@Override
	public void add(CartProduct cartProduct) {
		entityMng.persist(cartProduct);
	}                  
	
	@Override
	public void add(Customer customer) {
		entityMng.persist(customer);
	}

	@Override
	public void updateOrder(int orderId, String status) {
		Purchase cust_purchase = entityMng.find(Purchase.class, orderId);
		cust_purchase.setStatus(status);
		entityMng.merge(cust_purchase);
	}

	@Override
	public List<Category> findAllCategory() {
		@SuppressWarnings("unchecked")
		List<Category> categoryList = entityMng.createQuery(
				"SELECT c FROM Category c").getResultList();
		return categoryList;
	}

	@Override
	public List<Product> findNewProducts() {
		String sql = "SELECT product " + "FROM Product product "
				+ "ORDER BY product.productID DESC";

		@SuppressWarnings("unchecked")
		List<Product> products = entityMng.createQuery(sql).setMaxResults(6)
				.getResultList();
		return products;
	}

	@Override
	public List<Product> findProductsByCategory(String category) {
		@SuppressWarnings("unchecked")
		List<Product> categoryList = entityMng
				.createQuery(
						"SELECT prod FROM Product prod "
								+ "JOIN prod.category cat WHERE cat.categoryName = ?1")
				.setParameter(1, category).getResultList();
		return categoryList;

	}

	@Override
	public Product findProduct(int productId) {
		Product product = (Product) entityMng
				.createQuery(
						"SELECT prod FROM Product prod WHERE prod.productID = ?1")
				.setParameter(1, productId).getSingleResult();
		return product;
	}

	@Override
	public Category findCategory(int categoryId) {
		Category category = (Category) entityMng
				.createQuery(
						"SELECT cat FROM Category cat WHERE cat.categoryID = ?1")
				.setParameter(1, categoryId).getSingleResult();
		return category;
	}

	@Override
	public Customer findCustomer(int customerId) {
		Customer customer = (Customer) entityMng
				.createQuery(
						"SELECT cust FROM Customer cust WHERE cust.customerID = ?1")
				.setParameter(1, customerId).getSingleResult();
		return customer;
	}

	@Override
	public Customer findRecentCustomer() {
		Customer customer = (Customer) entityMng
				.createQuery(
						"SELECT cust FROM Customer cust ORDER BY cust.customerID DESC")
				.setMaxResults(1).getSingleResult();
		return customer;
	}

	@Override
	public Cart findRecentCart() {
		Cart cart = (Cart) entityMng
				.createQuery("SELECT c FROM Cart c ORDER BY c.cartID DESC")
				.setMaxResults(1).getSingleResult();
		return cart;
	}

	@Override
	public Purchase findRecentOrder() {
		Purchase purchase = (Purchase) entityMng
				.createQuery(
						"SELECT p FROM Purchase p ORDER BY p.purchaseID DESC")
				.setMaxResults(1).getSingleResult();
		return purchase;
	}

	@Override
	public List<Purchase> findAllOrder() {

		@SuppressWarnings("unchecked")
		List<Purchase> purchaseList = entityMng.createQuery(
				"SELECT p FROM Purchase p ").getResultList();
		return purchaseList;
	}

	@Override
	public List<Purchase> findOutstandingOrders() {
		@SuppressWarnings("unchecked")
		List<Purchase> purchaseList = entityMng
				.createQuery(
						"SELECT purh FROM Purchase purh WHERE purh.status = ?1 OR purh.status= ?2")
				.setParameter(1, "ORDERED").setParameter(2, "PAID")
				.getResultList();
		return purchaseList;
	}

	@Override
	public List<Product> findAllProduct() {
		@SuppressWarnings("unchecked")
		List<Product> productList = entityMng.createQuery(
				"SELECT p FROM Product p").getResultList();
		return productList;
	}

	@Override
	public List<Customer> findAllCustomer() {

		@SuppressWarnings("unchecked")
		List<Customer> customers = entityMng.createQuery(
				"SELECT c FROM Customer c").getResultList();
		return customers;
	}

	@Override
	public List<Cart> findAllCart() {
		@SuppressWarnings("unchecked")
		List<Cart> cartList = entityMng.createQuery(
				"SELECT cart FROM Cart cart").getResultList();
		return cartList;
	}

	@Override
	public List<Product> searchProductsByCart(int cartId) {

		@SuppressWarnings("unchecked")
		List<Product> productList = entityMng
				.createQuery(
						"SELECT p FROM Product p JOIN p.category cat JOIN p.cartProducts cart WHERE cart.id.cartID = ?1")
				.setParameter(1, cartId).getResultList();

		return productList;
	}

	@Override
	public Purchase searchOrderBy(int orderID, String customerSurname) {
		Purchase purchase = (Purchase) entityMng
				.createQuery(
						"SELECT p FROM Purchase p JOIN p.customer cust WHERE cust.surname = ?1 AND p.purchaseID = ?2")
				.setParameter(1, customerSurname).setParameter(2, orderID)
				.getSingleResult();
		return purchase;
	}

	@Override
	public Purchase searchOrderBy(int orderID) {

		Purchase order = (Purchase) entityMng
				.createQuery(
						"SELECT puch FROM Purchase puch WHERE puch.purchaseID = ?1")
				.setParameter(1, orderID).getSingleResult();
		return order;
	}

	@Override
	public Customer findOutstandingCustomersBy(int orderID) {

		Customer customer = (Customer) entityMng
				.createQuery(
						"select cust from Customer cust"
								+ "JOIN customer.purchases purchase WHERE purchase.purchaseID = ?1")
				.setParameter(1, orderID).getSingleResult();
		return customer;
	}

	@Override
	public Cart findOutstandingCartsBy(int orderID) {

		Cart cart = (Cart) entityMng
				.createQuery(
						"SELECT cart FROM Cart cart JOIN cart.purchase purh WHERE purh.purchaseID = ?1")
				.setParameter(1, orderID).getSingleResult();

		return cart;
	}

	@Override
	public Cart getLastCart() {
		Cart cart = (Cart) entityMng.createQuery(
						"SELECT cart FROM Cart cart ORDER BY cart.cartID DESC")
				.setMaxResults(1).getSingleResult();
		return cart;
	}

	@Override
	public List<Product> findProductsByCategorys(String type, int offSet,
			int noOfRecords) {
		@SuppressWarnings("unchecked")
		List<Product> categoryList = entityMng
				.createQuery(
						"SELECT prod  FROM Product prod "
								+ "JOIN prod.category cat WHERE cat.categoryName = ?1")
				.setParameter(1, type)
				.setFirstResult(offSet)
				.setMaxResults(noOfRecords)
				.getResultList();
		return categoryList;
	}

	@Override
	public Number getRowFound(String categoryName) {
		Number foundRow = (Number) entityMng.createQuery("SELECT COUNT(prod.productID) FROM Product prod JOIN prod.category cat  WHERE cat.categoryName = ?1")
				.setParameter(1, categoryName).getSingleResult();
		return foundRow;
	}
	
	public EntityManager getEntityMng() {
		return entityMng;
	}

	public void setEntityMng(EntityManager entityMng) {
		this.entityMng = entityMng;
	}
	
	@Override
	public List<Purchase> findPaidOrder() {
		@SuppressWarnings("unchecked")
		List<Purchase> purchaseList = entityMng
				.createQuery(
						"SELECT purh FROM Purchase purh "+
						"WHERE purh.status = ?1")
				.setParameter(1, "PAID")
				.getResultList();
		return purchaseList;
	}
}

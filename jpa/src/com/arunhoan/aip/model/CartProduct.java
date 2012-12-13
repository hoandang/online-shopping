package com.arunhoan.aip.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cart_product database table.
 * 
 */
@Entity
@Table(name="cart_product")
public class CartProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CartProductPK id;

	//bi-directional many-to-one association to Cart
    @ManyToOne (fetch=FetchType.EAGER)
	@JoinColumn(name="CartID", insertable=false, updatable=false)
	private Cart cart;

	//bi-directional many-to-one association to Product
    @ManyToOne (fetch=FetchType.EAGER)
	@JoinColumn(name="ProductID", insertable=false, updatable=false)
	private Product product;

    public CartProduct() {
    }

	public CartProduct(CartProductPK cartProductPK) {
		this.id = cartProductPK;
	}

	public CartProductPK getId() {
		return this.id;
	}

	public void setId(CartProductPK id) {
		this.id = id;
	}

	public Cart getCart() {
		return this.cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
package com.arunhoan.aip.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the cart_product database table.
 * 
 */
@Embeddable
public class CartProductPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int cartID;

	private int productID;
	
	private int quantity;

    public CartProductPK() {
    }
	public CartProductPK(int cartID, int productID, int quantity) {
		this.cartID = cartID;
		this.productID = productID;
		this.quantity = quantity;
	}
	public int getCartID() {
		return this.cartID;
	}
	public void setCartID(int cartID) {
		this.cartID = cartID;
	}
	public int getProductID() {
		return this.productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CartProductPK)) {
			return false;
		}
		CartProductPK castOther = (CartProductPK)other;
		return 
			(this.cartID == castOther.cartID)
			&& (this.productID == castOther.productID);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cartID;
		hash = hash * prime + this.productID;
		
		return hash;
    }
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
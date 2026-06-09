package kr.co.farmstory.dto.cart;

import kr.co.farmstory.dto.product.ProductDTO;

public class CartDTO extends ProductDTO {
	private int cartNo;
	private String userid;
	private int prodNo;
	private int count;
	private String regDate;
	private int stock;
	private String prodName;
    private int price;
    private int discount;
	
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getCartNo() {
		return cartNo;
	}
	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getProdNo() {
		return prodNo;
	}
	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	@Override
	public String toString() {
		return "CartDTO [cartNo=" + cartNo + ", userid=" + userid + ", prodNo=" + prodNo + ", count=" + count
				+ ", regDate=" + regDate + ", stock=" + stock + ", prodName=" + prodName + ", price=" + price
				+ ", discount=" + discount + "]";
	}
	
	
	
	
	
}

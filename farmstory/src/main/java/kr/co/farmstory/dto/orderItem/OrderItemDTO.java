package kr.co.farmstory.dto.orderItem;

public class OrderItemDTO {
	private int itemNo;
	private int orderNo;
	private int prodNo;
	private int count;
	private int price;
	
	// 관리자 주문목록을 위한 추가필드
	private int delivery;
	private int totalPrice;
	private String regDate;
	private String name;
	private String prodName;
	
	public int getDelivery() {
		return delivery;
	}
	public void setDelivery(int delivery) {
		this.delivery = delivery;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
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
	
	
	@Override
	public String toString() {
		return "OrdersDTO [itemNo=" + itemNo + ", orderNo=" + orderNo + ", prodNo=" + prodNo + ", count=" + count
				+ ", price=" + price + "]";
	}
}

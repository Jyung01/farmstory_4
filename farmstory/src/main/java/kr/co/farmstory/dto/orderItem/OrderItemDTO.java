package kr.co.farmstory.dto.orderItem;

public class OrderItemDTO {
	private int itemNo;
	private int orderNo;
	private int prodNo;
	private int count;
	private int prcie;
	
	
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
	public int getPrcie() {
		return prcie;
	}
	public void setPrcie(int prcie) {
		this.prcie = prcie;
	}
	
	@Override
	public String toString() {
		return "OrdersDTO [itemNo=" + itemNo + ", orderNo=" + orderNo + ", prodNo=" + prodNo + ", count=" + count
				+ ", prcie=" + prcie + "]";
	}
}

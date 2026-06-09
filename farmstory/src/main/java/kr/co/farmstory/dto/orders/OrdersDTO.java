package kr.co.farmstory.dto.orders;

public class OrdersDTO {
	private int orderNo;
	private String userid;
	private String orderer;
	private String hp;
	private String receiver;
	private String receiverHp;
	private String zip;
	private String addr1;
	private String addr2;
	private int usedPoint;
	private int savePoint;
	private int delivery;
	private int totalPrice;
	private String payment;
	private String memo;
	private String status;
	private String regDate;
	
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getOrderer() {
		return orderer;
	}
	public void setOrderer(String orderer) {
		this.orderer = orderer;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getReceiverHp() {
		return receiverHp;
	}
	public void setReceiverHp(String receiverHp) {
		this.receiverHp = receiverHp;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public int getUsedPoint() {
		return usedPoint;
	}
	public void setUsedPoint(int usedPoint) {
		this.usedPoint = usedPoint;
	}
	public int getSavePoint() {
		return savePoint;
	}
	public void setSavePoint(int savePoint) {
		this.savePoint = savePoint;
	}
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
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	@Override
	public String toString() {
		return "OrdersDTO [orderNo=" + orderNo + ", userid=" + userid + ", orderer=" + orderer + ", hp=" + hp
				+ ", receiver=" + receiver + ", receiverHp=" + receiverHp + ", zip=" + zip + ", addr1=" + addr1
				+ ", addr2=" + addr2 + ", usedPoint=" + usedPoint + ", savePoint=" + savePoint + ", delivery="
				+ delivery + ", totalPrice=" + totalPrice + ", payment=" + payment + ", memo=" + memo + ", status="
				+ status + ", regDate=" + regDate + "]";
	}
}

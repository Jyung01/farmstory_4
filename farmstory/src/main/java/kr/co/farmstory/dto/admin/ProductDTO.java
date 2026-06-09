package kr.co.farmstory.dto.admin;

public class ProductDTO {
	private int prodNo;
	private String cate;
	private String prodName;
	private int price;
	private int discount;
	private int point;
	private int delivery;
	private int stock;
	private String discript;
	private String thumb;
	private String info;
	private String detail;
	private String regDate;
	
	public int getProdNo() {
		return prodNo;
	}
	
	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}
	
	public String getCate() {
		return cate;
	}
	
	public void setCate(String cate) {
		this.cate = cate;
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
	
	public void setPrice(String price) {
		if(price!=null) {
			this.price = Integer.parseInt(price);
		}
	}
	
	public int getDiscount() {
		return discount;
	}
	
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public void setDiscount(String discount) {
		if(discount != null) {
			this.discount = Integer.parseInt(discount);
		}
	}
	
	public int getPoint() {
		return point;
	}
	
	public void setPoint(int point) {
		this.point = point;
	}
	public void setPoint(String point) {
		if(point != null) {
			this.price = Integer.parseInt(point);
		}
	}
	
	public int getDelivery() {
		return delivery;
	}
	
	public void setDelivery(int delivery) {
		this.delivery = delivery;
	}
	
	public void setDelivery(String delivery) {
		if(delivery != null) {
			this.delivery = Integer.parseInt(delivery);
		}
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	public void setStock(String stock) {
		if(stock != null) {
			this.stock = Integer.parseInt(stock);
		}
	}
	
	public String getDiscript() {
		return discript;
	}
	
	public void setDiscript(String discript) {
		this.discript = discript;
	}
	
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public String getRegDate() {
		return regDate;
	}
	
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "ProductDTO [prodNo=" + prodNo + ", cate=" + cate + ", prodName=" + prodName + ", price=" + price
				+ ", discount=" + discount + ", point=" + point + ", delivery=" + delivery + ", stock=" + stock
				+ ", discript=" + discript + ", thumb=" + thumb + ", regDate=" + regDate + "]";
	}
	
	
}

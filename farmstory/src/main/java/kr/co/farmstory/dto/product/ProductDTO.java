package kr.co.farmstory.dto.product;

public class ProductDTO {
	private int prodNo;
	private String cate;
	private String prodName;
	private int price;
	private int discount;
	private int point;
	private int delivery;
	private int stock;
	private String descript;
	private String thumb;
	private String infoImg;
	private String detailImg;
	private String regDate;
	private int salePrice; //할인가
	
	public int getSalePrice() {
		return this.price - (this.price * this.discount / 100);
	}
	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}
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
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
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
	public int getDelivery() {
		return delivery;
	}
	public void setDelivery(int delivery) {
		this.delivery = delivery;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getInfoImg() {
		return infoImg;
	}
	public void setInfoImg(String infoImg) {
		this.infoImg = infoImg;
	}
	public String getDetailImg() {
		return detailImg;
	}
	public void setDetailImg(String detailImg) {
		this.detailImg = detailImg;
	}
	
	@Override
	public String toString() {
		return "ProductDTO [prodNo=" + prodNo + ", cate=" + cate + ", prodName=" + prodName + ", price=" + price
				+ ", discount=" + discount + ", point=" + point + ", delivery=" + delivery + ", stock=" + stock
				+ ", descript=" + descript + ", thumb=" + thumb + ", infoImg=" + infoImg + ", detailImg=" + detailImg
				+ ", regDate=" + regDate + ", salePrice=" + salePrice + "]";
	}
	
	
	
	
	
	
}

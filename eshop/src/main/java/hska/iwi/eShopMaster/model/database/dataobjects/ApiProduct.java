package hska.iwi.eShopMaster.model.database.dataobjects;

public class ApiProduct {
	
	private String name;
	private String details;
	private int categoryId;
	private double price;
	
	public ApiProduct() {}
	
	
	public ApiProduct(String name, String details, int categoryId, double price) {
		super();
		this.name = name;
		this.details = details;
		this.categoryId = categoryId;
		this.price = price;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	

}

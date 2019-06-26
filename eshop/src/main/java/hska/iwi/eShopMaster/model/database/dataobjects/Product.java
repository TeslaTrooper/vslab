package hska.iwi.eShopMaster.model.database.dataobjects;

public class Product {

	private int product_id;

	private String name;

	private double price;

	private int categoryId;

	private String details;

	public Product() {
	}

	public Product(String name, double price, int categoryId) {
		this.name = name;
		this.price = price;
		this.categoryId = categoryId;
	}

	public Product(String name, double price, int categoryId, String details) {
		this.name = name;
		this.price = price;
		this.categoryId = categoryId;
		this.details = details;
	}

	public int getProductId() {
		return this.product_id;
	}

	public void setProductId(int id) {
		this.product_id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCategoryID() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}

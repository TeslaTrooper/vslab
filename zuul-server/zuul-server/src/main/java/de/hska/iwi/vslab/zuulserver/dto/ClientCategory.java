package de.hska.iwi.vslab.zuulserver.dto;

public class ClientCategory {

	
	private int id;
	private String name;
	private Product[] products;
	
	public ClientCategory() {}

	public ClientCategory(int id, String name, Product[] products) {
		super();
		this.id = id;
		this.name = name;
		this.products = products;
	}

	public Product[] getProducts() {
		return products;
	}

	public void setProducts(Product[] products) {
		this.products = products;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

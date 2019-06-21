package de.hska.iwi.vslab.categoryservice.dao;

@javax.persistence.Entity
public class Category {
	
	public Category() {
		
	}

	public Category(String name) {
		super();
		this.name = name;
	}

	@javax.persistence.Id
	@javax.persistence.GeneratedValue
	@javax.persistence.Column(name = "CATEGORY_ID")
	private Long id;
	@javax.persistence.Column(name = "CATEGORY_NAME")
	private String name;

	// getter und setter nicht aufgeführt
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

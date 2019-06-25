package de.hska.iwi.vslab.productservice.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.hska.iwi.vslab.productservice.dataobject.Product;

public interface ProductRepo extends CrudRepository<Product, Integer> {

	List<Product> findProductsByDetailsContainingIgnoreCaseAndPriceBetween(String searchDescription,
			double searchMinPrice, double searchMaxPrice);

}

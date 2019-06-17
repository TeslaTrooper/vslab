package de.hska.iwi.vslab.productservice.dao;

import org.springframework.data.repository.CrudRepository;

import de.hska.iwi.vslab.productservice.dataobject.Product;

public interface ProductRepo extends CrudRepository<Product, Long> {

}

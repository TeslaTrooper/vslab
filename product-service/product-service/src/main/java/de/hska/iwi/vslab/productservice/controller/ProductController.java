package de.hska.iwi.vslab.productservice.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.hska.iwi.vslab.productservice.dao.ProductRepo;
import de.hska.iwi.vslab.productservice.dataobject.JSONProduct;
import de.hska.iwi.vslab.productservice.dataobject.Product;

@RestController
@RequestMapping(value = "/products/")
public class ProductController {

	@Autowired
	private ProductRepo repo;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody JSONProduct product) throws Exception {
		// Create Product
		// Validate name:

		if (product.getName() == null || product.getName().length() == 0) {
			throw new IllegalArgumentException("Product must have a name!");
		}

		// Validate price:

		if (String.valueOf(product.getPrice()).length() > 0) {
			if (!String.valueOf(product.getPrice()).matches("[0-9]+(.[0-9][0-9]?)?") || product.getPrice() < 0.0) {
				throw new IllegalArgumentException("Price is not valid!");
			}
		} else {
			throw new IllegalArgumentException("Product must have price!");
		}

		Product p = new Product(product.getName(), product.getPrice(), product.getCategoryId(), product.getDetails());
		return new ResponseEntity<>(repo.save(p), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<Iterable<Product>> getProducts() {
		Iterable<Product> allProducts = repo.findAll();
		return new ResponseEntity<>(allProducts, HttpStatus.OK);

	}

	@GetMapping("{id}")
	public ResponseEntity<Product> getProduct(@PathVariable final int id) {
		Product product = repo.findById(id).orElseThrow(() -> new EntityNotFoundException());
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable final int id) {
		repo.deleteById(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@RequestMapping(value = "search", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getProductsForSearchValue(@RequestParam String searchDescription,
			@RequestParam double searchMinPrice, @RequestParam double searchMaxPrice) {
		try {
			searchDescription = URLDecoder.decode(searchDescription, "UTF-8");
			if (searchMaxPrice < 0) {
				searchMaxPrice = Double.MAX_VALUE;
			}
			List<Product> products = this.repo.findProductsByDetailsContainingIgnoreCaseAndPriceBetween(
					searchDescription, searchMinPrice, searchMaxPrice);
			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (UnsupportedEncodingException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}

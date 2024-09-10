package com.learningbatch.app.repo;

import java.util.List;

import com.learningbatch.app.model.Product;

public interface ProductRepo {
	void save(Product product);
	void saveAll(List<Product> products);
}

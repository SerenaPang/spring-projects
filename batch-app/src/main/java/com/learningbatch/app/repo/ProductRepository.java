package com.learningbatch.app.repo;

import java.util.List;

import com.learningbatch.app.model.Product;

public interface ProductRepository {
	void saveAll(List<Product> products);
}

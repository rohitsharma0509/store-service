package com.app.ecom.store.repository;

import java.util.List;

import com.app.ecom.store.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
	ProductCategory findByName(String name);

	void deleteByIdIn(List<Long> ids);
}

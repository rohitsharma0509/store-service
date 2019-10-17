package com.app.ecom.store.masterdata.service.impl;

import java.util.List;

import com.app.ecom.store.masterdata.dto.IdsDto;
import com.app.ecom.store.masterdata.dto.QueryRequest;
import com.app.ecom.store.masterdata.dto.productcategory.ProductCategoryDto;
import com.app.ecom.store.masterdata.dto.productcategory.ProductCategoryDtos;
import com.app.ecom.store.masterdata.dto.productcategory.ProductCategorySearchRequest;
import com.app.ecom.store.masterdata.handler.QueryHandler;
import com.app.ecom.store.masterdata.mapper.ProductCategoryMapper;
import com.app.ecom.store.masterdata.model.ProductCategory;
import com.app.ecom.store.masterdata.repository.ProductCategoryRepository;
import com.app.ecom.store.masterdata.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	private ProductCategoryRepository productCategoryRepository;

	@Autowired
	private ProductCategoryMapper productCategoryMapper;

	@Autowired
	private QueryHandler<ProductCategory> queryHandler;
	
	@Autowired
	private CacheManager cacheManager;

	@Override
	@Transactional
	public ProductCategoryDto addUpdateProductCategory(ProductCategoryDto productCategoryDto) {
		ProductCategory productCategory = productCategoryRepository.save(productCategoryMapper.productCategoryDtoToProductCategory(productCategoryDto));
		cacheManager.getCache("productCategoriesCache").clear();
		return productCategoryMapper.productCategoryToProductCategoryDto(productCategory);
	}

	@Override
	@Cacheable(value = "productCategoriesCache", key = "{ #productCategorySearchRequest.getId(), #productCategorySearchRequest.getName()}")
	public ProductCategoryDtos getProductCategories(ProductCategorySearchRequest productCategorySearchRequest) {
		queryHandler.setType(ProductCategory.class);
		QueryRequest queryRequest = new QueryRequest();
		queryRequest.setWhereClauses(productCategoryMapper.productCategorySearchRequestToWhereClauses(productCategorySearchRequest));
		queryRequest.setOrderByClauses(productCategorySearchRequest.getOrderByClauses());
		queryRequest.setOffset(productCategorySearchRequest.getOffset());
		queryRequest.setLimit(productCategorySearchRequest.getLimit());
		List<ProductCategory> productCategories = queryHandler.findByQueryRequest(queryRequest);
		return productCategoryMapper.productCategoriesToProductCategoryDtos(productCategories);
	}
	
	@Override
	public Long countProductCategories(ProductCategoryDto productCategoryDto) {
		queryHandler.setType(ProductCategory.class);
		QueryRequest queryRequest = new QueryRequest();
		queryRequest.setWhereClauses(productCategoryMapper.productCategoryDtoToWhereClauses(productCategoryDto));
		return queryHandler.countByQueryRequest(queryRequest, "id");
	}

	@Override
	@Transactional
	public void deleteCategories(IdsDto idsDto) {
		if (idsDto == null || CollectionUtils.isEmpty(idsDto.getIds())) {
			productCategoryRepository.deleteAll();
		} else if (idsDto.getIds().size() == 1) {
			productCategoryRepository.deleteById(idsDto.getIds().get(0));
		} else {
			productCategoryRepository.deleteByIdIn(idsDto.getIds());
		}
		cacheManager.getCache("productCategoriesCache").clear();
	}
}
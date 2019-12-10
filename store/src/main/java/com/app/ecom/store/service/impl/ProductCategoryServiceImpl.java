package com.app.ecom.store.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import com.app.ecom.store.client.MasterDataClient;
import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.masterdata.ProductCategoryDto;
import com.app.ecom.store.dto.masterdata.ProductCategoryDtos;
import com.app.ecom.store.dto.masterdata.ProductCategorySearchRequest;
import com.app.ecom.store.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
	
	@Autowired
	private MasterDataClient masterDataClient;
	
	@Override
	public List<ProductCategoryDto> getAllProductCategories() {
		ProductCategoryDtos productCategoryDtos = masterDataClient.getProductCategories(new ProductCategorySearchRequest());
		if(null != productCategoryDtos && !CollectionUtils.isEmpty(productCategoryDtos.getProductCategories())) {
			return productCategoryDtos.getProductCategories();
		} else {
			return Collections.emptyList();
		}
	}
	
	@Override
	public ProductCategoryDto getProductCategoryByIdOrName(Long id, String name) {
		ProductCategorySearchRequest productCategorySearchRequest = new ProductCategorySearchRequest();
		productCategorySearchRequest.setId(id);
		productCategorySearchRequest.setName(name);
		ProductCategoryDtos productCategoryDtos = masterDataClient.getProductCategories(productCategorySearchRequest);
		if(null != productCategoryDtos && !CollectionUtils.isEmpty(productCategoryDtos.getProductCategories())) {
			Optional<ProductCategoryDto> optional = productCategoryDtos.getProductCategories().stream().filter(Objects::nonNull).findFirst();
			return optional.isPresent() ? optional.get() : null;
		} else {
			return null;
		}
	}

	@Override
	public ProductCategoryDto addCategory(ProductCategoryDto productCategoryDto) {
		return masterDataClient.addUpdateProductCategory(productCategoryDto);
	}

	@Override
	public CustomPage<ProductCategoryDto> getCategories(Pageable pageable, Map<String, String> params) {	
		int offset = (pageable.getPageNumber() - 1)*pageable.getPageSize();
		int limit = offset + pageable.getPageSize();
		
		ProductCategorySearchRequest productCategorySearchRequest = new ProductCategorySearchRequest();
		productCategorySearchRequest.setOffset(offset);
		productCategorySearchRequest.setLimit(limit);	
		productCategorySearchRequest.setName(params.get(FieldNames.NAME));
		ProductCategoryDtos productCategoryDtos = masterDataClient.getProductCategories(productCategorySearchRequest);
		
		ProductCategoryDto productCategoryDto = new ProductCategoryDto();
		productCategoryDto.setName(params.get(FieldNames.NAME));
		Long totalRecords = masterDataClient.countProductCategories(productCategoryDto);
		CustomPage<ProductCategoryDto> page = new CustomPage<>();
		page.setContent(productCategoryDtos == null ? Collections.emptyList() : productCategoryDtos.getProductCategories());
		page.setPageNumber(pageable.getPageNumber() - 1);
		page.setSize(pageable.getPageSize());
		page.setTotalPages(totalRecords == null ? 0 : (int)Math.ceil((double)totalRecords/pageable.getPageSize()));
		return page;
	}

	@Override
	public void deleteCategories(IdsDto idsDto) {
		masterDataClient.deleteProductCategories(idsDto);
	}
}
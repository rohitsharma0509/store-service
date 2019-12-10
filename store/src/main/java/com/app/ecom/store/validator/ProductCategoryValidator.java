package com.app.ecom.store.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.dto.Response;
import com.app.ecom.store.dto.masterdata.ProductCategoryDto;
import com.app.ecom.store.enums.ErrorCode;
import com.app.ecom.store.service.ProductCategoryService;
import com.app.ecom.store.service.ProductService;
import com.app.ecom.store.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductCategoryValidator implements Validator {
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Override
	public boolean supports(Class<?> aClass) {
		return ProductCategoryDto.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		ProductCategoryDto productCategoryDto = (ProductCategoryDto) o;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, FieldNames.NAME, commonUtil.getMessage(ErrorCode.ERR000003.getCode()));
		
		
		List<ProductCategoryDto> productCategoryDtos = productCategoryService.getAllProductCategories();
		
		for(ProductCategoryDto productCategory : productCategoryDtos) {
			if(!productCategory.getName().equalsIgnoreCase(productCategoryDto.getOldName()) && productCategory.getName().equalsIgnoreCase(productCategoryDto.getName())) {
				errors.rejectValue(FieldNames.NAME, commonUtil.getMessage(ErrorCode.ERR000016.getCode()));
			}
		}
	}
	
	public Response validateCategoryAssociation(List<Long> categoryIds) {
		List<ProductCategoryDto> listOfProductCategoryDto = new ArrayList<>();
		if(CollectionUtils.isEmpty(categoryIds)) {
			listOfProductCategoryDto = productCategoryService.getAllProductCategories();
			categoryIds = listOfProductCategoryDto.stream().map(ProductCategoryDto::getId).collect(Collectors.toList());
		}
		
		String errorCode = listOfProductCategoryDto.size() <= 1 ? ErrorCode.ERR000017.getCode() : ErrorCode.ERR000018.getCode();
		Long productCount = productService.countByCategoryIdIn(categoryIds);
		return commonUtil.getResponse(productCount > 0, errorCode);
	}
}

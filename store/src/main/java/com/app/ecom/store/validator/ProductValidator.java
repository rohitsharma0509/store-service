package com.app.ecom.store.validator;

import java.util.List;
import java.util.stream.Collectors;

import com.app.ecom.store.dto.Response;
import com.app.ecom.store.dto.productservice.ProductDto;
import com.app.ecom.store.enums.ErrorCode;
import com.app.ecom.store.service.OrderService;
import com.app.ecom.store.service.ProductService;
import com.app.ecom.store.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductValidator implements Validator {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CommonUtil commonUtil;

	@Override
	public boolean supports(Class<?> aClass) {
		return ProductDto.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		ProductDto productDto = (ProductDto) o;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "brandName", commonUtil.getMessage(ErrorCode.ERR000003.getCode()));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", commonUtil.getMessage(ErrorCode.ERR000003.getCode()));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "perProductPrice", commonUtil.getMessage(ErrorCode.ERR000003.getCode()));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "purchasePrice", commonUtil.getMessage(ErrorCode.ERR000003.getCode()));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", commonUtil.getMessage(ErrorCode.ERR000003.getCode()));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "alertQuantity", commonUtil.getMessage(ErrorCode.ERR000003.getCode()));

		if (!commonUtil.isValid(productDto.getCategoryId() + "")) {
			errors.rejectValue("categoryId", commonUtil.getMessage(ErrorCode.ERR000009.getCode()));
		}

		if (!commonUtil.isDouble(productDto.getPerProductPrice() + "")) {
			errors.rejectValue("perProductPrice", commonUtil.getMessage(ErrorCode.ERR000010.getCode()));
		}

		if (!commonUtil.isDouble(productDto.getPurchasePrice() + "")) {
			errors.rejectValue("purchasePrice", commonUtil.getMessage(ErrorCode.ERR000011.getCode()));
		}

		if (!commonUtil.isInteger(productDto.getQuantity() + "")) {
			errors.rejectValue("quantity", commonUtil.getMessage(ErrorCode.ERR000012.getCode()));
		}

		if (!commonUtil.isInteger(productDto.getAlertQuantity() + "")) {
			errors.rejectValue("alertQuantity", commonUtil.getMessage(ErrorCode.ERR000013.getCode()));
		}
	}

	public Response validateProductAssociation(List<Long> productIds) {
		List<ProductDto> productDtos;
		if(CollectionUtils.isEmpty(productIds)) {
			productDtos = productService.getAllProducts();
			productIds = productDtos.stream().map(ProductDto::getId).collect(Collectors.toList());
		}
		
		String errorCode = productIds.size() <= 1 ? ErrorCode.ERR000019.getCode() : ErrorCode.ERR000020.getCode();
		Long orderCount = orderService.countOrderByProductIdIn(productIds);
		return commonUtil.getResponse(orderCount > 0, errorCode);
	}

}

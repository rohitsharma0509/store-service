package com.app.ecom.store.productservice.resource;

import com.app.ecom.store.productservice.constants.Endpoint;
import com.app.ecom.store.productservice.dto.IdsDto;
import com.app.ecom.store.productservice.dto.ProductDto;
import com.app.ecom.store.productservice.dto.ProductDtos;
import com.app.ecom.store.productservice.dto.ProductSearchRequest;
import com.app.ecom.store.productservice.enums.FileType;
import com.app.ecom.store.productservice.enums.QuantityStatus;
import com.app.ecom.store.productservice.service.ProductService;
import com.app.ecom.store.productservice.util.CommonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ProductResource {
	
	private static final Logger logger = LogManager.getLogger(ProductResource.class);
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@PutMapping(value = Endpoint.PRODUCT)
	public ResponseEntity<ProductDto> createUpdateProduct(@RequestBody ProductDto productDto) {
		try {
			ProductDto createdProductDto = productService.createUpdateProduct(productDto);
			return new ResponseEntity<>(createdProductDto, productDto.getId() == null ? HttpStatus.CREATED : HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while add/upadte product : ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value = Endpoint.IMPORT_PRODUCT)
	public ResponseEntity<Void> importProducts(@RequestParam("file") MultipartFile multiPartFile, @RequestParam(required= true) FileType fileType) {
		try {
			productService.importProducts(multiPartFile, fileType);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while importing products : ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = Endpoint.PRODUCT)
	public ResponseEntity<ProductDtos> getProducts(@RequestBody ProductSearchRequest productSearchRequest) {
		try {
			ProductDtos productDtos = productService.getProducts(productSearchRequest);
			return new ResponseEntity<>(productDtos, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while getting products : ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = Endpoint.COUNT_PRODUCT)
	public ResponseEntity<Long> countProducts(@RequestBody ProductSearchRequest productSearchRequest) {
		try {
			Long noOfProducts = productService.countProducts(productSearchRequest);
			return new ResponseEntity<>(noOfProducts, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while counting products : ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = Endpoint.PRODUCT)
	public ResponseEntity<Integer> getProductQuantity(@RequestParam(name="productId", required=true) Long productId, @RequestParam("status") QuantityStatus status) {
		try {
			Integer quantity = productService.getProductQuantity(productId, status);
			return new ResponseEntity<>(quantity, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while getting product quntity : ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = Endpoint.PRODUCT)
	public ResponseEntity<Void> deleteProducts(@RequestBody IdsDto idsDto) {
		try {
			productService.deleteProducts(idsDto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while deleting products : ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
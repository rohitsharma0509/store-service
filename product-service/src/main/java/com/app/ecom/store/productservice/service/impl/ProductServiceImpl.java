package com.app.ecom.store.productservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Tuple;

import com.app.ecom.store.productservice.dto.IdsDto;
import com.app.ecom.store.productservice.dto.ProductDto;
import com.app.ecom.store.productservice.dto.ProductDtos;
import com.app.ecom.store.productservice.dto.ProductSearchRequest;
import com.app.ecom.store.productservice.dto.QueryRequest;
import com.app.ecom.store.productservice.dto.StockDto;
import com.app.ecom.store.productservice.dto.StockDtos;
import com.app.ecom.store.productservice.enums.ProductStatus;
import com.app.ecom.store.productservice.enums.QuantityStatus;
import com.app.ecom.store.productservice.handler.QueryHandler;
import com.app.ecom.store.productservice.mapper.ProductMapper;
import com.app.ecom.store.productservice.model.Product;
import com.app.ecom.store.productservice.repository.ProductRepository;
import com.app.ecom.store.productservice.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Service
public class ProductServiceImpl implements ProductService {
	
	private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private QueryHandler<Product> queryHandler;

	@Override
	public ProductDto createUpdateProduct(ProductDto productDto) {
		return productMapper.productToProductDto(productRepository.save(productMapper.productDtoToProduct(productDto)));
	}

	@Override
	public ProductDtos getProducts(ProductSearchRequest productSearchRequest) {
		queryHandler.setType(Product.class);
		QueryRequest queryRequest = new QueryRequest();
		queryRequest.setWhereClauses(productMapper.productSearchRequestToWhereClauses(productSearchRequest));
		queryRequest.setOrderByClauses(productSearchRequest.getOrderByClauses());
		queryRequest.setOffset(productSearchRequest.getOffset());
		queryRequest.setLimit(productSearchRequest.getLimit());
		List<Product> products = queryHandler.findByQueryRequest(queryRequest);
		return productMapper.productsToProductDtos(products);
	}

	@Override
	public Long countProducts(ProductSearchRequest productSearchRequest) {
		if(ProductStatus.OUT_OF_STOCK == productSearchRequest.getStatus()) {
			Integer noOfProducts = productRepository.countOutOfStockProducts();
			return noOfProducts == null ? 0 : noOfProducts.longValue();
		} else if(ProductStatus.ALERT == productSearchRequest.getStatus()) {
			Integer noOfProducts = productRepository.countAlertProducts();
			return noOfProducts == null ? 0 : noOfProducts.longValue();
		} else {
			queryHandler.setType(Product.class);
			QueryRequest queryRequest = new QueryRequest();
			queryRequest.setWhereClauses(productMapper.productSearchRequestToWhereClauses(productSearchRequest));
			return queryHandler.countByQueryRequest(queryRequest, "id");
		}
	}

	@Override
	public void deleteProducts(IdsDto idsDto) {
		if (idsDto == null || CollectionUtils.isEmpty(idsDto.getIds())) {
			productRepository.deleteAll();
		} else if (idsDto.getIds().size() == 1) {
			productRepository.deleteById(idsDto.getIds().get(0));
		} else {
			productRepository.deleteByIdIn(idsDto.getIds());
		}
	}

	@Override
	public Integer getProductQuantity(Long productId, QuantityStatus status) {
		if(QuantityStatus.AVAILED == status) {
			return productRepository.getAvailableProductQuantity(productId);
		} else if(QuantityStatus.ORDERED == status) {
		
		}
		return null;
	}
	
	@Override
	public StockDtos getStockDetails(ProductSearchRequest productSearchRequest) {
		StringBuilder query = new StringBuilder("select c.category_name, p.brand_name, p.product_id, p.product_code, p.product_name, p.quantity as totalQty, p.quantity-ifnull(sum(od.quantity),0) as availableQty, ifnull(sum(od.quantity),0) as orderedQty from products p left join product_category c on p.category_id=c.category_id left join order_details od on p.product_id=od.product_id where 1=1 ");
		
		if(!CollectionUtils.isEmpty(productSearchRequest.getCategoryIds())) {
			if(productSearchRequest.getCategoryIds().size() > 1) {
				query.append(" and c.category_id in (:categoryId)");
			} else {
				query.append(" and c.category_id=:categoryId");
			}
		}
		if(!StringUtils.isEmpty(productSearchRequest.getBrandName())){
			query.append(" and p.brand_name like :brandName");
		}
		if(!StringUtils.isEmpty(productSearchRequest.getProductName())) {
			query.append(" and p.product_name like :name");
		}
		
		query.append(" group by p.product_id");
		if(!productSearchRequest.getIsExcel()){
			query.append(" limit ").append(productSearchRequest.getOffset()).append(", ").append(productSearchRequest.getLimit());
		}
		logger.debug("Retrieving Stock details: Query: "+query);
		
		StockDtos stockDtos = new StockDtos();
		List<Tuple> tuples = queryHandler.getTupleByQuery(query.toString(), productMapper.productSearchRequestToWhereClauses(productSearchRequest));
		
		List<StockDto> listOfStockDto = new ArrayList<>();
		for(Tuple tuple : tuples){
			StockDto stockDto = new StockDto();
			stockDto.setId(Long.parseLong(String.valueOf(tuple.get("product_id"))));
			stockDto.setCode(String.valueOf(tuple.get("product_code")));
			stockDto.setName(String.valueOf(tuple.get("product_name")));
			stockDto.setCategoryName(String.valueOf(tuple.get("category_name")));
			stockDto.setBrandName(String.valueOf(tuple.get("brand_name")));
			stockDto.setTotalQty(Integer.parseInt(String.valueOf(tuple.get("totalQty"))));
			stockDto.setOrderedQty(Integer.parseInt(String.valueOf(tuple.get("orderedQty"))));
			stockDto.setAvailableQty(Integer.parseInt(String.valueOf(tuple.get("availableQty"))));
			listOfStockDto.add(stockDto);
		}
		stockDtos.setStocks(listOfStockDto);
		return stockDtos;
	}

	@Override
	public Integer countStockDetails(ProductSearchRequest productSearchRequest) {
		StringBuilder countQuery = new StringBuilder("select count(p.product_id) from products p left join product_category c on p.category_id=c.category_id where 1=1");
		
		if(!CollectionUtils.isEmpty(productSearchRequest.getCategoryIds())) {
			if(productSearchRequest.getCategoryIds().size() > 1) {
				countQuery.append(" and c.category_id in (:categoryId)");	
			} else {
				countQuery.append(" and c.category_id=:categoryId");
			}
		}
		if(!StringUtils.isEmpty(productSearchRequest.getBrandName())){
			countQuery.append(" and p.brand_name like :brandName");
		}
		if(!StringUtils.isEmpty(productSearchRequest.getProductName())) {
			countQuery.append(" and p.product_name like :name");
		}
		logger.debug("Retrieving Stock details: Count Query: "+countQuery);
		return queryHandler.countByQuery(countQuery.toString(), productMapper.productSearchRequestToWhereClauses(productSearchRequest));
	}
}

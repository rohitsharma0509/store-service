package com.app.ecom.store.support.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.app.ecom.store.support.dto.OrderByClause;
import com.app.ecom.store.support.dto.QueryRequest;
import com.app.ecom.store.support.dto.WhereClause;
import com.app.ecom.store.support.enums.OperationType;
import com.app.ecom.store.support.enums.SortOrder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class QueryHandler<T> {
	
	private static final String PERCENT = "%";
	private Class<T> type;

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<T> findByQueryRequest(QueryRequest queryRequest) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
        Root<T> root = criteriaQuery.from(type);
        
        if(!CollectionUtils.isEmpty(queryRequest.getWhereClauses())) {
        	Predicate[] predicates = getPredicates(criteriaBuilder, root, queryRequest.getWhereClauses());  
        	criteriaQuery = criteriaQuery.where(criteriaBuilder.and(predicates));
        }
        if(!CollectionUtils.isEmpty(queryRequest.getOrderByClauses())) {
        	List<Order> orderList = getSortOrders(criteriaBuilder, root, queryRequest.getOrderByClauses());
        	criteriaQuery = criteriaQuery.orderBy(orderList);
        }
        if (null != queryRequest.getOffset() && null != queryRequest.getLimit()) {
        	return entityManager.createQuery(criteriaQuery).setFirstResult(queryRequest.getOffset()).setMaxResults(queryRequest.getLimit()).getResultList();
        } else {
        	return entityManager.createQuery(criteriaQuery).getResultList();
        }
	}
	
	public Long countByQueryRequest(QueryRequest queryRequest, String idField) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<T> root = criteriaQuery.from(type);
        
        criteriaQuery = criteriaQuery.select(criteriaBuilder.count(root.get(idField)));
        
        if(!CollectionUtils.isEmpty(queryRequest.getWhereClauses())) {
        	Predicate[] predicates = getPredicates(criteriaBuilder, root, queryRequest.getWhereClauses());  
        	criteriaQuery = criteriaQuery.where(criteriaBuilder.and(predicates));
        }
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
	
	public List<Tuple> getTupleByQuery(String queryStr, List<WhereClause> whereClauses) {
		Query query = entityManager.createNativeQuery(queryStr, Tuple.class);
		
		if(!CollectionUtils.isEmpty(whereClauses)){
			for (WhereClause whereClause : whereClauses){
				if(OperationType.EQUALS == whereClause.getOperation()) {
					query.setParameter(whereClause.getKey(), whereClause.getValue());
				} else if(OperationType.LIKE == whereClause.getOperation()) {
					query.setParameter(whereClause.getKey(),PERCENT+whereClause.getValue()+PERCENT);
				} else if(OperationType.IN == whereClause.getOperation()) {
					query.setParameter(whereClause.getKey(), whereClause.getValue());
				}
			}
		}
		
		return query.getResultList();
	}
	
	private Predicate[] getPredicates(CriteriaBuilder criteriaBuilder, Root<T> root, List<WhereClause> whereClauses) {
		Predicate[] predicates = new Predicate[whereClauses.size()];
		for(int i=0; i< whereClauses.size(); i++) {
			predicates[i] = toPredicate(whereClauses.get(i), criteriaBuilder, root);
		}
        return predicates;
	}
	
	private Predicate toPredicate(WhereClause whereClause, CriteriaBuilder criteriaBuilder, Root<T> root) {
		if (OperationType.EQUALS == whereClause.getOperation()) {
			return criteriaBuilder.equal(root.get(whereClause.getKey()), whereClause.getValue());
		} else if (OperationType.NOT_EQUAL == whereClause.getOperation()) {
			return criteriaBuilder.notEqual(root.get(whereClause.getKey()), whereClause.getValue());
		} else if (OperationType.IN == whereClause.getOperation()) {
			return criteriaBuilder.in(root.get(whereClause.getKey())).value(whereClause.getValue());
		} else if (OperationType.NOT_IN == whereClause.getOperation()) {
			return criteriaBuilder.not(root.get(whereClause.getKey()).in(whereClause.getValue()));
		} else if (OperationType.NULL == whereClause.getOperation()) {
			return criteriaBuilder.isNull(root.get(whereClause.getKey()));
		} if (OperationType.NOT_NULL == whereClause.getOperation()) {
			return criteriaBuilder.isNotNull(root.get(whereClause.getKey()));
		} else if (OperationType.GREATER == whereClause.getOperation()) {
			return criteriaBuilder.greaterThan(root.get(whereClause.getKey()), (Integer) whereClause.getValue());
		} else if (OperationType.GREATER_OR_EQUAL == whereClause.getOperation()) {
			return criteriaBuilder.greaterThanOrEqualTo(root.get(whereClause.getKey()), (Integer) whereClause.getValue());
		} else if (OperationType.LESS == whereClause.getOperation()) {
			return criteriaBuilder.lessThan(root.get(whereClause.getKey()), (Integer) whereClause.getValue());
		} else if (OperationType.LESS_OR_EQUAL == whereClause.getOperation()) {
			return criteriaBuilder.lessThanOrEqualTo(root.get(whereClause.getKey()), (Integer) whereClause.getValue());
		} else if (OperationType.LIKE == whereClause.getOperation()) {
			return criteriaBuilder.like(root.<String>get(whereClause.getKey()), PERCENT + whereClause.getValue() + PERCENT);
		} else {
			return null;
		}
	}

	private List<Order> getSortOrders(CriteriaBuilder criteriaBuilder, Root<T> root, List<OrderByClause> orderByClauses) {
		List<Order> orders = new ArrayList<>();
		orderByClauses.stream().filter(Objects::nonNull).forEach(orderByClause -> {
			if (SortOrder.DESC == orderByClause.getSortOrder()) {
				orders.add(criteriaBuilder.desc(root.get(orderByClause.getSortBy())));
            } else {
            	orders.add(criteriaBuilder.asc(root.get(orderByClause.getSortBy())));
            }
		});
		return orders;
 	}
	
	public void setType(Class<T> type) {
		this.type = type;
	}
}

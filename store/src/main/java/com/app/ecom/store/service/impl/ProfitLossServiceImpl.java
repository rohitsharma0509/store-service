package com.app.ecom.store.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Tuple;

import com.app.ecom.store.constants.Constants;
import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.ProfitLossDto;
import com.app.ecom.store.querybuilder.QueryBuilder;
import com.app.ecom.store.service.ProfitLossService;
import com.app.ecom.store.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProfitLossServiceImpl implements ProfitLossService{
	
	@Autowired
	private QueryBuilder queryBuilder;
	
	@Autowired
	private CommonUtil commonUtil;
	
	public CustomPage<ProfitLossDto> searchDailyProfitLoss(Pageable pageable) {
		int offset = (pageable.getPageNumber() - 1)*pageable.getPageSize();
		int limit = pageable.getPageSize();
		StringBuilder query = new StringBuilder("select date(o.created_ts) orderDate, (select count(*) from orders where date(created_ts)=date(o.created_ts)) totalOrders, sum(od.quantity) totalSoldQuantity, sum(od.quantity*p.per_product_price) totalAmountReceived, sum(od.quantity*p.per_product_price-od.quantity*p.purchase_price) totalProfitOrLoss from orders o left join order_details od on o.order_id=od.order_id left join products p on od.product_id=p.product_id group by date(o.created_ts) order by date(o.created_ts) desc");
		StringBuilder countQuery = new StringBuilder("select count(*) count from (select count(*) from orders o group by date(o.created_ts)) as temp");
		query.append(" limit "+offset+", "+limit);
		List<Tuple> tuples = queryBuilder.getTupleByQuery(query.toString(), null);
		
		List<ProfitLossDto> profitLossDtos = new ArrayList<>();
		for(Tuple tuple : tuples){
			ProfitLossDto profitLossDto = new ProfitLossDto();
			profitLossDto.setOrderDate(commonUtil.formatDate(String.valueOf(tuple.get(FieldNames.ORDER_DATE)), Constants.YYYY_MM_DD, Constants.DD_MM_YYYY));
			profitLossDto.setNoOfOrders(Integer.parseInt(String.valueOf(tuple.get(FieldNames.TOTAL_ORDERS))));
			profitLossDto.setSoldQuantity(Integer.parseInt(String.valueOf(tuple.get(FieldNames.TOTAL_SOLD_QUANTITY))));
			profitLossDto.setAmountReceived(Double.parseDouble(String.valueOf(tuple.get(FieldNames.TOTAL_AMOUNT_RECEIVED))));
			profitLossDto.setProfitOrLoss(Double.parseDouble(String.valueOf(tuple.get(FieldNames.TOTAL_PROFIT_OR_LOSS))));
			profitLossDtos.add(profitLossDto);
		}
		
		Integer totalRecords = queryBuilder.countByQuery(countQuery.toString(), null);
		CustomPage<ProfitLossDto> page = new CustomPage<>();
		page.setContent(profitLossDtos);
		page.setPageNumber(pageable.getPageNumber() - 1);
		page.setSize(pageable.getPageSize());
		page.setTotalPages((int)Math.ceil((double)totalRecords/pageable.getPageSize()));
		return page;
	}
	
	public CustomPage<ProfitLossDto> searchMonthlyProfitLoss(Pageable pageable, Integer month, Integer year) {
		int offset = (pageable.getPageNumber() - 1)*pageable.getPageSize();
		int limit = pageable.getPageSize();
		StringBuilder dailyQuery = new StringBuilder("select date(o.created_ts) orderDate, (select count(*) from orders where date(created_ts)=date(o.created_ts)) noOfOrders, sum(od.quantity) soldQuantity, sum(od.quantity*p.per_product_price) amountReceived, sum(od.quantity*p.per_product_price-od.quantity*p.purchase_price) profitOrLoss from orders o left join order_details od on o.order_id=od.order_id left join products p on od.product_id=p.product_id where 1=1 ");
		dailyQuery.append(" group by date(o.created_ts)");
		StringBuilder monthlyQuery = new StringBuilder("select concat(lpad(month(orderDate),2,0),'-',year(orderDate)) month, sum(noOfOrders) totalOrders, sum(soldQuantity) totalSoldQuantity, sum(amountReceived) totalAmountReceived, sum(profitOrLoss) totalProfitOrLoss from (");
		StringBuilder countQuery = new StringBuilder("select count(*) from ( select orderDate from ("+dailyQuery+") as temp where 1=1 ");
		monthlyQuery.append(dailyQuery);
		monthlyQuery.append(") as temp where 1=1 ");
		
		if(month != null && month != -1){
			monthlyQuery.append(" and month(orderDate)="+month);
			countQuery.append(" and month(orderDate)="+month);
		}
		if(null != year && year != -1){
			monthlyQuery.append(" and year(orderDate)="+year);
			countQuery.append(" and year(orderDate)="+year);
		}
		
		monthlyQuery.append(" group by month(orderDate), year(orderDate)");
		monthlyQuery.append(" order by orderDate desc");
		countQuery.append(" group by month(orderDate), year(orderDate)) as A");
		monthlyQuery.append(" limit "+offset+", "+limit);
		
		List<Tuple> tuples = queryBuilder.getTupleByQuery(monthlyQuery.toString(), null);
		
		List<ProfitLossDto> profitLossDtos = new ArrayList<>();
		for(Tuple tuple : tuples){
			ProfitLossDto profitLossDto = new ProfitLossDto();
			profitLossDto.setOrderDate(String.valueOf(tuple.get(FieldNames.MONTH)));
			profitLossDto.setNoOfOrders(Integer.parseInt(String.valueOf(tuple.get(FieldNames.TOTAL_ORDERS))));
			profitLossDto.setSoldQuantity(Integer.parseInt(String.valueOf(tuple.get(FieldNames.TOTAL_SOLD_QUANTITY))));
			profitLossDto.setAmountReceived(Double.parseDouble(String.valueOf(tuple.get(FieldNames.TOTAL_AMOUNT_RECEIVED))));
			profitLossDto.setProfitOrLoss(Double.parseDouble(String.valueOf(tuple.get(FieldNames.TOTAL_PROFIT_OR_LOSS))));
			profitLossDtos.add(profitLossDto);
		}

		Integer totalRecords = queryBuilder.countByQuery(countQuery.toString(), null);
		CustomPage<ProfitLossDto> page = new CustomPage<>();
		page.setContent(profitLossDtos);
		
		page.setPageNumber(pageable.getPageNumber() - 1);
		page.setSize(pageable.getPageSize());
		page.setTotalPages((int)Math.ceil((double)totalRecords/pageable.getPageSize()));
		return page;		
	}
	
	public CustomPage<ProfitLossDto> searchQuarterlyProfitLoss(Pageable pageable, Integer quarter, Integer year) {
		int offset = (pageable.getPageNumber() - 1)*pageable.getPageSize();
		int limit = pageable.getPageSize();
		StringBuilder dailyQuery = new StringBuilder("select date(o.created_ts) orderDate, (select count(*) from orders where date(created_ts)=date(o.created_ts)) noOfOrders, sum(od.quantity) soldQuantity, sum(od.quantity*p.per_product_price) amountReceived, sum(od.quantity*p.per_product_price-od.quantity*p.purchase_price) profitOrLoss from orders o left join order_details od on o.order_id=od.order_id left join products p on od.product_id=p.product_id where 1=1 ");
		dailyQuery.append(" group by date(o.created_ts)");
		StringBuilder quarterlyQuery = new StringBuilder("select year(orderDate) year, quarter(orderDate) quarter, sum(noOfOrders) totalOrders, sum(soldQuantity) totalSoldQuantity, sum(amountReceived) totalAmountReceived, sum(profitOrLoss) totalProfitOrLoss from (");
		StringBuilder countQuery = new StringBuilder("select count(year) from (select year(orderDate) year from ("+dailyQuery+") as temp where 1=1");
		
		quarterlyQuery.append(dailyQuery);
		quarterlyQuery.append(") as temp where 1=1");
		if(quarter != null && quarter != -1){
			quarterlyQuery.append(" and quarter(orderDate)="+quarter);
			countQuery.append(" and quarter(orderDate)="+quarter);
		}
		if(null != year && year != -1){
			quarterlyQuery.append(" and year(orderDate)="+year);
			countQuery.append(" and year(orderDate)="+year);
		}
		quarterlyQuery.append(" group by year(orderDate), quarter(orderDate)");
		countQuery.append(" group by year(orderDate), quarter(orderDate)) as A");
		quarterlyQuery.append(" limit "+offset+", "+limit);
		
		List<Tuple> tuples = queryBuilder.getTupleByQuery(quarterlyQuery.toString(), null);
		
		List<ProfitLossDto> profitLossDtos = new ArrayList<>();
		for(Tuple tuple : tuples){
			ProfitLossDto profitLossDto = new ProfitLossDto();
			profitLossDto.setOrderDate(tuple.get(FieldNames.QUARTER) +" Qaurter of "+ tuple.get(FieldNames.YEAR));
			profitLossDto.setNoOfOrders(Integer.parseInt(String.valueOf(tuple.get(FieldNames.TOTAL_ORDERS))));
			profitLossDto.setSoldQuantity(Integer.parseInt(String.valueOf(tuple.get(FieldNames.TOTAL_SOLD_QUANTITY))));
			profitLossDto.setAmountReceived(Double.parseDouble(String.valueOf(tuple.get(FieldNames.TOTAL_AMOUNT_RECEIVED))));
			profitLossDto.setProfitOrLoss(Double.parseDouble(String.valueOf(tuple.get(FieldNames.TOTAL_PROFIT_OR_LOSS))));
			profitLossDtos.add(profitLossDto);
		}

		Integer totalRecords = queryBuilder.countByQuery(countQuery.toString(), null);
		CustomPage<ProfitLossDto> page = new CustomPage<>();
		page.setContent(profitLossDtos);
		page.setPageNumber(pageable.getPageNumber() - 1);
		page.setSize(pageable.getPageSize());
		page.setTotalPages((int)Math.ceil((double)totalRecords/pageable.getPageSize()));
		return page;
	}	
	
	public CustomPage<ProfitLossDto> searchYearlyProfitLoss(Pageable pageable, Integer year) {
		int offset = (pageable.getPageNumber() - 1)*pageable.getPageSize();
		int limit = pageable.getPageSize();
		StringBuilder dailyQuery = new StringBuilder("select date(o.created_ts) orderDate, (select count(*) from orders where date(created_ts)=date(o.created_ts)) noOfOrders, sum(od.quantity) soldQuantity, sum(od.quantity*p.per_product_price) amountReceived, sum(od.quantity*p.per_product_price-od.quantity*p.purchase_price) profitOrLoss from orders o left join order_details od on o.order_id=od.order_id left join products p on od.product_id=p.product_id where 1=1 ");
		dailyQuery.append(" group by date(o.created_ts)");
		StringBuilder yearlyQuery = new StringBuilder("select year(orderDate) year, sum(noOfOrders) totalOrders, sum(soldQuantity) totalSoldQuantity, sum(amountReceived) totalAmountReceived, sum(profitOrLoss) totalProfitOrLoss from (");
		StringBuilder countQuery = new StringBuilder("select count(year) from (select year(orderDate) year from ("+dailyQuery+") as temp where 1=1");
		yearlyQuery.append(dailyQuery);
		yearlyQuery.append(") as temp where 1=1");
		if(null != year && year != -1){
			yearlyQuery.append(" and year(orderDate)="+year);
			countQuery.append(" and year(orderDate)="+year);
		}
		yearlyQuery.append(" group by year(orderDate) order by orderDate desc");
		countQuery.append(" group by year(orderDate)) as A");
		yearlyQuery.append(" limit "+offset+", "+limit);
		
		List<Tuple> tuples = queryBuilder.getTupleByQuery(yearlyQuery.toString(), null);
		
		List<ProfitLossDto> profitLossDtos = new ArrayList<>();
		for(Tuple tuple : tuples){
			ProfitLossDto profitLossDto = new ProfitLossDto();
			profitLossDto.setOrderDate(String.valueOf(tuple.get(FieldNames.YEAR)));
			profitLossDto.setNoOfOrders(Integer.parseInt(String.valueOf(tuple.get(FieldNames.TOTAL_ORDERS))));
			profitLossDto.setAmountReceived(Double.parseDouble(String.valueOf(tuple.get(FieldNames.TOTAL_AMOUNT_RECEIVED))));
			profitLossDto.setSoldQuantity(Integer.parseInt(String.valueOf(tuple.get(FieldNames.TOTAL_SOLD_QUANTITY))));
			profitLossDto.setProfitOrLoss(Double.parseDouble(String.valueOf(tuple.get(FieldNames.TOTAL_PROFIT_OR_LOSS))));
			profitLossDtos.add(profitLossDto);
		}

		Integer totalRecords = queryBuilder.countByQuery(countQuery.toString(), null);
		CustomPage<ProfitLossDto> page = new CustomPage<>();
		page.setContent(profitLossDtos);
		page.setPageNumber(pageable.getPageNumber() - 1);
		page.setSize(pageable.getPageSize());
		page.setTotalPages((int)Math.ceil((double)totalRecords/pageable.getPageSize()));
		return page;
	}
}
package com.app.ecom.store.dto.inventory;

public class InventoryItemDto {
	private Long id;
	private String name;
	private String code;
	private Double mrp;
	private Integer quantity;
	private Integer freeQuantity;
	private Double rate;
	private Double amount;
	private Double schemeDiscount;
	private Double tradeDiscount;
	private Double otherDiscount;
	private Double taxableAmount;
	private Double cgst;
	private Double sgst;
	private Double igst;
	private Double totalAmount;
	private boolean isDeleted;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Double getMrp() {
		return mrp;
	}
	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getFreeQuantity() {
		return freeQuantity;
	}
	public void setFreeQuantity(Integer freeQuantity) {
		this.freeQuantity = freeQuantity;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getSchemeDiscount() {
		return schemeDiscount;
	}
	public void setSchemeDiscount(Double schemeDiscount) {
		this.schemeDiscount = schemeDiscount;
	}
	public Double getTradeDiscount() {
		return tradeDiscount;
	}
	public void setTradeDiscount(Double tradeDiscount) {
		this.tradeDiscount = tradeDiscount;
	}
	public Double getOtherDiscount() {
		return otherDiscount;
	}
	public void setOtherDiscount(Double otherDiscount) {
		this.otherDiscount = otherDiscount;
	}
	public Double getTaxableAmount() {
		return taxableAmount;
	}
	public void setTaxableAmount(Double taxableAmount) {
		this.taxableAmount = taxableAmount;
	}
	public Double getCgst() {
		return cgst;
	}
	public void setCgst(Double cgst) {
		this.cgst = cgst;
	}
	public Double getSgst() {
		return sgst;
	}
	public void setSgst(Double sgst) {
		this.sgst = sgst;
	}
	public Double getIgst() {
		return igst;
	}
	public void setIgst(Double igst) {
		this.igst = igst;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}

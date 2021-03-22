package com.app.ecom.store.dto.inventory;

import java.util.List;

public class InventoryDto {
	private Long id;
	private String billNumber;
	private String billDate;
	private String gstNumber;
	private String agencyName;
	private String agencyAddress;
	private String agencyEmail;
	private String agencyContactNumber;
	private String panNumber;
	private String paymentStatus;
	private String paymentMode;
	private String salesmanName;
	private Double totalAmount;
	private List<InventoryItemDto> inventoryItems;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBillNumber() {
		return billNumber;
	}
	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public String getGstNumber() {
		return gstNumber;
	}
	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	public String getAgencyAddress() {
		return agencyAddress;
	}
	public void setAgencyAddress(String agencyAddress) {
		this.agencyAddress = agencyAddress;
	}
	public String getAgencyEmail() {
		return agencyEmail;
	}
	public void setAgencyEmail(String agencyEmail) {
		this.agencyEmail = agencyEmail;
	}
	public String getAgencyContactNumber() {
		return agencyContactNumber;
	}
	public void setAgencyContactNumber(String agencyContactNumber) {
		this.agencyContactNumber = agencyContactNumber;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getSalesmanName() {
		return salesmanName;
	}
	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public List<InventoryItemDto> getInventoryItems() {
		return inventoryItems;
	}
	public void setInventoryItems(List<InventoryItemDto> inventoryItems) {
		this.inventoryItems = inventoryItems;
	}
}

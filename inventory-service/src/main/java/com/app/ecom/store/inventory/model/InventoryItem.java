package com.app.ecom.store.inventory.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inventory_items")
public class InventoryItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "inventory_item_id")
	private Long id;
	
	@Column(name = "item_name")
	private String name;
	
	@Column(name = "item_code")
	private String code;
	
	@Column(name = "mrp")
	private Double mrp;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "free_quantity")
	private Integer freeQuantity;
	
	@Column(name = "rate")
	private Double rate;
	
	@Column(name = "amount")
	private Double amount;
	
	@Column(name = "scheme_discount")
	private Double schemeDiscount;
	
	@Column(name = "trade_discount")
	private Double tradeDiscount;
	
	@Column(name = "other_discount")
	private Double otherDiscount;
	
	@Column(name = "taxable_amount")
	private Double taxableAmount;
	
	@Column(name = "cgst")
	private Double cgst;
	
	@Column(name = "sgst")
	private Double sgst;
	
	@Column(name = "igst")
	private Double igst;
	
	@Column(name = "total_amount")
	private Double totalAmount;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "inventory_id")
	private Inventory inventory;
}

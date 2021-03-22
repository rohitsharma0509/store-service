package com.app.ecom.store.inventory.model;

import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "inventories")
public class Inventory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "inventory_id")
	private Long id;
	
	@Column(name = "bill_number")
	private String billNumber;
	
	@Column(name = "bill_date")
	private String billDate;
	
	@Column(name = "gst_number")
	private String gstNumber;
	
	@Column(name = "agency_name")
	private String agencyName;
	
	@Column(name = "agency_address")
	private String agencyAddress;
	
	@Column(name = "agency_email")
	private String agencyEmail;
	
	@Column(name = "agency_contact_number")
	private String agencyContactNumber;
	
	@Column(name = "pan_number")
	private String panNumber;
	
	@Column(name = "payment_status")
	private String paymentStatus;
	
	@Column(name = "payment_mode")
	private String paymentMode;
	
	@Column(name = "salesman_name")
	private String salesmanName;
	
	@Column(name = "total_amount")
	private Double totalAmount;
	
	@OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<InventoryItem> inventoryItems;
	
	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_ts", columnDefinition = "timestamp")
	private ZonedDateTime createdTs;

	@Column(name = "last_modified_by")
	private String lastModifiedBy;

	@Column(name = "last_modified_ts", columnDefinition = "timestamp")
	private ZonedDateTime lastModifiedTs;
}
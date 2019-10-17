package com.app.ecom.store.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;

@JsonInclude(value = Include.NON_NULL)
public class ProductDto {
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("code")
	private String code;

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("brandName")
	private String brandName;

	@JsonProperty("quantity")
	private Integer quantity;
	
	@JsonProperty("alertQuantity")
	private Integer alertQuantity;
	
	@JsonProperty("purchasePrice")
	private Double purchasePrice;

	@JsonProperty("perProductPrice")
	private Double perProductPrice;
	
	@JsonProperty("image")
	private MultipartFile image;
	
	@JsonProperty("base64Image")
	private String base64Image;
	
	@JsonProperty("categoryId")
	private Long categoryId;
	
	@JsonProperty("availableQuantity")
	private Integer availableQuantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getAlertQuantity() {
		return alertQuantity;
	}

	public void setAlertQuantity(Integer alertQuantity) {
		this.alertQuantity = alertQuantity;
	}

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Double getPerProductPrice() {
		return perProductPrice;
	}

	public void setPerProductPrice(Double perProductPrice) {
		this.perProductPrice = perProductPrice;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(Integer availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	@Override
	public String toString() {
		return "ProductDto [id=" + id + ", code=" + code + ", name=" + name
				+ ", brandName=" + brandName + ", quantity=" + quantity
				+ ", alertQuantity=" + alertQuantity + ", purchasePrice="
				+ purchasePrice + ", perProductPrice=" + perProductPrice
				+ ", image=" + image + ", base64Image=" + base64Image
				+ ", categoryId=" + categoryId + ", availableQuantity="+availableQuantity+"]";
	}
	
	/*public Set<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(Set<Purchase> purchases) {
		this.purchases = purchases;
	}*/
}

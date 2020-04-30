package com.app.ecom.store.dto;

import java.util.List;

import com.app.ecom.store.enums.SortOrder;

public class CustomPage<T> {
	private Integer pageNumber;
	private List<T> content;
	private Integer size;
	private Integer totalPages;
	private Integer totalRecords;
	private String sortedBy;
	private SortOrder sortOrder;

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getSortedBy() {
		return sortedBy;
	}

	public void setSortedBy(String sortedBy) {
		this.sortedBy = sortedBy;
	}

	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomPage [pageNumber=");
		builder.append(pageNumber);
		builder.append(", content=");
		builder.append(content);
		builder.append(", size=");
		builder.append(size);
		builder.append(", totalPages=");
		builder.append(totalPages);
		builder.append(", totalRecords=");
		builder.append(totalRecords);
		builder.append(", sortedBy=");
		builder.append(sortedBy);
		builder.append(", sortOrder=");
		builder.append(sortOrder);
		builder.append("]");
		return builder.toString();
	}

	
}

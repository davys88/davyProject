package com.site.common.entity;

public class CommonDTO {
	private String page;
	private String pageSize;
	private String start_row;
	private String end_row;
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getStart_row() {
		return start_row;
	}
	public void setStart_row(String start_row) {
		this.start_row = start_row;
	}
	public String getEnd_row() {
		return end_row;
	}
	public void setEnd_row(String end_row) {
		this.end_row = end_row;
	}
}

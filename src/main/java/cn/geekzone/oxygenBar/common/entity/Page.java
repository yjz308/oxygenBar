package cn.geekzone.oxygenBar.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Page {
	private static final Integer PAGE_SIZE = Integer.valueOf(25);

	private Integer pageSize = Integer.valueOf(1);

	private Integer offset;

	private Integer pageNumber = Integer.valueOf(1);

	@JsonIgnore
	public Integer getPageSize() {
		return this.pageSize;
	}

	@JsonIgnore
	public Integer getPageNumber() {
		return this.pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = Integer
				.valueOf((pageNumber == null || pageNumber.intValue() < 1) ? 1 : pageNumber.intValue());
		this.offset = Integer.valueOf((this.pageNumber.intValue() - 1) * this.pageSize.intValue());
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = (pageSize == null || pageSize.intValue() < 1) ? PAGE_SIZE : pageSize;
		this.offset = Integer.valueOf((this.pageNumber.intValue() - 1) * this.pageSize.intValue());
	}

	@JsonIgnore
	public Integer getOffset() {
		return this.offset;
	}

	public Integer getTotalPages(Integer totalCount) {
		if (totalCount == null || totalCount.intValue() <= 0) {
			return Integer.valueOf(0);
		}
		int fullPages = totalCount.intValue() / this.pageSize.intValue();
		int lastCount = totalCount.intValue() % this.pageSize.intValue();
		return Integer.valueOf(fullPages + ((lastCount == 0) ? 0 : 1));
	}
}

package com.spark.base.common.key;

/**
 * @author Jideas
 */
public class LimitKey {

	private int offset;

	private int pageSize = Integer.MAX_VALUE;

	private String searchText;

	/**
	 * 是否查询总数
	 */
	protected boolean queryTotalCount;

	/**
	 * 排序方式
	 */
	protected SortType sortType = SortType.Asc;

	public LimitKey(int offset, int pageSize, boolean queryTotalCount) {
		this.offset = offset;
		this.pageSize = pageSize;
		this.queryTotalCount = queryTotalCount;
	}

	public LimitKey() {

	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSearchText() {
		if (null == searchText) return null;
		return searchText.trim();
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public boolean isQueryTotalCount() {
		return queryTotalCount;
	}

	public void setQueryTotalCount(boolean queryTotalCount) {
		this.queryTotalCount = queryTotalCount;
	}

	public SortType getSortType() {
		return sortType;
	}

	public void setSortType(SortType sortType) {
		this.sortType = sortType;
	}
}

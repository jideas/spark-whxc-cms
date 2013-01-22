package com.spark.cms.common;

import java.util.ArrayList;
import java.util.List;

public class DataModel<T> {

	public DataModel(List<T> rows, long total) {
		this.rows = rows;
		this.total = total;
	}

	public DataModel() {
		this.rows = new ArrayList<T>();
		this.total = 0;
	}

	/**
	 * 行数
	 */
	private long total;

	/**
	 * 行记录
	 */
	private List<T> rows;

	/**
	 * 尾部
	 */
	private List<T> footer;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public List<T> getFooter() {
		return footer;
	}

	public void setFooter(List<T> footer) {
		this.footer = footer;
	}

}

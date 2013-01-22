/**
 * 
 */
package com.spark.cms.services.form.folder;

import java.util.List;

import com.spark.cms.common.tree.TreeModel;

/**
 * @author Jideas
 * 
 */
public class FolderTree {
	private boolean result;
	private String message;
	private List<TreeModel> list;

	public FolderTree(boolean result, String message, List<TreeModel> list) {
		this.result = result;
		this.message = message;
		this.list = list;
	}

	public boolean isResult() {
		return result;
	}

	public String getMessage() {
		return message;
	}

	public List<TreeModel> getList() {
		return list;
	}
}

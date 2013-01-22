package com.spark.cms.services.form;

import com.spark.cms.services.vo.CategoryPropertiesVo;

public class CategoryPropertiesForm extends CategoryPropertiesVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CategoryPvaluesForm[] values;

	public CategoryPvaluesForm[] getValues() {
		return values;
	}

	public void setValues(CategoryPvaluesForm[] values) {
		this.values = values;
	}
	
	
}

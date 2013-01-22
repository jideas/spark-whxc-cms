package com.spark.cms.services.form;

import com.spark.cms.services.vo.GoodsCategoryVo;

public class GoodsCategoryForm extends GoodsCategoryVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CategoryPropertiesForm[] propertiesForm;

	public CategoryPropertiesForm[] getPropertiesForm() {
		return propertiesForm;
	}

	public void setPropertiesForm(CategoryPropertiesForm[] propertiesForm) {
		this.propertiesForm = propertiesForm;
	}
	
	
}

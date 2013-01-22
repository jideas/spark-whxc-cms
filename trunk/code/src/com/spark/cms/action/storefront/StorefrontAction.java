package com.spark.cms.action.storefront;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StorefrontAction {
	@RequestMapping(value="sysHello")
	public ModelAndView sayHello(){
		ModelAndView mav = new ModelAndView();
		mav.addObject("Hello", "HelloWorld");
		mav.setViewName("app/cms/admin/storefront/Hello");
		return mav;
	}

}

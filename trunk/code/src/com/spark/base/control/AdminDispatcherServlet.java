package com.spark.base.control;

import org.springframework.web.servlet.DispatcherServlet;

public class AdminDispatcherServlet  extends DispatcherServlet{
	private static final long serialVersionUID = 1L;

	public AdminDispatcherServlet() {
		super();
		try{
			
			init();
			System.out.println("============7号生活馆电子商务平台启动==========");
		}catch(Exception e){
			System.out.println("============7号生活馆电子商务平台启动异常=========="+e.getMessage());
		}
		
	}
	

}

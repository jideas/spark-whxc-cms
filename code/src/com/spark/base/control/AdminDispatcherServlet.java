package com.spark.base.control;

import org.springframework.web.servlet.DispatcherServlet;

public class AdminDispatcherServlet  extends DispatcherServlet{
	private static final long serialVersionUID = 1L;

	public AdminDispatcherServlet() {
		super();
		try{
			
			init();
			System.out.println("============7������ݵ�������ƽ̨����==========");
		}catch(Exception e){
			System.out.println("============7������ݵ�������ƽ̨�����쳣=========="+e.getMessage());
		}
		
	}
	

}

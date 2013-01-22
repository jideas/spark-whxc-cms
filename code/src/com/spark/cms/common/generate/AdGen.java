package com.spark.cms.common.generate;

public class AdGen {
	
	public static String genAd(String url,String imgUrl){
		if(url!=null){
			if(url.startsWith("http://")){
				return "<a  target=\"_blank\" href=\""+url+"\"><img width=\"230px\" height=\"88px\" src=\""+imgUrl+"\"/></a>";
			}else  {
				return "<a target=\"_blank\"  href=\"http://"+url+"\"><img width=\"230px\" height=\"88px\" src=\""+imgUrl+"\"/></a>";
			}
		}
		else{
			return "<a target=\"_blank\" ><img width=\"230px\" src=\""+imgUrl+"\"/></a>";
		}
		
	}

}

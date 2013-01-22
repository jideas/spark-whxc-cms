package com.spark.cms.action.demo;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spark.cms.action.BaseAction;
import com.spark.cms.common.MessageModel;
import com.spark.cms.services.form.UserExtForm;

@Controller
public class JsonTestAction extends BaseAction{
	
	@RequestMapping("/hello")
	public String getHello(){
		return "/app/cms/admin/example/SpringMVC&Json";
	}
	
	@RequestMapping("/getRequestAndReponseBodyAnnJsonConvert")
	@ResponseBody
	public MessageModel getRequestAndReponseBodyAnnJsonConvert(@RequestBody UserExtForm user){
		ObjectMapper o = new ObjectMapper();
		return new MessageModel(true,"ÄãºÃ");
	}
	@RequestMapping(value = "/getRequestAndReponseBodyAnnJsonConvert1",method=RequestMethod.POST)
	@ResponseBody
	public MessageModel getRequestAndReponseBodyAnnJsonConvert1(UserExtForm user){
		ObjectMapper o = new ObjectMapper();
		return new MessageModel(true,"ÄãºÃ");
	}
	
	@RequestMapping("/getRequestAndResponseEntityJsonConvert")
	@ResponseBody
	public ResponseEntity<MessageModel> getRequestAndResponseEntityJsonConvert(HttpEntity<UserExtForm> user){
		System.out.println(user);
		MessageModel model = new MessageModel(true,"faile");
		List<String> list = new  ArrayList<String>();
		list.add("ok");
		list.add("ok");
		return new ResponseEntity<MessageModel>(model,HttpStatus.OK);
	}

}

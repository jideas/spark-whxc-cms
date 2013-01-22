package com.spark.cms.action.image;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spark.cms.base.utils.UpLoader;
import com.spark.cms.common.ResponseEntityUtil;

/**
 * 图片上传处理 
 */
@Controller
@RequestMapping(value="/image")
public class ImageManagerAction {
	
	@RequestMapping(value="/uploadImage")
	@ResponseBody
	public ResponseEntity<UpLoader> uploadImages(HttpServletRequest request){
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		Map<String, MultipartFile> multipartFileMap =  multipartHttpServletRequest.getFileMap();
		
		Iterator<String> fileIt = multipartFileMap.keySet().iterator();
		UpLoader upLoader = new UpLoader(request);
		 String path = "/images";
		 upLoader.setSavePath(path);
		 String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
		 upLoader.setAllowFiles(fileType);
		while(fileIt.hasNext()){
			String fileNameKey = fileIt.next();
			MultipartFile multipartFile = multipartFileMap.get(fileNameKey);
			try{
				upLoader.upload(multipartFile);
			}catch(Exception e){
			}
		}
		return ResponseEntityUtil.getResponseEntity(upLoader);
	}
	
	@RequestMapping(value="/getOnlineImagesList")
	public void getOnlineImagesList(HttpServletRequest request,HttpServletResponse response){
		String path = "/images";
		String imgStr ="";
		UpLoader upLoader = new UpLoader(request);
		
		String realPath = upLoader.getRealPath(path);
		
		String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
		upLoader.setAllowFiles(fileType);
	 
		List<File> files = new ArrayList<File>();
		upLoader.getFiles(realPath,files);
		
		for(File file :files ){
			imgStr+=file.getPath().replace(upLoader.getRealPath(path),"")+"ue_separate_ue";
		}
		if(imgStr!=""){
	        imgStr = imgStr.substring(0,imgStr.lastIndexOf("ue_separate_ue")).replace(File.separator, "/").trim();
	    }
		try{
			response.getWriter().print(imgStr);
		}catch(Exception e){
			e.getMessage();
		}
	}
	
	@RequestMapping(value="/editformsubmittest")
	public void geteidtorInfo(HttpServletRequest request,HttpServletResponse response){
		request.getParameter("myContent");
	}
	


    


}

/**
 * 
 */
package com.spark.cms.action.folder;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spark.base.common.utils.CheckIsNull;
import com.spark.base.type.GUID;
import com.spark.cms.action.BaseAction;
import com.spark.cms.base.utils.UpLoader;
import com.spark.cms.common.MessageModel;
import com.spark.cms.common.ResponseEntityUtil;
import com.spark.cms.services.ServiceMessage;
import com.spark.cms.services.folder.FolderService;
import com.spark.cms.services.form.folder.ImageForm;
import com.spark.cms.services.image.ImageService;
import com.spark.cms.services.vo.FolderVo;
import com.spark.cms.services.vo.ImageInfoVo;
import com.spark.front.utils.CmsString;

/**
 * @author Jideas
 * 
 */
@Controller
public class ImagesUploadAction extends BaseAction {

	@Autowired
	private FolderService folderService;

	@Autowired
	private ImageService imageService;

	/**
	 * 得到所有图片地址
	 * 
	 * @throws ServiceMessage
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping("/images/deleteImage")
	@ResponseBody
	public ResponseEntity<MessageModel> deleteImage(@RequestParam(value = "imgUrl", required = true)
	String imgUrl, HttpServletRequest request) throws ServiceMessage {
		try {
			this.imageService.deleteImage(imgUrl, request);
			return new ServiceMessage(true, "").getMessageModel();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("新增文件夹发生异常====" + e.getStackTrace());
		}
		return null;
	}

	/**
	 * 得到所有图片地址
	 * 
	 * @throws ServiceMessage
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping("/images/getImages")
	@ResponseBody
	public ResponseEntity<List<ImageForm>> getImages(String folderId, String text) throws ServiceMessage {
		try {
			if (CheckIsNull.isEmpty(folderId) && CheckIsNull.isEmpty(text)) {
				return null;
			}
			List<ImageInfoVo> volist = this.imageService.getImages(folderId, text);
			if (null == volist) {
				return null;
			}
			List<ImageForm> formList = new ArrayList<ImageForm>();
			for (ImageInfoVo vo : volist) {
				ImageForm form = new ImageForm();
				form.setImgUrl(vo.getImgUrl());
				form.setRealHeight(vo.getImgHeight() + "");
				form.setRealWidth(vo.getImgWidth() + "");
				String title = vo.getImgName() + "(" + vo.getImgWidth() + "X" + vo.getImgHeight() + ")";
				form.setTitle(title);
				if (vo.getImgWidth() == vo.getImgHeight() || (vo.getImgHeight() < 150 && vo.getImgWidth() < 150)) {
					form.setWidth("150");
					form.setHeight("150");
				} else if (vo.getImgWidth() > vo.getImgHeight()) {
					form.setWidth("150");
					int height = vo.getImgHeight() / (vo.getImgWidth() / 150);
					form.setHeight("" + height);
				} else {
					form.setHeight("150");
					int width = vo.getImgWidth() / (vo.getImgHeight() / 150);
					form.setWidth("" + width);
				}
				formList.add(form);
			}
			return ResponseEntityUtil.getResponseEntity(formList);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("新增文件夹发生异常====" + e.getStackTrace());
		}
		return null;
	}

	/**
	 * 新增或更新文件夹
	 * 
	 * @throws ServiceMessage
	 */
	@RequestMapping("/images/imagesUpload")
	@ResponseBody
	public ResponseEntity<MessageModel> imagesUpload(HttpServletRequest request, HttpServletResponse response)
			throws ServiceMessage {
		try {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> multipartFileMap = multipartHttpServletRequest.getFileMap();
			String folderId = request.getParameter("folderId");
			Iterator<String> fileIt = multipartFileMap.keySet().iterator();
			UpLoader upLoader = new UpLoader(request);
			String path = "/images/";
			FolderVo folder = this.folderService.findFolderById(folderId);
			String folderPath = Integer.parseInt(folder.getCode()) + "/";
			folderPath = this.addParentFolder(folderPath, folder.getParentId());
			upLoader.setSavePath(path + folderPath);
			String[] fileType = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };
			upLoader.setAllowFiles(fileType);
			while (fileIt.hasNext()) {
				String fileNameKey = fileIt.next();
				MultipartFile multipartFile = multipartFileMap.get(fileNameKey);
				try {
					String imgUrl = upLoader.upload(multipartFile, true);
					String oldname = multipartFile.getOriginalFilename();
					addImgToDatabase(imgUrl, request, oldname.substring(0, oldname.indexOf(".")), folderId);
					return new ServiceMessage(true, "文件上传成功！").getMessageModel();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("新增文件夹发生异常====" + e.getStackTrace());
		}
		return new ServiceMessage(false, "文件上传失败，请重试！").getMessageModel();
	}

	/**
	 * @param imgUrl
	 * @param request
	 */
	@SuppressWarnings("deprecation")
	private void addImgToDatabase(String imgUrl, HttpServletRequest request, String imgName, String folderId) {
		if (CmsString.isEmpty(imgUrl)) {
			return;
		}
		File file = new File(request.getRealPath("") + imgUrl);
		if (null == file) {
			return;
		}
		FileInputStream is = null;
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e2) {
			return;
		}
		BufferedImage sourceImg = null;
		try {
			sourceImg = javax.imageio.ImageIO.read(is);
		} catch (IOException e1) {
			return;
		}
		int width = sourceImg.getWidth();
		int height = sourceImg.getHeight();
		ImageInfoVo vo = new ImageInfoVo();
		vo.setImgHeight(height);
		vo.setImgName(imgName);
		vo.setFolderId(folderId);
		vo.setImgUrl(imgUrl.replaceAll("//", "/"));
		vo.setImgWidth(width);
		this.imageService.createImage(vo);
	}

	private String addParentFolder(String folderPath, String recid) {
		if (GUID.emptyID.toString().equals(recid)) {
			return folderPath;
		}
		FolderVo folder = this.folderService.findFolderById(recid);
		String path = Integer.parseInt(folder.getCode()) + "/" + folderPath;
		return this.addParentFolder(path, folder.getParentId());
	}
}

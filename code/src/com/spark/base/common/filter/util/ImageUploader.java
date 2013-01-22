/**
 * 
 */
package com.spark.base.common.filter.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.spark.base.common.utils.CheckIsNull;
import com.spark.base.type.GUID;
import com.spark.cms.services.folder.FolderService;
import com.spark.cms.services.image.ImageService;
import com.spark.cms.services.vo.FolderVo;
import com.spark.cms.services.vo.ImageInfoVo;
import com.spark.front.utils.CmsString;

/**
 * @author Jideas
 * 
 */
public class ImageUploader {

	/**
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("deprecation")
	public void doUploadImage(HttpServletRequest request, HttpServletResponse response) {

		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置内存缓冲区，超过后写入临时文件
		factory.setSizeThreshold(10240000);
		// 设置临时文件存储位置
		String path = "/images/";
		String folderId = request.getParameter("folderId");
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getSession()
				.getServletContext());
		if (CheckIsNull.isNotEmpty(folderId) && !"null".equals(folderId)) {
			FolderService folderService = context.getBean(FolderService.class);
			FolderVo folder = folderService.findFolderById(folderId);
			String folderPath = Integer.parseInt(folder.getCode()) + "/";
			folderPath = this.addParentFolder(folderPath, folder.getParentId(), folderService);
			path = (path + folderPath);
		} else {
			return;
		}
		File file = new File(request.getRealPath("") + path);
		if (!file.exists())
			file.mkdirs();
		factory.setRepository(file);
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置单个文件的最大上传值
		upload.setFileSizeMax(10002400000l);
		// 设置整个request的最大值
		upload.setSizeMax(10002400000l);
		upload.setHeaderEncoding("UTF-8");
		try {
			List<?> items = upload.parseRequest(request);
			FileItem item = null;
			String realName = null;
			String fileName = null;
			ImageService imageService = context.getBean(ImageService.class);
			for (int i = 0; i < items.size(); i++) {
				item = (FileItem) items.get(i);
				// 保存文件
				if ("Filename".equals(item.getFieldName())) {
					realName = item.getString("utf-8");
					fileName = path + new Date().getTime() + realName.substring(realName.indexOf("."));
				} else if (!item.isFormField() && item.getName().length() > 0) {
					item.write(new File(request.getRealPath("") + fileName));
					item.getInputStream().close();
					item.getOutputStream().close();
					addImgToDatabase(fileName, request, realName.substring(0, realName.indexOf(".")), folderId,
							imageService);
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("deprecation")
	private void addImgToDatabase(String imgUrl, HttpServletRequest request, String imgName, String folderId,
			ImageService imageService) {
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
			is.close();
		} catch (IOException e1) {
			return;
		}
		if (null == sourceImg) {
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
		imageService.createImage(vo);
	}

	private String addParentFolder(String folderPath, String recid, FolderService folderService) {
		if (GUID.emptyID.toString().equals(recid)) {
			return folderPath;
		}
		FolderVo folder = folderService.findFolderById(recid);
		String path = Integer.parseInt(folder.getCode()) + "/" + folderPath;
		return this.addParentFolder(path, folder.getParentId(), folderService);
	}
}
